
void main() {
    String sampleDoc = "L68\n" +
            "L30\n" +
            "R48\n" +
            "L5\n" +
            "R60\n" +
            "L55\n" +
            "L1\n" +
            "L99\n" +
            "R14\n" +
            "L82";
    int password = findPassword(sampleDoc);
    System.out.println("The password is: " + password);
}

public static final char LEFT = 'L';
public static final int START_TICK = 0;
public static final int END_TICK = 99;


int findPassword(String dialDocument) {
    String[] rotations = dialDocument.split("\n");
    int password = 0;
    int dialAt = 50;
    for(String rotation : rotations) {
        Pair out;
        if(rotation.charAt(0) == LEFT) {
            out = moveLeft(dialAt, Integer.parseInt(rotation.substring(1)));
        }else {
            out = moveRight(dialAt, Integer.parseInt(rotation.substring(1)));
        }
        dialAt = out.dialAt;
        password += out.numberOfZero;
        if (dialAt == 0) password++;
    }
    return password;
}

Pair moveLeft(int at, int ticks){
    int numberOfZero = ticks / 100;
    ticks = ticks % 100;
    int dialAt;
    if(at - ticks < START_TICK) {
        dialAt = END_TICK + (at - ticks) + 1;
        if (at != 0 && dialAt != 0) numberOfZero++;
    }else {
        dialAt =  at - ticks;
    }
    return new Pair(dialAt,numberOfZero);
}

Pair moveRight(int at, int ticks){
    int numberOfZero = ticks / 100;
    ticks = ticks % 100;
    int dialAt;
    if(at + ticks > END_TICK) {
        dialAt =  (at + (ticks)) - END_TICK - 1;
        if (at != 0 && dialAt != 0) numberOfZero++;
    }else {
        dialAt = at + ticks;
    }
    return new Pair(dialAt,numberOfZero);
}


record Pair(int dialAt, int numberOfZero) {}