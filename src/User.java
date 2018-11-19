import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private Profile profile;

    User()
    {

        profile=new Profile();

    }

    User(String name,int age, String gender, String contact, String address, String email, String password,String patient_id)
    {
        profile=new Profile();
        profile.setName(name);
        profile.setAge(age);
        profile.setGender(gender);
        profile.setContact(contact);
        profile.setAddress(address);
        profile.setEmail(email);
        profile.setEmail(password);
        profile.setId(patient_id);
        profile.setPassword(password);
    }



    public Profile getProfile() {
        return profile;
    }

    boolean login(Connection connect, String id , String password)
    {
        String pass=null;
        //get the password of the id from table
        try{
            Statement statement = connect.createStatement();
            String query = "SELECT password FROM patient WHERE patient_id = "+id;
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            pass = resultSet.getString("password");
        }
        catch (SQLException e){
            return false;
        }
        try {
            pass = StringHash.getHash(pass);
        }
        catch(NoSuchAlgorithmException e){

        }
        if(pass.equals(password))
        {
            return true;

        }

        return false;
    }

    void logout()
    {


    }

    void editprofile()
    {

    }

}
