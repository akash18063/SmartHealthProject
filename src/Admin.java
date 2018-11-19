import sun.awt.geom.AreaOp;

import javax.print.Doc;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Admin {
    private String username;
    private String password;





    public void addDoctor(Connection connection, Doctor doctor) throws SQLException, ParseException {


        PreparedStatement preparedStatement=connection.prepareStatement("insert into Doctor values(?,?,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1,doctor.getProfile().getId());
        preparedStatement.setString(2,doctor.getProfile().getName());
        preparedStatement.setInt(3,doctor.getProfile().getAge());
        preparedStatement.setString(4,doctor.getProfile().getAddress());
        preparedStatement.setString(5,doctor.getProfile().getContact());
        preparedStatement.setFloat(6,Float.parseFloat(doctor.getExperience()));
        preparedStatement.setInt(7,doctor.getSurgeon());
        preparedStatement.setInt(8,doctor.getPositionInDepartment());
        preparedStatement.setInt(9,doctor.getDepartmentNumber());
        preparedStatement.setString(10,doctor.getProfile().getGender());
        preparedStatement.setString(11,doctor.getProfile().getPassword());
        int c=preparedStatement.executeUpdate();
        //preparedStatement.executeUpdate("insert into slots values()");

//        DateFormat format = new SimpleDateFormat("hh:mm");
//        Date date=format.parse("09:00");
//        Time time = new Time(date.getTime());
//        Calendar calendar=Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.MINUTE,30);
//        Time newtime= new Time(format.parse(format.format(calendar.getTime())).getTime());



        for(int i=0;i<doctor.getDays().size();i++) {
            int date_of_week = 0;
            if (doctor.getDays().get(i).equals("monday")) {
                date_of_week = 1;
            } else if (doctor.getDays().get(i).equals("tuesday")) {
                date_of_week = 2;

            } else if (doctor.getDays().get(i).equals("wednesday")) {
                date_of_week = 3;

            } else if (doctor.getDays().get(i).equals("thursday")) {

                date_of_week = 4;
            } else if (doctor.getDays().get(i).equals("friday")) {

                date_of_week = 5;
            } else if (doctor.getDays().get(i).equals("saturday")) {
                date_of_week = 6;

            } else if (doctor.getDays().get(i).equals("sunday")) {

                date_of_week = 7;
            }

            Calendar date = Calendar.getInstance();

            int difference = date_of_week - date.get(Calendar.DAY_OF_WEEK);
            if (difference < 0) {
                difference = difference + 7;
                date.add(Calendar.DAY_OF_MONTH, difference);


            } else if (difference == 0) {

                date = Calendar.getInstance();


            } else {
                date.add(Calendar.DAY_OF_MONTH, difference);
            }



            DateFormat format = new SimpleDateFormat("hh:mm");

//            Date start_morning = format.parse("09:00");
//            Date end_morning = format.parse("12:00");
//            Date start_evening = format.parse("16:00");
//            Date end_evening = format.parse("19:00");
//            Time start_morning_time = new Time(start_morning.getTime());
//            Time end_morning_time = new Time(start_evening.getTime());
//            Time start_evening_time = new Time(start_evening.getTime());
//            Time end_evening_time = new Time(end_evening.getTime());

            Date cal_date=new Date(date.getTime().getTime());
            java.sql.Date cal_date_sql=new java.sql.Date(cal_date.getTime());
            //Date start_date=format.parse("09:00");
            //Date end_date=format.parse("09:30");
            int t=9;
            for(int k=0;k<3;k++) {
                Time start_time = new Time(t, 0, 0);
                Time end_time = new Time(t, 30, 0);
                Time end_time_1 = new Time(t + 1, 0, 0);
                PreparedStatement preparedStatement1 = connection.prepareStatement("insert into Slots(doctor_id,start_time,end_time,state,slot_day,slot_date) values(?,?,?,?,?,?)");
                preparedStatement1.setString(1, doctor.getProfile().getId());
                preparedStatement1.setTime(2, start_time);
                preparedStatement1.setTime(3, end_time);
                preparedStatement1.setString(4, "free");
                preparedStatement1.setString(5, doctor.getDays().get(i));
                preparedStatement1.setDate(6, cal_date_sql);

                preparedStatement1.executeUpdate();

                preparedStatement1.setString(1, doctor.getProfile().getId());
                preparedStatement1.setTime(2, end_time);
                preparedStatement1.setTime(3, end_time_1);
                preparedStatement1.setString(4, "free");
                preparedStatement1.setString(5, doctor.getDays().get(i));
                preparedStatement1.setDate(6, cal_date_sql);
                preparedStatement1.executeUpdate();

                t++;
            }


            t=16;
            for(int k=0;k<3;k++) {
                Time start_time = new Time(t, 0, 0);
                Time end_time = new Time(t, 30, 0);
                Time end_time_1 = new Time(t + 1, 0, 0);
                PreparedStatement preparedStatement1 = connection.prepareStatement("insert into Slots(doctor_id,start_time,end_time,state,slot_day,slot_date) values(?,?,?,?,?,?)");
                preparedStatement1.setString(1, doctor.getProfile().getId());
                preparedStatement1.setTime(2, start_time);
                preparedStatement1.setTime(3, end_time);
                preparedStatement1.setString(4, "free");
                preparedStatement1.setString(5, doctor.getDays().get(i));
                preparedStatement1.setDate(6, cal_date_sql);
                preparedStatement1.executeUpdate();

                preparedStatement1.setString(1, doctor.getProfile().getId());
                preparedStatement1.setTime(2, end_time);
                preparedStatement1.setTime(3, end_time_1);
                preparedStatement1.setString(4, "free");
                preparedStatement1.setString(5, doctor.getDays().get(i));
                preparedStatement1.setDate(6, cal_date_sql);
                preparedStatement1.executeUpdate();

                t++;
            }

        }

        //PreparedStatement preparedStatement1=connection.prepareStatement("select doctor_id");
        //return c;//return doctor id instead


    }

    int transferPatient(Connection connection,TransferRequest t) throws SQLException {

//        PreparedStatement preparedStatement=connection.prepareStatement("select * from Doctor where doctor_id = ?");
//        preparedStatement.setString(1,t.getDoctor_id_to);
//        ResultSet resultSet=preparedStatement.executeQuery();
//        preparedStatement.close();
        PreparedStatement preparedStatement1=connection.prepareStatement("update Patient set doctor_id = ? , seen = 0 , dept_id = ? where patient_id = ?");
        preparedStatement1.setString(1,t.getGetDoctor_id_to());
        preparedStatement1.setInt(2,t.getDept_id_to());
        preparedStatement1.setString(3,t.getPatient_id());
        preparedStatement1.executeUpdate();
        preparedStatement1.close();
        PreparedStatement preparedStatement2=connection.prepareStatement("delete TransferRequest where request_id = ?");
        preparedStatement2.setInt(1,t.getTransfer_id());
        int value=preparedStatement2.executeUpdate();

        return value;






    }

    void viewUser()
    {

    }

    void checkTrasferRequest()
    {


    }

    void handleTransferRequest(TransferRequest transferRequest)
    {




    }
    void logout()
    {


    }
}
