package no.kristiania.Dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StatusDao extends AbstractDao<Status> {
    public StatusDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertMember(Status status, PreparedStatement statement) throws SQLException {

        statement.setString(1, status.getName());


    }

    @Override
    protected Status readObject(ResultSet resultSet) throws SQLException {
        Status status = new Status();
        status.setName(resultSet.getString(2));
        status.setId(resultSet.getInt(1));
        return status;
    }


    public List<Status> listAll() throws SQLException {
        return listAll("select * from status");
    }


    public long insert(Status status) throws SQLException {
        long id = insert(status, "insert into status (status_cat) values (?)");
        status.setId((int) id);
        return id;
    }

    public Status retrieve(long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from status where id = ?")) {
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