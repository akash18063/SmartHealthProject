import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Specialist extends Doctor {
    Specialist()
    {

        this.setPositionInDepartment(3);
    }


    Specialist(String name, int age, String gender, String contact, String address, String email, String password, String doctor_id, String experience, String speciality, int departmentNumber, int surgeon, ArrayList<String> days) {
        super(name, age, gender, contact, address, email, password, doctor_id, experience, speciality, departmentNumber, 3, surgeon, days);
    }

    int transferToDepartment(Connection connection, BufferedReader bufferedReader) throws IOException, SQLException {

        System.out.println("enter the id of the patient you wanna transfer :");
        String patient_id=bufferedReader.readLine();
        PreparedStatement preparedStatement1=connection.prepareStatement("select * from Patient where patient_id = ?");
        preparedStatement1.setString(1,patient_id);
        ResultSet resultSet2=preparedStatement1.executeQuery();
        String p_id=null;
        if(resultSet2.next())
        {
            p_id=resultSet2.getString("patient_id");
        }
        if(!resultSet2.next())
        {
            preparedStatement1.close();
            return 0;
        }

        System.out.println("Select department :");
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from Department");

        System.out.println("------------------------------------------------------");
        while (resultSet.next())
        {
            int dept_id=resultSet.getInt("dept_id");
            String dept_name=resultSet.getString("dept_name");
            System.out.println(dept_id +"           "+dept_name);
            System.out.println("------------------------------------------------------");
        }


        String dept_id=bufferedReader.readLine();
        int dept_id_int=Integer.parseInt(dept_id);


        ResultSet resultSet1=statement.executeQuery("select * from Department where dept_id = "+dept_id_int);

        if(resultSet1.next()==false)
        {
            //System.out.println("please enter correct department id");
            return 1;
        }
        String dept_name=resultSet1.getString("dept_name");
        statement.close();

        //statement=connection.createStatement();
        //ResultSet resultSet3=statement.executeQuery("select * from Doctor where dept_id = ");
        PreparedStatement preparedStatement4=connection.prepareStatement("select * from Doctor where dept_id = ?");
        preparedStatement4.setInt(1,dept_id_int);
        ResultSet resultSet3=preparedStatement4.executeQuery();

        while(resultSet3.next())
        {

             resultSet3.getString("doctor_name");
             System.out.println(resultSet3.getString("doctor_id")+"             "+resultSet3.getString("doctor_name"));
        }
        preparedStatement4.close();
        String input_id=bufferedReader.readLine();
        PreparedStatement preparedStatement5=connection.prepareStatement("select * from Doctor where doctor_id = ?");
        preparedStatement5.setString(1,input_id);
        ResultSet resultSet5=preparedStatement5.executeQuery();
        if(!resultSet5.next())
        {
            return 2;
        }
        preparedStatement5.close();
        PreparedStatement preparedStatement=connection.prepareStatement("insert into TransferRequest(doctor_from_id,dept_from_id,dept_to_id,patient_id,doctor_to_id) values(?,?,?,?,?)");
        preparedStatement.setString(1,this.getProfile().getId());
        preparedStatement.setInt(2, this.getDepartmentNumber());
        preparedStatement.setInt(3,dept_id_int);
        preparedStatement.setString(4,p_id);
        preparedStatement.setString(5,input_id);
        preparedStatement.executeUpdate();
        return 3;
    }

    int transferToDoctor(Connection connection, BufferedReader bufferedReader) throws SQLException, IOException {


        System.out.println("enter the id of the patient you wanna transfer :");
        String patient_id=bufferedReader.readLine();
        PreparedStatement preparedStatement1=connection.prepareStatement("select * from Patient where patient_id = ?");
        preparedStatement1.setString(1,patient_id);
        ResultSet resultSet2=preparedStatement1.executeQuery();
        String p_id=null;
        if(resultSet2.next())
        {
            p_id=resultSet2.getString("patient_id");
        }
        if(!resultSet2.next())
        {
            preparedStatement1.close();
            return 0;
        }
        preparedStatement1.close();
        PreparedStatement preparedStatement4=connection.prepareStatement("select * from Doctor where dept_id = ? and position_in_dept > ?");
        preparedStatement4.setInt(1,this.getDepartmentNumber());
        preparedStatement4.setInt(2,this.getPositionInDepartment());
        ResultSet resultSet3=preparedStatement4.executeQuery();

        while(resultSet3.next())
        {

            resultSet3.getString("doctor_name");
            System.out.println(resultSet3.getString("doctor_id")+"             "+resultSet3.getString("doctor_name"));
        }
        preparedStatement4.close();
        String input_id=bufferedReader.readLine();
        PreparedStatement preparedStatement5=connection.prepareStatement("select * from Doctor where doctor_id = ?");
        preparedStatement5.setString(1,input_id);
        ResultSet resultSet5=preparedStatement5.executeQuery();
        if(!resultSet5.next())
        {
            return 1;
        }
        preparedStatement5.close();
        PreparedStatement preparedStatement=connection.prepareStatement("insert into TransferRequest(doctor_from_id,doctor_to_id,dept_from_id,dept_to_id,patient_id) values(?,?,?,?,?)");
        preparedStatement.setString(1,this.getProfile().getId());
        preparedStatement.setString(2,input_id);
        preparedStatement.setInt(3, this.getDepartmentNumber());
        preparedStatement.setInt(4,this.getDepartmentNumber());
        preparedStatement.setString(5,p_id);
        preparedStatement.executeUpdate();
        return 2;

    }
}
