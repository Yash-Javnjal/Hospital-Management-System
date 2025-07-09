package com.yash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class DBConnection{
    private static final String URL="jdbc:postgresql://localhost:5432/hospitaldb";
    private static final String USER_NAME="postgres";
    private static final String PASSWD="12345";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL,USER_NAME, PASSWD);
    }
}

class Doctor{
    private String dName;
    private String speciality;

   public  Doctor(String dName,String speciality){
        this.dName = dName;
        this.speciality = speciality;
    }

    public String getdName(){
        return dName;
    }

    public String getspeciality(){
        return speciality;
    }

    public static void addDoctor(String dName,String speciality) throws SQLException{
        try(Connection con = DBConnection.getConnection()){
            String sql ="INSERT INTO doctor(dName,speciality) VALUES(?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,dName);
            ps.setString(2,speciality);
            ps.executeUpdate();

            System.out.println("Doctor is Added.");
        }
        catch(Exception e){
            System.out.println("Error while adding Doctor"+e.getMessage());
        }
    }

    public static void removeDoctor(String dName) throws SQLException{
        try(Connection con = DBConnection.getConnection()){
            String sql="DELETE FROM doctor WHERE dName=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,dName);
            int rows=ps.executeUpdate();
            
            if(rows>0){
            System.out.println(dName+" Doctor removed");
            }
            else{
                System.out.println(dName+" Doctor not found");
            }
        }
            catch(Exception e){
            System.out.println("Error while removing Doctor"+ e.getMessage());    
        }
    }

    public static void viewDoctors()throws SQLException{
        try(Connection con = DBConnection.getConnection()){
            String sql="SELECT * FROM doctor";
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery(sql);

           System.out.println("**---Doctors List---**\n");
            while (rs.next()) {
                System.out.println("Doctor Name: " + rs.getString("dName"));
                 System.out.println("Speciality: " + rs.getString("speciality"));
                System.out.println();
            }
        }
            catch(Exception e){
            System.out.println("Error while viewing Doctor"+ e.getMessage());    
        }
    }
}
    
class Patient{
    private  String pName;
    private int age;
    private int p_id;
    private String disease;

    public Patient(int age, String disease, String pName, int p_id) {
        this.age = age;
        this.disease = disease;
        this.pName = pName;
        this.p_id = p_id;
    }

    public String getpName(){
        return pName;
    }

    public int getage(){
        return age;
    }

    public int getp_id(){
        return p_id;
    }

    public String getsdisease(){
        return disease;
    }

    public static void addPatient(String pName,int age,int p_id,String disease) throws SQLException{
        try(Connection con = DBConnection.getConnection()){
            String sql ="INSERT INTO patient(pName,age,p_id,disease) VALUES(?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,pName);
            ps.setInt(2, age);
            ps.setInt(3, p_id);
            ps.setString(4,disease);
            ps.executeUpdate();

            System.out.println("Patient Added.");
        }
        catch(Exception e){
            System.out.println("Error while adding Patient"+e.getMessage());
        }
    }

    public static  void removePatient(int p_id) throws SQLException{
        try(Connection con = DBConnection.getConnection()){
            String sql="DELETE FROM patient WHERE p_id=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,p_id);
            int rows=ps.executeUpdate();
            
            if(rows>0){
            System.out.println("Patient removed id:"+p_id);
            }
            else{
                System.out.println("Patient not found");
            }
        }
            catch(Exception e){
            System.out.println("Error while removing Patient:"+ e.getMessage());    
        }
    }

    public static void assignDoc(String assiDoc,String pName) throws SQLException{
        try(Connection con = DBConnection.getConnection()){
            String sql ="UPDATE patient SET assiDoc=? WHERE pName=?";
            PreparedStatement ps=con.prepareStatement(sql);
           ps.setString(1, assiDoc);
           ps.setString(2, pName);

            int rows=ps.executeUpdate();

            System.out.println("Doctor"+assiDoc+"is assigned to Patient:"+pName);
        }
        catch(Exception e){
            System.out.println("Error while assigning Doctor"+e.getMessage());
        }
    }

    public static void prescribed_medi(String pName){
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT disease FROM patient WHERE pName = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pName);
            ResultSet rs = stmt.executeQuery();
            String medicine;

            if (rs.next()) {
                String disease = rs.getString("disease");

                switch (disease) {
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

                sql = "UPDATE patient SET medicine = ? WHERE pName = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, medicine);
                stmt.setString(2, pName);
                stmt.executeUpdate();

                System.out.println(medicine+" is prescribed to "+pName);
            } else {
                System.out.println("Patient not found.");
            }
        } catch (Exception e) {
            System.out.println("Error while prescribing medicine: " + e.getMessage());
        }
 
    }



    public static void viewPatients()throws SQLException{
        try(Connection con = DBConnection.getConnection()){
            String sql="SELECT * FROM patient";
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery(sql);

           System.out.println("##---Patient List---##\n");
            while (rs.next()) {
                System.out.println("Patient Name: " + rs.getString("pName"));
                System.out.println("Patient Age: " + rs.getInt("age"));
                System.out.println("Patient ID: " + rs.getInt("p_id"));
                System.out.println("Patient Diagnosied with: " + rs.getString("disease"));
                System.out.println("Dr.Treating Patient: " + rs.getString("assiDoc"));
                System.out.println("Prescribed Medicine: " + rs.getString("medicine"));
                System.out.println();
            }
        }
            catch(Exception e){
            System.out.println("Error while viewing Patient"+e.getMessage());    
        }
    }
}


class Threading extends Thread {
    private boolean running = true;

    public void endt() {
        running = false;
    }

    public void run() {
        while (running) {
            try {
                Thread.sleep(15000);
            } catch (Exception e) {
                System.out.println("Error in thread");
            }
        }
    }

}

public class App {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Threading end = new Threading(); 
        end.start();

        while (true) {
            System.out.println("\n-----HOSPITAL MANAGEMENT-----");
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
                    System.out.println("Enter doctor name: ");
                    String dName = sc.nextLine();
                    System.out.println("Enter speciality: ");
                    String speciality = sc.nextLine();

                    Doctor.addDoctor(dName, speciality);
                    break;

                case 2:
                    System.out.println("Enter doctor name to remove: ");
                    String name = sc.nextLine();
                    Doctor.removeDoctor(name);
                    break;

                case 3:
                    Doctor.viewDoctors();
                    break;

                case 4:
                    System.out.println("Enter patient name: ");
                    String pName = sc.nextLine();
                    System.out.println("Enter age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Patient ID: ");
                    int p_id= sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter disease: ");
                    String disease = sc.nextLine();
                    Patient.addPatient(pName, age,p_id, disease);
                    break;

                case 5:
                Doctor.viewDoctors();
                    System.out.println("Enter patient name: ");
                    String patName = sc.nextLine();
                    System.out.println("Enter doctor name: ");
                    String docName = sc.nextLine();
                    Patient.assignDoc(docName, patName);
                    break;
                    
                case 6:
                    System.out.print("Enter patient name: ");
                    String ptname= sc.nextLine();
                    Patient.prescribed_medi(ptname);
                    break;
                        
                case 7:
                    System.out.println("Enter patient ID to remove: ");
                    int pat_id = sc.nextInt();
                    Patient.removePatient(pat_id);
                    break;

                case 8:
                    Patient.viewPatients();
                    break;

                case 9:
                    end.endt();
                    System.out.println("Exiting");                   
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}





    
