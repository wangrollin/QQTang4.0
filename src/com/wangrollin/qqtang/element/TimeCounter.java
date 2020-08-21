package com.wangrollin.qqtang.element;

public class TimeCounter {

    public int minute = 3, scond = 0;
    public boolean over = false;
    private int oneSecond = 0;
    private final int ONE_SECOND = 87;//test out, it is 87

    public void count() {
        if (!over) {
            oneSecond += 1;
            if (oneSecond == ONE_SECOND) {
                oneSecond = 0;
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
