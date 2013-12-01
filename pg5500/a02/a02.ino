#include <TFT.h>
#include <SPI.h>
#include <SD.h>

#include "a02.h"

TFT TFTscreen = TFT(tft_cs, tft_dc, tft_rst);
String currentHigh;

// variables for the position of the player
int playerX = player_start, playerY = player_start, oldPlayerX = player_start, oldPlayerY = player_start,
myWidth = TFTscreen.width(), myHeight = TFTscreen.height(),
currentScore, startingHighscore,
numberOfBalls = 0;

long currentMillis, updateScoreTimer = 0;
char scorebuffer[4];

File myFile;
// An array keeping track of all the balls (enemies) in the game
Ball balls[max_balls];

boolean gameOver;

void setup() {
  Serial.begin(baudrate);
  TFTscreen.begin();

  if (!SD.begin(sd_cs)) {
    Serial.println("SD-card initialization failed!");
    return;
  }

  initGame();

#ifdef DEBUG
  //Serial.println(myWidth);
  //Serial.println(myHeight);
#endif
}

void loop() {
  if (gameOver) {
    // If it's game over, and the stick is not pressed (the restart button), just return
    if (isStickClicked())  {
      initGame();
    } 
    else {
      return;
    }
  }

  currentMillis = millis();

  // Update once a second
  if (currentMillis - updateScoreTimer > 1000) {
    currentScore++; 
    writeCurrentTime();
    updateScoreTimer = currentMillis;

    // Add a ball every tenth second
    if (currentScore % 10 == 0 && numberOfBalls < max_balls) {
      addBall();
    }
  }

  // map the player's location to the position of the potentiometers   
  playerX = map(analogRead(stick_x), 0, 1023, 0, myWidth) - player_size / 2; 
  playerY = map(analogRead(stick_y), 0, 1023, 20, myHeight) - player_size / 2; 

  // set the fill color to black and erase the previous 
  // position of the player if different from present
  TFTscreen.fill(black, black, black);

  if (oldPlayerX != playerX || oldPlayerY != playerY) {
    TFTscreen.rect(oldPlayerX, oldPlayerY, player_size, player_size);
  }

  // draw the player on screen, save the current position
  // as the previous.
  TFTscreen.fill(black, black, white);

  TFTscreen.rect(playerX, playerY, player_size, player_size);
  oldPlayerX = playerX;
  oldPlayerY = playerY;

  // update the ball's position and draw it on screen
  if (currentMillis % ball_speed < 2) {
    for(int i = 0; i < numberOfBalls; i++){
      moveBall(&balls[i]);      
    }
  }
}

void moveBall(Ball* currentBall) {
  // if the ball goes offscreen, reverse the direction:
  if (currentBall->xPos > myWidth || currentBall->xPos < 0) {
    currentBall->xDirection = -currentBall->xDirection;
  }

  // Don't overwrite the scores
  if (currentBall->yPos > myHeight || currentBall->yPos < 20) {
    currentBall->yDirection = -currentBall->yDirection;
  }  

  // check if the ball and the player occupy the same space on screen
  if (inPlayer(currentBall->xPos, currentBall->yPos, playerX, playerY)) {
    gameOver = true;

    if(currentScore > startingHighscore) {
      writeScoreToStorage(currentScore);
    }
  }

  currentBall->xPos += currentBall->xDirection;
  currentBall->yPos += currentBall->yDirection;

  // Overwrite the ball's previous position
  TFTscreen.fill(black, black, black);

  if (currentBall->oldXPos != currentBall->xPos || currentBall->oldYPos != currentBall->yPos) {
    TFTscreen.rect(currentBall->oldXPos, currentBall->oldYPos, ball_size, ball_size);
  }

  // draw the ball's current position
  TFTscreen.fill(white, white, white);
  TFTscreen.rect(currentBall->xPos, currentBall->yPos, ball_size, ball_size);

  currentBall->oldXPos = currentBall->xPos;
  currentBall->oldYPos = currentBall->xPos;
}

// Check if a ball has hit the player
boolean inPlayer(int x, int y, int rectX, int rectY) {
  if ((x >= rectX && x <= (rectX + player_size)) && 
    (y >= rectY && y <= (rectY + player_size))) {
    return true; 
  }

  return false;  
}

int getCurrentHighscore(void) {
  myFile = SD.open(file_name);
  if (myFile) {
    int score = myFile.parseInt();

    myFile.close();

#ifdef DEBUG
    Serial.print("score read from file is: ");
    Serial.println(score);
#endif

    startingHighscore = score;

    return score;
  } 
  else {
    Serial.println("error opening leader.txt");
  }

  return -1;
}

void writeCurrentTime(void) {
  currentHigh = String(currentScore);

  TFTscreen.text(scorebuffer, 0, 10);
  TFTscreen.stroke(white, white, white);
  currentHigh.toCharArray(scorebuffer, 5);
  TFTscreen.text(scorebuffer, 0, 10);
  TFTscreen.stroke(black, black, black);
}

boolean isStickClicked(void) {
  // 10 is a magic number. It seemed to fluctuate between 5-10 for me when testing
  Serial.println(analogRead(stick_in));
  return analogRead(stick_in) < 10;
}

void writeScoreToStorage(int score) {
  // Magic line, which basically dumps any existing content of the file
  // http://forum.arduino.cc/index.php?topic=49649#msg354708
  myFile = SD.open(file_name, O_CREAT | O_TRUNC |O_WRITE);

  if (myFile) {
#ifdef DEBUG
    Serial.print("New highscore:  ");
    Serial.println(score);
#endif

    myFile.println(score);
    // close the file:
    myFile.close();
  } 
  else {
    // if the file didn't open, print an error:
    Serial.println("error opening leader.txt");
  }

  getCurrentHighscore();
}

void addBall(void) {
  balls[numberOfBalls].xPos = ball_start;
  balls[numberOfBalls].yPos = ball_start;
  balls[numberOfBalls].oldXPos = ball_start;
  balls[numberOfBalls].oldYPos = ball_start;
  balls[numberOfBalls].xDirection = 1;
  balls[numberOfBalls].yDirection = 1;

  numberOfBalls++;
}

void initGame(void) {
  gameOver = false;

  TFTscreen.background(black, black, black);

  TFTscreen.stroke(white, white, white);
  // write the text to the top left corner of the screen
  TFTscreen.text("Score:", 0, 0);
  // ste the font size very large for the loop 
  TFTscreen.text("Highscore:", myWidth - 60, 0);
  currentHigh = String(getCurrentHighscore());
  currentHigh.toCharArray(scorebuffer, 5);
  TFTscreen.text(scorebuffer, myWidth - 25, 10);

  scorebuffer[0] = '\0';
  currentScore = 0;

  memset(balls, 0, max_balls);

  numberOfBalls = 0;

  addBall();
}
