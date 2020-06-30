# CS2-Peg-Solitaire
CS2 final project involving a version of the game Peg Solitaire.

Peg Solitaire game that is in the shape of a cross, in a 7x7 grid. There is a single open space in the
center of the board. A player can only move game-pieces two spaces to the left, right, up, or down.
Additionally, each move must jump another game-piece, and thus, like checkers, the game-piece that was
jumped gets removed. The player begins by jumping a game-piece over another game-piece into the center
of the board. The object of the game is to finish with only one game-piece remaining in order to win the game.

This game was designed using a Java GUI, with Model-View-Controller (MVC) architecture. Each game-piece is a
JavaFX button that can be clicked, selecting the beginning of a move. The game board is represented as an
array of arrays (2D array). The array indices that are not game-piece spaces are not able to be clicked as
buttons and do not look like buttons to the user. Once a game-piece is selected, the user must click the space
they desire to move the game-piece. If they click an empty space AND it is a valid move, the piece will move
and the game continues smoothly. If not, an alert box pops up and informs the user that their move is invalid
and they must try again (deselecting the original game-piece).

There is also a reset button to restart the game and the game recognizes when there are no more available moves
(pieces are not adjacent to one another) and whether the player has won or lost the game.
