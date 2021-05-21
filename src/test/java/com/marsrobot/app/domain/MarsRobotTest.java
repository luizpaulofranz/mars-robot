package com.marsrobot.app.domain;

import com.marsrobot.app.exception.InvalidCommandException;
import com.marsrobot.app.exception.OutOfBorderException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MarsRobotTest {

    MarsRobot robot;
    Position currentPos;
    Position expectedPos;

    @Before
    public void creation() {
        Terrain terrain = new Terrain(5,5);
        Position robotPosition = Position.builder().x(0).y(0).direction('N').build();
        robot = MarsRobot.builder().terrain(terrain).currentPosition(robotPosition).build();
    }

    @Test
    public void testM() {
        robot.setCommands("M");
        currentPos = robot.move();
        expectedPos = new Position(0,1,'N');
        assertEquals(currentPos, expectedPos);
    }

    @Test(expected = OutOfBorderException.class)
    public void testMLM() {
        robot.setCommands("MLM");
        currentPos = robot.move();
    }

    @Test
    public void testRMMLMR() {
        robot.setCommands("RMMLMR");
        currentPos = robot.move();
        expectedPos = new Position(2,1,'E');
        assertEquals(currentPos, expectedPos);
    }

    @Test
    public void testRMMLMMLMLM() {
        robot.setCommands("RMMLMMLMLM");
        currentPos = robot.move();
        expectedPos = new Position(1,1,'S');
        assertEquals(currentPos, expectedPos);
    }

    @Test(expected = OutOfBorderException.class)
    public void testMMMMMMMMMMMMMMMMM() {
        robot.setCommands("MMMMMMMMRMMMMMMMMML");
        currentPos = robot.move();
    }

    @Test(expected = OutOfBorderException.class)
    public void testRRMMMMMMM() {
        robot.setCommands("RRMMMMMMM");
        currentPos = robot.move();
    }

    @Test
    public void testRRRRMMRMMMLL() {
        robot.setCommands("RRRRMMRMMMLL");
        currentPos = robot.move();
        expectedPos = new Position(3,2,'W');
        assertEquals(currentPos, expectedPos);
    }

    @Test
    public void testRMMMMLMMMM() {
        robot.setCommands("RMMMMLMMMM");
        currentPos = robot.move();
        expectedPos = new Position(4,4,'N');
        assertEquals(currentPos, expectedPos);
    }

    @Test(expected = OutOfBorderException.class)
    public void testRMMMMMMLMMMM() {
        robot.setCommands("RMMMMMMLMMMM");
        currentPos = robot.move();
    }

    @Test(expected = InvalidCommandException.class)
    public void testN() {
        robot.setCommands("N");
        currentPos = robot.move();
    }

    @Test(expected = InvalidCommandException.class)
    public void testE() {
        robot.setCommands("E");
        currentPos = robot.move();
    }

    @Test(expected = InvalidCommandException.class)
    public void testS() {
        robot.setCommands("S");
        currentPos = robot.move();
    }

    @Test(expected = InvalidCommandException.class)
    public void testW() {
        robot.setCommands("W");
        currentPos = robot.move();
    }

    @Test(expected = InvalidCommandException.class)
    public void testMMRMMQ() {
        robot.setCommands("MMRMMQ");
        currentPos = robot.move();
    }



    @Test
    public void testTurnRight1() {
        robot.turnRight();
        currentPos = robot.getCurrentPosition();
        expectedPos = new Position(0,0,'E');
        assertEquals(currentPos, expectedPos);
    }

    @Test
    public void testTurnLeft2() {
        robot.turnLeft();
        robot.turnLeft();
        currentPos = robot.getCurrentPosition();
        expectedPos = new Position(0,0,'S');
        assertEquals(currentPos, expectedPos);
    }

    @Test
    public void testTurnRight3Left5() {
        robot.turnRight();
        robot.turnRight();
        robot.turnRight();
        robot.turnLeft();
        robot.turnLeft();
        robot.turnLeft();
        robot.turnLeft();
        robot.turnLeft();
        currentPos = robot.getCurrentPosition();
        expectedPos = new Position(0,0,'S');
        assertEquals(currentPos, expectedPos);
    }

    @Test
    public void testMoveNorth3() {
        robot.move(3);
        currentPos = robot.getCurrentPosition();
        expectedPos = new Position(0,3,'N');
        assertEquals(currentPos, expectedPos);
    }

    @Test
    public void testMoveEast4() {
        robot.turnRight();
        robot.move(4);
        currentPos = robot.getCurrentPosition();
        expectedPos = new Position(4,0,'E');
        assertEquals(currentPos, expectedPos);
    }

    @Test(expected = OutOfBorderException.class)
    public void testMoveWest2() throws OutOfBorderException, InvalidCommandException {
        robot.turnLeft();
        robot.move(2);
    }
}