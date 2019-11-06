package no.kristiania.Dao;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class StatusDaoTest {

    private JdbcDataSource jdbcDataSource;


    @BeforeEach
    void testDataSource() {
        jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Flyway.configure().dataSource(jdbcDataSource).load().migrate();
    }

    @Test
    void shouldFindStatusnameandid() throws SQLException {
        Status status = new Status();
        status.setStatusName("Finish Exam");
        status.setStatusId(2);

      StatusDao dao = new StatusDao(jdbcDataSource);


        dao.insert(status);
        System.out.println(dao.listAll());
        assertThat(dao.listAll()).contains(status);
    }

}