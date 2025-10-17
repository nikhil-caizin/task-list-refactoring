package com.codurance.training.tasks;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tasks extends ArrayList<Task> {

    void formatProject(Map<String, List<Task>> projects, Writer writer) throws IOException {
        for (Map.Entry<String, List<Task>> task : projects.entrySet()) {
            writer.write(task.getKey());
            writer.write("\n");
            Tasks tasks = new Tasks();
            tasks.addAll(task.getValue());
            tasks.format(writer);
        }
    }

    void format(Writer writer) throws IOException {
        for (Task task : this) {
            writer.write(String.format("[%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription()));
        }
    }


}
