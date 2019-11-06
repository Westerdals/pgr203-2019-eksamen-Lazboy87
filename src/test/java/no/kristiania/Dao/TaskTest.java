package no.kristiania.Dao;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
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

    @Test
    void shouldFindDaoSIZEandid() throws SQLException {
        Task task = new Task();
        task.setTaskName("Finish Exam");

        TaskDao dao = new TaskDao(jdbcDataSource);

        dao.insert(task);
        dao.insert(task);


        System.out.println(dao.listAll());

        List<Task> listOfTasks = dao.listAll();

        assertThat(listOfTasks.size()).isEqualTo(2);
        assertThat(listOfTasks.get(0).getTaskId()).isEqualTo(1);
        assertThat(listOfTasks.get(1).getTaskId()).isEqualTo(2);


    }
}
