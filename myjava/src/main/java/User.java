public class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void say(String content) {
        System.out.println(content + " " + name);
    }

    private String name;
}
