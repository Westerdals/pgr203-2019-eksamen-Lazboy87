package no.kristiania.Dao;

import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ProjectMemberDao extends AbstractDao<ProjectMember> {

    public ProjectMemberDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertMember(ProjectMember member, PreparedStatement statement) throws SQLException {
        statement.setString(1, member.getName());
        statement.setString(2, member.getMail());
    }

    @Override
    protected ProjectMember readObject(ResultSet resultSet) throws SQLException {
        ProjectMember member = new ProjectMember();
        member.setName(resultSet.getString(1));
        member.setMail(resultSet.getString(2));
        return member;
    }


    public void insert(ProjectMember projectMember) throws SQLException{
        insert(projectMember, "insert into projectmembers (name,email) values (?,?)");
    }

    public List<ProjectMember> listAll() throws SQLException {
        return listAll("select * from projectmembers");
    }


    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("enter a member name to insert: ");
        String projectName = new Scanner(System.in).nextLine();

        System.out.println("enter a email to "+projectName +": ");
        String projectMail = new Scanner(System.in).nextLine();

        Properties properties = new Properties();
        properties.load(new FileReader("task-manager.properties"));

        PGSimpleDataSource dataSource= new PGSimpleDataSource();
        dataSource.setURL(properties.getProperty("dataSource.url"));
        dataSource.setUser(properties.getProperty("dataSource.username"));
        dataSource.setPassword(properties.getProperty("dataSource.password"));
        Flyway.configure().dataSource(dataSource).load().migrate();
        ProjectMemberDao memberDao = new ProjectMemberDao(dataSource);
        memberDao.insert(new ProjectMember(projectName, projectMail));

        System.out.println(memberDao.listAll());
    }
}


