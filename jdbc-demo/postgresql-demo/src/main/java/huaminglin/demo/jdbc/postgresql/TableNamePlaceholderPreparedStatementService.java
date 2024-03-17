package huaminglin.demo.jdbc.postgresql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Service
public class TableNamePlaceholderPreparedStatementService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public Integer execute() {
        // org.postgresql.util.PSQLException is generated.
        // serverError: ERROR: syntax error at or near "$1"   Position: 22
        // SQLState: 42601
        //
        // PreparedStatement can't support table name as a placeholder.
        PreparedStatementCreator psc = connection -> {
            PreparedStatement ps = connection.prepareStatement("select count(*) from ?");
            ps.setString(1, "my_value");
            return ps;
        };
        PreparedStatementCallback<Integer> action = new PreparedStatementCallback<Integer>() {
            public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ResultSet rs = ps.executeQuery();
                rs.next();
                return rs.getInt(1);
            }
        };
        return jdbcTemplate.execute(psc, action);
    }
}
