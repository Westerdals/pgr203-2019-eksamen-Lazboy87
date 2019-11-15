package no.kristiania.Dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaskMemberDao extends AbstractDao<TaskMember>{{


}

    public TaskMemberDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertMember(TaskMember taskMember, PreparedStatement statement) throws SQLException {
        statement.setLong(1, taskMember.getMemberId());
        statement.setLong(2, taskMember.getTaskId());
    }

    @Override
    protected TaskMember readObject(ResultSet resultSet) throws SQLException {
        TaskMember taskMember = new TaskMember();
        taskMember.setTaskId(resultSet.getLong(2));
        taskMember.setMemberId(resultSet.getLong(1));
        return taskMember;
    }

    public List<TaskMember> listAll() throws SQLException {
        return listAll("select * from taskmembers");
    }

    public long insert(TaskMember taskMember) throws SQLException {
        long id = insert(taskMember, "insert into taskmembers (member_id,task_id) values (?,?)");
        taskMember.setMemberId((int) id);
        taskMember.setTaskId((int) id);
        return id;
    }

    public TaskMember retrieveTaskID(long id) throws SQLException{
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from status where id = ?")) {
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
        public TaskMember retrieveMemberId(long id) throws SQLException{
            try (Connection connection = dataSource.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement("select * from status where id = ?")) {
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
