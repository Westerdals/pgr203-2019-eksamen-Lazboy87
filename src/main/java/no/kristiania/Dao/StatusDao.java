package no.kristiania.Dao;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StatusDao extends AbstractDao<Status>{
    public StatusDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertMember(Status status, PreparedStatement statement) throws SQLException {

        statement.setString(2, status.getStatusName());
        statement.setInt(1,status.getStatusId());

    }

    @Override
    protected Status readObject(ResultSet resultSet) throws SQLException {
        Status status = new Status();
        status.setStatusName(resultSet.getString(2));
        status.setStatusId(Integer.parseInt(resultSet.getString(1)));
        return status;
    }

    public void insert(Status status) throws SQLException{
        insert(status, "insert into status (status_id,status_cat) values (?,?)");
    }

    public List<Status> listAll() throws SQLException {
        return listAll("select * from status");
    }
}