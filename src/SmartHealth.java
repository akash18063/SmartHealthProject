import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;


//contains patient information and all the operations that a patient can perform
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
    boolean authenticate(String id, String pass){

        return login(id,pass);
    }
    void register_patient(){

    }
    Profile get_patient(){

        return this.getProfile();


    }
    void update(){

    }
}

//class that contains the profile
class User {
    private Profile profile;

    User()
    {



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
    }



    public Profile getProfile() {
        return profile;
    }

    boolean login(String id , String password)
    {
        String pass=null;
        //get the password of the id from table
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


//class to save the profile
class Profile {


    private String name;
    private int age;
    private String gender;
    private String contact;
    private String id;
    private String address;
    private String email;
    //String username;
    private String password;


    void setName(String name)
    {
        this.name=name;
    }
    void setAge(int age)
    {
        this.age=age;

    }


    void setGender(String gender)
    {
        this.gender=gender;
    }

    void setContact(String contact)
    {
        this.contact=contact;
    }
    void setId(String id)
    {
        this.id=id;

    }
    void setAddress(String address)
    {
        this.address=address;

    }

    void setEmail(String email)
    {
        this.email=email;

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getContact() {
        return contact;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}



//class for initiating transfer request
class TransferRequest
{
    private String patientID;
    private String doctorID;
    private String departmentID;

}


//class for admin
class Admin
{

    private String username;
    private String password;

    void login()
    {


    }

    int registerDoctor()
    {
        return 0;

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


////class for storing the contact
// class Contact {
//    private String email;
//    private String phone;
//    private Address adress;
//
//    Contact()
//    {
//
//
//    }
//}



////class for storing the address
// class Address {
//
//    private String house_number;
//    private String locality;
//    private String state;
//    private String country;
//    private int pincode;
//
//}



//doctor class
class Doctor extends User{

    //attributes

    private int experience;
    private String speciality;
    private int departmentNumber;
    private int positionInDepartment;
    private int schedule;


    //constructor
//    Doctor() {
//
//    }

    void add_doctor(){

    }
    void get_doctor(){

    }
    void update(){

    }


}








class TimeSlots{
    void get_doctor_slots(String id){

    }
    void update_slot(String id,String time) {

    }
    void add_slot(){

    }
}

//SmartHealthApplication class contains the operations performed on patients and doctors
class SmartHealthApplication
{

     void  registerPatient(Patient patient,Connection connection) throws SQLException {
        Profile profile=patient.get_patient();

        Statement statement=connection.createStatement();
       statement.execute("insert into Patient values ('" + profile.getId()+"','"+profile.getName()+"',"+profile.getAge()+",'"+profile.getGender()+"','"+profile.getContact()+"','"+profile.getAddress()+"','"+profile.getPassword()+"','"+profile.getEmail()+"')");





    }
}



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
                       System.out.println("1. Add a Doctor \n2. Handle transfer request \n3. View users of the application \n4. Logout");
                       String admin_menu_option=bufferedReader.readLine();
                       if(admin_menu_option.equals("1"))
                       {
                           //take details of the doctor and enter into database
                       }
                       else if(admin_menu_option.equals("2"))
                       {
                           //handle transfer request (implement it later)
                       }

                       else if(admin_menu_option.equals("3"))
                       {
                           //view users(easy using database)
                       }
                       else if(admin_menu_option.equals("4"))
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
