
public class Timecount {

    protected static int minute = 3, scond = 0;
    protected static boolean over = false;
    protected static int onescond = 0;
    private static int Onescond = 100;

    public void count() {
        if (over == false) {
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
                    return;
                }
            }
        }
    }
}
