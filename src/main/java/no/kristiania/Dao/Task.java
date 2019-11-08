package no.kristiania.Dao;

import java.util.Objects;

public class Task {
    public Task(){


    }
    private long id;
    private long statusId= 1;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                statusId == task.statusId &&
                Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusId, name);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + id +
                ", statusId=" + statusId +
                ", taskName='" + name + '\'' +
                '}';
    }
}
