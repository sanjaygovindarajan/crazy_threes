# Crazy three's
## What is Crazy Threes?
In short, it is objectively the best CSC207 project ever created.
It is a multiplayer card game up to five users can play against each other on the same computer. The game is based on the classic card game "Crazy 8's", except 3's can be used to change the suit of the playing deck instead of 8's!
A refreser of rules:
Each player will be dealt cards to begin.
You can play any card that is either the same suit or the same number as the previously played card.
You can always play a 3 even if the previous card was a different suit or number, and when you play a 3 you get to decide what suit the gameplay will change to.
## How to run the program?
Compile and run the file src/app/Main.java.
First, the program will prompt you to type whether you would like to play the game or view the rules.
If you type "Start game", the program will prompt you to enter the names of the players (up to 5 players allowed).
At any player's turn they are allowed to view the rules before they play in case they forget how to play!
Additionally, it gives you the option to save your game by typing "Save game" in case you would like to leave and return later.
To return, you just need to type the name of the game that you entered when it was saved, and you can resume where you left off.
If you want to play a card, the program will first ask you for the suit of the card you want to play, then it will ask you to type the number or the name of the face card.
The program will also show you a list of your cards and the last one played, so you can decide which card you want to / are allowed to play.
However, if you enter a command that is not allowed (like playing the 2 of diamonds when the previous played card was 6 of spades), the program will inform you in the console that the command was not accepted and allow you to make a new play.
Once a player plays every card in their hand, the game ends and they win!!!
## How can I learn the rules?
After running the program, type "View rules"
## What features does the program have?
Currently, as of Phase 1, it is possible to save a game to a database
and load a game from the database. Basic gameplay is also supported.
It is possible to view the rules before the game or before your turn.


