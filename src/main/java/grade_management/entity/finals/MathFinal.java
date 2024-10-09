package grade_management.entity.finals;

import jakarta.persistence.*;
import grade_management.entity.student.Student;

@Entity
@Table(name = "math_final")
public class MathFinal {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "student_number", nullable = false)
    private int studentNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "lecture", nullable = false)
    private String lecture;

    @Column(name = "math_final_grade", nullable = false)
    private int mathFinalGrade;

    @Column(name = "exam_type", nullable = false)
    private String examType;

    @OneToOne
    @JoinColumn(name = "student_number", insertable = false, updatable = false)
    private Student student;

    public MathFinal() {
    }

    public MathFinal(String firstName, String lastName, int mathFinalGrade, String lecture, int studentNumber, String examType, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mathFinalGrade = mathFinalGrade;
        this.lecture = lecture;
        this.studentNumber = studentNumber;
        this.examType = examType;
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

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }


    public int getMathFinalGrade() {
        return mathFinalGrade;
    }

    public void setMathFinalGrade(int mathFinalGrade) {
        this.mathFinalGrade = mathFinalGrade;
    }

    @Override
    public String toString() {
        return "MathMidterm{" +
                "  firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lecture='" + lecture + '\'' +
                ", mathFinalGrade=" + mathFinalGrade +
                ", studentNumber=" + studentNumber +
                ", examType='" + examType + '\'' +
                '}';
    }
}
