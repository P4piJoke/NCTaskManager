package ua.edu.sumdu.j2se.papizhuk.tasks.model;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Class for storing tasks.
 *
 * @author Danylo Papizhuk
 * @version 1.0.0
 * @see java.util.ArrayList
 * @see AbstractTaskList
 * @since 1.8.0_311
 */
public class ArrayTaskList extends AbstractTaskList {

    private static final Logger log = Logger.getLogger(ArrayTaskList.class);
    private Task[] taskArray;
    private int size;
    private int curr;

    {
        size = 1;
        taskArray = new Task[size];
    }

    /**
     * Adds the task to the array task list.
     *
     * @param task - task to be added
     */
    @Override
    public void add(Task task) {
        log.info("Processing task addition");
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

    /**
     * Deletes the task from the array task list.
     *
     * @param task - task to be deleted
     * @return true if the task was deleted, false if not
     */
    @Override
    public boolean remove(Task task) {
        log.info("Processing task deletion");
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

    /**
     * Returns array task list size.
     *
     * @return size of the array task list
     */
    @Override
    public int size() {
        log.info("Getting array task list size");
        return curr;
    }

    /**
     * Returns task by index.
     *
     * @param index - index to find a task in the array task list
     * @return desired task
     */
    @Override
    public Task getTask(int index) {
        log.info("Processing task taking by index");
        if (index < 0 || index >= curr) {
            throw new IndexOutOfBoundsException();
        }
        return taskArray[index];
    }

    /**
     * Returns the iterator for navigating through the array task list.
     *
     * @return array task list iterator
     */
    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {

            private int currIndex = 0;
            private int lastRemoved = -1;

            @Override
            public boolean hasNext() {
                return currIndex < curr;
            }

            @Override
            public Task next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastRemoved = currIndex;
                return taskArray[currIndex++];
            }

            @Override
            public void remove() {
                if (currIndex < 1) {
                    throw new IllegalStateException();
                }
                ArrayTaskList.this.remove(taskArray[lastRemoved]);
                currIndex--;
            }
        };
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(size, curr);
        hash = 31 * hash + Arrays.hashCode(taskArray);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ArrayTaskList taskList = (ArrayTaskList) obj;
        return size == taskList.size &&
                curr == taskList.curr &&
                Arrays.equals(taskArray, taskList.taskArray);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}