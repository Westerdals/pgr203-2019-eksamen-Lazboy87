package no.kristiania.Dao;

import java.util.Objects;

public class Project {
    private String projectname;
    private  int projectid;

    public Project (){}

    public Project(String name, Integer id) {
        this.projectname = name;
        this.projectid = id;
    }

    public String getProjectname() {
        return projectname;
    }

    public int getid() {
        return projectid;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectid == project.projectid &&
                Objects.equals(projectname, project.projectname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectname, projectid);
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectname='" + projectname + '\'' +
                ", projectid=" + projectid +
                '}';
    }
}