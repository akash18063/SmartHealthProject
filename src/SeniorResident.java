import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeniorResident extends Doctor {

    SeniorResident()
    {
        this.setPositionInDepartment(2);
    }
    SeniorResident(String name, int age, String gender, String contact, String address, String email, String password, String doctor_id, String experience, String speciality, int departmentNumber, int surgeon, ArrayList<String> days) {
        super(name, age, gender, contact, address, email, password, doctor_id, experience, speciality, departmentNumber, 2, surgeon, days);
    }


    int transferToDoctor(Connection connection, BufferedReader bufferedReader) throws IOException, SQLException {

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
