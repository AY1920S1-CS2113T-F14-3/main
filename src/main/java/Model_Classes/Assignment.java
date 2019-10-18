package Model_Classes;

import Enums.Priority;
import Enums.TimeUnit;

import java.util.Date;

/**
 * An object class representing types of tasks with deadlines.
 * Stores the description and when the task should be done by.
 */
public class Assignment extends Task {
    private Date by;
    private boolean isFixedDuration;
    private String duration;
    private TimeUnit timeUnit;
    /**
     * Constructor for the Deadline object.
     * Takes in inputs for description and date/time the tasks should be done by.
     * @param description Description of the task
     * @param by The time the tasks should be done by.
     */
    public Assignment (String description, Date by) {
        super(description);
        this.by = by;
        this.isFixedDuration = false;
    }

    public Assignment (String description, String duration, TimeUnit unit) {
        super(description);
        this.duration = duration;
        this.timeUnit = unit;
        this.isFixedDuration = true;
    }


    public void snoozeMonth(int amount) {
        this.by.setMonth(this.by.getMonth() + amount);;
    }

    public void snoozeDay(int amount) {
        this.by.setDate(this.by.getDate() + amount);;
    }

    public void snoozeHour(int amount){
        this.by.setHours(this.by.getHours() + amount);
    }

    public void snoozeMinute(int amount){
        this.by.setMinutes(this.by.getMinutes() + amount);
    }

    /**
     * Returns the full description including the deadline of the task.
     * @return A string indicating the task type, description, and when it should be done by.
     */
    @Override
    public String toString() {
        if (isFixedDuration) {
            return "[A]" + super.toString() + " (in: " + duration + " " + timeUnit.toString() + ")";
        } else {
            return "[A]" + super.toString() + " (by: " + by + ")";
        }
    }

    public boolean isFixedDuration() {
        return isFixedDuration;
    }

    public String getDuration() {
        return duration;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public Date getBy() {
        return by;
    }

}