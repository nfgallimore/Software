/*
 * GradeDatabase.java
 *
 * Author:          Computer Science S-111
 * Modified by:     <your name>, <your e-mail address>
 * Date modified:   <current date>
 */

import java.util.*;

/**
 * A simple in-memory database of student and grade information.
 */
public class GradeDatabase {
    /* 
     * A private inner class for storing information about a student.
     */
    private class StudentRecord {
        private int id;
        private String lastName;
        private String firstName;
        
        StudentRecord(int id, String lastName, String firstName) {
            this.id = id;
            this.lastName = lastName;
            this.firstName = firstName;
        }
    }
    
    /* 
     * A private inner class for storing information about a student's
     * grade on a particular assignment.
     */
    private class GradeRecord {
        private int studentID;
        private String assignment;    // e.g., "PS 1" or "midterm"
        private int grade;
        
        GradeRecord(int studentID, String assignment, int grade) {
            this.studentID = studentID;
            this.assignment = assignment;
            this.grade = grade;
        }
    }
    
    /**** add your instance variables here ****/
    
    
    
    public GradeDatabase() {
        /** complete the constructor below **/
        
        
    }
    
    /**
     * addStudent - add a record for the student with the specified information
     */
    public void addStudent(int id, String last, String first) {
        /* complete the method below */
        
    }
    
    /**
     * addGrade - add a record for the grade entry with the specified details
     */
    public void addGrade(int id, String asst, int grade) {
        /* complete the method below */
        
    }
    
    /**
     * printStudents - print the entries in the student table
     */
    public void printStudents() {
        System.out.println();
        System.out.println("id\tlast\t\tfirst");
        System.out.println("--------------------------------------------");
        
        /* complete the method below */
        
    }
    
    /**
     * printGrades - print the entries in the grade table
     */
    public void printGrades() {
        System.out.println();
        System.out.println("id\tassignment\tgrade");
        System.out.println("--------------------------------------------");
        
        /* complete the method below */
        
    }
    
    /**
     * printStudentsGrades - print a "join" of the student and grade
     * tables.  See the problem set handout for more details.
     */
    public void printStudentsGrades() {
        System.out.println();
        System.out.println("last\t\tfirst\tassignment\tgrade");
        System.out.println("------------------------------------------------");
        
        /* complete the method below */
        
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String last, first, asst;
        int id, grade, op;
        
        GradeDatabase db = new GradeDatabase();
        
        while (true) {
            System.out.println();
            System.out.println("(1) Add student");
            System.out.println("(2) Add grade");
            System.out.println("(3) Print students");
            System.out.println("(4) Print grades");
            System.out.println("(5) Print each student's grades");
            System.out.println("(6) Exit");
            System.out.print("Which operation (1-6)? ");
            op = in.nextInt();
            in.nextLine();
            
            switch (op) {
                case 1:
                    System.out.print("    id: ");
                    id = in.nextInt();
                    in.nextLine();
                    System.out.print("    last: ");
                    last = in.nextLine();
                    System.out.print("    first: ");
                    first = in.nextLine();
                    
                    db.addStudent(id, last, first);
                    break;
                case 2:
                    System.out.print("    student id: ");
                    id = in.nextInt();
                    in.nextLine();
                    System.out.print("    assignment: ");
                    asst = in.nextLine();
                    System.out.print("    grade: ");
                    grade = in.nextInt();
                    in.nextLine();
                    
                    db.addGrade(id, asst, grade);
                    break;
                case 3:
                    db.printStudents();
                    break;
                case 4:
                    db.printGrades();
                    break;
                case 5:
                    db.printStudentsGrades();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.  " + 
                                       "Please enter a number from 1-6.");
            }
        }
    }
}
