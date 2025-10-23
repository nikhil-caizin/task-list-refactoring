package com.codurance.training.tasks;

import java.util.List;

public class AddCommand implements Command{

    private final Projects projects;
    private final IdGenerator idGenerator;

    public AddCommand(Projects projects, IdGenerator idGenerator) {
        this.projects = projects;
        this.idGenerator = idGenerator;
    }


    @Override
    public void execute(String arguments) throws Exception {

        String[] subCommandArray = arguments.split(" ", 2);
        String subCommand = subCommandArray[0];
        
        if (subCommand.equals("project")) {

            projects.put(subCommandArray[1], new Tasks());

        }
        else if (subCommand.equals("task")) {

            String[] parts = subCommandArray[1].split(" ", 2);
            String projectName = parts[0];
            String description = parts[1];

            List<Task> projectTasks = projects.get(projectName);

            if(projectTasks == null) {
                throw new IllegalArgumentException("Unknown Project : "+projectName);
            }
            projectTasks.add(new Task(idGenerator.nextId(), description, false));
        }
    }
}
