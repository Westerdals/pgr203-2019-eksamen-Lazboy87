package no.kristiania.Dao;

import java.util.Objects;

public class ProjectMember {

    private long id;
    private String name;
    private String mail;

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public ProjectMember() {


    }

    public ProjectMember(String name, String mail) {
        this.name = name;
        this.mail = mail;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectMember member = (ProjectMember) o;
        return id == member.id &&
                Objects.equals(name, member.name) &&
                Objects.equals(mail, member.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mail);
    }

    @Override
    public String toString() {
        return "ProjectMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
