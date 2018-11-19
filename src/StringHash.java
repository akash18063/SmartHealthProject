import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
//import java.sql.Date;
import java.util.*;
import java.util.Date;


public class StringHash {

    static String getHash(String s)throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = md.digest(s.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
//    public static void main(String s[]) throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
////        System.out.println(StringHash.getHash("123456"));
////        Calendar calender = Calendar.getInstance();
////        Date date = calender.getTime();
//////        Time time = new Time(date.getTime());
//        Time time1 = new Time(22,2,1);
//        Time time2 = new Time(2,2,3);
//
////        System.out.println(calender.toString());
//        System.out.println(time2.compareTo(time1));
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/shs","root","akashgosain");
//        ArrayList list = new ArrayList<Integer>();
//        try{
//            String doc_id = "d1";
//            Statement stmt = connection.createStatement();
//            String query = "select slot_id,slot_date,slot_day,start_time,end_time from Slots where doctor_id = \""+doc_id+"\" and state = \"free\" order by slot_date";
//            ResultSet resultSet = stmt.executeQuery(query);
//            java.util.Date today = new java.util.Date();
//            Time ct = new Time(today.getTime());
//            System.out.println(ct);
//            System.out.println("------------------------------------------------------");
//            System.out.println("slot_id\tslot_date\tslot_day\tstart_time\tend_time");
//            System.out.println("------------------------------------------------------");
//
//
//            while(resultSet.next()){
//                Time st = resultSet.getTime("start_time");
//                java.sql.Date date = resultSet.getDate("slot_date");
//                java.util.Date d1 = (Date)today.clone();
//                d1.setYear(date.getYear());
//                d1.setMonth(date.getMonth());
//                d1.setDate(date.getDate());
//
//                int st1 = Integer.parseInt(st.toString().replaceAll(":",""));
//                int ct1 = Integer.parseInt(ct.toString().replaceAll(":",""));
//
//                if(d1.compareTo(today)==0 && st1<=ct1){
//                    continue;
//                }
//
//                Time et = resultSet.getTime("end_time");
//                int id = resultSet.getInt("slot_id");
////                java.sql.Date date = resultSet.getDate("slot_date");
//                String day = resultSet.getString("slot_day");
//                System.out.println("\t" + id + "\t" + date.toString() + "\t\t" + day + "\t\t" + st.toString() + "\t" + et.toString());
//                System.out.println("------------------------------------------------------");
//
//                list.add(id);
//            }
//        }
//
//        catch(Exception e){
//            System.out.println(e);
//        }
//        System.out.println(list.contains(7));
////        PreparedStatement preparedStatement=connection.prepareStatement("insert into Test1 values(?)");
////        preparedStatement.setDate(1, (java.sql.Date) calender.getTime());
////        preparedStatement.executeUpdate();
////        preparedStatement.close();
////        try {
////
//////            Statement stmt = connection.createStatement();
////            PreparedStatement ps=connection.prepareStatement("Update Slots Set slot_date = DATE_ADD(slot_date, INTERVAL 7 DAY), state=? where slot_date < CURDATE()");
////            ps.setString(1,"free");
////            ps.executeUpdate();
////            ps.close();
////            System.out.println("updated");
////        }
////        catch(Exception e){
////            System.out.println(e);
////        }
//
//    }
}