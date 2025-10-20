package com.codurance.training.tasks;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class Projects extends LinkedHashMap<String, Tasks>{

    void formatProjects(Writer writer) throws IOException {
        for (Map.Entry<String, Tasks> entry : this.entrySet()) {
            writer.write(entry.getKey());
            writer.write("\n");
            Tasks tasks = new Tasks();
            tasks.addAll(entry.getValue());
            tasks.formatTasks(writer);
        }
    }

}
