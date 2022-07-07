package edu.javacource.studentorder.dao;

import edu.javacource.studentorder.domain.Street;
import edu.javacource.studentorder.exception.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl  implements DictionaryDao{

    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql:\\localhost:5432/jc_student",
                "postgres","postgres");
        return con;
    }
    public List<Street> findStreets(String pattern) throws DaoException {

        List<Street> result = new LinkedList<>();

        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT street_code, street_name FROM jc_street WHERE UPPER(street_name) LIKE UPPER('%" + pattern + "%')";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Street str = new Street(rs.getLong("street_code"), rs.getString("street_name"));
                result.add(str);
            }
        }catch (SQLException ex){
            throw  new DaoException(ex);
        }
        return result;
    }
}
