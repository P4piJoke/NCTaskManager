package ua.edu.sumdu.j2se.papizhuk.tasks.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class for storing task data.
 *
 * @author Danylo Papizhuk
 * @version 1.0.0
 * @since 1.8.0_311
 */
public class Task implements Cloneable, Serializable {

    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean active;
    private boolean repeated;

    /**
     * Constructor for a non-repetitive task.
     *
     * @param title - task title
     * @param time  - task time
     */
    public Task(String title, LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.time = time;
        setActive(false);
        setRepeated(false);
    }

    /**
     * Constructor for a repetitive task.
     *
     * @param title    - task title
     * @param start    - task start time
     * @param end      - task end time
     * @param interval - task interval
     */
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        if (interval <= 0 || start == null || end == null) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        setActive(false);
        setRepeated(true);
    }

    /**
     * Returns task title.
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets task title.
     *
     * @param title - task title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns task activity.
     *
     * @return true is task is active, false if inactive.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets task activity.
     *
     * @param active - task activity
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns task time.
     *
     * @return time
     */
    public LocalDateTime getTime() {
        if (isRepeated()) {
            return start;
        }
        return time;
    }

    /**
     * Sets task time.
     *
     * @param time - task time
     */
    public void setTime(LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException();
        }
        if (isRepeated()) {
            setRepeated(false);
        }
        this.time = time;
    }

    /**
     * Returns task start time.
     *
     * @return start time
     */
    public LocalDateTime getStartTime() {
        if (!isRepeated()) {
            return time;
        }
        return start;
    }

    /**
     * Returns task end time.
     *
     * @return end time
     */
    public LocalDateTime getEndTime() {
        if (!isRepeated()) {
            return time;
        }
        return end;
    }

    /**
     * Returns task interval.
     *
     * @return interval
     */
    public int getRepeatInterval() {
        if (!isRepeated()) {
            return 0;
        }
        return interval;
    }

    /**
     * Sets repetitive task time.
     *
     * @param start    - start time
     * @param end      - end time
     * @param interval - interval
     */
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        if (interval <= 0 || start == null || end == null) {
            throw new IllegalArgumentException();
        }
        if (!isRepeated()) {
            setRepeated(true);
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /**
     * Returns the repetitive status.
     *
     * @return true if task is repetitive, false if non-repetitive
     */
    public boolean isRepeated() {
        return repeated;
    }

    /**
     * Sets the repetitive status.
     *
     * @param repeated - repetitive status
     */
    public void setRepeated(boolean repeated) {
        this.repeated = repeated;
    }

    /**
     * Returns the time of the next task after the specified time.
     *
     * @param current - specified time
     * @return next task time if the task run after the specified time, null if the task doesn't
     */
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (isActive()) {
            if (!isRepeated()) {
                if (time.isAfter(current)) {
                    return time;
                } else return null;
            }
            for (LocalDateTime i = start;
                 i.isBefore(end) || i.equals(end);
                 i = i.plusSeconds(interval)) {
                if (current.isBefore(i)) {
                    return i;
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return time == task.time
                && start == task.start
                && end == task.end
                && interval == task.interval
                && active == task.active
                && repeated == task.repeated
                && Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time, start, end, interval, active, repeated);
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        Task cloned = (Task) super.clone();
        cloned.title = new String(this.title);
        cloned.time = this.time;
        cloned.start = this.start;
        cloned.end = this.end;
        cloned.interval = this.interval;
        cloned.active = this.active;
        cloned.repeated = this.repeated;
        return cloned;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", active=" + active +
                ", repeated=" + repeated +
                '}';
    }
}