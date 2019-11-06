package no.kristiania.Dao;

import java.util.Objects;

public class Task {
    public Task(){


    }
    private int taskId;
    private int statusId= 1;
    private String taskName;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }




    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId == task.taskId &&
                statusId == task.statusId &&
                Objects.equals(taskName, task.taskName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, statusId, taskName);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", statusId=" + statusId +
                ", taskName='" + taskName + '\'' +
                '}';
    }
}
