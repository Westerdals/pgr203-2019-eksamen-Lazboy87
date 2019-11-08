package no.kristiania.Dao;

import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class TaskManagement {
    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("enter a member name to insert: ");
        String inputName = new Scanner(System.in).nextLine();

        System.out.println("enter a email to " + inputName + ": ");
        String inputMail = new Scanner(System.in).nextLine();

        Properties properties = new Properties();
        properties.load(new FileReader("task-manager.properties"));

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(properties.getProperty("dataSource.url"));
        dataSource.setUser(properties.getProperty("dataSource.username"));
        dataSource.setPassword(properties.getProperty("dataSource.password"));

        Flyway.configure().dataSource(dataSource).load().migrate();

        ProjectMemberDao memberDao = new ProjectMemberDao(dataSource);

        ProjectMember member = new ProjectMember();
        member.setName(inputName);
        member.setMail(inputMail);
        memberDao.insert(member);
        System.out.println(memberDao.listAll());
    }
}
