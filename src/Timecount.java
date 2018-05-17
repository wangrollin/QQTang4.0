
class Timecount {

    static int minute = 3, scond = 0;
    static boolean over = false;
    private static int onescond = 0;
    private static final int Onescond = 100;

    void count() {
        if (!over) {
            onescond += 1;
            if (onescond == Onescond) {
                onescond = 0;
                if (scond > 0) {
                    scond -= 1;
                    return;
                }
                if (scond == 0 && minute > 0) {
                    minute -= 1;
                    scond = 59;
                    return;
                }
                if (scond == 0 && minute == 0) {
                    over = true;
                }
            }
        }
    }
}
