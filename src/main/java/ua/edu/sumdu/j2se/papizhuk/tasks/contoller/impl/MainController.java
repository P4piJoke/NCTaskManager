package ua.edu.sumdu.j2se.papizhuk.tasks.contoller.impl;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.papizhuk.tasks.contoller.Controller;
import ua.edu.sumdu.j2se.papizhuk.tasks.contoller.TaskController;
import ua.edu.sumdu.j2se.papizhuk.tasks.contoller.notification.NotificationManager;
import ua.edu.sumdu.j2se.papizhuk.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.papizhuk.tasks.view.View;
import ua.edu.sumdu.j2se.papizhuk.tasks.view.util.Output;

import java.util.Scanner;

/**
 * Class that implements the <strong>Controller</strong> interface.
 *
 * @author Danylo Papizhuk
 * @version 1.0.0
 * @since 1.8.0_311
 */
public class MainController implements Controller {

    private static final Logger log = Logger.getLogger(MainController.class);
    private static final String QUESTION = "Are you confident in your choice? (Yes:No) ";
    private final TaskController controller;
    private final View view;
    private final Scanner scanner;

    /**
     * Constructor.
     *
     * @param list - general task list
     * @param view - view object
     */
    public MainController(AbstractTaskList list, View view) {
        this.view = view;
        controller = new TaskControllerImpl(list, view);
        scanner = new Scanner(System.in);

        NotificationManager manager = new NotificationManager(list);
        manager.setDaemon(true);
        manager.start();
    }

    /**
     * Executes main controller logic.
     */
    @Override
    public void execute() {
        log.info("Executing main controller command");
        while (true) {
            view.showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) {
                Output.print("Do you want to finish? (Yes:No): ");
                if (view.checkUserChoice()) {
                    Output.println("Program was finished");
                    log.info("The Task Manager application is getting ready to end");
                    break;
                }
            } else {
                switch (choice) {
                    case 1:
                        Output.print(QUESTION);
                        if (view.checkUserChoice()) controller.addTask();
                        break;
                    case 2:
                        Output.print(QUESTION);
                        if (view.checkUserChoice()) controller.editTask();
                        break;
                    case 3:
                        Output.print(QUESTION);
                        if (view.checkUserChoice()) controller.deleteTask();
                        break;
                    case 4:
                        controller.showTaskList();
                        break;
                    case 5:
                        controller.showCalendar();
                        break;
                }
            }
        }
    }
}