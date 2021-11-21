package ua.edu.sumdu.j2se.papizhuk.tasks;

public class TaskListFactory {
    
    public static AbstractTaskList createTaskList(ListTypes.types types) {
        switch (types) {
            case ARRAY:
                return new ArrayTaskList();
            case LINKED:
                return new LinkedTaskList();
            default:
                return null;
        }
    }
}
