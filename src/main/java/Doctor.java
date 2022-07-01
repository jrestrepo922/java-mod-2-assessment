import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String name; 
    private String speciality; 
    private List<Patient> patients;

    public Doctor(String name, String speciality) {
        this.name = name;
        this.speciality = speciality;
        this.patients = new ArrayList<Patient>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSpeciality() {
        return speciality;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    public List<Patient> getPatients() {
        return patients;
    }

    public void addPatient(Patient patient){
        this.patients.add(patient); 
    }
}
