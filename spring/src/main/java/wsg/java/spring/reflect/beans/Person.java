package wsg.java.spring.reflect.beans;

/**
 * @author Kingen
 */
public class Person {
    private int age;

    private String name;

    private SexEnum sex;

    public Person(int age, String name, SexEnum sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }
}
