//We always have to include the library
#include "LedControl.h"
#include "MeineHeader.h"

/*
 Now we need a LedControl to work with.
 ***** These pin numbers will probably not work with your hardware *****
 pin 12 is connected to the DataIn
 pin 11 is connected to the CLK
 pin 10 is connected to LOAD
 We have only a single MAX72XX.
 */
LedControl lc= LedControl(12,11,10,1);

byte inputBuffer[NUM_OF_CHARS];

boolean looping;
byte numberOfChars;
short currentChars[NUM_OF_BITS];

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

void loop() {
  // Reset variables
  looping = true;
  numberOfChars = 0;

  while (looping)
  {
    if(Serial.available() > 0)
    {
      byte incoming  = Serial.read();

      if (incoming == 124 || numberOfChars > 198)
      {
        looping = false;

        // Insert a space at the end
        inputBuffer[numberOfChars++] = 0;
        inputBuffer[numberOfChars++] = 0;
      }
      else if (incoming < 32 || incoming > 124)
      {
        // Add a space in the place of unkown chars
        inputBuffer[numberOfChars++] = 0;
      }
      else
      {
        // My char-map starts at space, which is ASCII nr 32
        inputBuffer[numberOfChars++] = incoming - 32;
      }
    }
  }

  buildSentence();
}

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

