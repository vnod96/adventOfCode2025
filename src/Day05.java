void main() throws IOException {
    String sampleInput = "3-5\n" +
            "10-14\n" +
            "16-20\n" +
            "12-18\n" +
            "\n" +
            "1\n" +
            "5\n" +
            "8\n" +
            "11\n" +
            "17\n" +
            "32";
    String input = Files.readString(Path.of("./src/day05.txt"));

//    long numberOfFreshItems = numberOfFreshItems(input);
//    System.out.println("numberOfFreshItems = " + numberOfFreshItems);
    System.out.println("numberOfFreshItemIds = " + numberOfFreshItemIds(input));
}

private long numberOfFreshItemIds(String input) throws IOException {
    String[] lines = input.split("\n");

    List<Range> unmergedRange =  Arrays.stream(lines).takeWhile(line -> !Objects.equals(line, "")).map(s -> s.split("-")).map(s -> new Range(Long.parseLong(s[0]), Long.parseLong(s[1])))
            .toList();
    List<Range> range = mergeRange(unmergedRange);

    List<Long> list = range.stream().map(r -> (r.end - r.start) + 1).toList();
    return list.stream().mapToLong(Long::longValue).sum();

}
private long numberOfFreshItems(String input) {
    String[] lines = input.split("\n");

    List<String> rangeInputLines = Arrays.stream(lines).takeWhile(line -> !Objects.equals(line, "")).toList();
    List<Long> availableItems = new ArrayList<>();

    boolean foundSeparator = false;
    for (String line : lines) {
        if(foundSeparator) {
            availableItems.add(Long.parseLong(line));
        }
        if(Objects.equals(line, "")) {
            foundSeparator = true;
        }
    }

    List<Range> unmergedRange = rangeInputLines.stream().map(s -> s.split("-")).map(s -> new Range(Long.parseLong(s[0]), Long.parseLong(s[1])))
            .toList();

    List<Range> range = mergeRange(unmergedRange);

    return availableItems.stream()
            .filter(n -> range.stream().anyMatch(r -> n >= r.start && n <= r.end ) )
            .count();
}

List<Range> mergeRange(List<Range> ranges) {
    ranges  = new ArrayList<>(ranges);
    ranges.sort(Comparator.comparingLong(o -> o.start));
    List<Range> merged = new ArrayList<>();
    merged.add(ranges.getFirst());

    for(int i = 1; i < ranges.size(); i++) {
        Range last = merged.getLast();
        if (last.end < ranges.get(i).start){
            merged.add(ranges.get(i));
        }else {
            merged.removeLast();
            Range e = new Range(last.start, Math.max(last.end, ranges.get(i).end));
            merged.add(e);
        }
    }

    return merged;
}

record Range(long start, long end){}