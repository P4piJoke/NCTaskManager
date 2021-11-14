package ua.edu.sumdu.j2se.papizhuk.tasks;

public class LinkedTaskList {
    private int size;
    private Node first;
    private Node last;

    private class Node {
        Task task;
        Node next;
        Node prev;

        Node(Node prev, Task t, Node next) {
            task = t;
            this.next = next;
            this.prev = prev;
        }
    }

    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException();
        }
        Node l = last;
        Node newNode = new Node(l, task, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public boolean remove(Task task) {
        for (Node temp = first; temp != null; temp = temp.next) {
            if (task.equals(temp.task)) {
                removeTask(temp);
                return true;
            }
        }
        return false;
    }

    private void removeTask(Node link) {
        Node next = link.next;
        Node prev = link.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            link.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            link.next = null;
        }

        link.task = null;
        size--;
    }

    public int size() {
        return size;
    }

    public Task getTask(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return task(index).task;
    }

    private Node task(int index) {
        Node t;
        if (index < (size >> 1)) {
            t = first;
            for (int i = 0; i < index; i++) {
                t = t.next;
            }
        } else {
            t = last;
            for (int i = size - 1; i > index; --i) {
                t = t.prev;
            }
        }
        return t;
    }

    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList ltl = new LinkedTaskList();
        Node curr = this.first;
        while (curr != null) {
            if (curr.task.nextTimeAfter(from) != -1 &&
                    curr.task.nextTimeAfter(from) <= to) {
                ltl.add(curr.task);
            }
            curr = curr.next;
        }
        return ltl;
    }
}
