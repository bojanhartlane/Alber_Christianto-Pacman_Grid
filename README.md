# Alber_Christianto-Pacman_Grid
IE Digital Code Challenge

-----------

Developer's Note:

- The application code is written in JavaScript and HTML.
- Bootstrap is used for the website front-end.
- Unit testing for the application is done by using Selenium. The test code is written in Java.
- Unit testing was conducted in Eclipse Oxygen IDE.
- Chrome Driver was used for the Selenium testing. This particular version of Chrome Driver is optimised for unit testing in Google Chrome version 72.x.
- The testing was conducted on Google Chrome version 72.0.3626.119.
- A video of the unit testing can be found in "Testing Documentation" folder.
- Test data can be found in "Test Cases" folder. It has 10 successful test cases and 6 failed test cases, which will return errors about the sequence.
- For this application, user must enter their input using the text area provided. Copying and pasting input from the provided test cases to the text area is allowed.

-----------

Pacman Simulator

Description:

- This application is a simulation of Pacman moving on in a grid, of dimensions 5 units x 5 units.
- There are no other obstructions on the grid.
- Pacman is free to roam around the surface of the grid, but must be prevented from moving off the grid. Any movement that would result in Pacman moving off the grid must  be prevented, however further valid movement commands must still be allowed.
- Acceptable commands for this application are:

```
PLACE X,Y,F

MOVE

LEFT

RIGHT

REPORT
```

- PLACE will put the Pacman on the grid in position X,Y and facing NORTH,SOUTH, EAST or WEST. Any other direction will be ignored by the application. Player can choose any combination of X and Y from (0,0) to (4,4).
- The origin (0,0) can be considered the SOUTH WEST most corner.
- PLACE command can be called more than once. Pacman's position will be readjusted based on the new PLACE command. For example, if Pacman's position is (2,4) facing NORTH and PLACE 1,1,WEST is called, Pacman's position will be (1,1) facing WEST. All commands after that will be run based on Pacman's (1,1) facing WEST, not the older position.
- The first valid command to Pacman is a PLACE command, after that, any sequence of commands may be issued, in any order, including another PLACE command. The application should discard all commands in the sequence until a valid PLACE command has been executed.
- MOVE will move Pacman one unit forward in the direction it is currently facing. For example, if Pacman's current position is (1,2) facing EAST, MOVE command will move Pacman to (2,2) facing EAST.
- LEFT and RIGHT will rotate Pacman 90 degrees in the specified direction without changing the position of Pacman. For example, if Pacman is facing NORTH, LEFT command will change Pacman's facing direction to WEST. If Pacman is facing EAST, RIGHT command will change Pacman's facing direction to SOUTH.
- REPORT will announce the X,Y and F of Pacman. Just like PLACE, REPORT can be called more than once.
- Pacman that is not on the grid can choose the ignore the MOVE, LEFT, RIGHT and REPORT commands.
- Input can be from a file, or from standard input, as the developer chooses.
- Provide test data to exercise the application.

**Constraints:**

- Pacman must not move off the grid during movement. This also includes the initial placement of Pacman.
- Any move that would cause Pacman to fall must be ignored.

Example Input and Output:
```
a)
PLACE 0,0,NORTH

MOVE

REPORT

Output: 0,1,NORTH
```
```
b)

PLACE 0,0,NORTH

LEFT

REPORT

Output: 0,0,WEST
```
```
c)

PLACE 1,2,EAST

MOVE

MOVE

LEFT

MOVE

REPORT

Output: 3,3,NORTH
```
