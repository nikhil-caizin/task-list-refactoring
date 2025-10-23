package com.codurance.training.tasks;

import java.io.IOException;
import java.io.Writer;

public class ShowCommand implements Command{

    private final Writer writer;
    private final Projects projects;

    public ShowCommand(Writer writer, Projects projects) {
        this.writer = writer;
        this.projects = projects;
    }

    @Override
    public void execute(String arguments) throws Exception {
        projects.formatProjects(writer);
    }
}
