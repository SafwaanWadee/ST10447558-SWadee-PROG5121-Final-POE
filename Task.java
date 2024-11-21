/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yourpackage.tasktests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Safwaan
 */
 //Task class for task management
public class Task {
    private String taskName;
    private String taskID;
    private String developer;
    private int duration;
    private String status;

    // Constructor
    public Task(String taskName, String taskID, String developer, int duration, String status) {
        this.taskName = taskName;
        this.taskID = taskID;
        this.developer = developer;
        this.duration = duration;
        this.status = status;
    }

    // Getters & Setters
    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }
    public String getTaskID() { return taskID; }
    public void setTaskID(String taskID) { this.taskID = taskID; }
    public String getDeveloper() { return developer; }
    public void setDeveloper(String developer) { this.developer = developer; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Method to delete task info
    public void deleteTask() {
        this.taskName = null;
        this.taskID = null;
        this.developer = null;
        this.duration = 0;
        this.status = null;
    }

    // Method to display task info
    @Override
    public String toString() {
        return "Task Name: " + taskName + " | Task ID: " + taskID + " | Developer: " + developer + 
               " | Duration: " + duration + " hours | Status: " + status;
    }

    /**
     * Unit Tests for Task Class
     */
    public static class TaskTest {
        private List<Task> tasks;

        @BeforeEach
        public void setUp() {
            tasks = new ArrayList<>();
            tasks.add(new Task("Create Login", "CR:1:ITH", "Mike Smith", 5, "To Do"));
            tasks.add(new Task("Create Add Features", "CR:2:SON", "Edward Harrison", 8, "Doing"));
            tasks.add(new Task("Create Reports", "CR:3:SON", "Samantha Paulson", 2, "Done"));
            tasks.add(new Task("Add Arrays", "AD:4:ZER", "Glenda Oberholzer", 11, "To Do"));
        }

        // Test: Developer array correctly populated with the expected test data
        @Test
        public void testDeveloperArrayCorrectlyPopulated() {
            String[] expectedDevelopers = {"Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"};
            for (int i = 0; i < tasks.size(); i++) {
                assertEquals(expectedDevelopers[i], tasks.get(i).getDeveloper(), 
                             "Developer array contains incorrect data");
            }
        }

        // Test: Display developer and duration for the longest task
        @Test
        public void testLongestTaskDuration() {
            Task longestTask = tasks.stream().max((t1, t2) -> Integer.compare(t1.getDuration(), t2.getDuration())).orElse(null);
            assertNotNull(longestTask, "No tasks found");
            assertEquals("Glenda Oberholzer", longestTask.getDeveloper(), "Longest task developer mismatch");
            assertEquals(11, longestTask.getDuration(), "Longest task duration mismatch");
        }

        // Test: Search for tasks by name
        @Test
        public void testSearchTaskByName() {
            String taskNameToSearch = "Create Login";
            Task foundTask = tasks.stream().filter(task -> task.getTaskName().equalsIgnoreCase(taskNameToSearch)).findFirst().orElse(null);
            assertNotNull(foundTask, "Task not found");
            assertEquals("Mike Smith", foundTask.getDeveloper(), "Developer mismatch for searched task");
            assertEquals("Create Login", foundTask.getTaskName(), "Task name mismatch for searched task");
        }

        // Test: Search all tasks assigned to developer
        @Test
        public void testSearchTasksByDeveloper() {
            String developerToSearch = "Samantha Paulson";
            List<Task> developerTasks = tasks.stream().filter(task -> task.getDeveloper().equalsIgnoreCase(developerToSearch)).toList();
            assertFalse(developerTasks.isEmpty(), "No tasks found for the developer");
            assertEquals(1, developerTasks.size(), "Incorrect number of tasks for the developer");
            assertEquals("Create Reports", developerTasks.get(0).getTaskName(), "Task mismatch for the developer");
        }

        // Test: Delete a task from array by name
        @Test
        public void testDeleteTaskByName() {
            String taskNameToDelete = "Create Reports";
            Task taskToDelete = tasks.stream().filter(task -> task.getTaskName().equalsIgnoreCase(taskNameToDelete)).findFirst().orElse(null);
            assertNotNull(taskToDelete, "Task to delete not found");
            tasks.remove(taskToDelete);
            assertTrue(tasks.stream().noneMatch(task -> task.getTaskName().equalsIgnoreCase(taskNameToDelete)), 
                       "Task was not successfully deleted");
        }
    }
}

