package ua.edu.sumdu.j2se.papizhuk.tasks.contoller.notification;

import ua.edu.sumdu.j2se.papizhuk.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

/**
 * Interface for notifying the user about upcoming tasks.
 *
 * @author Danylo Papizhuk
 * @version 1.0.0
 * @since 1.8.0_311
 */
public interface CalendarNotification {

    /**
     * Notifies the user about upcoming tasks.
     *
     * @param calendar - general task calendar to notify
     */
    void notify(SortedMap<LocalDateTime, Set<Task>> calendar);
}
