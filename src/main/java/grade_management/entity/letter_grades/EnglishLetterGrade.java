package grade_management.entity.letter_grades;
import grade_management.entity.student.Student;
import jakarta.persistence.*;


@Entity
@Table(name = "english_letter_grade")
public class EnglishLetterGrade {

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

    @Column(name = "english_average_grade")
    private Double averageEnglishGrade;

    @Column(name = "english_letter_grade")
    private String englishLetterGrade;

    @OneToOne
    @JoinColumn(name = "student_number", insertable = false, updatable = false)
    private Student student;

    public EnglishLetterGrade() {};

    public EnglishLetterGrade(int studentNumber, double averageEnglishGrade) {
        this.studentNumber = studentNumber;
        this.averageEnglishGrade = averageEnglishGrade;
    }


    public EnglishLetterGrade(int studentNumber, String firstName, String lastName, double averageEnglishGrade, String englishLetterGrade, String lecture, int id) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.averageEnglishGrade = averageEnglishGrade;
        this.englishLetterGrade = englishLetterGrade;
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

    public Double getAverageEnglishGrade() {
        return averageEnglishGrade;
    }

    public void setAverageEnglishGrade(Double averageEnglishGrade) {
        this.averageEnglishGrade = averageEnglishGrade;
    }

    public String getEnglishLetterGrade() {
        return englishLetterGrade;
    }

    public void setEnglishLetterGrade(String englishLetterGrade) {
        this.englishLetterGrade = englishLetterGrade;
    }

    @Override
    public String toString() {
        return "EnglishLetterGrades{" +
                "id=" + id +
                ", studentNumber='" + studentNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lecture='" + lecture + '\'' +
                ", averageEnglishGrade=" + averageEnglishGrade +
                ", englishLetterGrade='" + englishLetterGrade + '\'' +
                '}';
    }
}
