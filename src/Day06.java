void main() throws IOException {

    String sampleInput = "123 328  51 64 \n" +
            " 45 64  387 23 \n" +
            "  6 98  215 314\n" +
            "*   +   *   +  ";

    String input = Files.readString(Paths.get("./src/day06.txt"));

    Arrays.stream(input.split("\n"))
            .map(s -> s.substring(s.length() - 20))
            .forEach(System.out::println);

    long grandTotal = grandTotal02(input);

    System.out.println("grandTotal = " + grandTotal);
}

long grandTotal02(String input) {
    String[] lines = input.split("\n");
    String ops = lines[lines.length - 1];

    long grandTotal = 0;
    List<Long> nums = new ArrayList<>();
    for (int i = lines[0].length() - 1; i >= 0; i--) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < lines.length - 1; j++) {
            sb.append(lines[j].charAt(i));
        }
        String trim = sb.toString().trim();
        if (!trim.isBlank()) {
            nums.add(Long.parseLong(trim));
        }
        switch (ops.charAt(i)) {
            case '+':
                grandTotal += nums.stream().mapToLong(Long::longValue).sum();
                nums.clear();
                break;
            case '*':
                grandTotal += nums.stream().mapToLong(Long::longValue).reduce(1L, (left, right) -> left * right);
                nums.clear();
                break;
            default:
                break;
        }
    }
    
    return grandTotal;
}


long grandTotal01(String input) {
    String[][] grid = makeGrid(input);
    long grandTotal = 0;
    for (int j = 0; j < grid[0].length; j++) {
        String operation = grid[grid.length - 1][j];
        long result;
        if (operation.equals("+")) {
            result = 0;
        } else {
            result = 1;
        }
        for (int i = 0; i < grid.length - 1; i++) {
            long num = Long.parseLong(grid[i][j]);
            if (operation.equals("+")) {
                result += num;
            } else {
                result *= num;
            }
        }
        grandTotal += result;
    }

    return grandTotal;
}

private static String[][] makeGrid(String input) {
    return Arrays.stream(input.split("\n"))
            .map(String::trim)
            .map(s -> s.split("\\s+"))
            .map(s -> Arrays.stream(s).map(String::trim).toArray(String[]::new))
            .toArray(String[][]::new);
}