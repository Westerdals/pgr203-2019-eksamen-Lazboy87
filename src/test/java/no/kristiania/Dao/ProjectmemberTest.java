package no.kristiania.Dao;


import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ProjectmemberTest {

    private JdbcDataSource jdbcDataSource;

    public static ProjectMember sampleMember() {
        ProjectMember member = new ProjectMember();
        member.setName(pickOne(new String[]{"Per", "Arne", "Kristian"}));
        return member;
    }
    private static String pickOne(String[] alternatives){
        Random random = new Random();

        return alternatives[random.nextInt(alternatives.length)];
    }


    @BeforeEach
    void testDataSource() {
    jdbcDataSource = createDataSource();
    }
    @AfterEach
    void teardown() {
        Flyway.configure().dataSource(jdbcDataSource).load().clean();
        Flyway.configure().dataSource(jdbcDataSource).load().migrate();
    }

    static JdbcDataSource createDataSource(){
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Flyway.configure().dataSource(dataSource).load().migrate();
        return dataSource;
    }

    @Test
    void shouldFindmemberinDB() throws SQLException {
        ProjectMember member = new ProjectMember();
        member.setName("Test");
        member.setMail("test@test");
        ProjectMemberDao dao = new ProjectMemberDao(jdbcDataSource);


        dao.insert(member);
        System.out.println(dao.listAll());
        assertThat(dao.listAll()).contains(member);
    }



    @Test
    void shouldSaveAllProductFields() throws SQLException {
        ProjectMemberDao dao = new ProjectMemberDao(jdbcDataSource);
        ProjectMember member = new ProjectMember();
        long id = dao.insert(member);


        assertThat(dao.retrieve(id)).isEqualToComparingFieldByField(member);

    }


}
