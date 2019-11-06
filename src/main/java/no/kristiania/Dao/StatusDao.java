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

public class StatusDao extends AbstractDao<Status> {

    public StatusDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertMember(Status o, PreparedStatement statement) throws SQLException {

    }


    @Override
    protected void insertStatus(Status status, PreparedStatement statement) throws SQLException {
        statement.setString(1, status.getStatusName());

    }

    @Override
    protected Status readObject(ResultSet resultSet) throws SQLException {
        Status project = new Status();
        project.setProjectName(resultSet.getString(1));
        return project;
    }


    public void insertStatus( Status status) throws SQLException{
        insert(status, "insert into status (name) values (?)");
    }

    public List<Status> listAllStatus() throws SQLException {
        return listAll("select * from status");
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

        StatusDao status = new StatusDao()(dataSource);

        Status project = new Status();
        project.setStatusName(inputName);

        Status.insert(status);
        System.out.println(.listAllProjects());
    }
}


