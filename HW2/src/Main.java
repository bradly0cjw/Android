import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
       sol4();

    }
    public static void sol1() {
        String rawData = """
                    int[][] ary = {
                      {1, 1, 1, 1, 1, 1, 1, 1, 1},
                      {1, 1, 0, 0, 0, 0, 0, 0, 1},
                      {1, 0, 1, 0, 0, 0, 0, 1, 1},
                      {1, 0, 0, 1, 0, 0, 1, 1, 1},
                      {1, 1, 1, 1, 1, 1, 1, 1, 1},
                      {1, 0, 0, 1, 0, 0, 1, 1, 1},
                      {1, 0, 1, 0, 0, 0, 0, 1, 1},
                      {1, 1, 0, 0, 0, 0, 0, 0, 1},
                      {1, 1, 1, 1, 1, 1, 1, 1, 1}
                    };
                """;
        String digitsAndNewlines = rawData.replaceAll("[^01\\n]", "");
        String compact = Arrays.stream(digitsAndNewlines.split("\\R"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining("\n"));
        System.out.println(compact);
    }
    public static void sol2() {
        String compact = """
                        111111111
                        110000001
                        101000011
                        100100111
                        111111111
                        100100111
                        101000011
                        110000001
                        111111111
                        """;
        String[] lines = compact.split("\\R");
        StringBuilder sb = new StringBuilder();
        sb.append("int[][] ary = {\n");
        for (int i = 0; i < lines.length; i++) {
            sb.append("  {");
            for (int j = 0; j < lines[i].length(); j++) {
                sb.append(lines[i].charAt(j));
                if (j != lines[i].length() - 1) sb.append(", ");
            }
            sb.append("}");
            if (i != lines.length - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("};");
        System.out.println(sb.toString());
    }
    public static void sol3() {
        Triangle t1 = new Triangle(3, 4, 5);
        System.out.printf("Triangle area: %.2f\n", t1.area());
        Triangle t2 = new Triangle(3, 2, 5);
        System.out.printf("Triangle area: %.2f\n", t2.area());
    }
    public static void sol4() {
        Account acc = new Account("王大同", 1000);
        acc.deposit(500);
        acc.withdraw(200);
        acc.chkBalance();
        acc.withdraw(2000);
        acc.deposit(-100);
        acc.withdraw(-50);
        acc.showInfo();
    }
}