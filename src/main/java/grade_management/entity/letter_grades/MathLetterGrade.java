package grade_management.entity.letter_grades;
import jakarta.persistence.*;
import grade_management.entity.student.Student;


@Entity
@Table(name = "math_letter_grade")
public class MathLetterGrade {

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

    @Column(name = "math_average_grade")
    private Double averageMathGrade;

    @Column(name = "math_letter_grade")
    private String mathLetterGrade;

    @OneToOne
    @JoinColumn(name = "student_number", insertable = false, updatable = false)
    private Student student;

    public MathLetterGrade() {};

    public MathLetterGrade(int studentNumber, double averageMathGrade) {
        this.studentNumber = studentNumber;
        this.averageMathGrade = averageMathGrade;
    }


    public MathLetterGrade(int studentNumber, String firstName, String lastName, double averageMathGrade, String mathLetterGrade, String lecture, int id) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.averageMathGrade = averageMathGrade;
        this.mathLetterGrade = mathLetterGrade;
        this.lecture = lecture;
        this.id = id;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

    public double getAverageMathGrade() {
        return averageMathGrade;
    }

    public void setAverageMathGrade(Double averageMathGrade) {
        this.averageMathGrade = averageMathGrade;
    }

    public String getMathLetterGrade() {
        return mathLetterGrade;
    }

    public void setMathLetterGrade(String mathLetterGrade) {
        this.mathLetterGrade = mathLetterGrade;
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

    @Override
    public String toString() {
        return " MathLetterGrades{" +
                "studentNumber=" + studentNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lecture='" + lecture + '\'' +
                ", averageMathGrade=" + averageMathGrade +
                ", mathLetterGrade='" + mathLetterGrade + '\'' +
                '}';
    }
}
