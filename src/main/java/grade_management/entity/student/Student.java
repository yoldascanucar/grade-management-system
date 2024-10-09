    package grade_management.entity.student;
    import java.util.Set;

    import grade_management.entity.course_student.CourseStudent;
    import grade_management.entity.finals.EnglishFinal;
    import grade_management.entity.finals.MathFinal;
    import grade_management.entity.finals.PhysicsFinal;
    import grade_management.entity.letter_grades.MathLetterGrade;
    import grade_management.entity.letter_grades.PhysicsLetterGrade;
    import grade_management.entity.midterms.EnglishMidterm;
    import grade_management.entity.midterms.MathMidterm;
    import grade_management.entity.midterms.PhysicsMidterm;
    import grade_management.entity.teacher.Teacher;
    import jakarta.persistence.*;
    import grade_management.entity.letter_grades.EnglishLetterGrade;


    @Entity
    @Table(name = "students")
    public class Student {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "first_name", nullable = false)
        private String firstName;

        @Column(name = "last_name", nullable = false)
        private String lastName;

        @Column(name = "student_number", nullable = false, unique = true)
        private int studentNumber;

        @Column(name = "identity_number", nullable = false, unique = true)
        private String identityNumber;

        @Column(name = "mail_address", nullable = false)
        private String mailAddress;

        @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private Set<CourseStudent> courseStudents;

        @ManyToMany(mappedBy = "students")
        private Set<Teacher> teachers;

        @OneToOne(mappedBy = "student")
        private MathMidterm mathMidterm;

        @OneToOne(mappedBy = "student")
        private MathFinal mathFinal;

        @OneToOne(mappedBy = "student")
        private MathLetterGrade mathLetter;

        @OneToOne(mappedBy = "student")
        private EnglishMidterm englishMidterm;

        @OneToOne(mappedBy = "student")
        private EnglishFinal englishFinal;

        @OneToOne(mappedBy = "student")
        private EnglishLetterGrade englishLetter;

        @OneToOne(mappedBy = "student")
        private PhysicsMidterm physicsMidterm;

        @OneToOne(mappedBy = "student")
        private PhysicsFinal physicsFinal;

        @OneToOne(mappedBy = "student")
        private PhysicsLetterGrade physicsLetter;


        public Student() {
        }

        public Student(int id, String firstName, String lastName, int studentNumber, String identityNumber, String mailAddress) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.studentNumber = studentNumber;
            this.identityNumber = identityNumber;
            this.mailAddress = mailAddress;
        }

        public Student(String firstName, String lastName, int studentNumber, String identityNumber, String mailAddress) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.studentNumber = studentNumber;
            this.identityNumber = identityNumber;
            this.mailAddress = mailAddress;
        }

        public Set<Teacher> getTeachers() {
            return teachers;
        }

        public void setTeachers(Set<Teacher> teachers) {
            this.teachers = teachers;
        }

        public MathMidterm getMathMidterm() {
            return mathMidterm;
        }

        public void setMathMidterm(MathMidterm mathMidterm) {
            this.mathMidterm = mathMidterm;
        }

        public MathFinal getMathFinal() {
            return mathFinal;
        }

        public void setMathFinal(MathFinal mathFinal) {
            this.mathFinal = mathFinal;
        }

        public MathLetterGrade getMathLetter() {
            return mathLetter;
        }

        public void setMathLetter(MathLetterGrade mathLetter) {
            this.mathLetter = mathLetter;
        }

        public EnglishMidterm getEnglishMidterm() {
            return englishMidterm;
        }

        public void setEnglishMidterm(EnglishMidterm englishMidterm) {
            this.englishMidterm = englishMidterm;
        }

        public EnglishFinal getEnglishFinal() {
            return englishFinal;
        }

        public void setEnglishFinal(EnglishFinal englishFinal) {
            this.englishFinal = englishFinal;
        }

        public EnglishLetterGrade getEnglishLetter() {
            return englishLetter;
        }

        public void setEnglishLetter(EnglishLetterGrade englishLetter) {
            this.englishLetter = englishLetter;
        }

        public PhysicsMidterm getPhysicsMidterm() {
            return physicsMidterm;
        }

        public void setPhysicsMidterm(PhysicsMidterm physicsMidterm) {
            this.physicsMidterm = physicsMidterm;
        }

        public PhysicsFinal getPhysicsFinal() {
            return physicsFinal;
        }

        public void setPhysicsFinal(PhysicsFinal physicsFinal) {
            this.physicsFinal = physicsFinal;
        }

        public PhysicsLetterGrade getPhysicsLetter() {
            return physicsLetter;
        }

        public void setPhysicsLetter(PhysicsLetterGrade physicsLetter) {
            this.physicsLetter = physicsLetter;
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

        public int getStudentNumber() {
            return studentNumber;
        }

        public void setStudentNumber(int studentNumber) {
            this.studentNumber = studentNumber;
        }

        public String getIdentityNumber() {
            return identityNumber;
        }

        public void setIdentityNumber(String identityNumber) {
            this.identityNumber = identityNumber;
        }

        public String getMailAddress() {
            return mailAddress;
        }

        public void setMailAddress(String mailAddress) {
            this.mailAddress = mailAddress;
        }

        public Set<CourseStudent> getCourseStudents() {
            return courseStudents;
        }

        public void setCourseStudents(Set<CourseStudent> courseStudents) {
            this.courseStudents = courseStudents;
        }
    }



