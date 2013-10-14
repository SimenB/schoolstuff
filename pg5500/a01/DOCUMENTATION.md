#Innlevering 1 - PG5500-13
####Simen Bekkhus
Litt usikker på hvordan denne dokumentasjonen skal være, så satser på at dette ikke er _for_ feil. Mailen din gav inntrykk av det kun skal være nødvendig med basic forståelse av hvordan biblioteket jeg valgte fungerer, så da kjører vi på!


##Valg av bibliotek(er)
Bibilioteket jeg valgte å bruke var [Led Control][1]. Grunnen til at valget falt på den var stort sett bare at det var første treffet ved et Google-søk.
For å produsere bokstavene til utskrift kom jeg over [denne gisten.][2] Ved da å laste ned en fil fra [denne siden][3] (jeg valgte [Footballer Of Year 2][4], ettersom den nesten mappet direkte til ASCII-tabellen), og kjøre den gjennom gisten, fikk jeg ferdige boksatver i 8x8-form. Jeg måtte tweake litt (bl.a. legge til @ og endre rekkefølgen), men annet enn det var bokstavene ikke store jobben.


##Bruk av bibliotek
Jeg åpnet det eksempelet som følger med biblioteket, og modifiserte den for å få satt opp koden i utgansgpunktet. Dette betyr da en ren kopi av oppsettet, og pinnene den er koblet til på på Arduinoen.
```c
/*
 Now we need a LedControl to work with.
 ***** These pin numbers will probably not work with your hardware *****
 pin 12 is connected to the DataIn
 pin 11 is connected to the CLK
 pin 10 is connected to LOAD
 We have only a single MAX72XX.
 */
LedControl lc= LedControl(12,11,10,1);

...

void setup() {
  Serial.begin(9600);

  /*
   The MAX72XX is in power-saving mode on startup,
   we have to do a wakeup call
   */
  lc.shutdown(0,false);
  /* Set the brightness to a medium values */
  lc.setIntensity(0,8);
  /* and clear the display */
  lc.clearDisplay(0);
}
```
Serial ble lagt til slik at bruker kan ha input, og bestemme teksten som vises på matrisen ved runtime.
Det konstruktøren gjør er å ta imot parametere om hvilke pins som er koblet til DIn, CLK og LOAD på chippen, samt antall devices.

I loop-metoden, har jeg lagd en egen løkke inni der igjen. Sikkert ikke det mest elegante, men det er for å kunne hoppe ut av input ved bruk av '|' (pipe).
Ettersom jeg bare har ASCII-tabellen fra space og ut (som er på plass 32), trekker jeg fra 32 på inputen før den puttes inn i arrayet. Dette gjør at jeg klan gå direkte inn i mitt alfabet-array og hente ut rikitg bokstav.

buildSentence-funksjonen har i starten en ganske stygg hack for å legge inn en space fremst, slik at også første bokstav kommer scrollende inn på skjermen.

```c
  // Place a space in the first char, and the first inputed char in the second
  for (int i = 0; i < NUM_OF_BITS; i++)
  {
    firstChar[i] = alphabet[0][i];
    secondChar[i] = alphabet[inputBuffer[0]][i];
  }
```

Deretter looper funksjonen igjennom hele inputen fra bruker, og først kombinerer de to neste karakterene i lista til en short (Ettersom det er 2 byte, noe som gjør den enkel å bitshifte).

```c
    for (int j = 0; j < NUM_OF_BITS; j++)
    {
      // Combine the first and second byte into a single short
      // Not mine: http://stackoverflow.com/a/8258510/1850276
      currentChars[j] = (firstChar[j] << 8) | (secondChar[j] & 0xFF);
    }
```

Deretter skriver jeg ut en hel karakter på matrisen. Dette gjør jeg ved å iterere gjennom alle 8 bytene i en bokstav (bistshiftet 8 til høyre for å hente ut karakteren som er fremst). Deretter reverserer jeg byten, og skriver den ut til en rad på matrisen opp ned. Dette gjøres for å se utskriften på matrisen den veien jeg vil, og er veldig hacky. Deretter bitshifter jeg alt 1 til venstre (og lagrer resultatet), slik at det oppnås en "scrolle-effekt". Til slutt ventes det 100ms slik at det skal bli mulig å lese det som står.

```c
void printSingleChar()
{
  for (int i = 0; i < NUM_OF_BITS; i++)
  {
    // TODO: Negate the need for reversing
    // NB: This is not done through code
    int placement = 7;

    for (int j = 0; j < NUM_OF_BITS; j++)
    {
      byte currentLine = (currentChars[j] >> 8);
      currentLine = reverse(currentLine);

      lc.setRow(0, placement--, currentLine);
    }

    for (int i = 0; i < NUM_OF_BITS; i++)
    {
      currentChars[i] <<= 1;
    }

    delay(100);
  }
}

// http://stackoverflow.com/a/2602885/1850276
// This allows me to print out the letters "up-side down"
byte reverse(byte b) {
  b = (b & 0xF0) >> 4 | (b & 0x0F) << 4;
  b = (b & 0xCC) >> 2 | (b & 0x33) << 2;
  b = (b & 0xAA) >> 1 | (b & 0x55) << 1;

  return b;
}
```

Etter dette er vi tilbake i buildSentence, og nå tar vi og henter ut den neste bokstaven i inputBuffer, og putter den inn i bokstav 1 og 2 for å begynne på nytt.

```c
void buildSentence()
{
  byte firstChar[NUM_OF_BITS], secondChar[NUM_OF_BITS];

  // Place a space in the first char, and the first inputed char in the second
  for (int i = 0; i < NUM_OF_BITS; i++)
  {
    firstChar[i] = alphabet[0][i];
    secondChar[i] = alphabet[inputBuffer[0]][i];
  }

  for(int i = 0; i < numberOfChars - 1; i++)
  {
    for (int j = 0; j < NUM_OF_BITS; j++)
    {
      // Combine the first and second byte into a single short
      // Not mine: http://stackoverflow.com/a/8258510/1850276
      currentChars[j] = (firstChar[j] << 8) | (secondChar[j] & 0xFF);
    }

    printSingleChar();

    byte currentFirstLetter = inputBuffer[i], currentSecondLetter = inputBuffer[i + 1];

    for (int j = 0; j < NUM_OF_BITS; j++)
    {
      firstChar[j] = alphabet[currentFirstLetter][j];
      secondChar[j] = alphabet[currentSecondLetter][j];
    }
  }
}
```

###Biblioteket
Biblioteket skriver ut ved å bruke sort magi jeg ikke skjønner stort av. Det virker som om den shifter 1 og 1 byte (2 av gangen) inn på device, og skriver annehver high og low.

```cpp
void LedControl::spiTransfer(int addr, volatile byte opcode, volatile byte data) {
    //Create an array with the data to shift out
    int offset=addr*2;
    int maxbytes=maxDevices*2;

    for(int i=0;i<maxbytes;i++)
    spidata[i]=(byte)0;
    //put our device data into the array
    spidata[offset+1]=opcode;
    spidata[offset]=data;
    //enable the line 
    digitalWrite(SPI_CS,LOW);
    //Now shift out the data 
    for(int i=maxbytes;i>0;i--)
    shiftOut(SPI_MOSI,SPI_CLK,MSBFIRST,spidata[i-1]);
    //latch the data onto the display
    digitalWrite(SPI_CS,HIGH);
} 
```

##Oppsett av device
Brukte datsheet på [MaximIntegrated][5] for å vite hvilke pinner som skulle hvor.

Har ikke så mye annet å si om den, annet enn at den ble dritstygg, og jeg hadde ikke ork til å "refaktorere" den. I tilleg hadde jeg aldri greid å sette den opp uten hjelp fra Arne-Christian.

[1]:http://playground.arduino.cc/Main/LedControl
[2]:https://gist.github.com/npahucki/5404728
[3]:http://kofler.dot.at/c64/
[4]:http://kofler.dot.at/c64/font_08.html
[5]:http://datasheets.maximintegrated.com/en/ds/MAX7219-MAX7221.pdf
