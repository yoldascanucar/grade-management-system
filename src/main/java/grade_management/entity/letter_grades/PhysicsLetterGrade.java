package grade_management.entity.letter_grades;

import jakarta.persistence.*;
import grade_management.entity.student.Student;


@Entity
@Table(name = "physics_letter_grade")
public class PhysicsLetterGrade {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "student_number")
    private int studentNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "lecture")
    private String lecture;

    @Column(name = "physics_average_grade")
    private Double averagePhysicsGrade;

    @Column(name = "physics_letter_grade")
    private String physicsLetterGrade;

    @OneToOne
    @JoinColumn(name = "student_number", insertable = false, updatable = false)
    private Student student;

    public PhysicsLetterGrade() {};

    public PhysicsLetterGrade(int studentNumber, double averagePhysicsGrade) {
        this.studentNumber = studentNumber;
        this.averagePhysicsGrade = averagePhysicsGrade;
    }


    public PhysicsLetterGrade(int studentNumber, String firstName, String lastName, double averagePhysicsGrade, String physicsLetterGrade, String lecture, int id) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.averagePhysicsGrade = averagePhysicsGrade;
        this.physicsLetterGrade = physicsLetterGrade;
        this.lecture = lecture;
        this.id = id;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public Double getAveragePhysicsGrade() {
        return averagePhysicsGrade;
    }

    public void setAveragePhysicsGrade(Double averagePhysicsGrade) {
        this.averagePhysicsGrade = averagePhysicsGrade;
    }

    public String getPhysicsLetterGrade() {
        return physicsLetterGrade;
    }

    public void setPhysicsLetterGrade(String physicsLetterGrade) {
        this.physicsLetterGrade = physicsLetterGrade;
    }
}
