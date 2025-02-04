package Week1;

import java.util.*;

class Task {
    String id, description;
    int priority;

    public Task(String id, String description, int priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }

    public String toString() {
        return "ID: " + id + ", Desc: " + description + ", Priority: " + priority + ".";
    }
}

class TaskManager {
    private PriorityQueue<Task> taskQueue;

    public TaskManager() {
        taskQueue = new PriorityQueue<>(Comparator.comparingInt(t -> -t.priority));
    }

    public void addTask(String id, String description, int priority) {
        taskQueue.add(new Task(id, description, priority));
    }

    public void removeTask(String id) {
        taskQueue.removeIf(task -> task.id.equals(id));
    }

    public Task getHighestPriorityTask() {
        return taskQueue.peek();
    }

    public static void main(String[] args) {
        TaskManager tm = new TaskManager();
        tm.addTask("1", "Fix bug", 2);
        tm.addTask("2", "Develop feature", 5);
        tm.addTask("3", "Write tests", 3);

        System.out.println("Highest Priority Task: " + tm.getHighestPriorityTask());
        tm.removeTask("2");
        System.out.println("After removing high-priority task: " + tm.getHighestPriorityTask());
    }
}

