package ua.edu.sumdu.j2se.papizhuk.tasks;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayTaskList {
    private Task[] taskArray;
    private int size;
    private int curr;

    {
        size = 1;
        taskArray = new Task[size];
    }

    public void add(Task task) {
        if (curr == size) {
            Task[] temp = new Task[size * 2];
            System.arraycopy(taskArray, 0, temp, 0, taskArray.length);
            size *= 2;
            taskArray = temp;
        }
        taskArray[curr] = task;
        ++curr;
    }

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

    public int size() {
        return curr;
    }

    public Task getTask(int index) {
        if (index < 0 || index >= curr) {
            throw new NoSuchElementException();
        }
        return taskArray[index];
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList income = new ArrayTaskList();
        for (int i = 0; i < curr; ++i) {
            //for (int j = from; j < to; ++j){
            if (taskArray[i].nextTimeAfter(from) != -1) {
                income.add(taskArray[i]);
                break;
            }
            //}
        }
        return income;
    }
}
