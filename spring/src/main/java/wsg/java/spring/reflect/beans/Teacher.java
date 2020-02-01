package wsg.java.spring.reflect.beans;

/**
 * @author Kingen
 */
public class Teacher extends Person {

    private String teacherNo;

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public Teacher(int age, String name, SexEnum sex) {
        super(age, name, sex);
    }
}
