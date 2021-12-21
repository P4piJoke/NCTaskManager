package ua.edu.sumdu.j2se.papizhuk.tasks.contoller;

/**
 * Interface to perform main business logic.
 *
 * @author Danylo Papizhuk
 * @version 1.0.0
 * @since 1.8.0_311
 */
public interface TaskController {

    /**
     * Executes task addition command.
     */
    void addTask();

    /**
     * Executes task editing command.
     */
    void editTask();

    /**
     * Executes task deletion command.
     */
    void deleteTask();

    /**
     * Executes task list display command.
     */
    void showTaskList();

    /**
     * Executes task calendar display command.
     */
    void showCalendar();
}