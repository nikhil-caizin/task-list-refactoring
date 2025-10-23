package com.codurance.training.tasks;

public interface Command {

    public void execute(String arguments) throws Exception;
}
