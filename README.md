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

- **Winner Screen** - the game will show a Game Over/Winner Screen when one of the players dies, displaying who won the battle

- **Power-ups** - the game will have power-ups that each player can collect. Each one of them has a different consequence, for a short period of time:
    -  `Freeze` : freezes the oponent player
    -  `Speed Up` : boosts the ball's velocity (making it harder for the players to catch it or avoid getting hit)
    -  `Slow Down` : reduces the ball's speed (making it easier for the player to catch it or avoid getting hit)
    -  `Turbo` : boosts the players jump capacity
    -  `Shockwave` : doubles the ball's strength (duplicating the damage it gives to the player hit by it)

  being able to change the odds of the game if chosen correctly by each player

- **Music & Sound Effects** - the game will have background music and sound effects, that can have their volume changed in the settings menu

## Mockups
The following mockups ilustrate how we idealize the game's different menus and the game's arena as well as details like the two characters and the power-ups:

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

## Pause Screen
(with `RESUME` selected)

![Captura de ecrã 2024-11-25 093024](https://github.com/user-attachments/assets/bd047735-9fe9-4015-b00c-54d361262a6e)

(with `RESTART` selected)

![Captura de ecrã 2024-11-25 092648](https://github.com/user-attachments/assets/062eced7-2631-4c90-a50d-a23fe08584de)

(with `EXIT` selected)

![Captura de ecrã 2024-11-25 092732](https://github.com/user-attachments/assets/34d6bf4e-3184-4350-9a5b-f9acf51f3c2d)

(with `SETTINGS` selected)

![Captura de ecrã 2024-11-25 092835](https://github.com/user-attachments/assets/9ed52d1e-8bbf-492a-bb34-2fede53f79d0)

## Winner Screen
(when player1 wins)

![Captura de ecrã 2024-11-25 094452](https://github.com/user-attachments/assets/ab97f7e0-ebf9-41e0-9c26-2484294a99c0)

(when player2 wins)

![Captura de ecrã 2024-11-25 095331](https://github.com/user-attachments/assets/b3e0bab5-27e5-4c0e-a1c5-c03476d3a2a9)

### Main Arena
(including **characters** and **power-ups**)

![Captura de ecrã 2024-11-24 210633](https://github.com/user-attachments/assets/5b6d9a51-b010-4263-9d40-2499d391861d)

## UML

![UML_retro_league](https://github.com/user-attachments/assets/5c7596d5-8c46-4972-8c3f-f4ba1a81e961)

## Design

### General Structure of the code
**Problem in context**

With the increase of the complexity of the game, the code will become more difficult to understand and maintain. So, we need to find an appropriate pattern to organize it.

**The Pattern**

The main pattern applied to the project is the Architectural Pattern, more specifically the Model-View-Controller style which is commonly used in a GUI. This pattern is useful because it divides the code in three parts: model, view and controller. 
- The model part is responsible for the dataT
- he view part is responsible for the visual interface
- The controller part for the logic of the game.

  All the three packages are independent and work together to make the game work.

**Consequences**

A modular structure of the code allowing us to divide the code in different files can be very usefull for many reasons, but it can also not be an easy task to do. This means that when we are developing the code we need to think about the structure of the code and how we are going to divide it. This is, in fact, a problem for people who aren't used to work with this kind of structure. However, as the times goes on we will get used to it and benefit from it being able to:
- make cooperation easier
- easily add new features throughout the development stage

### Game State
**Problem in context**

It is easy to verify that the whole game has several states, such as the main menu, the game itself, the winner screen, etc. So, the program should be able to handle these states in a simple and efficient way. In addition, we need to easily change the game state when the user interacts with the game. For example, if one of the players dies, the game state should be set to the winner screen.

**The Pattern**

The State pattern allows the program to change the behavior of the application depending on the current state.

**Consequences**

The game state is defined in the Game class. This is usefull because we can change the state of the game by calling the setState method. The downside is that we need to have access to the Game class context in order to use this method.

### Factory
**Problem in context**

The game contains several elements like power-ups that are created in different ways due to their different effects on the player and the ball. Therefore the creation of many power-ups with their type being randomly decided would be much easier if there was a method of identifying each with an id and creating through it.

**The Pattern**

Factory Method defines an interface for creating an object, but lets subclasses decide which class to instantiate.

**Consequences**

This method allows us to reuse construction code when creating multiple power-ups or different elements making it easier to, for example, introduce new power-ups or walls or even players (if we changeed the game for more than two players) without breaking the current implemented code
