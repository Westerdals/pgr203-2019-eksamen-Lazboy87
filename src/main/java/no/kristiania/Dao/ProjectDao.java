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

public class ProjectDao extends AbstractDao<Project> {

    public ProjectDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertMember(Project o, PreparedStatement statement) throws SQLException {

    }


    @Override
    protected void insertProject(Project project, PreparedStatement statement) throws SQLException {
        statement.setString(1, project.getProjectName());

    }

    @Override
    protected Project readObject(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setProjectName(resultSet.getString(1));
        return project;
    }


    public void insert(Project project) throws SQLException{
        insert(project, "insert into projects (name) values (?)");
    }

    public List<Project> listAllProjects() throws SQLException {
        return listAll("select * from projects");
    }


    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("enter a member name to insert: ");
        String inputName = new Scanner(System.in).nextLine();

        Properties properties = new Properties();
        properties.load(new FileReader("task-manager.properties"));

        PGSimpleDataSource dataSource= new PGSimpleDataSource();
        dataSource.setURL(properties.getProperty("dataSource.url"));
        dataSource.setUser(properties.getProperty("dataSource.username"));
        dataSource.setPassword(properties.getProperty("dataSource.password"));

        Flyway.configure().dataSource(dataSource).load().migrate();

        ProjectDao projectDao = new ProjectDao(dataSource);

        Project project = new Project();
        project.setProjectName(inputName);

        projectDao.insert(project);
        System.out.println(projectDao.listAllProjects());
    }
}


