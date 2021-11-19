package ua.edu.sumdu.j2se.papizhuk.tasks;

public abstract class AbstractTaskList {

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public abstract AbstractTaskList incoming(int from, int to);
}
