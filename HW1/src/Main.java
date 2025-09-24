public class Main {
    public static void main(String[] args) {
       sol5();
    }
    public static void sol1() {
        String raw = """
                成婉財 27 91 21 33 13
                翁雅婷 96 90 40 55 69
                袁維茹 38 85 72 13 34
                黃士哲 81 40 24 93 79
                郭珮珊 72 33 32 83 73
                陳儀S琬 78 55 22 41 62
                李碧彥 30 48 13 93 70
                梁健玉 23 89 10 44 24
                許雅淑 90 11 33 27 67
                蕭宛新 29 64 64 90 43
                """;

        raw = raw.strip();
        for (String line : raw.split("\\R")) {
            line = line.strip();
            if (line.isEmpty()) continue;
            int idx = line.indexOf(' ');
            if (idx == -1) {
                System.out.println(line);
                continue;
            }
            String name = line.substring(0, idx);
            String nums = line.substring(idx + 1).trim();
            String[] parts = nums.split("\\s+");
            System.out.println(name);
            System.out.println(String.join(", ", parts));
        }
    }
    public static void sol2() {
        System.out.println("Solution 2 executed");
        int base = 9;
        for (int i = 1; i <= base ; i++) {
            for (int j = 1; j <= base; j++) {
                if (j==1) {
                    System.out.printf("%dx%d=%d ", i, j, i * j);
                }
                else if (i*j<10){
                    System.out.printf("%dx%d= %d ",i,j,i*j);
                }else{
                    System.out.printf("%dx%d=%2d ",i,j,i*j);
                }

            }
            System.out.println();
        }
    }

    public static void sol3() {
        int n = 9;
        int fixedColumnWidth = 7;
        int leadingSpacesCount;

        for (int i = 1; i <= n; i++) {
            int numberOfTermsInCurrentRow = i;

            if (n - numberOfTermsInCurrentRow == 1)
                leadingSpacesCount = 6;
            else if (n - numberOfTermsInCurrentRow == 0)
                leadingSpacesCount = 0;
            else
                leadingSpacesCount = (n - (numberOfTermsInCurrentRow + 1)) * fixedColumnWidth + 6;
            if (leadingSpacesCount != 0)
                System.out.print(String.format("%" + leadingSpacesCount + "s", ""));

            for (int j = (n + 1) - i; j <= n; j++) {
                String term;
                if (j == 1) {
                    term = String.format("%dx%d=%d ", i, j, i * j);
                } else {
                    term = String.format("%dx%d=%2d ", i, j, i * j);
                }
                System.out.print(term);
            }
            System.out.println("");
        }
    }


    public static void sol4() {
        int n = 9;
        boolean[][] printMask = new boolean[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                printMask[i][j] = false;
        for (int j = 0; j < n; j++) printMask[j][0] = true;
        for (int j = 0; j < n; j++) printMask[0][j] = true;
        printMask[1][1] = true;
        for (int j = 8; j < n; j++) printMask[1][j] = true;
        for (int j = 7; j < n; j++) printMask[2][j] = true;
        printMask[2][2] = true;
        for (int j = 7; j < n; j++) printMask[3][j] = true;
        printMask[3][3] = true;
        for (int j = 6; j < n; j++) printMask[3][j] = true;
        printMask[5][3] = true;
        printMask[6][2] = true;
        printMask[7][1] = true;
        for (int j = 0; j < n; j++) printMask[4][j] = true;
        for (int j = 6; j < n; j++) printMask[5][j] = true;
        for (int j = 7; j < n; j++) printMask[6][j] = true;
        for (int j = 8; j < n; j++) printMask[7][j] = true;
        for (int j = 0; j < n; j++) printMask[8][j] = true;

        int termWidth = 6;
        String emptySpace = String.format("%" + termWidth + "s", "");

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (printMask[i - 1][j - 1]) {
                    if (j == 1)
                        System.out.print(String.format("%dx%d=%d", i, j, i * j));
                    else
                        System.out.print(String.format("%dx%d=%2d", i, j, i * j));
                } else {
                    System.out.print(emptySpace);
                }
                if (j < n) {
                    System.out.print(" ");
                }

            }
            System.out.println();
        }
    }

    public static void sol5() {
        int rows = 10;
        int[][] ary = new int[rows][];
        int num = 1;
        for (int i = 0; i < rows; i++) {
            ary[i] = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                ary[i][j] = num++;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < ary[i].length; j++) {
                System.out.print(String.format("ary(%d,%d)=%2d ", i, j, ary[i][j]));
            }
            System.out.println();
        }
    }
}