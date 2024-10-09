package grade_management.entity.course_student;

import jakarta.persistence.*;
import grade_management.entity.student.Student;
import grade_management.entity.course.Course;
import grade_management.serializable.CourseStudentId;

@Entity
@Table(name = "course_student")
public class CourseStudent {

    @EmbeddedId
    private CourseStudentId courseStudentId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    @MapsId("studentId")
    private Student student;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    @MapsId("courseId")
    private Course course;

    @Column(name = "midterm_grade")
    private Integer midtermGrade;

    @Column(name = "final_grade")
    private Integer finalGrade;

    @Column(name = "average_grade")
    private Double averageGrade;

    @Column(name = "letter_grade")
    private String letterGrade;

    public CourseStudent() {}

    public CourseStudent(Course course, Student student) {
        this.course = course;
        this.student = student;
        this.courseStudentId = new CourseStudentId(course.getId(), student.getId());
    }

    public CourseStudent(Student student, Course course, int midtermGrade, int finalGrade, Double averageGrade, String letterGrade) {
        this.student = student;
        this.course = course;
        this.midtermGrade = midtermGrade;
        this.finalGrade = finalGrade;
        this.averageGrade = averageGrade;
        this.letterGrade = letterGrade;
        this.courseStudentId = new CourseStudentId(course.getId(), student.getId());
    }



    // Getters and Setters

    public CourseStudentId getCourseStudentId() {
        return courseStudentId;
    }

    public void setCourseStudentId(CourseStudentId courseStudentId) {
        this.courseStudentId = courseStudentId;
    }

    public CourseStudentId getId() {
        return courseStudentId;
    }

    public void setId(CourseStudentId id) {
        this.courseStudentId = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getMidtermGrade() {
        return midtermGrade;
    }

    public void setMidtermGrade(Integer midtermGrade) {
        this.midtermGrade = midtermGrade;
    }

    public Integer getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Integer finalGrade) {
        this.finalGrade = finalGrade;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

}

