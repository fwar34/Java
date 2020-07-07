public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
        String name = "World";
        StringBuilder sb = new StringBuilder();
        sb.append("Hello, ").append(name).append("!");
        System.out.println(sb.toString());
    }
}

class Test {
    public final String name = "TEST";
}
