void main() {
    String sampleInput = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124";
    long sumOfInvalidIds = sumOfInvalidIds(sampleInput);
    System.out.println("sumOfInvalidIds = " + sumOfInvalidIds);
}

long sumOfInvalidIds(String doc) {
    String[] ranges = doc.split(",");
    long sum = 0;

    List<Long> list = new ArrayList<>();
    for (String range : ranges) {
        String[] splitRange = range.split("-");
        long start = Long.parseLong(splitRange[0]);
        long end = Long.parseLong(splitRange[1]);
        sum += LongStream.rangeClosed(start, end).boxed()
                .filter(i -> {
                    String number = i.toString();
//                        int mid = number.length() / 2;
//                        String fistHalf = number.substring(0, mid);
//                        return number.equals(fistHalf + fistHalf);
                    int div = 2;
                    int len = number.length();
                    while(div <= len){
                        int chunk = len / div;
                        if(number.equals(number.substring(0, chunk).repeat(div))){
                            return true;
                        }
                        div++;
                    }
                    return false;
                }).peek(integer -> list.add(integer)).reduce(0L, Long::sum);
    }

    return sum;
}
