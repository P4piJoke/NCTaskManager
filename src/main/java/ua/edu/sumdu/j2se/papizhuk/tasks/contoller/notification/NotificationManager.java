package ua.edu.sumdu.j2se.papizhuk.tasks.contoller.notification;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.papizhuk.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.papizhuk.tasks.model.Task;
import ua.edu.sumdu.j2se.papizhuk.tasks.model.Tasks;
import ua.edu.sumdu.j2se.papizhuk.tasks.view.util.Output;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

/**
 * Class that extends the <strong>Thread</strong> class.
 *
 * @author Danylo Papizhuk
 * @version 1.0.0
 * @since 1.8.0_311
 */
public class NotificationManager extends Thread {

    private static final Logger log = Logger.getLogger(NotificationManager.class);
    private static final long TIME = 300_000; // -> 3 minutes
    private static final long MINUTES = 60;
    private final AbstractTaskList atl;
    private final CalendarNotification notification;

    /**
     * Constructor.
     *
     * @param list - general task list
     */
    public NotificationManager(AbstractTaskList list) {
        atl = list;
        notification = CalendarNotificationImpl.getInstance();
    }

    /**
     * Starts a thread to notify the user of upcoming tasks.
     */
    @Override
    public void run() {
        SortedMap<LocalDateTime, Set<Task>> calendar;
        while (true) {
            calendar = Tasks.calendar(atl,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(MINUTES));
            if (!calendar.isEmpty()) {
                notification.notify(calendar);
            }
            try {
                sleep(TIME);
            } catch (InterruptedException e) {
                log.error(e);
                Output.println(e.getMessage());
            }
        }
    }
}