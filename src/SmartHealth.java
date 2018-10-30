import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SmartHealth {

    public static  void main(String[] args) throws IOException {
        String ans = "no";
        InputStreamReader inputStreamReader=new InputStreamReader(System.in);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        do{
        System.out.println("Who are you ? \n1.Admin\n2.patients\n3.Doctors");
        String menu_option=bufferedReader.readLine();

            if (menu_option.equals("1")) {
                System.out.println("What do you want to do ?");
                System.out.println("1.Login");
                System.out.println("2.Register");
            } else if (menu_option.equals("2")) {

                System.out.println("What do you want to do ?");
                System.out.println("1.Login");
                System.out.println("2.Register");

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
