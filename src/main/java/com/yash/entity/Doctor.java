package com.yash.entity;

import com.yash.util.HibernateUtil;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dName")
    private String dName;

    @Column(name = "speciality")
    private String speciality;

    public Doctor() {}

    public Doctor(String dName, String speciality) {
        this.dName = dName;
        this.speciality = speciality;
    }

    public int getId() {
        return id;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public static void addDoctor(String dName, String speciality) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(new Doctor(dName, speciality));
            tx.commit();
            System.out.println(dName +" added successfully.");
        } 
        catch (Exception e) {
            if (tx != null && tx.isActive()){
                tx.rollback();
            }
            System.out.println("Error while adding doctor: " + e.getMessage());
        } 
        finally {
            em.close();
        }
    }

    public static void removeDoctor(String rmvName) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            List<Doctor> doctors = em.createQuery("FROM Doctor d WHERE d.dName = :name", Doctor.class)
                    .setParameter("name", rmvName)
                    .getResultList();
            if (!doctors.isEmpty()) {
                em.remove(em.merge(doctors.get(0)));
                tx.commit();
                System.out.println(rmvName + " removed successfully.");
            } else {
                System.out.println("Doctor not found.");
                tx.rollback();
            }
        } 
        catch (Exception e) {
            if (tx != null && tx.isActive()){
            tx.rollback();
            }
            System.out.println("Error removing doctor: " + e.getMessage());
        }
         finally {
            em.close();
        }
    }

    public static void viewDoctors() {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            List<Doctor> allDoctors = em.createQuery("FROM Doctor", Doctor.class).getResultList();
            if (allDoctors.isEmpty()) {
                System.out.println("No doctors found.");
            } else {
                System.out.println("------ Doctors List ------");          
                for (Doctor doc : allDoctors) {
                    System.out.println("Doctor Name: " + doc.getdName());
                    System.out.println("Speciality: " + doc.getSpeciality());
                    System.out.println(" ");
                }
            }
        } finally {
            em.close();
        }
    }
}
