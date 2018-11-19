import java.io.BufferedReader;
import java.sql.*;
class Patient extends User{


    Patient()
    {

        //Default constructor
    }

    Patient(String name,int age, String gender, String contact, String address, String email, String password,String patient_id)
    {
        super(name,age,gender, contact, address,  email, password, patient_id);

    }

    //    methods for accessing patient table and getting and updating patient details
    boolean authenticate(Connection connect, String id, String pass){

        if(login(connect, id,pass)) {
            try {
                Statement statement = connect.createStatement();
                String query = "SELECT * FROM Patient WHERE patient_id = " + id;
                ResultSet resultSet = statement.executeQuery(query);

                resultSet.next();

                pass = resultSet.getString("password");
            }
            catch(SQLException e){
                return false;
            }
        }
        return true;
    }
    boolean register_patient(Connection connect, String doctor_id, int dept_id, int location) throws SQLException {

        Profile pf = getProfile();

        PreparedStatement stmt=connect.prepareStatement("insert into Patient(patient_id,name,age,gender,contact,address,password,email,doctor_id,dept_id,location,seen) values(?,?,?,?,?,?,?,?,?,?,?,?)");
        stmt.setString(1,pf.getId());//1 patient_id
        stmt.setString(2,pf.getName()); // name
        stmt.setInt(3,pf.getAge()); // age
        stmt.setString(4,pf.getGender()); // gender
        stmt.setString(5,pf.getContact()); // contact
        stmt.setString(6,pf.getAddress()); //address
        stmt.setString(7,pf.getPassword()); // password
        stmt.setString(8,pf.getEmail()); // email
        stmt.setString(9,doctor_id); // doctor_id
        stmt.setInt(10,dept_id); // dept_id
        stmt.setInt(11,location);
        stmt.setInt(12,0);

        int i=stmt.executeUpdate();
        return i==1;
    }
    Profile get_patient(){

        return this.getProfile();


    }
    void update(){

    }
}