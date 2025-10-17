package com.codurance.training.tasks;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class Tasks {

    static void format(List<Task> tasks, Writer writer) throws IOException {
        for (Task task : tasks) {
            writer.write(String.format("[%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription()));
        }
    }

    static void formatProject(Writer writerProject) throws IOException {
        for (Map.Entry<String, List<Task>> project : TaskList.tasks.entrySet()) {
            writerProject.write(project.getKey());
            writerProject.write("\n");
            format(project.getValue(), writerProject);
        }
    }
}
