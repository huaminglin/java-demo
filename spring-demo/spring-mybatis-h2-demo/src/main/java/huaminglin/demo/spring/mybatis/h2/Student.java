package huaminglin.demo.spring.mybatis.h2;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = -1330254167728480569L;
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "Student[name=" + name + ", " + "age=" + age + "]";
    }
}
