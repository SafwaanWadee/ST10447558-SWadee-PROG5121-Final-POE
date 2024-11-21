/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.progfinalpoe;
import com.yourpackage.tasktests.Task;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author Safwaan
 */
public class ST10447558_PROG5121_POE_SafwaanWadee {
    // Store user credentials
    private static String registeredUsername;
    private static String registeredPassword;
    private static String firstName;
    private static String lastName;

    // Arrays to store task data
    private static final List<Task> tasks = new ArrayList<>();
    
    // Test data initialization
    private static String[] developers = new String[4];
    private static String[] taskNames = new String[4];
    private static String[] taskIDs = new String[4];
    private static int[] taskDurations = new int[4];
    private static String[] taskStatuses = new String[4];

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        // User Registration
        registerUser();
        // User Login
        if (loginUser()) {
            JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
            populateTestData(); // Populate the arrays with test data
            manageTasks();
        } else {
            JOptionPane.showMessageDialog(null, "Login failed.");
        }
    }

    // Method for user registration
    public static void registerUser() {
        boolean validInput = false;

        // Username input and validation
        while (!validInput) {
            String username = JOptionPane.showInputDialog("Enter username:");
            if (checkUserName(username)) {
                registeredUsername = username;
                JOptionPane.showMessageDialog(null, "Username successfully captured.");
                validInput = true;
            } else {
                JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }

        validInput = false;

        // Password input and validation
        while (!validInput) {
            String password = JOptionPane.showInputDialog("Enter password:");
            if (checkPasswordComplexity(password)) {
                registeredPassword = password;
                JOptionPane.showMessageDialog(null, "Password successfully captured.");
                validInput = true;
            } else {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.");
            }
        }

        // First name and last name input
        firstName = JOptionPane.showInputDialog("Enter your first name:");
        lastName = JOptionPane.showInputDialog("Enter your last name:");

        JOptionPane.showMessageDialog(null, "Registration successful.");
    }

    // Method for user login
    public static boolean loginUser() {
        String username = JOptionPane.showInputDialog("Enter your username:");
        String password = JOptionPane.showInputDialog("Enter your password:");

        return username.equals(registeredUsername) && password.equals(registeredPassword);
    }

    // Method to populate arrays with test data
    public static void populateTestData() {
        // Test Data Task 1
        developers[0] = "Mike Smith";
        taskNames[0] = "Create Login";
        taskDurations[0] = 5;
        taskStatuses[0] = "To Do";
        taskIDs[0] = "CR:1:ITH";

        // Test Data Task 2
        developers[1] = "Edward Harrison";
        taskNames[1] = "Create Add Features";
        taskDurations[1] = 8;
        taskStatuses[1] = "Doing";
        taskIDs[1] = "CR:2:SON";

        // Test Data Task 3
        developers[2] = "Samantha Paulson";
        taskNames[2] = "Create Reports";
        taskDurations[2] = 2;
        taskStatuses[2] = "Done";
        taskIDs[2] = "CR:3:SON";

        // Test Data Task 4
        developers[3] = "Glenda Oberholzer";
        taskNames[3] = "Add Arrays";
        taskDurations[3] = 11;
        taskStatuses[3] = "To Do";
        taskIDs[3] = "AD:4:ZER";
    }

    // Method to manage tasks after login
    public static void manageTasks() {
        while (true) {
            String choice = JOptionPane.showInputDialog("Choose an option:\n1) Show tasks with 'Done' status\n2) Show longest task duration\n3) Search task by name\n4) Search tasks by developer\n5) Delete task by name\n6) Show report\n7) Quit");

            switch (choice) {
                case "1" -> showTasksWithStatus("Done");
                case "2" -> showLongestTaskDuration();
                case "3" -> searchTaskByName();
                case "4" -> searchTasksByDeveloper();
                case "5" -> deleteTaskByName();
                case "6" -> displayFullTaskReport();
                case "7" -> {
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    return;
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid choice, please try again.");
            }
        }
    }

    // Method to display tasks with status "Done"
    public static void showTasksWithStatus(String status) {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < taskNames.length; i++) {
            if (taskStatuses[i].equals(status)) {
                report.append("Developer: ").append(developers[i])
                        .append(" | Task Name: ").append(taskNames[i])
                        .append(" | Duration: ").append(taskDurations[i])
                        .append(" hours\n");
            }
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    // Method to display the developer and task duration for the longest task
    public static void showLongestTaskDuration() {
        int maxDuration = 0;
        String developer = "";
        String taskName = "";
        for (int i = 0; i < taskDurations.length; i++) {
            if (taskDurations[i] > maxDuration) {
                maxDuration = taskDurations[i];
                developer = developers[i];
                taskName = taskNames[i];
            }
        }
        JOptionPane.showMessageDialog(null, "Longest Task: " + taskName + " | Developer: " + developer + " | Duration: " + maxDuration + " hours");
    }

    // Method to search for a task by its name
    public static void searchTaskByName() {
        String taskNameToSearch = JOptionPane.showInputDialog("Enter task name to search:");
        for (int i = 0; i < taskNames.length; i++) {
            if (taskNames[i].equalsIgnoreCase(taskNameToSearch)) {
                JOptionPane.showMessageDialog(null, "Task Name: " + taskNames[i] + " | Developer: " + developers[i] + " | Status: " + taskStatuses[i]);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    // Method to search tasks by developer name
    public static void searchTasksByDeveloper() {
        String developerToSearch = JOptionPane.showInputDialog("Enter developer name to search for tasks:");
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < developers.length; i++) {
            if (developers[i].equalsIgnoreCase(developerToSearch)) {
                report.append("Task Name: ").append(taskNames[i])
                        .append(" | Status: ").append(taskStatuses[i])
                        .append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, report.length() > 0 ? report.toString() : "No tasks found for this developer.");
    }

    // Method to delete a task by its name
    public static void deleteTaskByName() {
        String taskNameToDelete = JOptionPane.showInputDialog("Enter task name to delete:");
        for (int i = 0; i < taskNames.length; i++) {
            if (taskNames[i].equalsIgnoreCase(taskNameToDelete)) {
                taskNames[i] = null;
                developers[i] = null;
                taskDurations[i] = 0;
                taskStatuses[i] = null;
                taskIDs[i] = null;
                JOptionPane.showMessageDialog(null, "Task successfully deleted.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    // Method to show full report of all tasks
    public static void displayFullTaskReport() {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < taskNames.length; i++) {
            if (taskNames[i] != null) {
                report.append("Task Name: ").append(taskNames[i])
                        .append(" | Developer: ").append(developers[i])
                        .append(" | Status: ").append(taskStatuses[i])
                        .append(" | Duration: ").append(taskDurations[i])
                        .append(" hours\n");
            }
        }
        JOptionPane.showMessageDialog(null, report.length() > 0 ? report.toString() : "No tasks available.");
    }

    // Username check for format
    public static boolean checkUserName(String username) {
        // Username must contain an underscore and be no more than 5 characters
        return username.contains("_") && username.length() <= 5;
    }

    // Password complexity check
    public static boolean checkPasswordComplexity(String password) {
        return password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*[0-9].*")
                && password.matches(".*[!@#$%^&*].*");
    }
}