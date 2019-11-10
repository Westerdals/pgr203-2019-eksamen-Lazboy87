package no.kristiania.Dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectMemberHttpControllerTest {
    @Test
    void shouldReturnallMembers() {
        ProjectMemberDao memberDao= new ProjectMemberDao(ProjectmemberTest.jdbcDataSource());

    }
}