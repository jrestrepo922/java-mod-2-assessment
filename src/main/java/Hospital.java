import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String name;
    private List<Doctor> doctors; 

    public Hospital(String name) {
        this.name = name;
        this.doctors = new ArrayList<Doctor>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }


    public void addDoctor(Doctor doctor){
        this.doctors.add(doctor); 
    }

}
