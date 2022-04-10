package com.address.book.app.utils;

import com.address.book.app.entity.Address;
import com.address.book.app.entity.Contact;
import com.address.book.app.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class PersonUtils {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_PERSON_SQL="INSERT INTO PERSON(FIRST_NAME, LAST_NAME, person_contact_id, person_address_id) VALUES(?, ?, ?, ?)";
    private static final String INSERT_CONTACT_SQL="INSERT INTO PERSON_CONTACTS(EMAIL, MOBILE, LAND_LINE) VALUES(?, ?, ?)";
    private static final String INSERT_ADDRESS_SQL="INSERT INTO PERSON_ADDRESS(STREET, CITY_NAME, STATE_NAME, COUNTRY_NAME, ZIP_CODE) VALUES(?, ?, ?, ?, ?)";
    private static final String FIND_ALL_PERSONS_SQL=
            "SELECT p.ID as PERSON_ID, p.FIRST_NAME, p.LAST_NAME, c.ID as CONTACT_ID, c.EMAIL, c.MOBILE, c.LAND_LINE, " +
            " a.ID as ADDRESS_ID, a.STREET, a.CITY_NAME, a.STATE_NAME, a.COUNTRY_NAME, a.ZIP_CODE  FROM PERSON p " +
            " inner join PERSON_ADDRESS a on p.person_address_id = a.ID  inner join PERSON_CONTACTS c on p.person_contact_id = c.ID";
    private static final String FIND_PERSON_BY_ID_SQL = FIND_ALL_PERSONS_SQL +" WHERE p.id = ?";

    private static final String DELETE_ADDRESS_SQL="DELETE FROM PERSON_ADDRESS WHERE ID = ?";
    private static final String DELETE_CONTACT_SQL="DELETE FROM PERSON_CONTACTS WHERE ID = ?";
    private static final String DELETE_PERSON_SQL="DELETE FROM PERSON WHERE ID = ?";

    private static final String FIND_PERSON_BY_SEARCH_SQL = FIND_ALL_PERSONS_SQL +" WHERE lower(p.FIRST_NAME) like lower(?) " +
            "or lower(p.LAST_NAME) like lower(?)";

    private static final String UPDATE_PERSON_SQL="UPDATE PERSON SET FIRST_NAME = ?, LAST_NAME=? WHERE ID = ?";
    private static final String UPDATE_CONTACT_SQL="UPDATE PERSON_CONTACTS SET EMAIL = ?, MOBILE = ?, LAND_LINE = ? WHERE ID = ?";
    private static final String UPDATE_ADDRESS_SQL="UPDATE PERSON_ADDRESS SET STREET = ?, CITY_NAME = ?, STATE_NAME = ?, COUNTRY_NAME = ?, ZIP_CODE = ? WHERE ID = ?";


    public static PreparedStatementCreator saveContact(Person person) {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                PreparedStatement ps = connection.prepareStatement(INSERT_CONTACT_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, person.getPersonContact().getEmailAddress());
                ps.setString(2, person.getPersonContact().getMobileNumber());
                ps.setString(3, person.getPersonContact().getLandLineNumber());
                return ps;
            }
        };
    }

    public static PreparedStatementCreator saveAddress(Person person) {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                PreparedStatement ps = connection.prepareStatement(INSERT_ADDRESS_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, person.getPersonAddress().getStreet());
                ps.setString(2, person.getPersonAddress().getCity());
                ps.setString(3, person.getPersonAddress().getState());
                ps.setString(4, person.getPersonAddress().getCountry());
                ps.setString(5, person.getPersonAddress().getZipCode());
                return ps;
            }
        };
    }

    public static PreparedStatementCreator savePerson(Person person) {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                PreparedStatement ps = connection.prepareStatement(INSERT_PERSON_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, person.getFirstName());
                ps.setString(2, person.getLastName());
                ps.setInt(3, person.getPersonContact().getId());
                ps.setInt(4, person.getPersonAddress().getId());
                return ps;
            }
        };
    }

    public List<Person> findAllPersons() {
        return findPersons(FIND_ALL_PERSONS_SQL, false, null);
    }

    public List<Person> findPersons(String SQL, boolean isArgs, Object[] args) {
        return jdbcTemplate.query(SQL,isArgs?args:new Object[]{}, (rs, arg1) -> { // RowMapper as Lambda
            Person person = new Person();
            person.setId(rs.getInt("PERSON_ID"));
            person.setFirstName(rs.getString("FIRST_NAME"));
            person.setLastName(rs.getString("LAST_NAME"));

            Contact contact = new Contact();
            contact.setId(rs.getInt("CONTACT_ID"));
            contact.setEmailAddress(rs.getString("EMAIL"));
            contact.setMobileNumber(rs.getString("MOBILE"));
            contact.setLandLineNumber(rs.getString("LAND_LINE"));
            person.setPersonContact(contact);

            Address address = new Address();
            address.setId(rs.getInt("ADDRESS_ID"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city_name"));
            address.setState(rs.getString("state_name"));
            address.setCountry(rs.getString("country_name"));
            address.setZipCode(rs.getString("zip_code"));
            person.setPersonAddress(address);
            return person;
        });
    }

    public Person findPersonById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_PERSON_BY_ID_SQL, new Object[]{id}, (rs, arg1) -> { // RowMapper as Lambda
            Person person = new Person();
            person.setId(rs.getInt("PERSON_ID"));
            person.setFirstName(rs.getString("FIRST_NAME"));
            person.setLastName(rs.getString("LAST_NAME"));

            Contact contact = new Contact();
            contact.setId(rs.getInt("CONTACT_ID"));
            contact.setEmailAddress(rs.getString("EMAIL"));
            contact.setMobileNumber(rs.getString("MOBILE"));
            contact.setLandLineNumber(rs.getString("LAND_LINE"));
            person.setPersonContact(contact);

            Address address = new Address();
            address.setId(rs.getInt("ADDRESS_ID"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city_name"));
            address.setState(rs.getString("state_name"));
            address.setCountry(rs.getString("country_name"));
            address.setZipCode(rs.getString("zip_code"));
            person.setPersonAddress(address);
            return person;
        });
    }

    public boolean removePersonById(Integer id) {
        Person person = findPersonById(id);
        int delete_contact = jdbcTemplate.update(DELETE_CONTACT_SQL, new Object[]{person.getPersonContact().getId()});
        int delete_address = jdbcTemplate.update(DELETE_ADDRESS_SQL, new Object[]{person.getPersonAddress().getId()});
        int delete_person = jdbcTemplate.update(DELETE_PERSON_SQL, new Object[]{person.getId()});

        return (delete_address == 1 && delete_contact == 1 && delete_person == 1);
    }

    public List<Person> findPersonsBySearchKey(String search) {
        return findPersons(FIND_PERSON_BY_SEARCH_SQL, true, new Object[]{search, search});
    }

    public Person updatePerson(Person person) {
        int update_person = jdbcTemplate.update(UPDATE_PERSON_SQL, new Object[]{
                person.getFirstName(), person.getLastName(), person.getId()
        });
        int update_address = jdbcTemplate.update(UPDATE_ADDRESS_SQL, new Object[]{
                person.getPersonAddress().getStreet(), person.getPersonAddress().getCity(),
                person.getPersonAddress().getState(), person.getPersonAddress().getCountry(),
                person.getPersonAddress().getZipCode(), person.getPersonAddress().getId()
        });
        int update_contact = jdbcTemplate.update(UPDATE_CONTACT_SQL, new Object[]{
                person.getPersonContact().getEmailAddress(), person.getPersonContact().getMobileNumber(),
                person.getPersonContact().getLandLineNumber(), person.getPersonContact().getId()
        });

        return person;
    }
}
