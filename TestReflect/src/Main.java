import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws Exception {
        Class cls = Student.class;
        //获取字段Field
        System.out.println(cls.getField("score"));
        System.out.println(cls.getField("name"));
        System.out.println(cls.getDeclaredField("grade"));
        //获取字段的值
        Engineer p = new Engineer("Xiaoming");
        Class c = p.getClass();
        Field f = c.getDeclaredField("name");
        f.setAccessible(true);
        Object value = f.get(p);
        System.out.println(value);
        //设置字段的值
        f.set(p, "Xiaohong");
        System.out.println(p.getName());
    }
}

class Person {
    public String name;
}

class Student extends Person {
    public int score;
    private int grade;
}

class Engineer {
    private String name;
    public Engineer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
