package edu.javacource.studentorder.dao;

import edu.javacource.studentorder.config.Config;
import edu.javacource.studentorder.domain.*;
import edu.javacource.studentorder.exception.DaoException;

import java.sql.*;
import java.time.LocalDateTime;

public class StudentOrderDaoImpl implements StudentOrderDao {

    private static final String INSERT_ORDER =
            "INSERT INTO public.jc_student_order(" +
                    "student_order_status, student_order_date, h_sur_name, h_given_name, h_patronymic," +
                    " h_date_of_birth, h_passport_seria, h_passport_number, h_passport_date, h_passport_office_id, h_post_index," +
                    " h_street_code, h_building, h_extension, h_apartment, w_sur_name, w_given_name, w_patronymic, w_date_of_birth," +
                    " w_passport_seria, w_passport_number, w_passport_date, w_passport_office_id, w_post_index, w_street_code, w_building," +
                    " w_extension, w_apartment, certificate_id, register_office_id, marriage_date)" +
                    "VALUES (?, ?, ?, ?, ?," +
                    "        ?, ?, ?, ?, ?, ?," +
                    "        ?, ?, ?, ?, ?, ?, ?, ?," +
                    "        ?, ?, ?, ?, ?, ?, ?," +
                    "        ?, ?, ?, ?, ?); ";

    //TODO refactoring - make one method
    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        return con;
    }

    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException {
        Long result = -1L;
        try (Connection con = getConnection()) {

            PreparedStatement stmt = con.prepareStatement(INSERT_ORDER, new String [] {"student_order_id"} );
            //Header
            stmt.setInt(1,StudentOrderStatus.START.ordinal());
            stmt.setTimestamp(2,java.sql.Timestamp.valueOf(LocalDateTime.now()));

            //Husband
            stmt.setString(3,so.getHusband().getSurName());
            stmt.setString(4, so.getHusband().getGivenName());
            stmt.setString(5,so.getHusband().getPatronymic());
            stmt.setDate(6, java.sql.Date.valueOf(so.getHusband().getDateOfBirth()));
            stmt.setString(7,so.getHusband().getPassportSeria());
            stmt.setString(8,so.getHusband().getPassportNumber());
            stmt.setDate(9,java.sql.Date.valueOf(so.getHusband().getIssueDate()));
            stmt.setLong(10,so.getHusband().getIssueDepartment().getOfficeId());
            Address h_address = so.getHusband().getAddress();
            stmt.setString(11,h_address.getPostCode());
            stmt.setLong(12,h_address.getStreet().getStreetCode());
            stmt.setString(13,h_address.getBuilding());
            stmt.setString(14,h_address.getExtension());
            stmt.setString(15,h_address.getApartment());

            //Wife
            stmt.setString(16,so.getWife().getSurName());
            stmt.setString(17, so.getWife().getGivenName());
            stmt.setString(18,so.getWife().getPatronymic());
            stmt.setDate(19, java.sql.Date.valueOf(so.getWife().getDateOfBirth()));
            stmt.setString(20,so.getWife().getPassportSeria());
            stmt.setString(21,so.getWife().getPassportNumber());
            stmt.setDate(22,java.sql.Date.valueOf(so.getWife().getIssueDate()));
            stmt.setLong(23,so.getWife().getIssueDepartment().getOfficeId());
            Address w_address = so.getHusband().getAddress();
            stmt.setString(24,w_address.getPostCode());
            stmt.setLong(25,w_address.getStreet().getStreetCode());
            stmt.setString(26,w_address.getBuilding());
            stmt.setString(27,w_address.getExtension());
            stmt.setString(28,w_address.getApartment());
            //Marriage
            stmt.setString(29,so.getMarriageCertificateId());
            stmt.setLong(30, so.getMarriageOffice().getOfficeId());
            stmt.setDate(31,java.sql.Date.valueOf(so.getMarriageDate()));

            stmt.executeUpdate();

            ResultSet gkRs = stmt.getGeneratedKeys();
            if (gkRs.next()){
                result = gkRs.getLong(1);
            }
            gkRs.close();

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

}
