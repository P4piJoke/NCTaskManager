package ua.edu.sumdu.j2se.papizhuk.tasks;

public abstract class AbstractTaskList implements Iterable<Task> {

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public AbstractTaskList incoming(int from, int to) {
        AbstractTaskList atl = getAbstractTaskList();
        for (int i = 0; i < atl.size(); ++i) {
            if (getTask(i).nextTimeAfter(from) != -1 &&
                    getTask(i).nextTimeAfter(from) <= to) {
                atl.add(getTask(i));
            }
        }
        return atl;
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
