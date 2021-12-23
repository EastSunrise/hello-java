package cn.wsg.hello.spring.reflect.beans;

/**
 * @author Kingen
 */
public class Student extends Person{

    private Teacher teacher;

    private String studentNo;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public Student(int age, String name, SexEnum sex) {
        super(age, name, sex);
    }


}
