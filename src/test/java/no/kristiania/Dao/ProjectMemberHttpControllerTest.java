
package no.kristiania.Dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

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
}

