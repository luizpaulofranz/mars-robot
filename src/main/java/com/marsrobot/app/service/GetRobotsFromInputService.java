package com.marsrobot.app.service;

import com.marsrobot.app.domain.MarsRobot;
import com.marsrobot.app.domain.Position;
import com.marsrobot.app.domain.Terrain;
import com.marsrobot.app.exception.InvalidInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GetRobotsFromInputService {

    private static final String ONLY_DIGITS_REGEX = "[0-9]*";
    private static final String ROBOT_POSITION_INPUT_REGEX = "([0-9])([0-9])([NESW])";
    private static final String ROBOT_COMMANDS_INPUT_REGEX = "[LRM]*";

    private int currentLine = 0;

    public List<MarsRobot> execute(String fileName) {
        if (fileName == null)
            throw new InvalidInputException("O parâmetro \"fileName\" é obrigatório!");

        File file = new File(fileName);
        Scanner input;

        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new InvalidInputException("O arquivo \""+fileName+"\" não foi encontrado!");
        }

        Terrain terrain = this.getTerrainLimit(input);
        List<MarsRobot> robots = this.getRobotsList(input, terrain);
        input.close();

        return robots;
    }

    private Terrain getTerrainLimit(Scanner input) {
        Terrain terrain = null;
        if (input.hasNextLine()) {
            String line = input.nextLine();
            List<Integer> terrainBorders = this.getTerrainBorders(line);
            terrain = new Terrain(terrainBorders.get(0),terrainBorders.get(1));
            currentLine++;
        } else {
            throw new InvalidInputException();
        }
        return terrain;
    }


    private List<Integer> getTerrainBorders(String line) {
        line = line.replace(" ", "").trim();
        if(line.length() != 2 || !line.matches(ONLY_DIGITS_REGEX))
            throw new InvalidInputException("A linha "+(currentLine+1)+" do arquivo de input está inválida.");

        return Arrays.asList(
                Character.getNumericValue(line.charAt(0)),
                Character.getNumericValue(line.charAt(1))
        );
    }

    private List<MarsRobot> getRobotsList(Scanner input, Terrain terrain) {
        List<MarsRobot> robots = new ArrayList<>();

        while (input.hasNextLine()) {
            if(currentLine == 0)
                continue;

            String robotPositionLine = input.nextLine();
            this.validateRobotPositionInput(robotPositionLine);

            String robotCommandsLine = null;

            // cada robo tem duas linhas de input
            if(input.hasNextLine()) {
                robotCommandsLine = input.nextLine();
                currentLine++;
            } else
                throw new InvalidInputException("O robô da linha "+(currentLine+1)+" do arquivo de input está inválida.");

            this.validateRobotCommandsInput(robotCommandsLine);

            robots.add(
              MarsRobot.builder()
                      .currentPosition(this.getRobotPosition(robotPositionLine))
                      .terrain(terrain)
                      .commands(robotCommandsLine)
                      .build()
            );

            currentLine++;
        }

        return robots;
    }

    private void validateRobotPositionInput(String line) {
        line = line.replace(" ", "").trim();
        if(line.length() != 3 || !line.matches(ROBOT_POSITION_INPUT_REGEX))
            throw new InvalidInputException("A linha "+(currentLine+1)+" do arquivo de input está inválida.");
    }

    private Position getRobotPosition(String robotPositionLine) {
        robotPositionLine = robotPositionLine.replace(" ", "").trim();
        return Position.builder()
                .x(Character.getNumericValue(robotPositionLine.charAt(0)))
                .y(Character.getNumericValue(robotPositionLine.charAt(1)))
                .direction(robotPositionLine.charAt(2))
                .build();
    }

    private void validateRobotCommandsInput(String line) {
        if(!line.matches(ROBOT_COMMANDS_INPUT_REGEX))
            throw new InvalidInputException("A linha "+(currentLine+1)+" do arquivo de input está inválida.");
    }
}
