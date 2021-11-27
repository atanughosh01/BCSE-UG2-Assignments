/*Assignment 6:
Design and create a hospital information system with the following scenarios.
● Register a new patient.
● Each patient is assigned to one doctor, but a doctor can have any number of patients. Patients check in to the
hospital and assigned a doctor if they don't already have one.
● While in the hospital, doctors record various observations about each patient at various times. Examples of
observations are blood pressure and temperature. Record test results for a patient.
● The hospital keeps track of all the observations for a given patient until they check out of the hospital. Obtain
all of a patient’s information given the social security number.
NOTE: Patients id will be auto generated. */
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
//package java_assignments1.q6;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

class Doctor {
    static int count;
    int id;
    String name;

    Doctor() {
        count++;
        id = 20000 + count;
        name = "";
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    Doctor(int i) {
        id = i;
    }

    static void setCount() {
        count = 0;
    }

    static int getCount() {
        return count;
    }

    void readData() {
        System.out.println("Enter name");
        Scanner sc = new Scanner(System.in);
        name = sc.nextLine();
    }

    void display() {
        System.out.println("Doctor id : " + id);
        System.out.println("Dr. " + name);
    }

    public boolean equals(Object o) {
        return ((Doctor) o).id == ((Doctor) this).id;
    }
}

class Patient {
    static int count;
    int id;
    int status;
    int docId;
    String name;
    String docName;
    int hbp;
    int lbp;
    double temp; // temperature in fahrenheit
    ArrayList<Record> obs;

    Patient() {
        count++;
        id = 10000 + count;
        docId = -1;
        status = 1;
        name = "";
        docName = "";
        hbp = 120;
        lbp = 80;
        temp = 98.6;
        obs = new ArrayList<>();
    }

    Patient(int i) {
        id = i;
    }

    static void setCount() {
        count = 0;
    }

    static int getCount() {
        return count;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    void setDocId(int i) {
        docId = i;
    }

    void setDocName(String n) {
        docName = n;
    }

    int getDocId() {
        return docId;
    }

    String getDocName() {
        return docName;
    }

    int getHBP() {
        return hbp;
    }

    int getLBP() {
        return lbp;
    }

    double getTemp() {
        return temp;
    }

    void readData() {
        System.out.println("Enter name");
        Scanner sc = new Scanner(System.in);
        name = sc.nextLine();
        System.out.println("Enter doctor id you want to be assigned");
        docId = sc.nextInt();
    }

    void observe() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter systolic blood pressure\n");
        hbp = sc.nextInt();
        System.out.println("Enter diastolic blood pressure\n");
        lbp = sc.nextInt();
        System.out.println("Enter temperature\n");
        temp = sc.nextDouble();
        Record r = new Record();
        r.observe(this);
        obs.add(r);
    }

    void display() {
        System.out.println("Patient id : " + id);
        System.out.println("Name : " + name);
    }

    void showRec() {
        int i = 1;
        System.out.println(
                "Patient id : " + id + "\n" + "Patient name : " + name + "\n" + "Observing Doctor : Dr." + docName);
        System.out.println("Sl.No.\tDate\tTime\tSystolic pressure\tDiastolic pressure\tTemperature");
        for (Record r : obs) {
            System.out.print(i++ + "\t");
            r.dispPat();
        }
    }

    void remove() {
        status = 0;
    }

    int getStatus() {
        return status;
    }

    public boolean equals(Object o) {
        return ((Patient) o).id == this.id;
    }
}

// ------------------------------------------------------------------------------------------

class Record {
    String patName;
    String docName;
    Date obsDate;
    int hbp;
    int lbp;
    double temp;

    void observe(Patient p) {
        patName = p.getName();
        docName = p.getDocName();
        obsDate = new Date();
        hbp = p.getHBP();
        lbp = p.getLBP();
        temp = p.getTemp();
    }

    void display() {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat f2 = new SimpleDateFormat("hh:mm:ss");
        System.out.println(patName + "\t" + docName + "\t" + f.format(obsDate) + "\t" + f2.format(obsDate) + "\t" + hbp
                + "\t" + lbp + "\t" + temp);
    }

    void dispPat() {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat f2 = new SimpleDateFormat("hh:mm:ss");
        System.out.println(f.format(obsDate) + "\t" + f2.format(obsDate) + "\t" + hbp + "\t" + lbp + "\t" + temp);
    }
}

class Hospital {
    ArrayList<Patient> arrp;
    ArrayList<Doctor> arrd;
    ArrayList<Record> arrr;

    Hospital() {
        arrp = new ArrayList<>();
        arrd = new ArrayList<>();
        arrr = new ArrayList<>();
    }

    String findDoc(int i) {
        for (Doctor d : arrd) {
            if (d.getId() == i)
                return d.getName();
        }
        return "";
    }

    void addPatient() {
        Patient p = new Patient();
        p.readData();
        p.display();
        int doc = p.getDocId();
        Scanner sc = new Scanner(System.in);
        while (findDoc(doc).length() == 0) {
            System.out.println("Doctor not available...Enter doctor id again");
            doc = sc.nextInt();
            p.setDocId(doc);
        }
        p.setDocName(findDoc(doc));
        System.out.println("Allocated doctor : Dr. " + p.getDocName());
        arrp.add(p);
    }

    void addDoctor() {
        Doctor d = new Doctor();
        d.readData();
        d.display();

        arrd.add(d);
    }

    Patient findPat() {
        System.out.println("Enter patient code");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        Patient p = new Patient(i);
        int in = arrp.indexOf(p);
        if (in == -1)
            return null;
        else
            return arrp.get(in);
    }

    void observe() {
        Patient p = findPat();
        while (p == null || p.getStatus() == 0) {
            System.out.println("Patient not found...Enter patient code again");
            p = findPat();
        }
        p.observe();
        Record r = new Record();
        r.observe(p);
        arrr.add(r);
    }

    void dispRecPat() {
        Patient p = findPat();
        while (p == null || p.getStatus() == 0) {
            System.out.println("Patient not found...Enter patient code again");
            p = findPat();
        }
        p.showRec();
    }

    void dispRec() {
        int i = 1;
        System.out.println("Sl.No.\tPatient\tDoctor\tDate\tTime\tSystolic pressure\tDiastolic pressure\tTemperature");
        for (Record r : arrr) {
            System.out.print(i++ + "\t");
            r.display();
        }
    }

    void discharge() {
        Patient p = findPat();
        p.remove();
    }
}

public class Q6 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Patient.setCount();
        Doctor.setCount();
        Hospital h = new Hospital();
        int ch;
        do {
            System.out.println("Enter choice");
            System.out.println("1. Admit patient");
            System.out.println("2. Enter doctor");
            System.out.println("3. Observe a patient");
            System.out.println("4. Discharge a patient");
            System.out.println("5. Display observation records of a patient");
            System.out.println("6. Display all observation records");
            System.out.println("7. Exit");
            ch = sc.nextInt();
            int count = 0;
            switch (ch) {
            case 1:
                if (Doctor.getCount() != 0)
                    h.addPatient();
                else
                    System.out.println("No Doctors in hospital...Enter doctors first.");
                break;
            case 2:
                h.addDoctor();
                break;
            case 3:
                if (Patient.getCount() != 0)
                    h.observe();
                else
                    System.out.println("No patient in hospital");
                break;
            case 4:
                if (Patient.getCount() != 0)
                    h.discharge();
                else
                    System.out.println("No patient in hospital");
                break;
            case 5:
                if (Patient.getCount() != 0)
                    h.dispRecPat();
                else
                    System.out.println("No patient in hospital");
                break;
            case 6:
                if (Patient.getCount() != 0)
                    h.dispRec();
                else
                    System.out.println("No patient in hospital");
                break;
            case 7:
                System.out.println("Exitting");
                break;
            }
        } while (ch != 7);
    }
}
