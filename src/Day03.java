void main() {
    String sampleInput = "987654321111111\n" +
            "811111111111119\n" +
            "234234234234278\n" +
            "818181911112111";
    long sumOfMaxJoltage = sumOfMaxJoltage(sampleInput);
    System.out.println("sumOfMaxJoltage = " + sumOfMaxJoltage);
}

long sumOfMaxJoltage(String banks) {
    return Arrays.stream(banks.split("\n"))
            .mapToLong(this::calculateMaxJoltage2)
            .sum();
}

private long calculateMaxJoltage2(String s) {
    long sum = 0;
    int numOfBatteries = 12;
    int[] batteries = new int[numOfBatteries];
    int cur = 0;
    for (int i = 0; i < numOfBatteries; i++) {
        int l = cur, r = s.length() - (numOfBatteries - i);
        while(l <=  r){
            int digit = s.charAt(l) - '0';
            if(batteries[i] < digit){
                batteries[i] = digit;
                cur = l;
            }
            l++;
        }
        cur++;
    }

    long f = 1L;
    for (int i = batteries.length - 1; i >= 0 ; i--) {
        sum += ((long) batteries[i] * f);
        f *= 10L;
    }
    return sum;
}

private long calculateMaxJoltage(String s) {
    int l = 0, r = s.length() - 1;
    int f = s.charAt(l) - '0';
    int sec = s.charAt(r) - '0';
    int l_pointer = l;
    while(l < r) {
        if(f < s.charAt(l) - '0') {
            l_pointer = l;
            f =  s.charAt(l) - '0';
        }
        l++;
    }
    while(r > l_pointer) {
        sec = Math.max(sec, s.charAt(r--) - '0');
    }

    return (f*10L) + sec;
}

