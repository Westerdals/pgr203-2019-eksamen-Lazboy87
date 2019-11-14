
package no.kristiania.Dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import org.assertj.core.api.Assertions.*;

class ProjectMemberHttpControllerTest {
    @Test
    void shouldReturnallMembers() throws SQLException {
        ProjectMemberDao memberDao= new ProjectMemberDao(ProjectmemberTest.createDataSource());
        ProjectMember member = ProjectmemberTest.sampleMember();
        memberDao.insert(member);

        ProjectMemberHttpController controller = new ProjectMemberHttpController(memberDao);
        Assertions.assertThat(controller.getBody())
                .contains("<option value='" + member.getId() + "'>" + member.getName() + "</option>");


    }

    @Test
    void shouldCreateMembers() throws SQLException, IOException {
        ProjectMemberDao memberDao= new ProjectMemberDao(ProjectmemberTest.createDataSource());


        ProjectMemberHttpController controller = new ProjectMemberHttpController(memberDao);

       String requestBody="memberName=NewTestMember&mail=mail@mail";
       controller.handle("POST", "/api/projectMembers",null,requestBody,new ByteArrayOutputStream());

        Assertions.assertThat(memberDao.listAll()).extracting(ProjectMember::getName).contains("NewTestMember");


    }
}

