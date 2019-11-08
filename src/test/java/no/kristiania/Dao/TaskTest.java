package no.kristiania.Dao;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TaskTest {

    private JdbcDataSource jdbcDataSource;


    @BeforeEach
    void testDataSource() {
        jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Flyway.configure().dataSource(jdbcDataSource).load().migrate();
    }

    @AfterEach
    void teardown() {
        jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");

        Flyway.configure().dataSource(jdbcDataSource).load().clean();
        Flyway.configure().dataSource(jdbcDataSource).load().migrate();
    }

    @Test
    void shouldFindTasknameandid() throws SQLException {
        Task task = new Task();
        task.setName("Finish Exam");


        TaskDao dao = new TaskDao(jdbcDataSource);

        dao.insert(task);



        System.out.println(dao.listAll());
        long id = dao.insert(task);


        assertThat(dao.retrieve(id)).isEqualToComparingFieldByField(task);
    }

    @Test
    void shouldFindTaskinDB() throws SQLException {
        Task task = new  Task();
        task.setName("Test");

        TaskDao dao = new TaskDao(jdbcDataSource);


        dao.insert(task);
        System.out.println(dao.listAll());
        assertThat(dao.listAll()).contains(task);
    }
}

