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

    private static int  patient_ID=1;

    private long doctor_ID=10000;

    public static  void main(String[] args) throws IOException, ClassNotFoundException, SQLException, ParseException {


        String ans = "no";
        InputStreamReader inputStreamReader=new InputStreamReader(System.in);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/shs","root","akashgosain");
        SmartHealthApplication smartHealthApplication=new SmartHealthApplication();
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
                       System.out.println("1. Add a Doctor \n2. Reassign Doctors for a patient \n3. View list of doctors \n4. View list of patients \n5. logout");
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

                            Doctor doctor=new Doctor(doctor_name,Integer.parseInt(age), gender, contact, address,  email, password, doctor_id, experience, speciality, dept_id_int,Integer.parseInt(position), Integer.parseInt(surgeon), days);

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

                           //handle transfer request (implement it later)

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
                     new Patient().authenticate(pid,pass);
                    if(true){
                        System.out.println("User id or password doesn't match");
                    }
                    else{
                        System.out.println("MENU\n1) Explore doctors2) Update Requirements\n3) Edit Profile\n4) Logout");
                        pch = bufferedReader.readLine();
                        if(pch.equals("1")){
                            //get requirements and search doctor

                        }
                        else if(pch.equals("2")){
                            //Update requirements
                        }
                        else if(pch.equals("3")){
                            //Edit profile
                        }
                        else if(pch.equals("4")){
                            continue;
                        }
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

                    StringBuilder stringBuilder=new StringBuilder();
                    stringBuilder.append("patient_");
                    stringBuilder.append(patient_ID);
                    String patientID=stringBuilder.toString();


                    Patient patient=new Patient(name,age,gender,contact,address,email,password,patientID);
                    patient_ID++;
                    try
                    {
                        //smartHealthApplication.registerPatient(patient,connection);
                        //System.out.println("you have been successfully registered with ID : " + patientID);


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
                                if(flag==false)
                                {
                                    continue;
                                    //System.out.println("there are no appointments");
                                }



                            }
                            else if(opt.equals("2"))
                            {

                                Doctor doctor=new Doctor();
                                doctor.getProfile().setId(input_uid);
                                boolean flag=doctor.showTodaysAppointment(connection);
                                if(flag==false)
                                {
                                    continue;
                                    //System.out.println("there are no appointments");
                                }

                            }
                            else
                            {
                                System.out.println("please select the correct option");
                            }



                        } else if (doctor_menu_choice.equals("3")) {

                            int position=resultSet.getInt("position_in_dept");

                            int surgeon=resultSet.getInt("surgeon");
                            if(position==4)
                            {

                                System.out.println("transfer to another department");

                            }
                            else if(position==3)
                            {
                                 System.out.println("please select the transfer type");
                                 System.out.println("1. transfer inside department");
                                 System.out.println("2. transfer to another department");
                                 String option=bufferedReader.readLine();
                                 if(option.equals("1"))
                                 {

                                 }
                                 else if(option.equals("2"))
                                 {
                                     System.out.println("transfer to another department ");
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
                    preparedStatement.executeUpdate();

                    preparedStatement.close();

                    System.out.println("You have been successfully logged out ");



                }



            System.out.println("Do you want to continue (yes/no):");
            ans=bufferedReader.readLine();
        } while(ans.equals("yes"));





    }
}
