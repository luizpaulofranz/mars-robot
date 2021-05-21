package com.marsrobot.app.service;

import com.marsrobot.app.domain.MarsRobot;
import com.marsrobot.app.domain.Position;
import com.marsrobot.app.domain.Terrain;
import com.marsrobot.app.exception.InvalidInputException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GetRobotsFromInputServiceTest {

    GetRobotsFromInputService getRobotsFromInputService;

    @Before
    public void setup() {
        getRobotsFromInputService = new GetRobotsFromInputService();
    }

    @Test
    public void shouldReadValidInputFileSuccess() {
        List<MarsRobot> result = getRobotsFromInputService.execute("src/test/resources/valid_input.txt");
        List<MarsRobot> expected = Arrays.asList(
                MarsRobot.builder()
                        .currentPosition(Position.builder().x(1).y(2).direction('N').build())
                        .terrain(new Terrain(5,5))
                        .commands("LMLMLMLMM")
                        .build(),
                MarsRobot.builder()
                        .currentPosition(Position.builder().x(3).y(3).direction('E').build())
                        .terrain(new Terrain(5,5))
                        .commands("MMRMMRMRRM")
                        .build()
        );
        assertFalse(result.isEmpty());
        assertEquals(expected, result);
    }

    @Test
    public void shouldReadValidInputFileWithoutCommandSuccess() {
        List<MarsRobot> result = getRobotsFromInputService.execute("src/test/resources/valid_input2.txt");
        List<MarsRobot> expected = Arrays.asList(
                MarsRobot.builder()
                        .currentPosition(Position.builder().x(1).y(2).direction('N').build())
                        .terrain(new Terrain(5,5))
                        .commands("")
                        .build(),
                MarsRobot.builder()
                        .currentPosition(Position.builder().x(3).y(3).direction('E').build())
                        .terrain(new Terrain(5,5))
                        .commands("MMRMMRMRRM")
                        .build()
        );
        assertFalse(result.isEmpty());
        assertEquals(expected, result);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldReadInvalidInputFileNameNull() {
        getRobotsFromInputService.execute(null);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldReadInvalidInputFileNameUnexistentFile() {
        getRobotsFromInputService.execute("asknda");
    }

    @Test(expected = InvalidInputException.class)
    public void shouldReadInvalidInputFileEmpty() {
        getRobotsFromInputService.execute("src/test/resources/empty.txt");
    }

    @Test(expected = InvalidInputException.class)
    public void shouldReadInvalidInputFileTerrain1() {
        try {
            getRobotsFromInputService.execute("src/test/resources/invalid_input_terrain_1.txt");
        } catch (InvalidInputException e) {
            assertTrue(e.getMessage().contains("linha 1"));
            throw e;
        }
    }

    @Test(expected = InvalidInputException.class)
    public void shouldReadInvalidInputFileTerrain2() {
        try {
            getRobotsFromInputService.execute("src/test/resources/invalid_input_terrain_2.txt");
        } catch (InvalidInputException e) {
            assertTrue(e.getMessage().contains("linha 1"));
            throw e;
        }
    }

    @Test(expected = InvalidInputException.class)
    public void shouldReadInvalidInputFileRobot1() {
        try {
            getRobotsFromInputService.execute("src/test/resources/invalid_input_robot_1.txt");
        } catch (InvalidInputException e) {
            assertTrue(e.getMessage().contains("linha 2"));
            throw e;
        }
    }

    @Test(expected = InvalidInputException.class)
    public void shouldReadInvalidInputFileRobot2() {
        try {
            getRobotsFromInputService.execute("src/test/resources/invalid_input_robot_2.txt");
        } catch (InvalidInputException e) {
            assertTrue(e.getMessage().contains("linha 3"));
            throw e;
        }
    }

    @Test(expected = InvalidInputException.class)
    public void shouldReadInvalidInputFileRobot3() {
        try {
            getRobotsFromInputService.execute("src/test/resources/invalid_input_robot_3.txt");
        } catch (InvalidInputException e) {
            assertTrue(e.getMessage().contains("linha 4"));
            throw e;
        }
    }
}
