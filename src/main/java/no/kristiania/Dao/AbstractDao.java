package no.kristiania.Dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> {
    protected DataSource dataSource;

    public AbstractDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(T member, String sql1) throws SQLException {

        try (Connection conn = dataSource.getConnection();) {
            String sql = sql1;
            PreparedStatement statement = conn.prepareStatement(sql);
            insertMember(member, statement);
            statement.executeUpdate();
        }

    }

    protected abstract void insertMember(T o, PreparedStatement statement) throws SQLException;

    public List<T> listAll(String sql) throws SQLException {

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<T> result = new ArrayList<>();

                    while (resultSet.next()) {

                      result.add(readObject(resultSet));


                    }

                    return result;
                }
            }
        }
    }

    protected abstract T readObject(ResultSet resultSet) throws SQLException;
}
