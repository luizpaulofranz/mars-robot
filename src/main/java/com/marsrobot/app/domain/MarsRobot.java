/**
 * This class contains the mars robot abstraction with all possible movements.
 * 
 * @author Luiz P. Franz
 */

package com.marsrobot.app.domain;

import com.marsrobot.app.exception.InvalidCommandException;
import com.marsrobot.app.exception.OutOfBorderException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarsRobot {
	
	/**
	 * Current robot position.
	 */
	private Position currentPosition;
	/**
	 * Terrein limits.
	 */
	private Terrain terrain;
	/**
	 * String of commands. Ex: MMRMLLMM
	 */
	private String commands;
	
	/**
	 * Here we pass an command input which will made the robot moves. This is our entry movement point.
	 *
	 * @return Position - The robot position.
	 * @throws OutOfBorderException - if robot goes out of terrain limits.
	 * @throws InvalidCommandException - if command String has invalid commands.
	 */
	public Position move() {

		if(commands == null)
			throw new InvalidCommandException();
		
		for (int i = 0; i < commands.length(); i++) {
			switch (commands.charAt(i)) {
			case 'L':
				this.turnLeft();
				break;
			case 'R':
				this.turnRight();
				break;
			case 'M':
				this.move(1);
				break;
			default:
				throw new InvalidCommandException();
			}
		}
		
		return currentPosition;
	}

	public void printCurrentPosition() {
		System.out.println(currentPosition.getX()+" "+currentPosition.getY()+" "+currentPosition.getDirection());
	}
	
	public void move(int steps) throws OutOfBorderException {
		switch (this.currentPosition.getDirection()) {
		case 'N':
			this.moveNorth(steps);
			break;
		case 'W':
			this.moveWest(steps);
			break;
		case 'S':
			this.moveSouth(steps);
			break;
		case 'E':
			this.moveEast(steps);
			break;
		default:
			break;
		}
	}
	
	public void turnLeft() {
		char currentDir = currentPosition.getDirection();
		switch (currentDir) {
		case 'N':
			currentPosition.setDirection('W');
			break;
		case 'W':
			currentPosition.setDirection('S');
			break;
		case 'S':
			currentPosition.setDirection('E');
			break;
		case 'E':
			currentPosition.setDirection('N');
			break;
		}
	}
	
	public void turnRight() {
		char currentDir = currentPosition.getDirection();
		switch (currentDir) {
		case 'N':
			currentPosition.setDirection('E');
			break;
		case 'E':
			currentPosition.setDirection('S');
			break;
		case 'S':
			currentPosition.setDirection('W');
			break;
		case 'W':
			currentPosition.setDirection('N');
			break;
		}
	}
	
	public void moveNorth(int steps) throws OutOfBorderException {
		int curY = currentPosition.getY();
		if (curY + steps <= terrain.getNorthLimit()) {
			currentPosition.setY(currentPosition.getY() + steps);
		} else {
			throw new OutOfBorderException();
		}
	}
	
	public void moveSouth(int steps) throws OutOfBorderException {
		int curY = currentPosition.getY();
		if (curY - steps >= terrain.getSouthLimit()) {
			currentPosition.setY(currentPosition.getY() - steps);
		} else {
			throw new OutOfBorderException();
		}
	}
	
	public void moveWest(int steps) throws OutOfBorderException {
		int curX = currentPosition.getX();
		if (curX - steps >= terrain.getWestLimit()) {
			currentPosition.setX(currentPosition.getX() - steps);
		} else {
			throw new OutOfBorderException();
		}
	}
	
	public void moveEast(int steps) throws OutOfBorderException {
		int curX = currentPosition.getX();
		if (curX + steps <= terrain.getEastLimit()) {
			currentPosition.setX(currentPosition.getX() + steps);
		} else {
			throw new OutOfBorderException();
		}
	}
	
}
