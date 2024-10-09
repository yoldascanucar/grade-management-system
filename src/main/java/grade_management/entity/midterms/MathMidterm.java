package grade_management.entity.midterms;
import jakarta.persistence.*;
import grade_management.entity.student.Student;

@Entity
@Table(name = "math_midterm")
public class MathMidterm {

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

    @Column(name = "math_midterm_grade", nullable = false)
    private int mathMidtermGrade;

    @Column(name = "exam_type", nullable = false)
    private String examType;

    @OneToOne
    @JoinColumn(name = "student_number", insertable = false, updatable = false)
    private Student student;

    public MathMidterm() {
    }

    public MathMidterm(String firstName, String lastName, int mathMidtermGrade, String lecture, int studentNumber, String examType, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mathMidtermGrade = mathMidtermGrade;
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

    public int getMathMidtermGrade() {
        return mathMidtermGrade;
    }

    public void setMathMidtermGrade(int mathMidtermGrade) {
        this.mathMidtermGrade = mathMidtermGrade;
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


    @Override
    public String toString() {
        return "MathMidterm{" +
                "  firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lecture='" + lecture + '\'' +
                ", mathMidtermGrade=" + mathMidtermGrade +
                ", studentNumber=" + studentNumber +
                ", examType='" + examType + '\'' +
                '}';
    }
}

