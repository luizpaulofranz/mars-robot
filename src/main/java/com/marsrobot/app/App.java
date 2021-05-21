package com.marsrobot.app;

import com.marsrobot.app.exception.CliExceptionHandler;
import com.marsrobot.app.exception.InvalidInputException;
import com.marsrobot.app.service.GetRobotsFromInputService;
import com.marsrobot.app.service.RunCommandsAndPrintResultsService;

public class App {

    public static void main( String[] args ) {
        CliExceptionHandler globalExceptionHandler = new CliExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(globalExceptionHandler);

        if(args.length > 0){
            GetRobotsFromInputService getRobotsFromInputService = new GetRobotsFromInputService();
            RunCommandsAndPrintResultsService runCommandsAndPrintResultsService = new RunCommandsAndPrintResultsService();
            runCommandsAndPrintResultsService.execute(getRobotsFromInputService.execute(args[0]));
        } else
            throw new InvalidInputException("É preciso passar um arquivo de input como parâmetro.");

    }
}
