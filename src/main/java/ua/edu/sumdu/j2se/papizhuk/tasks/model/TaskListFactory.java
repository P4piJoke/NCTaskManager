package ua.edu.sumdu.j2se.papizhuk.tasks.model;

/**
 * Abstract Factory.
 *
 * @author Danylo Papizhuk
 * @version 1.0.0
 * @see ListTypes
 * @since 1.8.0_311
 */
public class TaskListFactory {

    /**
     * Returns the object of a certain task list
     * depending on the type of enumeration list.
     *
     * @param types - list enumeration type
     * @return ArrayTaskList object if ARRAY, LinkedTaskList if LINKED
     */
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