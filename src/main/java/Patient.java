
public class Patient {
    private String name; 
    private String symptom;

    public Patient(String name, String symptom) {
        this.name = name;
        this.symptom = symptom;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
    
}
