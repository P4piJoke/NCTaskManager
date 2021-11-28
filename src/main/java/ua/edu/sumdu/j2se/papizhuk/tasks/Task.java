package ua.edu.sumdu.j2se.papizhuk.tasks;

import java.util.Objects;

public class Task implements Cloneable {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeated;

    public Task(String title, int time) {
        if (time < 0) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.time = time;
        setActive(false);
        setRepeated(false);
    }

    public Task(String title, int start, int end, int interval) {
        if (interval <= 0 || start < 0 || end < 0) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        setActive(false);
        setRepeated(true);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {
        if (isRepeated()) {
            return start;
        }
        return time;
    }

    public void setTime(int time) {
        if (time < 0) {
            throw new IllegalArgumentException();
        }
        if (isRepeated()) {
            setRepeated(false);
        }
        this.time = time;
    }

    public int getStartTime() {
        if (!isRepeated()) {
            return time;
        }
        return start;
    }

    public int getEndTime() {
        if (!isRepeated()) {
            return time;
        }
        return end;
    }

    public int getRepeatInterval() {
        if (!isRepeated()) {
            return 0;
        }
        return interval;
    }

    public void setTime(int start, int end, int interval) {
        if (interval <= 0 || start < 0 || end < 0) {
            throw new IllegalArgumentException();
        }
        if (!isRepeated()) {
            setRepeated(true);
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public void setRepeated(boolean repeated) {
        this.repeated = repeated;
    }

    public int nextTimeAfter(int current) {
        if (isActive()) {
            if (!isRepeated()) {
                if (time > current) {
                    return time;
                } else return -1;
            }
            for (int i = start; i < end; i += interval) {
                if (current < i) {
                    return i;
                }
            }
        }
        return -1;
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


