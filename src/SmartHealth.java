import com.sun.org.apache.bcel.internal.generic.DCMPG;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.awt.*;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class SmartHealth {
//
//    private static int  patient_ID=1;
//
//    private long doctor_ID=10000;

    public static void show_departments(Connection connection)throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Department");

        System.out.println("------------------------------------------------------");
        while (resultSet.next()) {
            int dept_id = resultSet.getInt("dept_id");
            String dept_name = resultSet.getString("dept_name");
            System.out.println(dept_id + "           " + dept_name);
            System.out.println("------------------------------------------------------");
        }
        statement.close();
    }

    public static void show_doctors(Connection connection, int d_id)throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Doctor where dept_id = "+d_id);
        System.out.println("------------------------------------------------------");
        while (resultSet.next()) {
            String doctor_id = resultSet.getString("doctor_id");
            float exp = resultSet.getFloat("experience");
            int doctor_age = resultSet.getInt("doctor_age");
            String doctor_name = resultSet.getString("doctor_name");
            String gender = resultSet.getString("gender");
            System.out.println(doctor_id + "    " + doctor_name + "     "  );
            System.out.println("------------------------------------------------------");
        }
        statement.close();
    }

    public static void clear_slots(Connection connection){
        try{
            PreparedStatement ps=connection.prepareStatement("Update Slots Set slot_date = DATE_ADD(slot_date, INTERVAL 7 DAY), state=? where slot_date < CURDATE()");
            ps.setString(1,"free");
            ps.executeUpdate();
            ps.close();
        }
        catch(Exception e){

        }
    }

    public static ArrayList<Integer> show_slots(Connection connection, String doc_id){
        ArrayList list = new ArrayList<Integer>();

        try {
            Statement stmt = connection.createStatement();
            String query = "select slot_id,slot_date,slot_day,start_time,end_time from Slots where doctor_id = \"" + doc_id + "\" and state = \"free\" order by slot_date";
            ResultSet resultSet = stmt.executeQuery(query);
            java.util.Date today = new java.util.Date();
            Time ct = new Time(today.getTime());
            System.out.println("------------------------------------------------------");
            System.out.println("slot_id\tslot_date\tslot_day\tstart_time\tend_time");
            System.out.println("------------------------------------------------------");


            while (resultSet.next()) {
                Time st = resultSet.getTime("start_time");
                java.sql.Date date = resultSet.getDate("slot_date");
                java.util.Date d1 = (java.util.Date) today.clone();
                d1.setYear(date.getYear());
                d1.setMonth(date.getMonth());
                d1.setDate(date.getDate());
                int st1 = Integer.parseInt(st.toString().replaceAll(":",""));
                int ct1 = Integer.parseInt(ct.toString().replaceAll(":",""));

                if(d1.compareTo(today)==0 && st1<=ct1){
                    continue;
                }
                Time et = resultSet.getTime("end_time");
                int id = resultSet.getInt("slot_id");
                String day = resultSet.getString("slot_day");
                System.out.println("\t" + id + "\t" + date.toString() + "\t\t" + day + "\t\t" + st.toString() + "\t" + et.toString());
                System.out.println("------------------------------------------------------");
                list.add(id);
            }
            stmt.close();
        }
        catch(Exception e) {

        }
        return list;
    }

    //Intelligent Algo for doctor allotment
    public static String algo(Connection connection,int dep_id){
        String doc_id="";
        try {
            Statement stmt = connection.createStatement();
            String query = "select doctor_id from Slots where state = \"free\" and doctor_id in " +
                    "(select doctor_id from Doctor where dept_id = " + dep_id + " ) GROUP BY doctor_id ORDER BY COUNT(*) DESC";
            ResultSet rs = stmt.executeQuery(query);
            stmt.close();
            if (rs.next()) {
                doc_id = rs.getString("doctor_id");
            }
            else {
                stmt = connection.createStatement();
                rs = stmt.executeQuery("select doctor_id from Doctor LIMIT 1");
                rs.next();
                doc_id = rs.getString("doctor_id");
            }
        }
        catch(SQLException e){

        }
        return doc_id;
    }

    public static  void main(String[] args) throws IOException, ClassNotFoundException, SQLException, ParseException {


        String ans = "no";
        InputStreamReader inputStreamReader=new InputStreamReader(System.in);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/shs","root","akashgosain");
        //SmartHealthApplication smartHealthApplication=new SmartHealthApplication();
        do{
        System.out.println("Who are you ? \n1.Admin\n2.patients\n3.Doctors");
        String menu_option=bufferedReader.readLine();

            if (menu_option.equals("1")) {
               System.out.println("Welcome Admin");
               System.out.println("Login as admin to continue ");
               System.out.println("enter your ID");
               String id=bufferedReader.readLine();
               System.out.println("Enter password :");
               String password=bufferedReader.readLine();
               String admin_continue="no";
               if(id.equals("admin") && password.equals("admin123"))
               {
                   Admin admin=new Admin();
                   do
                   {
                       System.out.println("Please select from the options :");
                       System.out.println("1. Add a Doctor \n2. Handle transfer request  \n3. View list of doctors \n4. View list of patients \n5. logout");
                       String admin_menu_option=bufferedReader.readLine();
                       if(admin_menu_option.equals("1"))
                       {
                           //take details of the doctor and enter into database
                           System.out.println("Enter the name of the doctor :");
                           String doctor_name=bufferedReader.readLine();
                           System.out.println("Enter age :");
                           String age=bufferedReader.readLine();
                           System.out.println("Enter the gender :");
                           String gender=bufferedReader.readLine();
                           System.out.println("Enter the contact number :");
                           String contact=bufferedReader.readLine();
                           System.out.println("Enter the address :");
                           String address=bufferedReader.readLine();
                           System.out.println("Enter email :");
                           String email=bufferedReader.readLine();
                           Console console = System.console();
                           System.out.println("Enter the password :");
//                           String pass=bufferedReader.readLine();
                           //char[] pass;

                           //String pass = console.readPassword("pass: ");
                           String pass_string=bufferedReader.readLine();
                           System.out.println("Enter the experience in years :");
                           String experience=bufferedReader.readLine();
                           System.out.println("Enter doctor's speciality :");
                           String speciality=bufferedReader.readLine();
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

                           ResultSet resultSet1=statement.executeQuery("select * from Department where dept_id="+dept_id_int);
                           if(resultSet1.next()==false)
                           {
                               System.out.println("please enter correct department id");
                               continue;
                           }
                           String dept_name=resultSet1.getString("dept_name");
                           //System.out.println();
                           System.out.println("Select the position in the department :");
                           System.out.println("1. junior resident \n2. senior resident \n3. specialist \n4.senior specialist");
                           String position=bufferedReader.readLine();
                           //assign different positions as per the input
                           System.out.println("Doctor is :\n1. not a Surgeon \n2. surgeon \n3.senior surgeon");
                           String surgeon=bufferedReader.readLine();
                           System.out.println("Select the days for on which doctor will be available :");
                           System.out.println("1.Monday \n2.Tuesday \n3.Wednesday \n4.Thursday \n5.Friday \n6.Saturday \n7.Sunday");
                           System.out.println("Enter minimum 3 maximum 5 distinct days");
                           boolean flag_ex=true;
                           ArrayList<String> days=null;
                           do{
                               String day_opt=bufferedReader.readLine();
                               String [] day_arr=day_opt.split(",");
                               boolean flag_days=true;
                               //flag_ex=true;
                               if(day_arr.length<3)
                               {
                                   System.out.println("you have entered less than 3, Please try again");
                                   continue;
                               }
                               if(day_arr.length>5)
                               {
                                   System.out.println("you have entered more 5, please try again");
                                   continue;
                               }

                               for(int k=0;k<day_arr.length;k++)
                               {
                                   for(int h=k+1;h<day_arr.length;h++)
                                   {
                                       if(day_arr[k].equals(day_arr[h]))
                                       {
                                           flag_days=false;
                                           break;
                                       }
                                   }
                               }
                               if(flag_days==false)
                               {
                                   System.out.println("Don't enter duplicate values .  Please enter the days again");
                                   continue;

                               }
                               //flag_ex=false;
                                days=new ArrayList<String>();
                               for(int i=0;i<day_arr.length;i++)
                               {
//                                   for(int j=0;j<days.size();j++)
//                                   {
//                                       if(day_arr[i].equals(days.get(j)))
//                                       {
//                                           continue;
//                                       }
//                                   }

                                   if(day_arr[i].equals("1"))
                                   {
                                       days.add("monday");
                                   }
                                   else if(day_arr[i].equals("2"))
                                   {
                                       days.add("tuesday");
                                   }
                                   else if(day_arr[i].equals("3"))
                                   {
                                       days.add("wednesday");
                                   }
                                   else if(day_arr[i].equals("4"))
                                   {
                                       days.add("thursday");
                                   }
                                   else if(day_arr[i].equals("5"))
                                   {
                                       days.add("friday");
                                   }else if(day_arr[i].equals("6"))
                                   {
                                       days.add("saturday");
                                   }
                                   else if(day_arr[i].equals("7"))
                                   {
                                       days.add("sunday");
                                   }
                                   else
                                   {

                                       flag_ex=false;
                                       System.out.println(" please enter valid days ");
                                       break;

                                   }


                               }

                           }while (flag_ex==false);

                           ResultSet resultSet2=statement.executeQuery("Select doctor_id from Ids");
                           int doctor_id_res = 0;
                           while(resultSet2.next())
                           {
                            doctor_id_res=resultSet2.getInt("doctor_id");
                           }
                           statement.close();
                           doctor_id_res=doctor_id_res+1;
                           String short_form=dept_name.substring(0,3);
                           String doctor_id=short_form+"_"+String.valueOf(doctor_id_res);

                            Doctor doctor=new Doctor(doctor_name,Integer.parseInt(age), gender, contact, address,  email, pass_string, doctor_id, experience, speciality, dept_id_int,Integer.parseInt(position), Integer.parseInt(surgeon), days);

                            admin.addDoctor(connection,doctor);

                            PreparedStatement preparedStatement_1=connection.prepareStatement("set sql_safe_updates =0");
                            preparedStatement_1.executeUpdate();
                            PreparedStatement preparedStatement=connection.prepareStatement("update Ids set doctor_id = ? where doctor_id = ?");
                            preparedStatement.setInt(1,doctor_id_res);
                            preparedStatement.setInt(2,doctor_id_res-1);
                            preparedStatement.executeUpdate();
                            preparedStatement.close();

                            System.out.println("Doctor has been registered with ID : "+ doctor_id);

                       }
                       else if(admin_menu_option.equals("2"))
                       {
                                System.out.println("Please select the transfer requests to :");

                                TransferRequest transferRequest=null;
                                PreparedStatement preparedStatement=connection.prepareStatement("select * from TransferRequest");
                                ResultSet resultSet=preparedStatement.executeQuery();
                                while(resultSet.next())
                                {
                                    transferRequest=new TransferRequest(resultSet.getInt(resultSet.getInt("request_id")),resultSet.getString(resultSet.getString("doctor_from_id")),resultSet.getString("doctor_to_id"),resultSet.getInt("dept_from_id"),resultSet.getInt("dept_to_id"),resultSet.getString("patient_id"));
                                    transferRequest.displayrequest();

                                }
                                preparedStatement.close();
                                String req_id =bufferedReader.readLine();
                                String req_ids[]=req_id.split(",");
                                for(int i=0;i<req_ids.length;i++) {
                                    PreparedStatement preparedStatement1 = connection.prepareStatement("select * from TransferRequest where request_id = ?");
                                    preparedStatement.setInt(1, Integer.parseInt(req_ids[i]));
                                    ResultSet resultSet1 = preparedStatement.executeQuery();
                                    if (!resultSet1.next()) {
                                        System.out.println("please enter valid request id");
                                        break;
                                    }

                                    preparedStatement1.close();
                                    PreparedStatement preparedStatement2 = connection.prepareStatement("select * from TransferRequest where request_id = ?");
                                    preparedStatement2.setInt(1, Integer.parseInt(req_id));
                                    ResultSet resultSet2 = preparedStatement2.executeQuery();
                                    while (resultSet2.next()) {
                                        transferRequest = new TransferRequest(resultSet.getInt(resultSet2.getInt("request_id")), resultSet2.getString("doctor_from_id"), resultSet2.getString("doctor_to_id"), resultSet2.getInt("dept_from_id"), resultSet2.getInt("dept_to_id"), resultSet2.getString("patient_id"));
                                        int value=admin.transferPatient(connection,transferRequest);
                                        if(value==0)
                                        {
                                            System.out.println("could not approve the transfer with ID :" +transferRequest.getTransfer_id());
                                        }
                                        else
                                        {
                                            System.out.println("approved the transfer having ID :"+transferRequest.getTransfer_id());

                                        }

                                    }
                                }






                       }

                       else if(admin_menu_option.equals("3"))
                       {
                          PreparedStatement preparedStatement=connection.prepareStatement("select * from Doctor");
                          ResultSet resultSet=preparedStatement.executeQuery();

                          System.out.println("---------------------------------------------------------");
                          while(resultSet.next())
                          {
                              Doctor doctor=new Doctor(resultSet.getString("doctor_name"),resultSet.getInt("doctor_age"),resultSet.getString("gender"),resultSet.getString("contact"),resultSet.getString("address"),resultSet.getString("email"),resultSet.getString("password"),resultSet.getString("doctor_id"),resultSet.getString("experience"),resultSet.getString("speciality"),resultSet.getInt("dept_id"),resultSet.getInt("position_in_dept"),resultSet.getInt("surgeon"),null);
                              doctor.display(connection);
                              System.out.println("---------------------------------------------------------");


                          }

                       }
                       else if(admin_menu_option.equals("4"))
                       {
                           //list the patients registered
                           System.out.println("Wanna view patient's profile");
                           String opt=bufferedReader.readLine();
                           if(opt.equals("yes"))
                           {
                               System.out.println("Enter the patient ID from the list above");
                               String pat_id=bufferedReader.readLine();
                               //call display patients and display the details
                           }
                           else
                           {
                               //do nothing
                           }


                       }
                       else if(admin_menu_option.equals("5"))
                       {
                           System.out.println("you have been successfully logged out !!!");

                           break;
                       }
                       else
                       {
                           continue;

                       }

                       System.out.println("do you wanna continue(yes/no)");
                       admin_continue=bufferedReader.readLine();


                   }
                   while(admin_continue.equals("yes"));
               }
               else
               {
                    System.out.println("Incorrect ID or Password");

                    continue;

               }

            } else if (menu_option.equals("2")) {

                System.out.println("What do you want to do ?");
                System.out.println("1.Login");
                System.out.println("2.Register");
                String pch =bufferedReader.readLine();
                if (pch.equals("1")) {
                    System.out.println("Enter your id:");
                    String pid = bufferedReader.readLine();
                    String pass = bufferedReader.readLine();
                    boolean auth = new Patient().authenticate(connection, pid, pass);
                    if(!auth){
                        System.out.println("User id or password doesn't match");
                    }
                    else {
                        // get patient details from table Patient
                        do {
                            Boolean cancel = false;
                            Statement statement;
                            System.out.println("MENU\n1) Book appointment2) View Appointment\n3) Edit Profile\n4) Logout");
                            pch = bufferedReader.readLine();
                            if (pch.equals("1")) {
                                clear_slots(connection);
                                statement = connection.createStatement();
                                ResultSet rs = statement.executeQuery("select * from Slots where patient_id = \"" + pid + "\" and not slot = \"free\"");
                                if(rs.next()){
                                    System.out.println("You already have a booked appointment");
                                    continue;
                                }
                                statement = connection.createStatement();
                                ResultSet resultSet1 = statement.executeQuery("select * from Patient where patient_id=" + pid);
                                resultSet1.next();
                                int d_id = resultSet1.getInt("dept_id");
                                String doc_id = resultSet1.getString("doctor_id");
                                int location = resultSet1.getInt("location");
                                statement.close();
                                if (location == 2) {
                                    System.out.println("Please come and check with the doctor to get admitted.");
                                }
                                else {
                                    ArrayList list = show_slots(connection, doc_id);
                                    System.out.println("Enter slot_id of your preferred slot, 0 for cancel:");
                                    int s_id;
                                    do {
                                        s_id = Integer.parseInt(bufferedReader.readLine());
                                        if (list.contains(s_id))
                                            break;
                                        System.out.println("Not a valid slot_id, Enter slot_id of above shown slots");
                                    } while (true);
                                    PreparedStatement ps = connection.prepareStatement("update Slots set patient_id = ? , state = ? where slot_id = ?");
                                    ps.setString(1, pid);
                                    ps.setString(2, "occupied");
                                    ps.setInt(3, s_id);
                                    ps.executeUpdate();
                                    ps.close();
                                    System.out.println("Appointment Booked");
                                }
                            }
                            else if (pch.equals("2")) {
                                clear_slots(connection);
                                statement = connection.createStatement();
                                ResultSet rs = statement.executeQuery("select * from Slots where patient_id = \"" + pid + "\" and not slot = \"free\"");
                                statement.close();
                                if(rs.next()){
                                    System.out.println("Doctor_id\tDate\tDay\tStart_time\tEnd_time");
                                    int slot_id = rs.getInt("slot_id");
                                    System.out.println(rs.getString("doctor_id")+"\t\t"+rs.getDate("slot_date").toString()+"\t"
                                            +rs.getString("slot_day"+"\t"+rs.getTime("start_time").toString()+"\t"+rs.getTime("end_time").toString()));
                                    System.out.println("Want to cancel(yes/no)");
                                    if(bufferedReader.readLine().equals("yes")){
                                        PreparedStatement ps=connection.prepareStatement("Update Slots set state = \"free\" where slot_id = ?");
                                        ps.setInt(1,slot_id);
                                        ps.executeUpdate();
                                        ps.close();
                                        System.out.println("Appointment Cancelled");
                                    }
                                }
                                else{
                                    System.out.println("You don't have any appointment scheduled, or there is a change in schedule of doctor." +
                                            " Book appointment from main menu");
                                }
                            }
                            else if (pch.equals("3")) {
                                System.out.println("Want to change email(yes/no):");
                                if(bufferedReader.readLine().equals("yes")){
                                    System.out.println("Enter new email id");
                                    String email = bufferedReader.readLine();
                                    PreparedStatement ps=connection.prepareStatement("Update Patient set email = ? where patient_id = ?");
                                    ps.setString(1,email);
                                    ps.setString(2,pid);
                                    ps.executeUpdate();
                                    ps.close();
                                }
                                System.out.println("Want to change contact no(yes/no):");
                                if(bufferedReader.readLine().equals("yes")){
                                    System.out.println("Enter new contact no");
                                    String contact = bufferedReader.readLine();
                                    PreparedStatement ps=connection.prepareStatement("Update Patient set contact = ? where patient_id = ?");
                                    ps.setString(1,contact);
                                    ps.setString(2,pid);
                                    ps.executeUpdate();
                                    ps.close();
                                }
                                System.out.println("Want to change category(yes/no):");
                                if(bufferedReader.readLine().equals("yes")){
                                    clear_slots(connection);
                                    statement = connection.createStatement();
                                    ResultSet rs = statement.executeQuery("select * from Slots where patient_id = \"" + pid + "\" and not slot = \"free\"");
                                    statement.close();
                                    if(rs.next()){
                                        System.out.println("You already have a booked appointment, First cancel it and then try to change category again");
                                    }
                                    else {
                                        int d_id;
                                        show_departments(connection);
                                        System.out.println("Select a category by entering it's id:");
                                        do {
                                            statement = connection.createStatement();

                                            String dept_id = bufferedReader.readLine();
                                            d_id = Integer.parseInt(dept_id);

                                            ResultSet resultSet1 = statement.executeQuery("select * from Department where dept_id=" + d_id);
                                            if (!resultSet1.next()) {
                                                System.out.println("please enter correct department id");
                                                statement.close();
                                                continue;
                                            }
                                            statement.close();
                                            break;
                                        } while (true);
                                        String doc_id;

                                        System.out.println("Select:\n1)Select a doctor\n2)Use shs to select a doctor for you");
                                        int shs_ch;
                                        do {
                                            shs_ch = Integer.parseInt(bufferedReader.readLine());
                                            if (shs_ch != 1 && shs_ch != 2) {
                                                System.out.println("Enter either 1 or 2");
                                                continue;
                                            }
                                            break;
                                        } while (true);
                                        if (shs_ch == 1) {
                                            show_doctors(connection, d_id);
                                            do {
                                                doc_id = bufferedReader.readLine();
                                                statement = connection.createStatement();
                                                ResultSet resultSet1 = statement.executeQuery("select * from Doctor where doctor_id=" + doc_id + "and dept_id=" + d_id);
                                                if (!resultSet1.next()) {
                                                    System.out.println("please enter correct Doctor id");
                                                    statement.close();
                                                    continue;
                                                }
                                                statement.close();
                                                break;
                                            } while (true);
                                        } else {
                                            doc_id = algo(connection, d_id);
                                        }
                                        PreparedStatement ps=connection.prepareStatement("Update Patient set doctor_id = ? , dept_id = ? where patient_id = ?");
                                        ps.setString(1,doc_id);
                                        ps.setInt(2,d_id);
                                        ps.setString(3,pid);
                                        ps.executeUpdate();
                                        ps.close();
                                    }
                                }
                            }
                            else if (pch.equals("4")) {
                                break;
                            }
                        }while(true);
                    }
                }
                else if (pch.equals("2")) {

                    System.out.println("Enter your name :");
                    String name=bufferedReader.readLine();
                    System.out.println("Enter your age :");
                    int age=Integer.parseInt(bufferedReader.readLine());
                    System.out.println("Enter your gender");
                    String gender=bufferedReader.readLine();
                    System.out.println("Enter your contact number :");
                    String contact=bufferedReader.readLine();
                    System.out.println("Enter your address :");
                    String address=bufferedReader.readLine();
                    System.out.println("Enter your email address :");
                    String email=bufferedReader.readLine();
                    System.out.println("Enter the password :");
                    String password=bufferedReader.readLine();
                    int d_id;
                    show_departments(connection);
                    do {
                        Statement statement = connection.createStatement();

                        String dept_id = bufferedReader.readLine();
                        d_id = Integer.parseInt(dept_id);

                        ResultSet resultSet1 = statement.executeQuery("select * from Department where dept_id=" + d_id);
                        if (!resultSet1.next()) {
                            System.out.println("please enter correct department id");
                            statement.close();
                            continue;
                        }
                        statement.close();
                        break;
                    }while(true);
                    String doc_id;
                    System.out.println("Enter Location:\n1) OPD\n2) LOCAL");
                    int location;
                    do {
                        location = Integer.parseInt(bufferedReader.readLine());
                        if(location!=1 && location!=2){
                            System.out.println("Enter either 1 or 2");
                            continue;
                        }
                        break;
                    }while(true);
                    if(location==1) {
                        System.out.println("Select:\n1)Select a doctor\n2)Use shs to select a doctor for you");
                        int shs_ch;
                        do {
                            shs_ch = Integer.parseInt(bufferedReader.readLine());
                            if(shs_ch!=1 && shs_ch!=2){
                                System.out.println("Enter either 1 or 2");
                                continue;
                            }
                            break;
                        }while(true);
                        if(shs_ch==1) {
                            show_doctors(connection, d_id);
                            do {
                                doc_id = bufferedReader.readLine();
                                Statement statement = connection.createStatement();
                                ResultSet resultSet1 = statement.executeQuery("select * from Doctor where doctor_id='" + doc_id + "' and dept_id=" + d_id);
                                if (!resultSet1.next()) {
                                    System.out.println("please enter correct Doctor id");
                                    statement.close();
                                    continue;
                                }
                                statement.close();
                                break;
                            } while (true);
                        }
                        else{
                            doc_id = algo(connection, d_id);
                        }
                    }
                    else{
                        doc_id = algo(connection, d_id);
                    }

                    String query = "SELECT patient_id FROM Ids";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    resultSet.next();
                    int i = resultSet.getInt("patient_id");
                    query = "SELECT dept_name FROM Department where dept_id = "+d_id;
                    resultSet = statement.executeQuery(query);
                    resultSet.next();
                    String dep = resultSet.getString("dept_name");
                    i += 1;
                    statement.close();

                    String patientID= dep.substring(0,3) + "_" + i;


                    Patient patient=new Patient(name,age,gender,contact,address,email,password,patientID);
                    try
                    {
                        patient.register_patient(connection, doc_id, d_id, location);
                        System.out.println("you have been successfully registered with ID : " + patientID + " and alloted Doctor having id "+doc_id);
                        if(location==2){
                            System.out.println("Come and meet doctor");
                        }
                        query = "UPDATE Ids SET patient_id = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, i);
                        preparedStatement .executeUpdate();
                        preparedStatement.close();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Could not register you !!! Please try after some time");

                    }
                }
            } else if (menu_option.equals("3")) {



                    System.out.println("UserID : ");
                    String input_uid=bufferedReader.readLine();
                    System.out.println("Password :");
                    String input_password=bufferedReader.readLine();

                    PreparedStatement preparedStatement=connection.prepareStatement("select *  from Doctor where doctor_id = ?");
                    preparedStatement.setString(1,input_uid);
                    ResultSet resultSet=preparedStatement.executeQuery();
                    if(!resultSet.next())
                    {
                        System.out.println("Please get yourself registered to log in ");
                        //System.out.println("please try logging again");
                        continue;
                    }
                    if(!resultSet.getString("password").equals(input_password))
                    {
                        System.out.println("Incorrect password");
                        System.out.println("please try logging again");
                        continue;
                    }

                    String doctor_name=resultSet.getString("doctor_name");
                    int doctor_age=resultSet.getInt("doctor_age");
                    //get other details here



                    //Doctor doctor=new Doctor();

                    String doc_continue="no";

                    System.out.println("welcome Dr." + doctor_name);

                    preparedStatement.close();


                do {
                        System.out.println("What do you wanna do ?");
                        System.out.println("1. View Patients");
                        //System.out.println("2. View upcoming appointments");
                        System.out.println("2. Check Appointments");
                        System.out.println("3. Request Transfer of Patient - Request Admin to reassign a patient to a different department or doctor");
                        System.out.println("4. Edit Profile");
                        System.out.println("5. Logout");
                        String doctor_menu_choice = bufferedReader.readLine();
                        if (doctor_menu_choice.equals("1")) {
                            System.out.println("List of patients");
                            System.out.println("1. View patients since last login ");
                            System.out.println("2. View all the patients");
                            String view_patient_choice=bufferedReader.readLine();
                            if (view_patient_choice.equals("1"))
                            {
                                //show the list of patients since last login
                                System.out.println("Do you wanna apply a filer ?(yes/no)");
                                String filter_choice=bufferedReader.readLine();
                                if(filter_choice.equals("yes"))
                                {
                                    System.out.println("Select the field based on which you want to sort your patients : ");
                                    System.out.println("1. Name \n2.age \n3.gender");
                                    String patient_field=bufferedReader.readLine();


                                    System.out.println("List of patients sorted based on "+ patient_field + " is :");
                                    PreparedStatement preparedStatement1=null;

                                    if(patient_field.equals("1"))
                                    {
                                         preparedStatement1=connection.prepareStatement("select * from Patient where doctor_id = ? and seen = ? order by name");
                                        preparedStatement1.setString(1,input_uid);
                                        preparedStatement1.setInt(2,0);
                                    }
                                    else if (patient_field.equals("2"))
                                    {
                                         preparedStatement1=connection.prepareStatement("select * from Patient where doctor_id = ? and seen = ? order by age");
                                        preparedStatement1.setString(1,input_uid);
                                        preparedStatement1.setInt(2,0);
                                    }
                                    else if(patient_field.equals("3"))
                                    {
                                         preparedStatement1=connection.prepareStatement("select * from Patient where doctor_id = ? and seen = ? order by gender");
                                        preparedStatement1.setString(1,input_uid);
                                        preparedStatement1.setInt(2,0);
                                    }
                                    else
                                    {
                                        System.out.println("invalid input");
                                        continue;
                                    }
                                    ResultSet resultSet1=preparedStatement1.executeQuery();

                                    System.out.println("----------------------------------------------------");

                                    while (resultSet.next())
                                    {
                                        String patient_id=resultSet1.getString("patient_id");
                                        String patient_name=resultSet1.getString("name");
                                        int age=resultSet1.getInt("age");
                                        String gender=resultSet1.getString("gender");
                                        String contact =resultSet1.getString("contact");
                                        String address=resultSet1.getString("address");
                                        String email=resultSet1.getString("email");
                                        int loc =resultSet1.getInt("location");
                                        String location=null;
                                        if(loc==0)
                                        {
                                            location="local";
                                        }
                                        else
                                        {
                                            location="opd";
                                        }

                                        System.out.println("Patient ID : " +patient_id);
                                        System.out.println("Patient name : "+patient_name);
                                        System.out.println("age : "+age);
                                        System.out.println("gender :"+ gender);
                                        System.out.println("contact :"+contact);
                                        System.out.println("address :"+address);
                                        System.out.println("email address :"+email);
                                        System.out.println("location :"+ location);



                                    }

                                    System.out.println("----------------------------------------------------");


                                    preparedStatement1.close();


                                    //call function to sort the patients and display the patients



                                }
                                else
                                {
                                    PreparedStatement preparedStatement1=connection.prepareStatement("select * from Patient where doctor_id = ? and seen = ?");
                                    preparedStatement1.setString(1,input_uid);
                                    preparedStatement1.setInt(2,0);
                                    ResultSet resultSet1=preparedStatement1.executeQuery();

                                    System.out.println("----------------------------------------------------");

                                    while (resultSet.next())
                                    {
                                            String patient_id=resultSet1.getString("patient_id");
                                            String patient_name=resultSet1.getString("name");
                                            int age=resultSet1.getInt("age");
                                            String gender=resultSet1.getString("gender");
                                            String contact =resultSet1.getString("contact");
                                            String address=resultSet1.getString("address");
                                            String email=resultSet1.getString("email");
                                            int loc =resultSet1.getInt("location");
                                            String location=null;
                                            if(loc==0)
                                            {
                                                location="local";
                                            }
                                            else
                                            {
                                                location="opd";
                                            }

                                            System.out.println("Patient ID : " +patient_id);
                                            System.out.println("Patient name : "+patient_name);
                                            System.out.println("age : "+age);
                                            System.out.println("gender :"+ gender);
                                            System.out.println("contact :"+contact);
                                            System.out.println("address :"+address);
                                            System.out.println("email address :"+email);
                                            System.out.println("location :"+ location);



                                    }

                                    System.out.println("----------------------------------------------------");


                                    preparedStatement1.close();

                                }

                            }
                            else if(view_patient_choice.equals("2"))
                            {
                                //show the list of all patients
                                //show the list of patients since last login
                                System.out.println("Do you wanna apply a filer ?(yes/no)");
                                String filter_choice=bufferedReader.readLine();
                                if(filter_choice.equals("yes"))
                                {
                                    System.out.println("Enter the field based on which you want to sort your patients :");
                                    String  patient_field=bufferedReader.readLine();

                                    System.out.println("List of patients sorted based on "+ patient_field + " is :");

                                    //call function to sort the patients and display the patients



                                }
                                else
                                {
                                    //do nothing

                                }

                            }
                            else
                            {
                                System.out.println("invalid input");
                                continue;

                            }
                        }



//                        else if (doctor_menu_choice.equals("2")) {
//
//                            System.out.println("List of upcoming appointments");
//                        }
                        else if (doctor_menu_choice.equals("2")) {
                            System.out.println("1. All appointments");
                            System.out.println("2. today's appointments");
                            String opt=bufferedReader.readLine();
                            if(opt.equals("1"))
                            {
                                //show all appointments

                                Doctor doctor=new Doctor();
                                doctor.getProfile().setId(input_uid);
                                boolean flag=doctor.showAllAppointments(connection);


                            }
                            else if(opt.equals("2"))
                            {

                                Doctor doctor=new Doctor();
                                doctor.getProfile().setId(input_uid);
                                boolean flag=doctor.showTodaysAppointment(connection);


                            }
                            else
                            {
                                System.out.println("please select the correct option");
                            }



                        } else if (doctor_menu_choice.equals("3")) {

                            int position=resultSet.getInt("position_in_dept");

                            int surgeon=resultSet.getInt("surgeon");
                            int dept_id=resultSet.getInt("dept_id");
                            if(position==4)
                            {
                                SeniorSpecialist seniorSpecialist=new SeniorSpecialist();
                                seniorSpecialist.getProfile().setId(input_uid);
                                seniorSpecialist.setDepartmentNumber(resultSet.getInt("dept_id"));
                                System.out.println("transfer to another department");
                                int flag=seniorSpecialist.transferToDepartment(connection,bufferedReader);
                                if(flag==0)
                                {
                                    System.out.println("please enter correct patient id ");

                                }
                                else if(flag==1)
                                {
                                    System.out.println("please enter the correct department id");
                                }
                                else if(flag==2)
                                {
                                    System.out.println("please enter the correct doctor id");
                                }
                                else
                                {
                                    System.out.println("The transfer request has been initiated");
                                }



                            }
                            else if(position==3)
                            {
                                 System.out.println("please select the transfer type");
                                 System.out.println("1. transfer inside department");
                                 System.out.println("2. transfer to another department");
                                 Specialist specialist=new Specialist();
                                 specialist.getProfile().setId(input_uid);
                                 specialist.setDepartmentNumber(resultSet.getInt("dept_id"));
                                 System.out.println("transfer to another department");
                                 String option=bufferedReader.readLine();
                                 if(option.equals("1"))
                                 {


                                 }
                                 else if(option.equals("2"))
                                 {
                                     System.out.println("transfer to another department ");
                                     int flag=specialist.transferToDepartment(connection,bufferedReader);
                                     if(flag==0)
                                     {
                                         System.out.println("please enter correct patient id ");

                                     }
                                     else if(flag==1)
                                     {
                                         System.out.println("please enter the correct department id");
                                     }
                                     else if(flag==2)
                                     {
                                         System.out.println("please enter the correct doctor id");

                                     }
                                     else
                                     {
                                         System.out.println("The transfer request has been initiated");
                                     }
                                 }
                                 else
                                 {
                                     System.out.println("transfer to another doctor");
                                 }



                            }
                            else
                            {


                            }


                            //System.out.println("Patient transfer has been initiated");

                        } else if (doctor_menu_choice.equals("4")) {

                            System.out.println("Select the field you wanna edit :");
                            System.out.println("1. contact ");
                            System.out.println("2. address");
                            System.out.println("3. experience");
                            System.out.println("4. position in the department");
                            System.out.println("5. Change your password");
                            String edit_opt=bufferedReader.readLine();
                            PreparedStatement preparedStatement1=connection.prepareStatement("select * from Doctor where doctor_id = ?");
                            preparedStatement1.setString(1,input_uid);
                            ResultSet resultSet1=preparedStatement1.executeQuery();
                            resultSet1.next();
                            preparedStatement1.close();
                            PreparedStatement preparedStatement_update=null;
                            if(edit_opt.equals("1"))
                            {
                                resultSet1.getString("contact");
                                System.out.println("your older contact is :" + resultSet1.getString("contact"));
                                System.out.println("enter the new contact details");
                                String contact=bufferedReader.readLine();
                                preparedStatement_update=connection.prepareStatement("update Doctor set contact = ? where doctor_id = ?");
                                preparedStatement_update.setString(1,contact);
                                preparedStatement_update.setString(2,input_uid);
                                preparedStatement_update.executeUpdate();

                                System.out.println("your profile has been successfully updated ");
                                preparedStatement_update.close();



                            }
                            else if(edit_opt.equals("2"))
                            {
                                resultSet1.getString("address");
                                System.out.println("your older address is :" + resultSet1.getString("address"));
                                System.out.println("enter the new address details");
                                String address=bufferedReader.readLine();
                                preparedStatement_update=connection.prepareStatement("update Doctor set address = ? where doctor_id = ?");
                                preparedStatement_update.setString(1,address);
                                preparedStatement_update.setString(2,input_uid);
                                preparedStatement_update.executeUpdate();

                                System.out.println("your profile has been successfully updated ");
                                preparedStatement_update.close();

                            }
                            else if(edit_opt.equals("3"))
                            {

                                resultSet1.getString("experience");
                                System.out.println("your older contact is :" + resultSet1.getString("experience"));
                                System.out.println("enter the new experience");
                                String experience=bufferedReader.readLine();
                                preparedStatement_update=connection.prepareStatement("update Doctor set experience = ? where doctor_id = ?");
                                preparedStatement_update.setString(1,experience);
                                preparedStatement_update.setString(2,input_uid);
                                preparedStatement_update.executeUpdate();

                                System.out.println("your profile has been successfully updated ");
                                preparedStatement_update.close();

                            }
                            else if(edit_opt.equals("4"))
                            {
                                System.out.println("Please select your new position :");
                                System.out.println("1. junior resident \n2. senior resident \n3. specialist \n4.senior specialist");
                                String pos=bufferedReader.readLine();
                                int position=Integer.parseInt(pos);
                                if(position==1 ||position==2 ||position==3 ||position==4 ||position==5)
                                {
                                    preparedStatement_update=connection.prepareStatement("update Doctor set position = ? where doctor_id = ?");
                                    preparedStatement_update.setInt(1,position);
                                    preparedStatement_update.setString(2,input_uid);
                                    preparedStatement_update.executeUpdate();

                                    System.out.println("your profile has been successfully updated ");
                                    preparedStatement_update.close();
                                }
                                else
                                {

                                    System.out.println("invalid input please try again ");

                                }
                            }
                            else if(edit_opt.equals("5"))
                            {

                                System.out.println("Please enter your old password :");
                                String old_input=bufferedReader.readLine();
                                preparedStatement_update =connection.prepareStatement("select password from Doctor where doctor_id = ?");
                                preparedStatement_update.setString(1,old_input);
                                resultSet1=preparedStatement_update.executeQuery();
                                if (resultSet1.next())
                                {
                                    if(!resultSet1.getString("password").equals(old_input))
                                    {
                                        System.out.println("please enter the correct password");
                                        continue;
                                    }
                                }

                                preparedStatement_update.close();

                                preparedStatement_update=connection.prepareStatement("update Doctor set password =? where doctor_id = ?");

                                System.out.println("Please enter your new password : ");
                                String new_input=bufferedReader.readLine();

                                preparedStatement_update.setString(1,new_input);
                                preparedStatement_update.setString(2,input_uid);
                                preparedStatement_update.executeUpdate();
                                preparedStatement_update.close();

                                System.out.println("your password has been successfully updated");

                            }
                            else
                            {

                                System.out.println("invalid input , please try again ");
                            }

                        } else if (doctor_menu_choice.equals("5")) {
                            //logout

                            break;


                        } else {
                            continue;

                        }

                        System.out.println("Do you wanna continue(yes/no)");
                        doc_continue=bufferedReader.readLine();
                    }while(doc_continue.equals("yes"));

                    PreparedStatement preparedStatement1=connection.prepareStatement("update Patient set seen = 1 where doctor_id = ?");
                    preparedStatement1.setString(1,input_uid);
                    preparedStatement1.executeUpdate();

                    preparedStatement.close();

                    System.out.println("You have been successfully logged out ");

            }



            System.out.println("Do you want to continue (yes/no):");
            ans=bufferedReader.readLine();
        } while(ans.equals("yes"));





    }
}
