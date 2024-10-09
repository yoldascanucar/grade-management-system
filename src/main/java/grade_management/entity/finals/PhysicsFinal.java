package grade_management.entity.finals;

import jakarta.persistence.*;
import grade_management.entity.student.Student;

@Entity
@Table(name = "physics_final")
public class PhysicsFinal {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "lecture")
    private String lecture;

    @Column(name = "physics_final_grade")
    private int physicsFinalGrade;

    @Column(name = "student_number")
    private int studentNumber;

    @Column(name = "exam_type", nullable = false)
    private String examType;

    @OneToOne
    @JoinColumn(name = "student_number", insertable = false, updatable = false)
    private Student student;

    public PhysicsFinal() {
    }

    public PhysicsFinal(String firstName, String lastName, int physicsFinalGrade, String lecture, int studentNumber, String examType, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.physicsFinalGrade = physicsFinalGrade;
        this.lecture = lecture;
        this.studentNumber = studentNumber;
        this.id = id;
        this.examType = examType;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
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

    public int getPhysicsFinalGrade() {
        return physicsFinalGrade;
    }

    public void setPhysicsFinalGrade(int physicsFinalGrade) {
        this.physicsFinalGrade = physicsFinalGrade;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Midterm{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lecture='" + lecture + '\'' +
                ", physicsFinalGrade=" + physicsFinalGrade +
                ", studentNumber=" + studentNumber +
                '}';
    }
}
