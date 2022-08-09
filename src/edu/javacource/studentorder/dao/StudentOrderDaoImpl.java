package edu.javacource.studentorder.dao;

import edu.javacource.studentorder.config.Config;
import edu.javacource.studentorder.domain.*;
import edu.javacource.studentorder.exception.DaoException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class StudentOrderDaoImpl implements StudentOrderDao {

    private static final String INSERT_ORDER =
            "INSERT INTO public.jc_student_order(" +
                    " student_order_status, student_order_date, h_sur_name, h_given_name, h_patronymic," +
                    " h_date_of_birth, h_passport_seria, h_passport_number, h_passport_date, h_passport_office_id, h_post_index," +
                    " h_street_code, h_building, h_extension, h_apartment,h_university_id, h_student_number," +
                    " w_sur_name, w_given_name, w_patronymic, w_date_of_birth, w_passport_seria," +
                    " w_passport_number, w_passport_date, w_passport_office_id, w_post_index," +
                    " w_street_code, w_building, w_extension, w_apartment, w_university_id, w_student_number," +
                    " certificate_id, register_office_id, marriage_date)" +
                    "VALUES (?, ?, ?, ?, ?," +
                    "        ?, ?, ?, ?, ?, ?," +
                    "        ?, ?, ?, ?, ?, ?," +
                    "        ?, ?, ?, ?, ?," +
                    "        ?, ?, ?, ?," +
                    "        ?, ?, ?, ?, ?, ?, " +
                    "        ?, ?, ?); ";
    private static final String INSERT_CHILD =
            "INSERT INTO jc_student_child(" +
                    " student_order_id, c_sur_name, c_given_name," +
                    " c_patronymic, c_date_of_birth, c_certificate_number, c_certificate_date," +
                    " c_register_office_id, c_post_index, c_street_code, c_building," +
                    " c_extension, c_apartment)" +
                    "VALUES (?, ?, ?, " +
                    "?, ?, ?, ?," +
                    "?, ?, ?, ?," +
                    " ?, ?); ";
    private static final String SELECT_ORDERS =
            "select so.*, ro.r_office_area_id, ro.r_office_name," +
                    " po_h.p_office_area_id as h_p_office_area_id, po_h.p_office_name as h_p_office_name," +
                    " po_w.p_office_area_id as w_p_office_area_id, po_w.p_office_name as w_p_office_name" +
                    " from jc_student_order as so"+
                    " inner join jc_register_office ro on ro.r_office_id = so.register_office_id" +
                    " inner join jc_passport_office po_h on po_h.p_office_id = so.h_passport_office_id"+
                    " inner join jc_passport_office po_w on po_w.p_office_id = so.w_passport_office_id"+
                    " where student_order_status = ?"+
                    " order by student_order_date";


    //TODO refactoring - make one method
    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        return con;
    }

    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException, SQLException {
        Long result = -1L;
        try (Connection con = getConnection()) {

            PreparedStatement stmt = con.prepareStatement(INSERT_ORDER, new String[]{"student_order_id"});
            con.setAutoCommit(false);

            try{
                //Header
                stmt.setInt(1, StudentOrderStatus.START.ordinal());
                stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));

                //Husband
                setParamsForAdult(stmt, 3, so.getHusband());
                //Wife
                setParamsForAdult(stmt, 18, so.getWife());
                //Marriage
                stmt.setString(33, so.getMarriageCertificateId());
                stmt.setLong(34, so.getMarriageOffice().getOfficeId());
                stmt.setDate(35, java.sql.Date.valueOf(so.getMarriageDate()));

                stmt.executeUpdate();

                ResultSet gkRs = stmt.getGeneratedKeys();
                if (gkRs.next()) {
                    result = gkRs.getLong(1);
                }
                gkRs.close();

                saveChildren(con, so, result);
                con.commit();
            }catch (SQLException ex) {
                con.rollback();
                throw ex;
            }
        } catch(SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }
    private void saveChildren(Connection con, StudentOrder so, Long soId) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(INSERT_CHILD);
        int counter = 0;
        for (Child child : so.getChildren()) {
            stmt.setLong(1,soId);
            setParamsForChild(stmt, child);
            stmt.addBatch();
        }
        stmt.executeBatch();
    }
    private void setParamsForAdult(PreparedStatement stmt, int start, Adult adult) throws SQLException {
        setParamsForPerson(stmt, start, adult);
        stmt.setString(start +4, adult.getPassportSeria());
        stmt.setString(start +5, adult.getPassportNumber());
        stmt.setDate(start +6, Date.valueOf(adult.getIssueDate()));
        stmt.setLong(start +7, adult.getIssueDepartment().getOfficeId());
        setParamsForAddress(stmt, start + 8, adult);
        stmt.setLong(start + 13, adult.getUniversity().getUniversityId());
        stmt.setString(start + 14, adult.getStudentId());
    }
    private void setParamsForChild(PreparedStatement stmt, Child child) throws SQLException{
        setParamsForPerson(stmt,2,child);
        stmt.setString(6,child.getCertificateNumber());
        stmt.setDate(7,java.sql.Date.valueOf(child.getIssueDate()));
        stmt.setLong(8,child.getIssueDepartment().getOfficeId());
        setParamsForAddress(stmt,9,child);
    }
    private void setParamsForPerson(PreparedStatement stmt, int start, Person person) throws SQLException {
        stmt.setString(start, person.getSurName());
        stmt.setString(start +1, person.getGivenName());
        stmt.setString(start +2, person.getPatronymic());
        stmt.setDate(start +3, Date.valueOf(person.getDateOfBirth()));
    }
    private void setParamsForAddress(PreparedStatement stmt, int start, Person person) throws SQLException {
        Address h_address = person.getAddress();
        stmt.setString(start ,h_address.getPostCode());
        stmt.setLong(start +1,h_address.getStreet().getStreetCode());
        stmt.setString(start +2,h_address.getBuilding());
        stmt.setString(start +3,h_address.getExtension());
        stmt.setString(start +4,h_address.getApartment());
    }

    @Override
    public List<StudentOrder> getStudentOrders() throws DaoException{
        List<StudentOrder> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ORDERS)){
            stmt.setInt(1,StudentOrderStatus.START.ordinal());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                StudentOrder so = new StudentOrder();
                fillStudentOrder(rs,so);
                fillMarriage(rs,so);
                Adult husband = fillAdult(rs,"h_");
                Adult wife = fillAdult(rs,"w_");
                so.setHusband(husband);
                so.setWife(wife);
                result.add(so);
            }

            rs.close();
        }catch (SQLException ex){
            throw new DaoException(ex);
        }

        return result;
    }

    private Adult fillAdult(ResultSet rs, String prefix) throws SQLException {
        Adult adult = new Adult();
        adult.setSurName(rs.getString(prefix + "sur_name"));
        adult.setGivenName(rs.getString(prefix + "given_name"));
        adult.setPatronymic(rs.getString(prefix + "patronymic"));
        adult.setDateOfBirth(rs.getDate(prefix + "date_of_birth").toLocalDate());
        adult.setPassportSeria(rs.getString(prefix + "passport_seria"));
        adult.setPassportNumber(rs.getString(prefix + "passport_number"));
        adult.setIssueDate(rs.getDate(prefix + "passport_date").toLocalDate());
        Long poId = rs.getLong(prefix + "passport_office_id");
        String poArea = rs.getString(prefix + "p_office_area_id" );
        String poName = rs.getString(prefix + "p_office_name");
        PassportOffice po = new PassportOffice(poId,poArea,poName);
        adult.setIssueDepartment(po);
        Address address = new Address();
        address.setPostCode(rs.getString(prefix + "post_index"));
        address.setBuilding(rs.getString(prefix + "building"));
        address.setExtension(rs.getString(prefix + "extension"));
        address.setApartment(rs.getString(prefix + "apartment"));

        Street st = new Street(rs.getLong(prefix + "street_code"),"");
        address.setStreet(st);
        adult.setAddress(address);
        University university = new University(rs.getLong(prefix + "university_id"),"");
        adult.setUniversity(university);
        adult.setStudentId(rs.getString(prefix + "student_number"));

        return adult;

    }
    private void fillStudentOrder(ResultSet rs, StudentOrder so) throws SQLException {
        so.setStudentOrderId(rs.getLong("student_order_id"));
        so.setStudentOrderDate(rs.getTimestamp("student_order_date").toLocalDateTime());
        so.setStudentOrderStatus(StudentOrderStatus.fromValue(rs.getInt("student_order_status")));
    }
    private void fillMarriage(ResultSet rs, StudentOrder so) throws SQLException {
        so.setMarriageCertificateId(rs.getString("certificate_id"));
        so.setMarriageDate(rs.getDate("marriage_date").toLocalDate());

        Long roId = rs.getLong("register_office_id");
        String areaId = rs.getString("r_office_area_id");
        String name = rs.getString("r_office_name");
        RegisterOffice ro = new RegisterOffice(roId,areaId,name);
        so.setMarriageOffice(ro);
    }



}
