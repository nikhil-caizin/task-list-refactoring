package com.codurance.training.tasks;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class Tasks {

    static void format(List<Task> tasks, Writer writer) throws IOException {
        for (Task task : tasks) {
            writer.write(String.format("[%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription()));
        }
    }
}
