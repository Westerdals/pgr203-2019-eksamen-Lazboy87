package no.kristiania.Dao;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ProjectTest {
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
    void shouldFindProjects() throws SQLException {
        Project project = new Project();
        project.setProjectname("Finish Exam");

        ProjectDao dao = new ProjectDao(jdbcDataSource);


        dao.insert(project);
        System.out.println(dao.listAll());
        assertThat(dao.listAll()).contains(project);
    }
}


