/*
JavaScript code for Pacman Grid challenge
Created by: Alber Jonathan Christianto
*/

// Global variable to determine which direction Pacman is facing
var directions = ["NORTH", "EAST", "SOUTH", "WEST"];

function pacman_command() {
	// Variable for Pacman positions
	var x = -1, y = -1;
	/*
	Variable for Pacman facing direction, e.g. if Pacman is facing NORTH and Pacman's position is (0,0), then
	when Pacman moves, they will move to (0,1) position. If Pacman is facing EAST and Pacman moves, they will
	move to (1,1) position from their initial (0,1) position. Initial position for Pacman is INVALID so that
	the program can capture invalid user input
	*/
	var facingDirection = "INVALID";
	
	var command_per_line, command_individual, command_place, result = "";
	var checkInput = true;
	// Variable to retrieve commands from index.html
	var commands = document.getElementById("commands").value.toUpperCase();
	command_per_line = commands.split("\n");
	
	/*	
	Read command line per line
	The only acceptable size for each line is 1 word ("MOVE", "LEFT", "RIGHT", or "REPORT")
	or 2 words for "PLACE X,Y,F"
	*/	
	for (line = 0; line < command_per_line.length; line++) {		
		command_individual = command_per_line[line].split(" ");
		
		if (command_individual.length == 1) {		
			if (command_individual[0] == "MOVE") {
				var move_result = move(x, y, facingDirection);
				var move_result_split = move_result.split(",");
				x = move_result_split[0];
				y = move_result_split[1];				
		} else if (command_individual[0] == "LEFT" || command_individual[0] == "RIGHT") {
				facingDirection = turn(facingDirection, command_individual[0]);
								
			} else if (command_individual[0] == "REPORT") {
				// Display the latest Pacman position and facing direction to the user
				result += "Output: " + x + ", " + y + ", " + facingDirection + "\n";
			}
		} else if (command_individual.length == 2) {
			
			if (command_individual[0] == "PLACE" && x == -1) {
				command_place = command_individual[1].split(",");		
				
				if (command_place.length == 3) {
					var xInit = command_place[0];
					var yInit = command_place[1];
					var facingDirectionInit = command_place[2];
					
					var initialPositionString = place(xInit, yInit, facingDirectionInit);
					
					var initialPosition = initialPositionString.split(",");
					if (initialPosition[0] == -1) {
						checkInput = false;
					} else {
						x = initialPosition[0];
						y = initialPosition[1];
						facingDirection = initialPosition[2];
					}					
				}				
			}			
		}
		
		if (!checkInput) {
			if (x == -1 || y == -1) {
				result = "Command error at line " + (line+1) + ": Pacman must be placed within (0,0) to (4,4)";
			} else {
				result = "Command not recognised at line " + (line+1);
			}			
			line = command_per_line.length;
		}
	}
	
	document.getElementById("result").value = result;
	
}

// Function to set initial position for Pacman on the grid
function place(x, y, facingDirection) {
	var result = "-1,-1,INVALID";
	// Check if horizontal position input is valid position (between 0 to 4)
	if (x >= 0 && x <= 4) {
		// Check if vertical position input is valid position (between 0 to 4)
		if (y >= 0 && y <= 4) {
			// Check if facing_direction input is a valid facing_direction			
			if (checkFacingDirection(facingDirection)) {
				// If all parameters are valid, then return initial x, y, and direction
				result = x + "," + y + "," + facingDirection;
			}
		}
	}
	return result;
}

// Function to check if input for facing_direction on "PLACE" command is valid or not
function checkFacingDirection(input) {
	var result = false;	
	for (faceDirSize = 0; faceDirSize < directions.length; faceDirSize++) {
		// If direction entered by the user is included in "directions" array, then input is valid
		if (input == directions[faceDirSize]) {
			result = true;
		}
	}
	return result;
}

// Function to move Pacman according to their facing direction
function move(x, y, facingDirection) {
	var xStart = Number(x), yStart = Number(y);
	// Pacman will move towards north 1 spot
	if (facingDirection == "NORTH") {
		y = Number(y) + 1;
	}
	// Pacman will move towards east 1 spot
	else if (facingDirection == "EAST") {
		x = Number(x) + 1;
	}
	// Pacman will move towards south 1 spot
	else if (facingDirection == "SOUTH") {
		y = Number(y) - 1;
	}
	// Pacman will move towards west 1 spot
	else if (facingDirection == "WEST") {
		x = Number(x) - 1;
	}
	
	// If Pacman moves outside the boundaries, they will be put back to their previous position
	if (Number(x) < 0 || Number(x) > 4) {
		x = xStart;
	}
	if (Number(y) < 0 || Number(y) > 4) {
		y = yStart;
	}	
	
	var result = x + "," + y;
	// Return new position or previous position if the move is invalid
	return result;	
}

// Function to turn Pacman's facing direction to the left or right
function turn(facingDirection, turnDirection) {
	var currentDirection = facingDirection, currentDirectionIndex = -1;
	for (faceDirLength = 0; faceDirLength < directions.length; faceDirLength++) {
		// If direction entered by the user is included in "directions" array, then input is valid
		if (currentDirection == directions[faceDirLength]) {
			currentDirectionIndex = faceDirLength;
			if (turnDirection == "LEFT") {
				currentDirectionIndex -= 1;
			} else if (turnDirection == "RIGHT") {
				currentDirectionIndex += 1;
			}
			
			if (currentDirectionIndex < 0) {
				currentDirectionIndex = 3;
			} else if (currentDirectionIndex > 3) {
				currentDirectionIndex = 0;
			}
			currentDirection = directions[currentDirectionIndex];			
			faceDirLength = directions.length;
		}
	}
	return currentDirection;
}
