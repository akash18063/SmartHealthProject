import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Patient{
//    methods for accessing patient table and getting and updating patient details
    boolean authenticate(String id, String pass){
        return true;
    }
    void register_patient(){

    }
    void get_patient(String username){

    }
    void update(){

    }
}
class Doctor{
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

public class SmartHealth {

    public static  void main(String[] args) throws IOException {
        String ans = "no";
        InputStreamReader inputStreamReader=new InputStreamReader(System.in);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

        System.out.println("Who are you ? \n1.Admin\n2.patients\n3.Doctors");
        String menu_option=bufferedReader.readLine();
        do {
            if (menu_option.equals("1")) {
                System.out.println("What do you want to do ?");
                System.out.println("1.Login");
                System.out.println("2.Register");
            } else if (menu_option.equals("2")) {

                System.out.println("What do you want to do ?");
                System.out.println("1.Login");

                System.out.println("2.Register");
                String pch =bufferedReader.readLine();
                if (pch.equals("1")) {
                    System.out.println("Enter your id:");
                    String pid = bufferedReader.readLine();
                    String pass = bufferedReader.readLine();
                    boolean is_authenticated = new Patient().authenticate(pid,pass);
                    if(! is_authenticated){
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
                    //Register patient using Patient class
                    // get details
                    // assign id
                    // get requirements

                }
            } else if (menu_option.equals("3")) {

                System.out.println("What do you want to do ?");
                System.out.println("1.Login");
                System.out.println("2.Register");

            } else {

                System.out.println("Please enter a valid input");
            }
            System.out.println("Do you want to continue (yes/no):");
            ans=bufferedReader.readLine();
        } while(ans.equals("yes"));


        System.out.print("test Line 2");

        System.out.println("");

        System.out.println("some changes are done here");

        System.out.println("test line");


    }
}
