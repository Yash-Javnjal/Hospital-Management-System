package com.yash.entity;

import com.yash.util.HibernateUtil;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "patient")

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private int p_id;


    @Column(name = "pName")
    private String pName;

    @Column(name = "age")
    private int age;

    @Column(name = "disease")
    private String disease;

    @Column(name = "assiDoc")
    private String assiDoc;

    @Column(name = "medicine")
    private String medicine;

    public Patient() {}

    public Patient(String pName,int age, String disease) {
        this.pName = pName;
        this.age = age;
        this.disease = disease;
    }

    public int getP_id() {
        return p_id;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getAssiDoc() {
        return assiDoc;
    }

    public void setAssiDoc(String assiDoc) {
        this.assiDoc = assiDoc;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public static void addPatient(String pName, int age, String disease) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(new Patient(pName, age, disease));
            tx.commit();
            System.out.println(pName + " added successfully.");
        } 
        catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error while adding patient: " + e.getMessage());
        } 
        finally {
            em.close();
        }
    }

    public static void removePatient(String removeName) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            List<Patient> patients = em.createQuery("FROM Patient p WHERE p.pName = :name", Patient.class)
                    .setParameter("name", removeName)
                    .getResultList();
            if (!patients.isEmpty()) {
                em.remove(em.merge(patients.get(0)));
                tx.commit();
                System.out.println(removeName + " removed successfully.");
            } else {
                System.out.println("Patient not found.");
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx != null && tx.isActive()){
             tx.rollback();
            }
            System.out.println("Error while removing patient: " + e.getMessage());
        } 
        finally {
            em.close();
        }
    }

    public static void assignDoctor(String pName, String assiDoc) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Patient patient = em.createQuery("FROM Patient p WHERE p.pName = :name", Patient.class)
                    .setParameter("name", pName)
                    .getSingleResult();
            patient.setAssiDoc(assiDoc);
            em.merge(patient);
            tx.commit();
            System.out.println(assiDoc + " assigned successfully to " + pName);
        } 
        catch (Exception e) {
            if (tx != null && tx.isActive()){
            tx.rollback();
            }
            System.out.println("Error while assigning doctor: " + e.getMessage());
        } 
        finally {
            em.close();
        }
    }

    public static void presc_medi(String pName) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            List<Patient> patients = em.createQuery("FROM Patient p WHERE p.pName = :name", Patient.class)
                    .setParameter("name", pName)
                    .getResultList();
            if (!patients.isEmpty()) {
                String medicine;
                Patient pat = patients.get(0);
                switch (pat.getDisease().toLowerCase()) {
                    case "fever": 
                                medicine = "Paracetamol"; 
                                break;

                    case "cold": 
                                medicine = "Cetirizine"; 
                                break;

                    case "diabetes": 
                                medicine = "Gluconorm"; 
                                break;

                    case "cough": 
                                medicine = "Ascoril";
                                break;

                    case "stress": 
                                medicine = "Take a walk outside";
                                break;

                    default: 
                    medicine = "Perform Surgery";
                }
                pat.setMedicine(medicine);
                em.merge(pat);
                tx.commit();
                System.out.println(" Medicine prescribed: " + medicine +" to " + pName);
            } else {
                System.out.println(" Patient not found.");
                tx.rollback();
            }
        } 
        catch (Exception e) {
            if (tx != null && tx.isActive()){
            tx.rollback();
            }
            System.out.println(" Error while prescribing medicine: " + e.getMessage());
        } 
        finally {
            em.close();
        }
    }

    public static void viewPatient() {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            List<Patient> allPatients = em.createQuery("FROM Patient", Patient.class).getResultList();
            if (allPatients.isEmpty()) {
                System.out.println("No patients found.");
            } else {
                System.out.println("------ Patients List ------");
                for (Patient pat : allPatients) {
                    
                    System.out.println("Patient Name: " + pat.getPName());
                    System.out.println("Age: " + pat.getAge());
                    System.out.println("Disease: " + pat.getDisease());
                    System.out.println("Assigned Doctor: " + pat.getAssiDoc());
                    System.out.println("Medicine: " + pat.getMedicine());
                    System.out.println(" ");
                }
            }
        } 
        finally {
            em.close();
        }
    }
}
