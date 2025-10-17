package com.codurance.training.tasks;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class TasksTest extends TestCase {

    @Test
    public void testFormat_SingleTaskOutputIsCorrect() throws IOException {

        List<Task> tasks = List.of(new Task(10, "Refactor code", true));
        StringWriter writer = new StringWriter();

        Tasks.format(tasks, writer);

        assertEquals("[x] 10: Refactor code" + System.lineSeparator(), writer.toString());
    }

    @Test
    public void testFormat_AllTasksDone() throws IOException {
        List<Task> tasks = List.of(
                new Task(1, "Submit report", true),
                new Task(2, "Review code", true)
        );
        StringWriter writer = new StringWriter();

        Tasks.format(tasks, writer);

        String expected = "[x] 1: Submit report" + System.lineSeparator() +
                "[x] 2: Review code" + System.lineSeparator();
        assertEquals(expected, writer.toString());
    }

    @Test
    public void testFormat_AllTasksPending() throws IOException {
        List<Task> tasks = List.of(
                new Task(1, "Refactoring", false),
                new Task(2, "Review", false)
        );
        StringWriter writer = new StringWriter();

        Tasks.format(tasks,writer);

        String expected = "[ ] 1: Refactoring" + System.lineSeparator() +
                "[ ] 2: Review" + System.lineSeparator();
        assertEquals(expected, writer.toString());
    }

    @Test
    public void testFormatProject_SingleProjectWithTasks() throws IOException {
        List<Task> projectTasks = new ArrayList<>();
        projectTasks.add(new Task(1, "Do homework", true));
        projectTasks.add(new Task(2, "Wash dishes", false));
        TaskList.tasks.put("Home", projectTasks);

        StringWriter writer = new StringWriter();

        Tasks.formatProject(writer);

        String expected =
                "Home" + System.lineSeparator() +
                        "[x] 1: Do homework" + System.lineSeparator() +
                        "[ ] 2: Wash dishes" + System.lineSeparator();

        assertEquals(expected, writer.toString());
    }

    @Test
    public void testFormatProject_EmptyTasksMap() throws IOException {
        StringWriter writer = new StringWriter();

        Tasks.formatProject(writer);

        assertEquals("", writer.toString());
    }
}