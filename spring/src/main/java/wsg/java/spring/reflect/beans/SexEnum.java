package wsg.java.spring.reflect.beans;

/**
 * @author Kingen
 */
public enum SexEnum {
    MALE("0", "男"),
    FEMALE("1", "女");

    private String code;

    private String name;

    SexEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
