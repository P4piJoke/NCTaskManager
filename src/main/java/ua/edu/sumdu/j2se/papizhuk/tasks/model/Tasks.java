package ua.edu.sumdu.j2se.papizhuk.tasks.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Class for working with task collections.
 *
 * @author Danylo Papizhuk
 * @version 1.0.0
 * @since 1.8.0_311
 */
public class Tasks {

    /**
     * Returns iterated subset of tasks
     * from a specific <strong>tasks</strong> collection.
     * Which are scheduled to run at least once:
     * after the <strong>start</strong> time
     * and no later than <strong>end</strong>.
     *
     * @param tasks - iterated task collection
     * @param start - the time after which to look for
     * @param end   - the time up to which to look
     * @return iterated subset of tasks
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        AbstractTaskList atl = TaskListFactory.createTaskList(ListTypes.types.ARRAY);

        if (atl == null) throw new IllegalStateException();
        for (Task t : tasks) {
            if (t.nextTimeAfter(start) != null
                    && t.nextTimeAfter(start).compareTo(end) <= 0) {
                atl.add(t);
            }
        }
        return atl;
    }

    /**
     * Builds a calendar of tasks for a given period.
     * Where each date corresponds to a set of tasks
     * that must be completed at that time.
     * And one task can occur according to several dates,
     * if it is to be performed several times in a given period.
     *
     * @param tasks - iterated task collection
     * @param start - date from which to build the task calendar
     * @param end   - date up to which to build the task calendar.
     * @return task calendar
     */
    public static SortedMap<LocalDateTime, Set<Task>> calendar
    (Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {

        SortedMap<LocalDateTime, Set<Task>> dateSetSortedMap = new TreeMap<>(LocalDateTime::compareTo);
        Iterable<Task> taskIterable = incoming(tasks, start, end);

        for (Task t : taskIterable) {
            for (LocalDateTime time = t.nextTimeAfter(start);
                 time != null && (time.isBefore(end) || time.isEqual(end));
                 time = t.nextTimeAfter(time)) {

                if (dateSetSortedMap.containsKey(time)) {
                    dateSetSortedMap.get(time).add(t);
                } else {
                    Set<Task> taskSet = new HashSet<>();
                    taskSet.add(t);
                    dateSetSortedMap.put(time, taskSet);
                }
            }
        }
        return dateSetSortedMap;
    }
}