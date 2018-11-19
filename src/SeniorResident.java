import java.util.ArrayList;

public class SeniorResident extends Doctor {
    SeniorResident(String name, int age, String gender, String contact, String address, String email, String password, String doctor_id, String experience, String speciality, int departmentNumber, int surgeon, ArrayList<String> days) {
        super(name, age, gender, contact, address, email, password, doctor_id, experience, speciality, departmentNumber, 2, surgeon, days);
    }
}
