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
