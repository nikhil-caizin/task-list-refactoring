package com.codurance.training.tasks;

import java.util.Map;

public class CheckCommand implements Command{

    private final Projects projects;
    private final boolean done;

    public CheckCommand(Projects projects, boolean done) {
        this.projects = projects;
        this.done = done;
    }

    @Override
    public void execute(String arguments) throws Exception {

        int id = Integer.parseInt(arguments);
        for (Map.Entry<String, Tasks> project : projects.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        System.out.println("Could not find a task with id : " + id);
    }
}
