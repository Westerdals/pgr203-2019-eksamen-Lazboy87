package no.kristiania.Dao;


import java.util.Objects;

public class Status {
    private String name;
    private long id;

    public Status(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Status() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return id == status.id &&
                Objects.equals(name, status.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "Status{" +
                "StatusName='" + name + '\'' +
                ", statusId=" + id +
                '}';
    }
}


