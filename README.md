# Mars Robot
[![Maintainability](https://api.codeclimate.com/v1/badges/a7549ae74d8fa13ece73/maintainability)](https://codeclimate.com/github/luizpaulofranz/mars-robot/maintainability)

Mars Robot Code Challenge. In this challange, we have an Java CLI program, developed with **Maven/Java 8**, in which we have to pass a file name as param. 
```
java -jar target/mars-robot-1.0.jar input.txt
```
Where `input.txt` is a filename containing a list of intems such as: Terrain, Robots and Commands. Are valid commands:

## Commands
| Command | Action |
| ------- | ------ |
| M | Moves robot foward |
| R | Turns robot right |
| L | Turns robot left |


**Any other charactere is not recognized by our robot as a command, and will results in error!**
## Input
We should provide an input file and pass its name as argument when run our jar from CLI.

Input file example:
```json
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```

Input file explanations:

| Input line | Explanation
| ------- | ------ |
| 5 5 | Terrein borders |
| 1 2 N | First robot initial position |
| LMLMLMLMM | First robot commands |
| 3 3 E | Second robot initial position |
| MMRMMRMRRM | Second robot commands |

1. The mars Terrain is a world composed by squares, is a **matrix** setted on the first line of our input file.
2. Each robot definition needs two lines, one to set its initial position inside terrain, and other to set the commands
3. We can have any number of robots in our input file, but following the required format (two lines, position/commands) is a must.

# Compile and Build
```
mvn clean package
```
This will create a `jar` file inside target folder, and it can be executed with:
```
java -jar mars-robot-0.0.1-SNAPSHOT.jar input.txt
```
