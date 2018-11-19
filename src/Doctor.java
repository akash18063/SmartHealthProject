import javax.print.Doc;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Doctor extends User{

    //attributes

    private String experience;
    private String speciality;
    private int departmentNumber;
    private String department_name;
    private int positionInDepartment;



    //private int schedule;
    private int surgeon;
    private ArrayList<String> days=null;


    //constructor
//    Doctor() {
//
//    }

    //default constructor
    Doctor()
    {


    }

    Doctor(String name,int age, String gender, String contact, String address, String email, String password,String doctor_id,String experience, String speciality, int departmentNumber, int positionInDepartment,int surgeon,ArrayList<String> days)
    {
        super(name,age,gender, contact, address,  email, password, doctor_id);
        this.experience=experience;
        this.speciality=speciality;
        this.departmentNumber=departmentNumber;
        this.positionInDepartment=positionInDepartment;
        this.surgeon=surgeon;
        this.days=days;


    }


    public String display(Connection connection) throws SQLException {

        PreparedStatement preparedStatement=connection.prepareStatement("select dept_name from Department where dept_id = ?");
        preparedStatement.setString(1,this.getProfile().getId());
        ResultSet resultSet=preparedStatement.executeQuery();
        String dept_name = null;
        if(resultSet.next()) {
             dept_name = resultSet.getString("dept_name");
        }
        preparedStatement.close();

        String position=null, surgeon=null ,days="";
        if(positionInDepartment==1)
        {
            position="junior resident";
        }
        else if(positionInDepartment==2)
        {
            position="senior resident";
        }

        else if(positionInDepartment==3)
        {
            position="specialist";
        }
        else if(positionInDepartment==4)
        {
            position="senior specialist";
        }


        if(this.surgeon==0)
        {
            surgeon="not a surgeon";
        }
        else if (this.surgeon==1)
        {
            surgeon="surgeon";
        }
        else
        {
            surgeon="senior surgeon";
        }

        PreparedStatement preparedStatement1=connection.prepareStatement("select distinct day from Slots where doctor_id = ?");
        preparedStatement1.setString(1,this.getProfile().getId());
        ResultSet resultSet1=preparedStatement1.executeQuery();
        while (resultSet1.next())
        {
            days=days.concat(resultSet1.getString("day") + "    ");
        }
        preparedStatement1.close();


        String details="Doctor id :" + this.getProfile().getId()+ "\n"+
                "name :" + this.getProfile().getName()+ "\n"+
                "age :" + this.getProfile().getAge()+ "\n"+
                "gender :" + this.getProfile().getGender()+ "\n"+
                "contact :" + this.getProfile().getContact()+ "\n"+
                "address :" + this.getProfile().getAddress()+ "\n"+
                "email :" + this.getProfile().getEmail()+ "\n"+
                "password :"+ this.getProfile().getPassword()+ "\n"+
                "department name :" +dept_name + "\n" +
                "experience :"+ this.experience+ "\n"+
                "speciality :" + this.speciality+ "\n"+
                "position :" + position+ "\n"+
                "surgeon :" + surgeon+ "\n"+
                "days of " + days+ "\n"+
                "timings : 9:00 am to 12:00pm (morning)  4:00pm to 7:00pm"
                ;

        return details;
    }


    boolean showAllAppointments(Connection connection) throws SQLException {

        PreparedStatement preparedStatement1=connection.prepareStatement("select * from Slots where doctor_id = ? and state = ?");
        preparedStatement1.setString(1,this.getProfile().getId());
        preparedStatement1.setString(2,"occupied");
        ResultSet resultSet1=preparedStatement1.executeQuery();
        if(!resultSet1.next())
        {
            System.out.println("you don't have any appointments");
            return false;
        }
        System.out.println("Your appointments :");
        System.out.println("Patient ID      Patient name        start time      end time        day     date");
        System.out.println("---------------------------------------------------------------------------------------");
        while(resultSet1.next())
        {
            String p_id=resultSet1.getString("patient_id");
            PreparedStatement preparedStatement2=connection.prepareStatement("select name from Patient where patient_id = ?");
            preparedStatement2.setString(1,p_id);
            ResultSet resultSet2=preparedStatement2.executeQuery();
            String patient_name=resultSet2.getString("name");
            preparedStatement2.close();
            Time start_time=resultSet1.getTime("start_time");
            Time end_time=resultSet1.getTime("end_time");
            String day=resultSet1.getString("day");
            Date date=resultSet1.getDate("date"); //may throw some error
            System.out.println(p_id+"       "+patient_name+"        "+start_time+"      "+end_time+"        "+day+"     "+date);
            System.out.println("---------------------------------------------------------------------------------------");
        }
        preparedStatement1.close();
        return true;

    }

    boolean showTodaysAppointment(Connection connection) throws SQLException {
        System.out.println("Your appointments :");
        PreparedStatement preparedStatement1=connection.prepareStatement("select * from Slots where doctor_id = ? and state = ? and date = ?");
        preparedStatement1.setString(1,this.getProfile().getId());
        preparedStatement1.setString(2,"occupied");
        preparedStatement1.setDate(3,new java.sql.Date(new Date().getTime()));
        ResultSet resultSet1=preparedStatement1.executeQuery();
        if(!resultSet1.next())
        {
            System.out.println("you don't have any appointments today");
            return false;
        }
        System.out.println("Patient ID      Patient name        start time      end time        day     date");
        System.out.println("---------------------------------------------------------------------------------------");
        while(resultSet1.next())
        {
            String p_id=resultSet1.getString("patient_id");
            PreparedStatement preparedStatement2=connection.prepareStatement("select name from Patient where patient_id = ?");
            preparedStatement2.setString(1,p_id);
            ResultSet resultSet2=preparedStatement2.executeQuery();
            String patient_name=resultSet2.getString("name");
            preparedStatement2.close();
            Time start_time=resultSet1.getTime("start_time");
            Time end_time=resultSet1.getTime("end_time");
            String day=resultSet1.getString("day");
            Date date=resultSet1.getDate("date"); //may throw some error
            System.out.println(p_id+"       "+patient_name+"        "+start_time+"      "+end_time+"        "+day+"     "+date);
            System.out.println("---------------------------------------------------------------------------------------");
        }
        preparedStatement1.close();
        return  true;

    }

    public String getExperience() {
        return experience;
    }

    public String getSpeciality() {
        return speciality;
    }

    public int getDepartmentNumber() {
        return departmentNumber;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public int getPositionInDepartment() {
        return positionInDepartment;
    }

    public int getSurgeon() {
        return surgeon;
    }

    public ArrayList<String> getDays() {
        return days;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setDepartmentNumber(int departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public void setPositionInDepartment(int positionInDepartment) {
        this.positionInDepartment = positionInDepartment;
    }

    public void setSurgeon(int surgeon) {
        this.surgeon = surgeon;
    }

    public void setDays(ArrayList<String> days) {
        this.days = days;
    }

    void get_doctor()
    {

    }
    void update()
    {

    }
}
