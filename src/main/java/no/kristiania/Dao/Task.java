package no.kristiania.Dao;

import java.util.Objects;

public class Task {
    public Task() {


    }

    private long id;
    private long statusId;
    private String statusName;
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
                Objects.equals(statusName, task.statusName) &&
                Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusId, statusName, name);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", statusId=" + statusId +
                ", statusName='" + statusName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
