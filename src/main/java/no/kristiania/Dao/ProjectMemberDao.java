package no.kristiania.Dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProjectMemberDao extends AbstractDao<ProjectMember> {

    public ProjectMemberDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertMember(ProjectMember member, PreparedStatement statement) throws SQLException {
        statement.setString(1, member.getName());
        statement.setString(2, member.getMail());

    }

    @Override
    protected ProjectMember readObject(ResultSet resultSet) throws SQLException {
        ProjectMember member = new ProjectMember();

        member.setId(resultSet.getInt(1));
        member.setName(resultSet.getString(2));
        member.setMail(resultSet.getString(3));
        return member;
    }


    public long insert(ProjectMember projectMember) throws SQLException {
        long id = insert(projectMember, "insert into projectmembers (name,email) values (?,?)");
        projectMember.setId(id);
        return id;
    }

    public List<ProjectMember> listAll() throws SQLException {
        return listAll("select * from projectmembers");
    }

    public ProjectMember retrieve(long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from projectmembers where id = ?")) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return (readObject(resultSet));
                    } else {
                        return null;
                    }
                }
            }
        }
    }


}


