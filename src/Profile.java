import java.security.NoSuchAlgorithmException;

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
    void setPassword(String password)
    {

        try {
            this.password = StringHash.getHash(password);
        }
        catch(NoSuchAlgorithmException e){

        }
        //this.password=password;

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