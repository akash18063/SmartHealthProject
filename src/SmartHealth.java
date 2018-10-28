import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

        System.out.println("some changes are done here");

        System.out.println("test line");


    }
}
