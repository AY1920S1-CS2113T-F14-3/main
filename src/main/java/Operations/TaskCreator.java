package Operations;

import CustomExceptions.RoomShareException;
import Enums.Priority;
import Enums.RecurrenceScheduleType;
import Enums.TimeUnit;
import Model_Classes.Assignment;
import Model_Classes.Meeting;
import Model_Classes.Task;

import java.util.Date;

public class TaskCreator {
    private Parser parser = new Parser();

    public TaskCreator() {
    }
    public Task create(String input) {
        Priority priorityType;
        RecurrenceScheduleType recurrenceScheduleType;
        String assignee;
        // extract the description
        String[] descriptionArray = input.split("\\(");
        String[] descriptionArray2 = descriptionArray[1].trim().split("\\)");
        String description = descriptionArray2[0].trim();
        // extract the priority
        String[] priorityArray = input.split("\\*");
        if (priorityArray.length != 1) {
            String priority = priorityArray[1].trim();
            try {
                priorityType = Priority.valueOf(priority);
            } catch (IllegalArgumentException e) {
                System.out.println("There seems to some mistake in your priority entry, will be setting priority as low");
                priorityType = Priority.low;
            }
        } else {
            priorityType = Priority.low;
        }
        // extract the assignee
        String[] assigneeArray = input.split("@");
        if (assigneeArray.length != 1) {
            assignee = assigneeArray[1].trim();
        } else {
            assignee = null;
        }
        // extract recurrence schedule
        String[] recurrenceArray = input.split("%");
        if (recurrenceArray.length != 1) {
            String recurrence = recurrenceArray[1].trim();
            try {
                recurrenceScheduleType = RecurrenceScheduleType.valueOf(recurrence);
            } catch (IllegalArgumentException e) {
                System.out.println("There seems to some mistake in your recurrence entry, will be setting recurrence as none");
                recurrenceScheduleType = RecurrenceScheduleType.none;
            }
        } else {
            recurrenceScheduleType = RecurrenceScheduleType.none;
        }
        // extract the Task Type
        String[] type = input.split("#");
        // extract the time
        String[] timeArray = input.split("&");
        if (timeArray.length != 1) {
            // not a fixed duration task
            String time = timeArray[1].trim();
            // create the time
            Date by;
            try {
                by = parser.formatDate(time);
            } catch (RoomShareException e) {
                by = new Date();
            }
            if (type[1].contains("assignment")) {
                Assignment createdTask = new Assignment(description, by);
                createdTask.setPriority(priorityType);
                createdTask.setUser(assignee);
                createdTask.setRecurrenceSchedule(recurrenceScheduleType);
                return createdTask;
            } else {
                Meeting createdTask = new Meeting(description, by);
                createdTask.setPriority(priorityType);
                createdTask.setUser(assignee);
                createdTask.setRecurrenceSchedule(recurrenceScheduleType);
                return createdTask;
            }
        } else {
            // fixed duration task
            // extract the fixed duration timing
            String[] fixedDurationArray = input.split("\\^");
            String[] tempArray = fixedDurationArray[1].split("\\s+");
            String duration = tempArray[0].trim();
            TimeUnit unit = TimeUnit.valueOf(tempArray[1].toLowerCase().trim());
            if (type[1].contains("assignment")) {
                Assignment assignment = new Assignment(description, duration, unit);
                assignment.setPriority(priorityType);
                assignment.setUser(assignee);
                assignment.setRecurrenceSchedule(recurrenceScheduleType);
                return assignment;
            } else {
                Meeting meeting = new Meeting(description, duration, unit);
                meeting.setPriority(priorityType);
                meeting.setUser(assignee);
                meeting.setRecurrenceSchedule(recurrenceScheduleType);
                return meeting;
            }
        }
    }
}