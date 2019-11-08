package no.kristiania.Dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProjectDao extends AbstractDao<Project>{

    public ProjectDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertMember(Project project, PreparedStatement statement) throws SQLException {
        statement.setString(1, project.getName());

    }

    @Override
    protected Project readObject(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setName(resultSet.getString(2));
        project.setId(Integer.parseInt(resultSet.getString(1)));
        return project;
    }



    public long insert(Project project) throws SQLException{
        long id = insert(project, "insert into projects (project_name) values (?)");
        project.setId((int)id);
        return id;
    }

    public List<Project> listAll() throws SQLException {
        return listAll("select * from projects");
    }
    public Project retrieve(long id) throws SQLException{
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from projects where id = ?")) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if(resultSet.next()) {
                        return (readObject(resultSet));
                    } else {
                        return null;
                    }
                }
            }
        }
    }
}