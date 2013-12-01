#ifndef awesome
#define awesome

#define DEBUG

#define baudrate        9600

#define tft_cs          10
#define tft_dc          9
#define tft_rst         8

#define sd_mosi         11
#define sd_miso         12
#define sd_clk          13
#define sd_cs           4

#define stick_x         A0
#define stick_y         A1
#define stick_in        A2

#define black           0x00
#define white           0xFF

#define max_balls       5
#define ball_start      25
#define player_start    65
#define ball_speed      10
#define ball_size       5
#define player_size     10

#define file_name       "LEADER.TXT"

typedef struct
{
    int xPos, yPos, xDirection, yDirection, oldXPos, oldYPos;
} Ball;

// Function used for setting up everything before the game starts
// It initializes the Screen and SD-card, and loads the leaderboard-file and checks for input from the analog stick to check everything is in order.
void setup(void);

// Function looping through all the different functions of the game.
void loop(void);

// Move a single ball and check for impact with the player
void moveBall(Ball* currentBall);

// Check if a ball has hit the player
boolean inPlayer(int x, int y, int rectX, int rectY);

// Load the current highscore from file
int getCurrentHighscore(void);

// Write the current play-time (score) in the upper left corner
void writeCurrentTime(void);

// Check if the stick is pressed (used to restart the game)
boolean isStickClicked(void);

// Write the new (if any) highscore to the SD-card
void writeScoreToStorage(int score);

// Add a new ball to the game
void addBall(void);

// Initialize a new game (clear the screen and reset variables)
void initGame(void);

#endif
