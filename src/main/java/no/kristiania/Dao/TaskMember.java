package no.kristiania.Dao;

import java.util.Objects;

public class TaskMember {
    private long memberId;
    private long taskId = 1;


    public TaskMember() {
        this.memberId = memberId;
        this.taskId = taskId;
    }


    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskMember that = (TaskMember) o;
        return memberId == that.memberId &&
                taskId == that.taskId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, taskId);
    }

    @Override
    public String toString() {
        return "TaskMember{" +
                "memberId=" + memberId +
                ", taskId=" + taskId +
                '}';
    }
}
