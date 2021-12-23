package cn.wsg.hello.spring.reflect;

import cn.wsg.hello.spring.reflect.beans.SexEnum;
import cn.wsg.hello.spring.reflect.beans.Student;
import cn.wsg.hello.spring.reflect.beans.Teacher;

/**
 * @author Kingen
 */
public class Main {

    public static void main(String[] args) {
        Student Jack = new Student(19, "Jack", SexEnum.MALE);
        Teacher Tom = new Teacher(23, "Tom", SexEnum.MALE);
        Tom.setTeacherNo("t001");
        Jack.setTeacher(Tom);
        Jack.setStudentNo("s001");
    }
}
