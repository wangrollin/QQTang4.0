
public class WallMap {
    public static void maker(int[][] a) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 8; j++) {
                if (a[i][j] == 1)
                    new Wall(1, 4, i, j);
                if (a[i][j] == 2)
                    new Wall(1, 3, i, j);
                if (a[i][j] == 3)
                    new Wall(1, 1, i, j);
                if (a[i][j] == 4)
                    new Wall(1, 2, i, j);
                if (a[i][j] == 11)
                    new Wall(2, 3, i, j);
                if (a[i][j] == 12)
                    new Wall(2, 2, i, j);
                if (a[i][j] == 13)
                    new Wall(2, 4, i, j);
                if (a[i][j] == 14)
                    new Wall(2, 5, i, j);
                if (a[i][j] == 15)
                    new Wall(2, 6, i, j);
                if (a[i][j] == 16)
                    new Wall(2, 1, i, j);
            }
        }
    }

    //比武地图1
    static void biwu1() {
        new Wall(1, 1, 0, 2);
        new Wall(1, 1, 0, 3);
        new Wall(1, 1, 0, 4);
        new Wall(1, 1, 0, 5);

        new Wall(1, 1, 1, 2);
        new Wall(1, 2, 1, 3);
        new Wall(1, 2, 1, 4);
        new Wall(1, 1, 1, 5);

        new Wall(1, 1, 2, 2);
        new Wall(1, 2, 2, 3);
        new Wall(1, 2, 2, 4);
        new Wall(1, 1, 2, 5);

        new Wall(1, 2, 3, 0);
        new Wall(1, 2, 3, 1);
        new Wall(1, 1, 3, 2);
        new Wall(1, 2, 3, 3);
        new Wall(1, 2, 3, 4);
        new Wall(1, 1, 3, 5);
        new Wall(1, 2, 3, 6);
        new Wall(1, 2, 3, 7);
        //4
        new Wall(1, 2, 4, 0);
        new Wall(1, 2, 4, 1);
        new Wall(1, 1, 4, 2);
        new Wall(1, 2, 4, 3);
        new Wall(1, 2, 4, 4);
        new Wall(1, 1, 4, 5);
        new Wall(1, 2, 4, 6);
        new Wall(1, 2, 4, 7);
        //5
        new Wall(1, 1, 5, 0);
        new Wall(1, 1, 5, 1);
        new Wall(2, 1, 5, 2);
        new Wall(1, 1, 5, 3);
        new Wall(1, 1, 5, 4);
        new Wall(2, 1, 5, 5);
        new Wall(1, 1, 5, 6);
        new Wall(1, 1, 5, 7);
        //6
        new Wall(1, 1, 6, 0);
        new Wall(1, 2, 6, 1);
        new Wall(1, 1, 6, 2);
        new Wall(1, 2, 6, 3);
        new Wall(1, 2, 6, 4);
        new Wall(1, 1, 6, 5);
        new Wall(1, 2, 6, 6);
        new Wall(1, 1, 6, 7);
        //7
        new Wall(1, 1, 7, 0);
        new Wall(1, 1, 7, 1);
        new Wall(2, 1, 7, 2);
        new Wall(1, 1, 7, 3);
        new Wall(1, 1, 7, 4);
        new Wall(2, 1, 7, 5);
        new Wall(1, 1, 7, 6);
        new Wall(1, 1, 7, 7);
        //8
        new Wall(1, 2, 8, 0);
        new Wall(1, 2, 8, 1);
        new Wall(1, 1, 8, 2);
        new Wall(1, 2, 8, 3);
        new Wall(1, 2, 8, 4);
        new Wall(1, 1, 8, 5);
        new Wall(1, 2, 8, 6);
        new Wall(1, 2, 8, 7);
        //9
        new Wall(1, 2, 9, 0);
        new Wall(1, 2, 9, 1);
        new Wall(1, 1, 9, 2);
        new Wall(1, 2, 9, 3);
        new Wall(1, 2, 9, 4);
        new Wall(1, 1, 9, 5);
        new Wall(1, 2, 9, 6);
        new Wall(1, 2, 9, 7);

        new Wall(1, 1, 10, 2);
        new Wall(1, 2, 10, 3);
        new Wall(1, 2, 10, 4);
        new Wall(1, 1, 10, 5);

        new Wall(1, 1, 11, 2);
        new Wall(1, 2, 11, 3);
        new Wall(1, 2, 11, 4);
        new Wall(1, 1, 11, 5);

        new Wall(1, 1, 12, 2);
        new Wall(1, 1, 12, 3);
        new Wall(1, 1, 12, 4);
        new Wall(1, 1, 12, 5);
    }

    static void putong() {
        new Wall(2, 2, 0, 0);
        new Wall(1, 4, 0, 1);
        new Wall(1, 4, 0, 5);
        new Wall(2, 6, 0, 6);
        new Wall(2, 4, 0, 7);


        new Wall(1, 4, 1, 3);
        new Wall(2, 2, 1, 6);
        new Wall(2, 6, 1, 7);


        new Wall(1, 4, 2, 1);
        new Wall(1, 4, 2, 5);
        new Wall(2, 5, 2, 7);

        new Wall(2, 2, 3, 0);
        new Wall(1, 4, 3, 3);
        new Wall(1, 4, 3, 7);
        //4
        new Wall(2, 4, 4, 0);
        new Wall(1, 4, 4, 1);
        new Wall(1, 4, 4, 5);

        //5
        new Wall(2, 6, 5, 0);
        new Wall(1, 4, 5, 3);
        new Wall(1, 4, 5, 7);
        //6
        new Wall(2, 5, 6, 0);
        new Wall(1, 4, 6, 1);
        new Wall(1, 4, 6, 5);
        //7

        new Wall(1, 4, 7, 3);
        new Wall(1, 4, 7, 7);
        //8

        new Wall(1, 4, 8, 1);
        new Wall(1, 4, 8, 5);

        //9

        new Wall(1, 4, 9, 3);
        new Wall(1, 4, 9, 7);


        new Wall(1, 4, 10, 1);
        new Wall(1, 4, 10, 5);


        new Wall(2, 2, 11, 0);
        new Wall(1, 4, 11, 3);


        new Wall(2, 6, 12, 0);
        new Wall(2, 4, 12, 1);
        new Wall(2, 6, 12, 5);
        new Wall(2, 4, 12, 6);
        new Wall(2, 5, 12, 7);
    }

    static void maoxian() {
        new Wall(2, 3, 0, 0);
        new Wall(1, 3, 0, 1);
        new Wall(1, 3, 0, 2);
        new Wall(1, 3, 0, 3);
        new Wall(1, 3, 0, 4);
        new Wall(1, 3, 0, 5);
        new Wall(1, 3, 0, 6);
        new Wall(2, 3, 0, 7);

        new Wall(1, 3, 1, 0);
        new Wall(1, 3, 1, 7);

        new Wall(1, 3, 2, 0);
        new Wall(2, 3, 2, 1);
        new Wall(2, 3, 2, 6);
        new Wall(1, 3, 2, 7);

        new Wall(1, 3, 3, 0);
        new Wall(1, 3, 3, 3);
        new Wall(1, 3, 3, 7);
        //4
        new Wall(1, 3, 4, 0);
        new Wall(1, 3, 4, 2);
        new Wall(1, 3, 4, 3);
        new Wall(1, 3, 4, 4);
        new Wall(1, 3, 4, 7);
        //5
        new Wall(1, 3, 5, 0);
        new Wall(1, 3, 5, 2);
        new Wall(1, 3, 5, 3);
        new Wall(2, 3, 5, 4);
        new Wall(1, 3, 5, 5);
        new Wall(1, 3, 5, 7);
        //6
        new Wall(1, 3, 6, 0);
        new Wall(1, 3, 6, 2);
        new Wall(1, 3, 6, 3);
        new Wall(2, 3, 6, 4);
        new Wall(2, 3, 6, 5);
        new Wall(1, 3, 6, 6);
        new Wall(1, 3, 6, 7);
        //7
        new Wall(1, 3, 7, 0);
        new Wall(1, 3, 7, 2);
        new Wall(1, 3, 7, 3);
        new Wall(2, 3, 7, 4);
        new Wall(1, 3, 7, 5);
        new Wall(1, 3, 7, 7);
        //8
        new Wall(1, 3, 8, 0);
        new Wall(1, 3, 8, 2);
        new Wall(1, 3, 8, 3);
        new Wall(1, 3, 8, 4);
        new Wall(1, 3, 8, 7);
        //9
        new Wall(1, 3, 9, 0);
        new Wall(1, 3, 9, 3);
        new Wall(1, 3, 9, 7);

        new Wall(1, 3, 10, 0);
        new Wall(2, 3, 10, 1);
        new Wall(2, 3, 10, 6);
        new Wall(1, 3, 10, 7);

        new Wall(1, 3, 11, 0);
        new Wall(1, 3, 11, 7);

        new Wall(2, 3, 12, 0);
        new Wall(1, 3, 12, 1);
        new Wall(1, 3, 12, 2);
        new Wall(1, 3, 12, 3);
        new Wall(1, 3, 12, 4);
        new Wall(1, 3, 12, 5);
        new Wall(1, 3, 12, 6);
        new Wall(2, 3, 12, 7);
    }
}
