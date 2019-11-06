package no.kristiania.Dao;



import java.util.Objects;

public class Status {

    private String StatusName;
    private static int statusId = 0;



    public static int getId() {
        return statusId;
    }



    public static void setStatusId(){
        statusId = 1;
        if (Project.projectId == Project.getId()){
            statusId++;}
    }


    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        this.StatusName = statusName;
    }


    public Status() {
        setStatusId();
    }

    public Status(String statusName, int statusId) {
        this.StatusName = statusName;
        this.statusId = statusId;

    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status that = (Status) o;
        return Objects.equals(StatusName, that.StatusName) &&
                Objects.equals(statusId, that.statusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(StatusName, statusId);
    }

    @Override
    public String toString() {
        return "ProjectMember{" +
                "name='" + StatusName + '\'' +
                ", id='" + statusId + '\'' +
                '}';
    }
}
