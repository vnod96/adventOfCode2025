void main() throws IOException {
    String sampleInput = "..@@.@@@@.\n" +
            "@@@.@.@.@@\n" +
            "@@@@@.@.@@\n" +
            "@.@@@@..@.\n" +
            "@@.@@@@.@@\n" +
            ".@@@@@@@.@\n" +
            ".@.@.@.@@@\n" +
            "@.@@@.@@@@\n" +
            ".@@@@@@@@.\n" +
            "@.@.@@@.@.";
    File directory = new File("./");
    System.out.println(directory.getAbsolutePath());
    String input = Files.readString(Paths.get("./src/day04.txt"));

    int numberOfRollingPaper = numberOfRollingPaper2(input);
    System.out.println("numberOfRollingPaper = " + numberOfRollingPaper);
}

private int numberOfRollingPaper2(String input) {
    int sum = 0;

    char[][] array = Arrays.stream(input.split("\n"))
            .map(String::toCharArray)
            .toArray(char[][]::new);

    while (true) {
        int currSum = 0;
        boolean[][] found = new boolean[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == '@' && isAccessible(array, i, j)) {
                    found[i][j] = true;
                    currSum++;
                }
            }
        }
        for (int i = 0; i < found.length; i++) {
            for (int j = 0; j < found[i].length; j++) {
                if (found[i][j]) {
                    array[i][j] = '.';
                }
            }
        }
        if(currSum == 0) {
            break;
        }
        sum += currSum;
    }

    return sum;
}

private int numberOfRollingPaper(String input) {
    int sum = 0;

    char[][] array = Arrays.stream(input.split("\n"))
            .map(String::toCharArray)
            .toArray(char[][]::new);

    for (int i = 0; i < array.length; i++) {
        for (int j = 0; j < array[i].length; j++) {
            if (array[i][j] == '@' && isAccessible(array, i, j)) {
                sum++;
            }
        }
    }

    return sum;
}

private boolean isAccessible(char[][] array, int i, int j) {
    int forkliftLimit = 4;
    int[][] d = new int[][]{
            new int[]{-1, -1},
            new int[]{-1, 0},
            new int[]{-1, 1},
            new int[]{0, -1},
            new int[]{0, 1},
            new int[]{1, -1},
            new int[]{1, 0},
            new int[]{1, 1},
    };

    int M = array.length;
    int N = array[0].length;

    for (int[] dir : d) {
        int newI = i + dir[0];
        int newJ = j + dir[1];
        if (newI >= 0 && newI < M && newJ >= 0 && newJ < N) {
            if (array[newI][newJ] == '@') {
                forkliftLimit--;
            }
        }
    }

    return forkliftLimit > 0;
}
