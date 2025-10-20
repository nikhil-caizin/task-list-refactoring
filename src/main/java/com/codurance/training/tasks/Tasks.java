package com.codurance.training.tasks;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Tasks extends ArrayList<Task> {

    void formatTasks(Writer writer) throws IOException {
        for (Task task : this) {
            writer.write(format(task));
        }
    }

    private static String format(Task task) {
        return String.format("[%c] %d: %s%n",
                (task.isDone() ? 'x' : ' '),
                task.getId(),
                task.getDescription());
    }
}
