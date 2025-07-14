package com.yash.util;

import java.util.Scanner;
import java.util.logging.LogManager;

import com.yash.entity.Doctor;
import com.yash.entity.Patient;

public class HospitalManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LogManager.getLogManager().reset(); 
        System.setProperty("org.jboss.logging.provider", "slf4j"); 

        while (true) {
            System.out.println("\n##---- HOSPITAL MANAGEMENT SYSTEM ----##");
            System.out.println("1. Add Doctor");
            System.out.println("2. Remove Doctor");
            System.out.println("3. View Doctors");
            System.out.println("4. Add Patient");
            System.out.println("5. Assign Doctor to Patient");
            System.out.println("6. Prescribe Medicine");
            System.out.println("7. Remove Patient");
            System.out.println("8. View Patients");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();


                switch (choice) {
                    case 1:
                        System.out.print("Enter Doctor Name: ");
                        String dName = sc.nextLine();
                        System.out.print("Enter Speciality: ");
                        String speciality = sc.nextLine();

                        Doctor.addDoctor(dName, speciality);
                        break;

                    case 2:
                        System.out.print("Enter Doctor Name to Remove: ");
                        String rvmName = sc.nextLine();

                       Doctor.removeDoctor(rvmName);
                        break;

                    case 3:
                        Doctor.viewDoctors();
                        break;

                    case 4:
                    
                        System.out.print("Enter Patient Name: ");
                        String pName = sc.nextLine();
                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Disease: ");
                        String disease = sc.nextLine();

                       Patient.addPatient(pName,age, disease);
                        break;

                    case 5: 
                        
                        System.out.print("Enter Patient Name: ");
                        String patientName = sc.nextLine();
                        System.out.print("Enter Doctor Name: ");
                        String doctorName = sc.nextLine();

                        Patient.assignDoctor(patientName, doctorName);
                        break;

                    case 6: 
                        System.out.print("Enter Patient Name: ");
                        String patName = sc.nextLine();

                       Patient.presc_medi(patName);
                        break;

                    case 7:
                        System.out.print("Enter Patient Name to Remove: ");
                        String removeName = sc.nextLine();

                       Patient.removePatient(removeName);
                        break;

                    case 8:
                        Patient.viewPatient();
                        break;

                    case 9:
                        HibernateUtil.shutdown();
                        System.out.println(" Thank you for using Hospital Management System.");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            } 
        }
    }

