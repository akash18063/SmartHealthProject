import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class SmartHealth {

    private static int  patient_ID=1;

    private long doctor_ID=1;

    public static  void main(String[] args) throws IOException, ClassNotFoundException, SQLException {


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
                           System.out.println("Enter the ");

                       }
                       else if(admin_menu_option.equals("2"))
                       {
                           //handle transfer request (implement it later)
                       }

                       else if(admin_menu_option.equals("3"))
                       {
                           //list the doctors registered
                           System.out.println("Wanna view doctor's profile");
                           String opt=bufferedReader.readLine();
                           if(opt.equals("yes"))
                           {
                               System.out.println("Enter the doctor ID from the list above");
                               String doc_id=bufferedReader.readLine();
                               //call display doctor and display the details
                           }
                           else
                           {
                               //do nothing
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
                        smartHealthApplication.registerPatient(patient,connection);
                        System.out.println("you have been successfully registered with ID : " + patientID);


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

                    //check if the doctor is registered
                    String doc_continue="no";

                if(true)
                {
                    do {
                        System.out.println("What do you wanna do ?");
                        System.out.println("1. View Patients");
                        System.out.println("2. View upcoming appointments");
                        System.out.println("3. Mark Appointments");
                        System.out.println("4. Request Transfer of Patient - Request Admin to reassign a patient to a different department or doctor");
                        System.out.println("5. Edit Profile");
                        System.out.println("6. Logout");
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
                        } else if (doctor_menu_choice.equals("2")) {

                            System.out.println("List of upcoming appointments");
                        } else if (doctor_menu_choice.equals("3")) {
                            System.out.println("Mark from the following  assignments");


                        } else if (doctor_menu_choice.equals("4")) {

                            System.out.println("Patient transfer has been initiated");

                        } else if (doctor_menu_choice.equals("5")) {
                            //edit profile

                        } else if (doctor_menu_choice.equals("6")) {
                            //logout

                            break;


                        } else {
                            continue;

                        }

                        System.out.println("Do you wanna continue(yes/no)");
                        doc_continue=bufferedReader.readLine();
                    }while(doc_continue.equals("yes"));

                    System.out.println("You have been successfully logged out ");

                }
                else
                {

                    System.out.println("Please get yourself registered first " );

                }


                }



            System.out.println("Do you want to continue (yes/no):");
            ans=bufferedReader.readLine();
        } while(ans.equals("yes"));





    }
}
