package no.kristiania.Dao;

import javax.sql.DataSource;
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


    public void insert(Project project) throws SQLException{
        insert(project, "insert into projects (project_name) values (?)");
    }

    public List<Project> listAll() throws SQLException {
        return listAll("select * from projects");
    }
}