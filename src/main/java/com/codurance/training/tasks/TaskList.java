package com.codurance.training.tasks;

import java.io.Writer;
import java.util.*;

public final class TaskList {

    private final Projects projects = new Projects();
    private final Writer writer;
    private final IdGenerator idGenerator = new IdGenerator();
    private final CommandFactory commandRegistry = new CommandFactory();

    public TaskList(Writer writer) {
        this.writer = writer;
        registerCommands();
    }

    private void registerCommands() {
        commandRegistry.register("show", new ShowCommand(writer, projects));
        commandRegistry.register("add", new AddCommand(projects, idGenerator));
        commandRegistry.register("check", new CheckCommand(projects, true));
        commandRegistry.register("uncheck", new CheckCommand(projects, false));
    }

    public void execute(String commandLine) throws Exception {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        String args = commandRest.length > 1 ? commandRest[1] : "";
        commandRegistry.get(command).execute(args);
    }
    
}
