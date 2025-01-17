package no.kristiania.Dao;

import org.assertj.core.api.AssertionsForClassTypes;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class StatusTest {

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
    void shouldFindStatusnameandid() throws SQLException {
        Status status = new Status();
        status.setName("Finish Exam");


      StatusDao dao = new StatusDao(jdbcDataSource);

        dao.insert(status);



        System.out.println(dao.listAll());
        long id = dao.insert(status);


        assertThat(dao.retrieve(id)).isEqualToComparingFieldByField(status);
    }

    @Test
    void shouldFindStatusinDB() throws SQLException {
        Status status = new  Status();
        status.setName("Test");

        StatusDao dao = new StatusDao(jdbcDataSource);


        dao.insert(status);
        System.out.println(dao.listAll());
        assertThat(dao.listAll()).contains(status);
    }

}