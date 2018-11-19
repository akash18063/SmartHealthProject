import com.sun.corba.se.spi.orb.StringPair;

class TransferRequest
{
    int transfer_id;
    String doctor_id_from;
    String getDoctor_id_to;
    int dept_id_from;
    int dept_id_to;
    String patient_id;

    public TransferRequest(int transfer_id, String doctor_id_from, String getDoctor_id_to, int dept_id_from, int dept_id_to, String patient_id) {
        this.transfer_id = transfer_id;
        this.doctor_id_from = doctor_id_from;
        this.getDoctor_id_to = getDoctor_id_to;
        this.dept_id_from = dept_id_from;
        this.dept_id_to = dept_id_to;
        this.patient_id = patient_id;
    }

    void displayrequest()
    {
        System.out.println(transfer_id +"       "+doctor_id_from+"      "+getDoctor_id_to+"     "+dept_id_from+"        "+dept_id_to+"      "+patient_id );

    }

//    void addTransferRequest()
//    {
//
//
//    }

    public int getTransfer_id() {
        return transfer_id;
    }

    public void setTransfer_id(int transfer_id) {
        this.transfer_id = transfer_id;
    }

    public String getDoctor_id_from() {
        return doctor_id_from;
    }

    public void setDoctor_id_from(String doctor_id_from) {
        this.doctor_id_from = doctor_id_from;
    }

    public String getGetDoctor_id_to() {
        return getDoctor_id_to;
    }

    public void setGetDoctor_id_to(String getDoctor_id_to) {
        this.getDoctor_id_to = getDoctor_id_to;
    }

    public int getDept_id_from() {
        return dept_id_from;
    }

    public void setDept_id_from(int dept_id_from) {
        this.dept_id_from = dept_id_from;
    }

    public int getDept_id_to() {
        return dept_id_to;
    }

    public void setDept_id_to(int dept_id_to) {
        this.dept_id_to = dept_id_to;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }
}