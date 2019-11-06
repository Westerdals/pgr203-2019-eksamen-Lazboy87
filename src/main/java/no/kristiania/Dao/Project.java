package no.kristiania.Dao;



import java.util.Objects;

public class Project {

    private String projectName;
    private static int projectId = 0;



    public static int getId() {
     return projectId;
}



    public static void setProjectId(){
        projectId = 1;
        if (Project.projectId == Project.getId()){
            projectId++;}
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public Project() {
        setProjectId();
    }

    public Project(String projectName, int projectId) {
        this.projectName = projectName;
        this.projectId = projectId;

    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project that = (Project) o;
        return Objects.equals(projectName, that.projectName) &&
                Objects.equals(projectId, that.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName,projectId );
    }

    @Override
    public String toString() {
        return "ProjectMember{" +
                "name='" + projectName + '\'' +
                ", id='" + projectId + '\'' +
                '}';
    }
}
