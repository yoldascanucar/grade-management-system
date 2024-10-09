package grade_management.entity.course;

import grade_management.entity.course_student.CourseStudent;
import jakarta.persistence.*;
import grade_management.entity.teacher.Teacher;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name= "credit", nullable = false)
    private String credit;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CourseStudent> courseStudents;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Course() {
    }

    public Course(int id, String courseName, String credit) {
        this.id = id;
        this.courseName = courseName;
        this.credit = credit;
    }

    public Course(String courseName, String credit) {
        this.courseName = courseName;
        this.credit = credit;
    }


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public Set<CourseStudent> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(Set<CourseStudent> courseStudents) {
        this.courseStudents = courseStudents;
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", credit='" + credit + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id &&
                Objects.equals(courseName, course.courseName) &&
                Objects.equals(credit, course.credit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, credit);
    }

}


