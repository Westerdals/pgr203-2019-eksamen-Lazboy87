package no.kristiania.Dao;



import java.util.Objects;

public class Status {
    private String StatusName;
    private  int statusId ;

    public Status(String statusName, int statusId) {
        StatusName = statusName;
        this.statusId = statusId;
    }

    public Status() {
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
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
        Status status = (Status) o;
        return statusId == status.statusId &&
                Objects.equals(StatusName, status.StatusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(StatusName, statusId);
    }

    @Override
    public String toString() {
        return "Status{" +
                "StatusName='" + StatusName + '\'' +
                ", statusId=" + statusId +
                '}';
    }
}


