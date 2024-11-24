# LDTS_T13_G06 - RETRO LEAGUE

## Game Description

The Retro League is a game inspired by the Lethal League game, a 2D arena fighting game. 

In this game, two players are put into an arena where they have to hit a ball into their opponent to win. However, there are a few tricks to it:
- each player has the chance to catch the ball avoiding being hit,
- there are power-ups that can change the game's outcome by affecting the players and the ball,
- the ball bounces off the walls which can lead to a player beign hit by their own launch,

testing the players' strategy and reflexes.


This project was developed by:
- Afonso Saraiva (up202304461@fe.up.pt)
- Daniel Marques (up202306365@fe.up.pt)
- Inês Francisco (up202304726@fe.up.pt)

for LDTS 2024/2025.

Enjoy playing this multiplayer game with your friends and challenge them, just be careful not to get hit and killed in the process.

## Planned Features

- **Playable Characters** - the game will have two playable characters that can be controlled by pressing:
    - _Player1_ : `A`, `D`, `W` and `S`
    - _Player2_ : `LEFT`, `RIGHT`, `UP` and `DOWN`

  to move left, right, to jump and to catch/throw the ball, respectively. In terms of catching and throwing the ball, each player will also have a designated key for that.

- **Main Menu** - the game will have a main menu when we first open the game. It has the following options:
    - `PLAY` : starts a "new" game
    - `SETTINGS` : allows the players to control game settings and displays the instructions of the game as well as a brief explanation of what every power-up does

- **Pause Menu** - the game will be able to be paused by pressing the escape key. Then the players will be able to resume, exit and restart the game and also, go to the game's settings

- **Walls & Platforms** - the game's walls and platforms will work almost like a trampoline allowing the ball to bounce off them and, consequently, change their trajectory

- **Collision Detection** - The game will detect collisions between the ball and the walls and platforms as well as between the ball and the players. When the ball collides with the players and they are not able to catch it, they lose a life and can, eventually, die ending the game

- **Game Over/Winner Screen** - the game will show a Game Over/Winner Screen when one of the players dies, displaying who won the battle

- **Power-ups** - the game will have power-ups that each player can collect. Each one of them has a different consequence, for a short period of time:
    -  `Freeze` : freezes the oponent player
    -  `Speed Up` : boosts the ball's velocity (making it harder for the players to catch it or avoid getting hit)
    -  `Slow Down` : reduces the ball's speed (making it easier for the player to catch it or avoid getting hit)
    -  `Turbo` : boosts the players jump capacity
    -  `Shockwave` : doubles the ball's strength (duplicating the damage it gives to the player hit by it)

  being able to change the odds of the game if chosen correctly by each player

- **Music & Sound Effects** - the game will have background music and sound effects, that can have their volume changed in the settings menu

## Mockups
The following mockups ilustrate how we idealize the game's differente menus and the game's arena as well as details like the two characters and the power-ups:

### Main Menu
(with `PLAY` selected)

![Captura de ecrã 2024-11-24 134914](https://github.com/user-attachments/assets/8ee3f088-fb67-4ed6-9e8a-da79707a4c64)

(with `SETTINGS` selected)

![Captura de ecrã 2024-11-24 134933](https://github.com/user-attachments/assets/28171de5-7ee7-41cd-a6ae-df3781f57585)

### Settings Menu
(with `SOUND EFFECTS VOLUME` selected)

![Captura de ecrã 2024-11-24 225402](https://github.com/user-attachments/assets/37b2a0d8-7555-48ef-a621-f73fc677815f)

(with `MUSIC VOLUME` selected)

![Captura de ecrã 2024-11-24 225422](https://github.com/user-attachments/assets/509df3d8-1ffa-47d7-981d-3c84aa5b41fe)

### Main Arena
(including **characters** and **power-ups**)

![Captura de ecrã 2024-11-24 210633](https://github.com/user-attachments/assets/5b6d9a51-b010-4263-9d40-2499d391861d)

## UML

## Design
