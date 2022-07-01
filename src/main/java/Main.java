import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // create the hospital
    
        try (Scanner scanner = new Scanner(System.in)) {
            // create hosptial
            System.out.println("============ " + "Welcome to the Hosptial Management System" + " ==============");
            Hospital hospital = Main.createHospital(scanner); 
            
            // numberOfDoctorsOrPatients method created to get the number of Doctors 
            Integer numberOfDoctors = Main.numberOfDoctorsOrPatients(scanner, "doctor"); 

            // create an arrayList with the specialities 
            List<String> specialities = new ArrayList<String>(Arrays.asList("Dermatology", "Pediatrics", "Radiology")); 

            // create the doctors and add them to the hospital
            for(Integer i = 0; i < numberOfDoctors; i++){
                Doctor newDoctor = Main.createDoctor(scanner, specialities); 
                hospital.addDoctor(newDoctor);
            }

            // numberOfDoctorsOrPatients method created to get the number of Doctors 
            Integer numberOfPatients = Main.numberOfDoctorsOrPatients(scanner, "patient"); 
            List<Patient> PatientsNotAssignToDoctors = new ArrayList<Patient>();

            // create an arrayList with the specialities 
            List<String> symptoms = new ArrayList<String>(Arrays.asList("acne", "colic", "broken bones")); 

            // create the doctors and add them to the hospital
            for(Integer i = 0; i < numberOfPatients; i++){
                Patient newPatient = Main.createPatient(scanner, symptoms);  
                PatientsNotAssignToDoctors.add(newPatient); 
            }

            // Assign patients to doctor base on speciality and symptoms
            Main.DoctorToPatientMapping(hospital.getDoctors(), PatientsNotAssignToDoctors);

            // Print out the world
            Main.printTheWorld(hospital, PatientsNotAssignToDoctors);

        } 
        //catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }

    }

    private static Integer numberOfDoctorsOrPatients(Scanner scanner, String type){
        String number; 
        if(type.equals("doctor")){
            System.out.println("Please provide a number between 1 and 3 for the number of doctors that you want to add");
            number = scanner.nextLine();
            while(!(number.equals("1") || number.equals("2") || number.equals("3"))){
                System.out.println("Please provide a number between 1 and 3");
                number = scanner.nextLine(); 
            }
        } else {
            System.out.println("Please provide a number between 1 and 5 for the number of patients that you want to add");
            number = scanner.nextLine(); 
            while(!(number.equals("1") || number.equals("2") || number.equals("3") || number.equals("4") || number.equals("5"))){
                System.out.println("Please provide a number between 1 and 5");
                number = scanner.nextLine(); 
            }
        }
        return Integer.parseInt(number); 
    }

    private static Doctor createDoctor(Scanner scanner, List<String> specialities){
        System.out.println("Please provide the name of the doctor");
        String nameOfDoctor = scanner.nextLine(); 
        while(nameOfDoctor.equals("") || nameOfDoctor.equals(" ") ){
            System.out.println("Name of doctor can not be empty");
            nameOfDoctor = scanner.nextLine(); 
        }
        System.out.println("Please select the speciality by typing a number between 0 to 2");
        System.out.println("Dermatology: type 0");
        System.out.println("Pediatrics: type 1");
        System.out.println("Radiology: type 2");
        String speciality = scanner.nextLine(); 
        while(!(speciality.equals("0") || speciality.equals("1") || speciality.equals("2")) || 
        (speciality.equals("") || speciality.equals(" "))
        ){
            System.out.println("Please provide a number between 0 and 2");
            speciality = scanner.nextLine(); 
        }
        return new Doctor(nameOfDoctor, specialities.get(Integer.parseInt(speciality)));
    }

    private static Patient createPatient(Scanner scanner, List<String> symptoms){
        System.out.println("Please provide the name of the patient");
        String nameOfPatient = scanner.nextLine(); 
        while(nameOfPatient.equals("") || nameOfPatient.equals(" ") ){
            System.out.println("Name of patient can not be empty");
            nameOfPatient = scanner.nextLine(); 
        }
        System.out.println("Please select the symptoms by typing a number between 0 to 2");
        System.out.println("acne: type 0");
        System.out.println("colic: type 1");
        System.out.println("broken bones: type 2");
        String symptom = scanner.nextLine(); 
        while(!(symptom.equals("0") || symptom.equals("1") || symptom.equals("2")) ||
        (symptom.equals("") || symptom.equals(" "))
        ){
            System.out.println("Please provide a number between 0 and 2");
            symptom = scanner.nextLine(); 
        }
        return new Patient(nameOfPatient, symptoms.get(Integer.parseInt(symptom)));
    }

    private static Hospital createHospital(Scanner scanner){
        System.out.println("Please provide the name of the Hospital");
        String nameOfHospital = scanner.nextLine(); 
        while(nameOfHospital.equals("") || nameOfHospital.equals(" ") ){
            System.out.println("Name of Hospital can not be empty");
            nameOfHospital = scanner.nextLine(); 
        }
        return new Hospital(nameOfHospital); 
    }


    private static void DoctorToPatientMapping(List<Doctor> doctors, List<Patient> patients){
        // for loop that iterates over every doctor
        for(int i = 0; i < doctors.size(); i++){

            // every doctor needs to check the patients and see if the patient's symptoms is something
            // they specialize in
            Doctor doctor = doctors.get(i); 
            for(int j = 0; j < patients.size(); j++){
                Patient patient = patients.get(j); 

                if(doctor.getSpeciality()== "Dermatology" && patient.getSymptom() == "acne"){
                    doctor.addPatient(patient);
                    patients.remove(patient);
                } else if(doctor.getSpeciality()== "Pediatrics" && patient.getSymptom() == "colic"){
                    doctor.addPatient(patient);
                    patients.remove(patient);
                } else if(doctor.getSpeciality()== "Radiology" && patient.getSymptom() == "broken bones"){
                    doctor.addPatient(patient);
                    patients.remove(patient);
                }
            }

        }
    }

    private static void printTheWorld(Hospital hospital, List<Patient> patientsNotAssignToDoctors){
        List<Doctor> doctors = hospital.getDoctors(); 

        System.out.println("");
        System.out.println(" ******************* Hospital Management System Information ****************************");
        System.out.println("");
        System.out.println("Hospital: " + hospital.getName());
        System.out.println("");
        System.out.println("======= Doctors and their patients =========");
        System.out.println("");
        for(int i = 0; i < doctors.size(); i++){
            Doctor doctor = doctors.get(i); 
            System.out.println("Doctor: " + doctor.getName() + ", Speciality: " + doctor.getSpeciality());
            System.out.println("     List of Patients");
            List<Patient> patients = doctor.getPatients();
            for(int j = 0; j < patients.size(); j++){
                Patient patient = patients.get(j); 
                System.out.println("      Patient Name: " + patient.getName() + ", Sympton: " + patient.getSymptom());
                System.out.println("");
            }
            System.out.println("");
            System.out.println("-----------------------");
            System.out.println("");
        }
        System.out.println("Patients without doctors due to doctors not being specialize on patients symptons");
        System.out.println("-----------------------------");
        for(int z = 0; z < patientsNotAssignToDoctors.size(); z++){
            Patient patientNotAssignToDoctor = patientsNotAssignToDoctors.get(z); 
            System.out.println("");
            System.out.println("      Patient Name: " + patientNotAssignToDoctor.getName() + ", Sympton: " + patientNotAssignToDoctor.getSymptom());
            System.out.println("");
            System.out.println("-----------------------------");

        }
    }
}
