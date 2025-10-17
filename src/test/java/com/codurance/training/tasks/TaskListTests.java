package com.codurance.training.tasks;

import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class TaskListTests {

    @Test
    public void testExecuteWithAdditionOfAProjectContainingOneTask() throws Exception {
        StringWriter writer = new StringWriter();
        TaskList taskList = new TaskList(writer);
        taskList.execute("add project caizin");
        taskList.execute("add task caizin Task1");
        taskList.execute("show");

        String expected = "caizin\n" + "[ ] 1: Task1" + "\n";
        assertEquals(expected, writer.toString());
    }

    @Test
    public void testExecuteWithAdditionOfAProjectContainingACoupleOfTasks() throws Exception {
        StringWriter writer = new StringWriter();
        TaskList taskList = new TaskList(writer);
        taskList.execute("add project caizin");
        taskList.execute("add task caizin Task1");
        taskList.execute("add task caizin Task2");
        taskList.execute("show");

        String expected = "caizin\n" + "[ ] 1: Task1" + "\n" + "[ ] 2: Task2" + "\n";
        assertEquals(expected, writer.toString());
    }

    @Test
    public void testExecuteCheckMarksTaskAsDone() throws Exception{
        StringWriter writer = new StringWriter();
        TaskList taskList = new TaskList(writer);
        taskList.execute("add project refactoring");
        taskList.execute("add task refactoring Formatting");
        taskList.execute("check 1");
        writer.getBuffer().setLength(0);
        taskList.execute("show");

        String expected = "refactoring\n" + "[x] 1: Formatting" + "\n";
        assertEquals("Task should be marked as done after 'check'.", expected, writer.toString());
    }

    @Test
    public void testExecuteCheckOnAlreadyDoneTask() throws Exception{
        StringWriter writer = new StringWriter();
        TaskList taskList = new TaskList(writer);
        taskList.execute("add project tensera");
        taskList.execute("add task tensera deploy");
        taskList.execute("check 1");
        taskList.execute("check 1");
        writer.getBuffer().setLength(0);
        taskList.execute("show");

        String expected = "tensera\n" + "[x] 1: deploy" + "\n";
        assertEquals("Checking an already done task should keep it done", expected, writer.toString());
    }

    @Test
    public void testExecuteUncheckMarksTaskAsUndone() throws Exception{
        StringWriter writer = new StringWriter();
        TaskList taskList = new TaskList(writer);
        taskList.execute("add project tensera");
        taskList.execute("add task tensera Pay bills");
        taskList.execute("check 1"); //mark as done first
        writer.getBuffer().setLength(0);
        taskList.execute("uncheck 1"); //uncheck the task
        taskList.execute("show");

        String expected = "tensera\n" + "[ ] 1: Pay bills" + "\n";
        assertEquals("Task should be marked as undone after 'uncheck'.", expected, writer.toString());
    }

    @Test
    public void testExecuteUncheckOnAlreadyUndoneTask() throws Exception{
        StringWriter writer = new StringWriter();
        TaskList taskList = new TaskList(writer);
        taskList.execute("add project office");
        taskList.execute("add task office code");
        taskList.execute("uncheck 1"); //mark as done first
        taskList.execute("uncheck 1"); //mark as done first
        writer.getBuffer().setLength(0);
        taskList.execute("uncheck 1"); //uncheck the task
        taskList.execute("show");

        String expected = "office\n" + "[ ] 1: code" + "\n";
        assertEquals("Unchecking an already undone task should keep it undone", expected, writer.toString());
    }

}