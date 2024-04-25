# FinalCS1

## Project Description
For my final project, I recreated the gambling card game called Blackjack. The goal of the game is to bring your hand closest to 21. Every round, you can bet however much you want, and your "opponent" will also make a bet.  Using the commands "hit" and "fold", you can add another card to your hand, or stop the program from giving you another card. At the end of each round, the program will ask you if you want to play again. If you choose not to, you will be asked if you want to save your game. If you choose to save your game, your total balance will be saved and accessed the next time you play.

## Overview
Within this repository contains a folder called Blackjack. In this folder is my project, Blackjack.java, as well as other classes Blackjack.java is dependent on to run and compile. These classes include the Clear.java, Opponent.java, and Player.java files. Included is also a savegame.txt file which contains information from the last game the user exited (if they decided to save their game). 

The goal of this program was to create a program similar to the card game Blackjack that I've played with friends and family in the past. I used custom objects to create the actual player game where you print "hit" and another card is dealt to you, and a method to bet however much you want. Another class called Opponent extends the Player class and has slight tweaks to both the game method and the betting system method. Within the client code, I used methods for things like asking for/making a save game, getting the deposit and the bet from the player, describing the win conditions, etc. 

Overall, the thing I struggled with the most was creating a way to save your game as well as detailing out all the win conditions. The win conditions was more tedious as I had to think of the different scenarios that could occur and decide if the player or the opponent should win. For creating a save game, it took me a while to come up with an idea for how I could save the game. Using files isn't exactly my strong suit but I went back to the textbook, as well as the class recordings, and managed to figure out a way.

## Instructions For Execution

To execute my project, you can launch the Blackjack.java file within the Blackjack folder from command prompt or something similar.

## Sample Output
![image](https://user-images.githubusercontent.com/91097095/144520238-748c26d5-8198-465b-9510-cb6fb7498972.png)

This picture shows the tutorial for the game.

![image](https://user-images.githubusercontent.com/91097095/144520249-af6df686-0f30-4046-8be0-a822dc5f1d3a.png)

This is the text that is shown at the end of each round.

![image](https://user-images.githubusercontent.com/91097095/144520262-dc10bd5a-17ab-4dd1-a180-5df3f1b1c8e0.png)

This is when you exit out the game.

## Citations
This [website]( https://stackoverflow.com/questions/12702076/try-catch-with-inputmismatchexception-creates-infinite-loop) was useful when I was figuring out the try/catch statement.

This [website](https://www.tutorialspoint.com/how-to-delete-a-string-inside-a-file-txt-in-java) showed how to convert a string into an int, which I used for my save file system.
