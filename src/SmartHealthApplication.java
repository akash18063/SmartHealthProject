import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class SmartHealthApplication
{

    void  registerPatient(Patient patient, Connection connection) throws SQLException {
        Profile profile=patient.get_patient();

        Statement statement=connection.createStatement();
        statement.execute("insert into Patient values ('" + profile.getId()+"','"+profile.getName()+"',"+profile.getAge()+",'"+profile.getGender()+"','"+profile.getContact()+"','"+profile.getAddress()+"','"+profile.getPassword()+"','"+profile.getEmail()+"')");





    }
}
