package grade_management.management_dao;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import grade_management.entity.course.Course;
import grade_management.entity.course_student.CourseStudent;
import grade_management.entity.letter_grades.EnglishLetterGrade;
import grade_management.entity.letter_grades.MathLetterGrade;
import grade_management.entity.letter_grades.PhysicsLetterGrade;
import grade_management.entity.student.Student;
import grade_management.entity.teacher.Teacher;
import grade_management.hbutil.HibernateUtil;
import grade_management.serializable.CourseStudentId;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.*;

public class ManagementDAO {
    HibernateUtil hbUtil = new HibernateUtil();

    public <T> List<T> listMidtermGrades(Class<T> gradeClass, String courseName) {
        Transaction transaction;
        Session session = hbUtil.getSessionFactory().openSession();
        List<T> listMidtermGrades = null;

        String newTableName = courseName.toLowerCase() + "_midterm";

        try (session) {
            transaction = session.beginTransaction();
            String sql = " SELECT * FROM " + newTableName;
            NativeQuery<T> query = session.createNativeQuery(sql, gradeClass);
            listMidtermGrades = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMidtermGrades;
    }

    public <T> List<T> listFinalGrades(Class<T> gradeClass, String courseName) {
        Transaction transaction;
        Session session = hbUtil.getSessionFactory().openSession();
        List<T> listFinalGrades = null;

        String newTableName = courseName.toLowerCase() + "_final";

        try (session) {
            transaction = session.beginTransaction();
            String sql = " SELECT * FROM " + newTableName;
            NativeQuery<T> query = session.createNativeQuery(sql, gradeClass);
            listFinalGrades = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listFinalGrades;
    }

    public <T> List<T> listLetterTableRecords(String courseName, Class<T> gradeClass) {
        Transaction transaction;
        Session session = hbUtil.getSessionFactory().openSession();
        List<T> gradeList = null;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM " + courseName.toLowerCase() + "_letter_grade";
            NativeQuery<T> query = session.createNativeQuery(sql, gradeClass);
            gradeList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gradeList;
    }

    public <T> void addStudentMidtermInfo(T midterm) {
        Transaction transaction;
        Session session = hbUtil.getSessionFactory().openSession();

        String courseName = getCourseNameForMidterm(midterm); // "math", "physics", "english"
        String course = capitalizeFirstLetter(courseName);
        String tableName = courseName + "_midterm"; //  math_midterm, physics_midterm, english_midterm
        String columnName = tableName + "_grade";

        try (session) {
            transaction = session.beginTransaction();
            String sql = "INSERT INTO " + tableName +
                    " (id, student_number, first_name, last_name, lecture, exam_type, " + columnName + ") " +
                    "VALUES (:id, :student_number, :first_name, :last_name, :lecture, :exam_type, :" + columnName + ")";
            NativeQuery<Void> query = session.createNativeQuery(sql, Void.class);

            Method getIdMethod = midterm.getClass().getMethod("getId");
            int idValue = (int) getIdMethod.invoke(midterm);
            query.setParameter("id", idValue);

            Method getStudentNumberMethod = midterm.getClass().getMethod("getStudentNumber");
            int studentNumberValue = (int) getStudentNumberMethod.invoke(midterm);
            query.setParameter("student_number", studentNumberValue);

            Method getFirstNameMethod = midterm.getClass().getMethod("getFirstName");
            String firstNameValue = (String) getFirstNameMethod.invoke(midterm);
            query.setParameter("first_name", firstNameValue);

            Method getLastNameMethod = midterm.getClass().getMethod("getLastName");
            String lastNameValue = (String) getLastNameMethod.invoke(midterm);
            query.setParameter("last_name", lastNameValue);


            Method getLectureMethod = midterm.getClass().getMethod("getLecture");
            String lectureValue = (String) getLectureMethod.invoke(midterm);
            query.setParameter("lecture", lectureValue);

            Method getExamTypeMethod = midterm.getClass().getMethod("getExamType");
            String examTypeValue = (String) getExamTypeMethod.invoke(midterm);
            query.setParameter("exam_type", examTypeValue);

            Method getMidtermGradeMethod = midterm.getClass().getMethod("get" + course + "MidtermGrade");
            int midtermGradeValue = (int) getMidtermGradeMethod.invoke(midterm);
            query.setParameter(courseName + "_midterm_grade", midtermGradeValue);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> void addStudentFinalInfo(T finals) {
        Transaction transaction;
        Session session = hbUtil.getSessionFactory().openSession();

        String courseName = getCourseNameForFinal(finals); // "math", "physics", "english"
        String course = capitalizeFirstLetter(courseName);
        String tableName = courseName + "_final"; //  math_midterm, physics_midterm, english_midterm
        String columnName = tableName + "_grade";

        try (session) {
            transaction = session.beginTransaction();
            String sql = "INSERT INTO " + tableName +
                    " (id, student_number, first_name, last_name, lecture, exam_type, " + columnName + ") " +
                    "VALUES (:id, :student_number, :first_name, :last_name, :lecture, :exam_type, :" + columnName + ")";
            NativeQuery<Void> query = session.createNativeQuery(sql, Void.class);

            Method getIdMethod = finals.getClass().getMethod("getId");
            int idValue = (int) getIdMethod.invoke(finals);
            query.setParameter("id", idValue);

            Method getStudentNumberMethod = finals.getClass().getMethod("getStudentNumber");
            int studentNumberValue = (int) getStudentNumberMethod.invoke(finals);
            query.setParameter("student_number", studentNumberValue);

            Method getFirstNameMethod = finals.getClass().getMethod("getFirstName");
            String firstNameValue = (String) getFirstNameMethod.invoke(finals);
            query.setParameter("first_name", firstNameValue);

            Method getLastNameMethod = finals.getClass().getMethod("getLastName");
            String lastNameValue = (String) getLastNameMethod.invoke(finals);
            query.setParameter("last_name", lastNameValue);


            Method getLectureMethod = finals.getClass().getMethod("getLecture");
            String lectureValue = (String) getLectureMethod.invoke(finals);
            query.setParameter("lecture", lectureValue);

            Method getExamTypeMethod = finals.getClass().getMethod("getExamType");
            String examTypeValue = (String) getExamTypeMethod.invoke(finals);
            query.setParameter("exam_type", examTypeValue);

            Method getMidtermGradeMethod = finals.getClass().getMethod("get" + course + "FinalGrade");
            int finalGradeValue = (int) getMidtermGradeMethod.invoke(finals);
            query.setParameter(courseName + "_final_grade", finalGradeValue);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String capitalizeFirstLetter(String courseName) {
        String firstWord = courseName.substring(0, 1).toUpperCase();
        String secondWord = courseName.substring(1).toLowerCase();
        return firstWord + secondWord;
    }


    public <T> String getCourseNameForMidterm(T midterm) {
        String name = midterm.getClass().getSimpleName();
        return name.replace("Midterm", "");
    }

    public <T> String getCourseNameForFinal(T finals) {
        String name = finals.getClass().getSimpleName();
        return name.replace("Final", "");
    }


    public <T> void addLetterGradeTableInfo(T letterGrade, String courseName) {
        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();

        try (session) {
            transaction = session.beginTransaction();

            // Construct the dynamic table name based on the course name
            String sql = "INSERT INTO " + courseName.toLowerCase() + "_letter_grade " +
                    "(id, student_number, first_name, last_name, lecture, " +
                    courseName.toLowerCase() + "_average_grade, " + courseName.toLowerCase() + "_letter_grade) " +
                    "VALUES (:id, :student_number, :first_name, :last_name, :lecture, " +
                    ":average_grade, :letter_grade)";

            @SuppressWarnings("unchecked")
            NativeQuery<T> query = session.createNativeQuery(sql, (Class<T>) letterGrade.getClass());

            query.setParameter("id", letterGrade.getClass().getMethod("getId").invoke(letterGrade));
            query.setParameter("student_number", letterGrade.getClass().getMethod("getStudentNumber").invoke(letterGrade));
            query.setParameter("first_name", letterGrade.getClass().getMethod("getFirstName").invoke(letterGrade));
            query.setParameter("last_name", letterGrade.getClass().getMethod("getLastName").invoke(letterGrade));
            query.setParameter("lecture", letterGrade.getClass().getMethod("getLecture").invoke(letterGrade));
            query.setParameter("average_grade", letterGrade.getClass().getMethod("getAverage" + courseName + "Grade").invoke(letterGrade));
            query.setParameter("letter_grade", letterGrade.getClass().getMethod("get" + courseName + "LetterGrade").invoke(letterGrade));
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    // Derslerin Midterm ya da Final tablolarınındaki notların update edilmesi
    public void updateMidtermOrFinalGrade(String tableName, String gradeColumnName, int studentId, int grade) {
        Transaction transaction = null;
        try (Session session = hbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sqlQuery = String.format("UPDATE %s SET %s = :grade WHERE id = :id", tableName, gradeColumnName);
            NativeQuery<Void> query = session.createNativeQuery(sqlQuery, Void.class);
            query.setParameter("id", studentId);
            query.setParameter("grade", grade);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // öğrenci midterm ya da final grade'ini course_student'a ekleme
    public void updateCourseStudentMidtermGrade(int studentId, int courseId, int midtermGrade) {
        Transaction transaction;
        Session session = hbUtil.getSessionFactory().openSession();

        try (session) {
            transaction = session.beginTransaction();
            String sql = "UPDATE course_student SET midterm_grade = :midterm_grade WHERE student_id = :student_id AND course_id = :course_id";
            NativeQuery<Void> query = session.createNativeQuery(sql, Void.class);
            query.setParameter("student_id", studentId);
            query.setParameter("course_id", courseId);
            query.setParameter("midterm_grade", midtermGrade);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCourseStudentFinalGrade(int studentId, int courseId, int finalGrade) {
        Transaction transaction;
        Session session = hbUtil.getSessionFactory().openSession();

        try (session) {
            transaction = session.beginTransaction();
            String sql = "UPDATE course_student SET final_grade = :final_grade WHERE student_id = :student_id AND course_id = :course_id";
            NativeQuery<Void> query = session.createNativeQuery(sql, Void.class);
            query.setParameter("student_id", studentId);
            query.setParameter("course_id", courseId);
            query.setParameter("final_grade", finalGrade);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> T selectStudentExam(Class<T> examType, int id) {
        T student = null;
        Transaction transaction = null;

        try (Session session = hbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            student = session.get(examType, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return student;
    }

        // DOESMIDTERMEXIST TEK METHOD. MathMidterm mathMidterm gibi.

    public <T> boolean doesExamExist(Class<T> examClass, int studentNumber) {
        String entityName = examClass.getSimpleName();
        String queryString = "SELECT COUNT(e) FROM " + entityName + " e WHERE e.studentNumber = :studentNumber";

        try (Session session = hbUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery(queryString, Long.class);
            query.setParameter("studentNumber", studentNumber);

            Long count = query.getSingleResult();
            return count != null && count > 0;
        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            throw new RuntimeException("Error checking existence of exam for " + entityName, e);
        }
    }


    public boolean doesMidtermExist(Class<?> midtermClass, int studentNumber) {
        return doesExamExist(midtermClass, studentNumber);
    }

    public boolean doesFinalExist(Class<?> finalClass, int studentNumber) {
        return doesExamExist(finalClass, studentNumber);
    }

    public double calculateWeightedAverage(int studentNumber, String courseName) {
        return weightedAverage(studentNumber, courseName);
    }

    private double weightedAverage(int studentNumber, String courseName) {
        double averageGrade = 0;
        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();
        try (session) {

            transaction = session.beginTransaction();

            String midtermTable = courseName.toLowerCase() + "_midterm";
            String finalTable = courseName.toLowerCase() + "_final";

            // For midterms
            String midtermQueryStr = "SELECT " + courseName.toLowerCase() + "_midterm_grade FROM " + midtermTable + " WHERE student_number = :student_number";
            NativeQuery<Integer> midtermQuery = session.createNativeQuery(midtermQueryStr, Integer.class);
            midtermQuery.setParameter("student_number", studentNumber);
            Integer midtermGrade = midtermQuery.uniqueResult();

            // For finals
            String finalQueryStr = "SELECT " + courseName.toLowerCase() + "_final_grade FROM " + finalTable + " WHERE student_number = :student_number";
            NativeQuery<Integer> finalQuery = session.createNativeQuery(finalQueryStr, Integer.class);
            finalQuery.setParameter("student_number", studentNumber);
            Integer finalGrade = finalQuery.uniqueResult();

            if (midtermGrade != null && finalGrade != null) {
                averageGrade = midtermGrade * 0.4 + finalGrade * 0.6;
            }
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return averageGrade;
    }


    public Student getStudentById(int id) {

        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();
        Student student = null;

        try (session) {
            transaction = session.beginTransaction();
            String sqlQuery = "SELECT id, first_name, last_name, student_number, identity_number, mail_address FROM students WHERE id = :id";
            NativeQuery<Student> query = session.createNativeQuery(sqlQuery, Student.class);
            query.setParameter("id", id);
            student = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }


    public String assignLetterGrade(Object letterGradeRecord, double mean, double sd, String courseName) {
        double averageGrade = 0;

        try {
            averageGrade = (double) letterGradeRecord.getClass()
                    .getMethod("getAverage" + courseName + "Grade")
                    .invoke(letterGradeRecord);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sd == 0) {
            return "AA";
        }
        if (averageGrade >= mean + sd) {
            return "AA";
        } else if (averageGrade >= mean + 0.7 * sd) {
            return "BA";
        } else if (averageGrade >= mean + 0.4 * sd) {
            return "BB";
        } else if (averageGrade >= mean) {
            return "CB";
        } else if (averageGrade >= mean - 0.4 * sd) {
            return "CC";
        } else if (averageGrade >= mean - 0.7 * sd) {
            return "DC";
        } else {
            return "DD";
        }
    }


    public String getStudentNumber(String mailAddress, String identityNumber) {
        Transaction transaction = null;
        String studentNumber = null;
        Session session = hbUtil.getSessionFactory().openSession();

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT student_number FROM students WHERE mail_address = :mail_address" +
                    " AND identity_number = :identity_number";
            NativeQuery<String> query = session.createNativeQuery(sql, String.class);
            query.setParameter("mail_address", mailAddress);
            query.setParameter("identity_number", identityNumber);
            studentNumber = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentNumber;
    }

    public void addAccountInDB(Student student) {
        Transaction transaction = null;

        Session session = hbUtil.getSessionFactory().openSession();

        try (session) {
            transaction = session.beginTransaction();
            String sql = "INSERT INTO students (id, first_name, last_name, identity_number, mail_address, student_number)" +
                    " VALUES (:id, :first_name, :last_name, :identity_number, :mail_address, :student_number)";
            NativeQuery<Student> query = session.createNativeQuery(sql, Student.class);
            query.setParameter("id", student.getId());
            query.setParameter("first_name", student.getFirstName());
            query.setParameter("last_name", student.getLastName());
            query.setParameter("identity_number", student.getIdentityNumber());
            query.setParameter("mail_address", student.getMailAddress());
            query.setParameter("student_number", student.getStudentNumber());

            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public boolean isAllDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isAllChars(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    public String studentNumberGeneration() {
        Random random = new Random();
        int generatedStudentNumber = random.nextInt(900000) + 100000;
        return String.valueOf(generatedStudentNumber);
    }

    public String teacherNumberGeneration() {
        Random random = new Random();
        long generatedStudentNumber = (long) (random.nextDouble() * 9000000000L) + 1000000000L;
        return String.valueOf(generatedStudentNumber);
    }


    public boolean authenticateStudentForLogin(int studentNumber, String mailAddress) {
        Session session = hbUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Student student = null;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM students WHERE student_number = :student_number AND mail_address = :mail_address";
            NativeQuery<Student> query = session.createNativeQuery(sql, Student.class);
            query.setParameter("student_number", studentNumber);
            query.setParameter("mail_address", mailAddress);
            student = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student != null;
    }

    public boolean authenticateStudentForNumberRenewal(String mailAddress, String identityNumber, String firstName) {
        Session session = hbUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Student student = null;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM students WHERE first_name = :first_name AND identity_number = :identity_number AND mail_address = :mail_address";
            NativeQuery<Student> query = session.createNativeQuery(sql, Student.class);
            query.setParameter("first_name", firstName);
            query.setParameter("identity_number", identityNumber);
            query.setParameter("mail_address", mailAddress);
            student = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student != null;
    }


    public boolean authenticateTeacherForLogin(String teacherNumber, String mailAddress) {
        Session session = hbUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Teacher teacher = null;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM teachers WHERE teacher_number = :teacher_number AND mail_address = :mail_address";
            NativeQuery<Teacher> query = session.createNativeQuery(sql, Teacher.class);
            query.setParameter("teacher_number", teacherNumber);
            query.setParameter("mail_address", mailAddress);
            teacher = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacher != null;
    }


    public boolean authenticateTeacherForNumberRenewal(String mailAddress, String identityNumber, String firstName) {
        Session session = hbUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Teacher teacher = null;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM teachers WHERE first_name = :first_name AND identity_number = :identity_number AND mail_address = :mail_address";
            NativeQuery<Teacher> query = session.createNativeQuery(sql, Teacher.class);
            query.setParameter("first_name", firstName);
            query.setParameter("identity_number", identityNumber);
            query.setParameter("mail_address", mailAddress);
            teacher = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacher != null;
    }


    public Teacher getTeacherByCredentials(String teacherNumber, String mailAddress) {
        Session session = hbUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Teacher teacher = null;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM teachers WHERE teacher_number = :teacher_number AND mail_address = :mail_address";
            NativeQuery<Teacher> query = session.createNativeQuery(sql, Teacher.class);
            query.setParameter("teacher_number", teacherNumber);
            query.setParameter("mail_address", mailAddress);
            teacher = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacher;
    }

    public Student getStudentByIdentityNumber(String identityNumber) {
        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();
        Student student = null;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM students WHERE identity_number = :identity_number ";
            NativeQuery<Student> query = session.createNativeQuery(sql, Student.class);
            query.setParameter("identity_number", identityNumber);
            student = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    public Student getStudentByStudentNumber(int studentNumber) {
        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();
        Student student = null;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM students WHERE student_number = :student_number ";
            NativeQuery<Student> query = session.createNativeQuery(sql, Student.class);
            query.setParameter("student_number", studentNumber);
            student = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }


    public Teacher getTeacherByIdentityNumber(String identityNumber) {
        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();
        Teacher teacher = null;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM teachers WHERE identity_number = :identity_number ";
            NativeQuery<Teacher> query = session.createNativeQuery(sql, Teacher.class);
            query.setParameter("identity_number", identityNumber);
            teacher = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teacher;
    }


    public void updateStudent(int studentNumber, Student student) {

        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();

        try (session) {
            transaction = session.beginTransaction();
            String sql = "UPDATE students SET student_number = :student_number WHERE identity_number = :identity_number";
            NativeQuery<Student> query = session.createNativeQuery(sql, Student.class);
            query.setParameter("student_number", studentNumber);
            query.setParameter("identity_number", student.getIdentityNumber());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTeacher(String teacherNumber, Teacher teacher) {

        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();

        try (session) {
            transaction = session.beginTransaction();
            String sql = "UPDATE teachers SET teacher_number = :teacher_number WHERE identity_number = :identity_number";
            NativeQuery<Teacher> query = session.createNativeQuery(sql, Teacher.class);
            query.setParameter("teacher_number", teacherNumber);
            query.setParameter("identity_number", teacher.getIdentityNumber());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllIdentityNumbers() {
        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();
        List<String> idList = null;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT identity_number FROM students";
            NativeQuery<String> query = session.createNativeQuery(sql, String.class);
            idList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idList;
    }

    public List<String> getAllMailAddresses() {
        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();
        List<String> mailList = null;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT mail_address FROM students";
            NativeQuery<String> query = session.createNativeQuery(sql, String.class);
            mailList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mailList;
    }


    public void addIdsToCourseStudent(CourseStudent courseStudent) {
        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();
        try (session) {
            transaction = session.beginTransaction();
            Course course = courseStudent.getCourse();
            if (course != null) {
                if (session.contains(course)) {
                    int courseId = course.getId();
                    course = session.get(Course.class, courseId);
                } else {
                    course = session.merge(course);
                }
                courseStudent.setCourse(course);
            }

            Student student = courseStudent.getStudent();
            if (student != null) {
                if (!session.contains(student)) {
                    student = session.merge(student);
                    courseStudent.setStudent(student);
                }
            }
            if (courseStudent.getId() == null) {
                assert course != null;
                assert student != null;
                courseStudent.setId(new CourseStudentId(course.getId(), student.getId()));
            }

            if (courseStudent.getLetterGrade() == null ||
                    courseStudent.getMidtermGrade() == null ||
                    courseStudent.getFinalGrade() == null) {
                courseStudent.setLetterGrade(null);
                courseStudent.setMidtermGrade(null);
                courseStudent.setFinalGrade(null);
            }
            session.persist(courseStudent);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Course getCourseById(int id) {
        Transaction transaction;
        Session session = hbUtil.getSessionFactory().openSession();
        Course course = null;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM courses WHERE id = :id";
            NativeQuery<Course> query = session.createNativeQuery(sql, Course.class);
            query.setParameter("id", id);
            course = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return course;
    }

    public List<Course> getAllCourses() {
        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();
        List<Course> courseList = null;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM courses";
            NativeQuery<Course> query = session.createNativeQuery(sql, Course.class);
            courseList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseList;
    }


    public List<CourseStudent> getCourseStudentInformation(int id) {
        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();
        List<CourseStudent> studentInformation = null;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM course_student WHERE student_id = :id";
            NativeQuery<CourseStudent> query = session.createNativeQuery(sql, CourseStudent.class);
            query.setParameter("id", id);
            studentInformation = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentInformation;
    }

    public String getCourseNameById(int id) {
        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();
        String courseName = null;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT course_name FROM courses WHERE id = :id";
            NativeQuery<String> query = session.createNativeQuery(sql, String.class);
            query.setParameter("id", id);
            courseName = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseName;
    }


    public int getCourseIdByName(String lectureName) {
        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();
        int courseId = 0;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT id FROM courses WHERE course_name = :course_name";
            NativeQuery<Integer> query = session.createNativeQuery(sql, Integer.class);
            query.setParameter("course_name", lectureName);
            courseId = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseId;
    }


    public void addTeacherStudentAssociation(int teacherId, int studentId) {
        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();
        try (session) {
            transaction = session.beginTransaction();
            String sql = "INSERT INTO teacher_student (student_id, teacher_id) VALUES (:student_id, :teacher_id)";
            NativeQuery<Integer> query = session.createNativeQuery(sql, Integer.class);
            query.setParameter("student_id", studentId);
            query.setParameter("teacher_id", teacherId);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int getCurrentTeacherId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            if (teacher != null) {
                return teacher.getId();
            }
        }
        return -1;
    }


    public Course getCourseByTeacherId(int currentTeacherId) {
        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();
        Course course = null;

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM courses WHERE teacher_id = :teacher_id";
            NativeQuery<Course> query = session.createNativeQuery(sql, Course.class);
            query.setParameter("teacher_id", currentTeacherId);
            course = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return course;
    }


    public MultiValuedMap<Integer, Integer> getCourseStudentIdPairs() {
        Transaction transaction;
        Session session = hbUtil.getSessionFactory().openSession();
        MultiValuedMap<Integer, Integer> ids = new ArrayListValuedHashMap<>();

        try (session) {
            transaction = session.beginTransaction();
            String sql = "SELECT cs.courseStudentId.courseId, cs.courseStudentId.studentId FROM CourseStudent cs";

            SelectionQuery<Object[]> query = session.createSelectionQuery(sql, Object[].class);
            List<Object[]> resultList = query.getResultList();

            for (Object[] result : resultList) {
                Integer courseId = (Integer) result[0];
                Integer studentId = (Integer) result[1];
                ids.put(courseId, studentId);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }

    public void updateCourseStudentLetterGrade(int studentId, String letterGrade, int courseId, String courseName) {
        Transaction transaction = null;

        try (Session session = hbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "UPDATE course_student SET letter_grade = :letter_grade " +
                    "WHERE student_id = :student_id AND course_id = :course_id";
            NativeQuery<Void> query = session.createNativeQuery(sql, Void.class);
            query.setParameter("letter_grade", letterGrade);
            query.setParameter("student_id", studentId);
            query.setParameter("course_id", courseId);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateLetterGrade(int id, String letterGrade, String courseName) {
        Transaction transaction;
        Session session = hbUtil.getSessionFactory().openSession();

        try (session) {
            transaction = session.beginTransaction();
            String sql = "UPDATE " + courseName + "_letter_grade SET " +
                    courseName + "_letter_grade = :letter_grade WHERE id = :id";
            NativeQuery<Void> query = session.createNativeQuery(sql, Void.class);
            query.setParameter("id", id);
            query.setParameter("letter_grade", letterGrade);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public void addCourseStudentAverage(double mathAverage, int courseId, int studentId) {
        Transaction transaction = null;
        Session session = hbUtil.getSessionFactory().openSession();
        try (session) {
            transaction = session.beginTransaction();

            String sql = "UPDATE course_student SET average_grade = :average_grade " +
                    "WHERE course_id = :course_id AND student_id = :student_id";

            NativeQuery<CourseStudent> query = session.createNativeQuery(sql, CourseStudent.class);
            query.setParameter("average_grade", mathAverage);
            query.setParameter("course_id", courseId);
            query.setParameter("student_id", studentId);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public double calculateMean(List<?> records, String lecture) {
        double total = 0;
        for (Object record : records) {
            total += getAverageGrade(record, lecture);
        }
        return total / records.size();
    }

    public double calculateStandardDeviation(List<?> records, double mean, String lecture) {
        double sumOfSquaredDifferences = 0;
        for (Object record : records) {
            double diff = getAverageGrade(record, lecture) - mean;
            sumOfSquaredDifferences += diff * diff;
        }
        return Math.sqrt(sumOfSquaredDifferences / records.size());
    }

    public double getAverageGrade(Object record, String lecture) {
        switch (lecture.toLowerCase()) {
            case "math":
                return ((MathLetterGrade) record).getAverageMathGrade();
            case "english":
                return ((EnglishLetterGrade) record).getAverageEnglishGrade();
            case "physics":
                return ((PhysicsLetterGrade) record).getAveragePhysicsGrade();
            default:
                throw new IllegalArgumentException("Lecture not found!! : " + lecture);
        }
    }

    public int getRecordId(Object record) {
        if (record instanceof MathLetterGrade) {
            return ((MathLetterGrade) record).getId();
        } else if (record instanceof EnglishLetterGrade) {
            return ((EnglishLetterGrade) record).getId();
        } else if (record instanceof PhysicsLetterGrade) {
            return ((PhysicsLetterGrade) record).getId();
        }
        throw new IllegalArgumentException("Unsupported record type: " + record.getClass().getName());
    }


    public <T> void updateCourseStudentAverageAndLetterGrade(T letterGrade, int courseId, int studentId, String courseName) {
        Transaction transaction;
        Session session = hbUtil.getSessionFactory().openSession();

        try (session) {
            transaction = session.beginTransaction();

            String sql = "UPDATE course_student SET average_grade = :average_grade, letter_grade = :letter_grade " +
                    "WHERE course_id = :course_id AND student_id = :student_id";

            NativeQuery<?> query = session.createNativeQuery(sql, CourseStudent.class);
            query.setParameter("course_id", courseId);
            query.setParameter("student_id", studentId);

            query.setParameter("average_grade", letterGrade.getClass().getMethod("getAverage" + courseName + "Grade").invoke(letterGrade));
            query.setParameter("letter_grade", letterGrade.getClass().getMethod("get" + courseName + "LetterGrade").invoke(letterGrade));
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public void updateLetterGradeTableAverageGrade(double averageGrade, int studentId, String courseName) {
        Transaction transaction;
        Session session = hbUtil.getSessionFactory().openSession();

        try (session) {
            transaction = session.beginTransaction();
            String sql = "UPDATE " + courseName.toLowerCase() + "_letter_grade" +
                    " SET " + courseName.toLowerCase() + "_average_grade = :averageGrade " +
                    "WHERE id = :id";
            NativeQuery<Void> query = session.createNativeQuery(sql, Void.class);
            query.setParameter("id", studentId);
            query.setParameter("averageGrade", averageGrade);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCourseStudentAverageGrade(double averageGrade, int studentId, int courseId) {
        Transaction transaction;
        Session session = hbUtil.getSessionFactory().openSession();

        try (session) {
            transaction = session.beginTransaction();
            String sql = "UPDATE course_student SET average_grade = :average_grade" +
                    " WHERE course_id = :course_id AND student_id = :student_id";
            NativeQuery<CourseStudent> query = session.createNativeQuery(sql, CourseStudent.class);
            query.setParameter("student_id", studentId);
            query.setParameter("course_id", courseId);
            query.setParameter("average_grade", averageGrade);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
