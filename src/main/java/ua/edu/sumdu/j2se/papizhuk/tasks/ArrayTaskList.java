package ua.edu.sumdu.j2se.papizhuk.tasks;

public class ArrayTaskList extends AbstractTaskList {

    private Task[] taskArray;
    private int size;
    private int curr;

    {
        size = 1;
        taskArray = new Task[size];
    }

    @Override
    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException();
        }
        if (curr == size) {
            Task[] temp = new Task[size * 2];
            System.arraycopy(taskArray, 0, temp, 0, taskArray.length);
            size *= 2;
            taskArray = temp;
        }
        taskArray[curr] = task;
        ++curr;
    }

    @Override
    public boolean remove(Task task) {
        int index = findTask(task);
        if (task == null || index == -1) {
            return false;
        }
        for (int i = 0; i < index; ++i) {
            taskArray[i] = taskArray[i];
        }
        for (int i = index + 1; i < curr; ++i) {
            taskArray[i - 1] = taskArray[i];
        }
        --curr;
        return true;
    }

    private int findTask(Task task) {
        for (int i = 0; i < curr; ++i) {
            if (taskArray[i].equals(task)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return curr;
    }

    @Override
    public Task getTask(int index) {
        if (index < 0 || index >= curr) {
            throw new IndexOutOfBoundsException();
        }
        return taskArray[index];
    }

    @Override
    public AbstractTaskList incoming(int from, int to) {
        ArrayTaskList income = new ArrayTaskList();
        for (int i = 0; i < curr; ++i) {
            if (taskArray[i].nextTimeAfter(from) != -1 &&
                    taskArray[i].nextTimeAfter(from) <= to) {

                income.add(taskArray[i]);
            }
        }
        return income;
    }
}
