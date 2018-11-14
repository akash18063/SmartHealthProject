public class User {
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
