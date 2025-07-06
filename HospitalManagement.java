import java.util.ArrayList;
import java.util.Scanner;

class Doctor {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String specialization;

    public Doctor(String name, String specialization) {
        this.id = idCounter++;
        this.name = name;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String toString() {
        return "Doctor ID: " + id + ", Name: " + name + ", Specialization: " + specialization;
    }
}

class Patient {
    private static int idCounter = 1;
    private int id;
    private String name;
    private int age;
    private Doctor assignedDoctor;

    public Patient(String name, int age) {
        this.id = idCounter++;
        this.name = name;
        this.age = age;
        this.assignedDoctor = null;
    }

    public int getId() {
        return id;
    }

    public void assignDoctor(Doctor doctor) {
        this.assignedDoctor = doctor;
    }

    public String toString() {
        String doctorInfo = (assignedDoctor != null) ?
                "Assigned Doctor: " + assignedDoctor.getName() :
                "No doctor assigned";
        return "Patient ID: " + id + ", Name: " + name + ", Age: " + age + ", " + doctorInfo;
    }
}

public class HospitalManagement {
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Add Doctor");
            System.out.println("2. Add Patient");
            System.out.println("3. View Doctors");
            System.out.println("4. View Patients");
            System.out.println("5. Assign Doctor to Patient");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addDoctor();
                case 2 -> addPatient();
                case 3 -> viewDoctors();
                case 4 -> viewPatients();
                case 5 -> assignDoctorToPatient();
                case 6 -> {
                    System.out.println("Exiting system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addDoctor() {
        System.out.print("Enter doctor's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();
        doctors.add(new Doctor(name, specialization));
        System.out.println("Doctor added successfully!");
    }

    private static void addPatient() {
        System.out.print("Enter patient's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());
        patients.add(new Patient(name, age));
        System.out.println("Patient added successfully!");
    }

    private static void viewDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
        } else {
            System.out.println("\n--- Doctor List ---");
            for (Doctor doc : doctors) {
                System.out.println(doc);
            }
        }
    }

    private static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients available.");
        } else {
            System.out.println("\n--- Patient List ---");
            for (Patient pat : patients) {
                System.out.println(pat);
            }
        }
    }

    private static void assignDoctorToPatient() {
        if (doctors.isEmpty() || patients.isEmpty()) {
            System.out.println("Make sure both doctors and patients exist before assignment.");
            return;
        }

        System.out.print("Enter Patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Doctor ID: ");
        int doctorId = Integer.parseInt(scanner.nextLine());

        Patient selectedPatient = null;
        for (Patient p : patients) {
            if (p.getId() == patientId) {
                selectedPatient = p;
                break;
            }
        }

        Doctor selectedDoctor = null;
        for (Doctor d : doctors) {
            if (d.getId() == doctorId) {
                selectedDoctor = d;
                break;
            }
        }

        if (selectedPatient != null && selectedDoctor != null) {
            selectedPatient.assignDoctor(selectedDoctor);
            System.out.println("Doctor assigned to patient successfully.");
        } else {
            System.out.println("Invalid patient or doctor ID.");
        }
    }
}
