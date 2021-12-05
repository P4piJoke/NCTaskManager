package ua.edu.sumdu.j2se.papizhuk.tasks;

import java.util.Arrays;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task> {

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public final AbstractTaskList incoming(int from, int to) {
        AbstractTaskList atl = getAbstractTaskList();
        getStream().filter(t -> t.nextTimeAfter(from) != -1
                && t.nextTimeAfter(from) <= to).forEach(atl::add);
        return atl;
    }

    public Stream<Task> getStream() {
        Task[] tasks = new Task[this.size()];
        for (int i = 0; i < tasks.length; ++i) {
            tasks[i] = getTask(i);
        }
        return Arrays.stream(tasks);
    }

    private AbstractTaskList getAbstractTaskList() {
        return (this.getClass().getSimpleName().equals("ArrayTaskList")) ?
                TaskListFactory.createTaskList(ListTypes.types.ARRAY) :
                TaskListFactory.createTaskList(ListTypes.types.LINKED);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        for (Task t : this) {
            hash = 31 * hash + ((t == null) ? 0 : t.hashCode());
        }
        return hash;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        AbstractTaskList atl = getAbstractTaskList();
        for (Task t : this) {
            atl.add(t);
        }
        return atl;
    }
}
