package com.marsrobot.app.service;

import com.marsrobot.app.domain.MarsRobot;

import java.util.List;

public class RunCommandsAndPrintResultsService {

    public void execute(List<MarsRobot> robots) {
        robots.forEach(bot -> {
            bot.move();
            bot.printCurrentPosition();
        });
    }
}
