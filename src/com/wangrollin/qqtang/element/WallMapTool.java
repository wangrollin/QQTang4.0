package com.wangrollin.qqtang.element;

import com.wangrollin.qqtang.constants.GameConstants;

public class WallMapTool {

    public static Wall[][] createBiwuMap() {
        Wall[][] wallMap = new Wall[GameConstants.HENG][GameConstants.SHU];
        for (int i = 0; i < GameConstants.HENG; i++) {
            for (int j = 0; j < GameConstants.SHU; j++) {
                wallMap[i][j] = null;
            }
        }

        wallMap[0][2] = new Wall(Wall.WALL_TYPE0);
        wallMap[0][3] = new Wall(Wall.WALL_TYPE0);
        wallMap[0][4] = new Wall(Wall.WALL_TYPE0);
        wallMap[0][5] = new Wall(Wall.WALL_TYPE0);

        wallMap[1][2] = new Wall(Wall.WALL_TYPE0);
        wallMap[1][3] = new Wall(Wall.WALL_TYPE1);
        wallMap[1][4] = new Wall(Wall.WALL_TYPE1);
        wallMap[1][5] = new Wall(Wall.WALL_TYPE0);

        wallMap[2][2] = new Wall(Wall.WALL_TYPE0);
        wallMap[2][3] = new Wall(Wall.WALL_TYPE1);
        wallMap[2][4] = new Wall(Wall.WALL_TYPE1);
        wallMap[2][5] = new Wall(Wall.WALL_TYPE0);

        wallMap[3][0] = new Wall(Wall.WALL_TYPE1);
        wallMap[3][1] = new Wall(Wall.WALL_TYPE1);
        wallMap[3][2] = new Wall(Wall.WALL_TYPE0);
        wallMap[3][3] = new Wall(Wall.WALL_TYPE1);
        wallMap[3][4] = new Wall(Wall.WALL_TYPE1);
        wallMap[3][5] = new Wall(Wall.WALL_TYPE0);
        wallMap[3][6] = new Wall(Wall.WALL_TYPE1);
        wallMap[3][7] = new Wall(Wall.WALL_TYPE1);

        wallMap[4][0] = new Wall(Wall.WALL_TYPE1);
        wallMap[4][1] = new Wall(Wall.WALL_TYPE1);
        wallMap[4][2] = new Wall(Wall.WALL_TYPE0);
        wallMap[4][3] = new Wall(Wall.WALL_TYPE1);
        wallMap[4][4] = new Wall(Wall.WALL_TYPE1);
        wallMap[4][5] = new Wall(Wall.WALL_TYPE0);
        wallMap[4][6] = new Wall(Wall.WALL_TYPE1);
        wallMap[4][7] = new Wall(Wall.WALL_TYPE1);

        wallMap[5][0] = new Wall(Wall.WALL_TYPE0);
        wallMap[5][1] = new Wall(Wall.WALL_TYPE0);
        wallMap[5][2] = new Wall(Wall.WALL_TYPE4);
        wallMap[5][3] = new Wall(Wall.WALL_TYPE0);
        wallMap[5][4] = new Wall(Wall.WALL_TYPE0);
        wallMap[5][5] = new Wall(Wall.WALL_TYPE4);
        wallMap[5][6] = new Wall(Wall.WALL_TYPE0);
        wallMap[5][7] = new Wall(Wall.WALL_TYPE0);

        wallMap[6][0] = new Wall(Wall.WALL_TYPE0);
        wallMap[6][1] = new Wall(Wall.WALL_TYPE1);
        wallMap[6][2] = new Wall(Wall.WALL_TYPE0);
        wallMap[6][3] = new Wall(Wall.WALL_TYPE1);
        wallMap[6][4] = new Wall(Wall.WALL_TYPE1);
        wallMap[6][5] = new Wall(Wall.WALL_TYPE0);
        wallMap[6][6] = new Wall(Wall.WALL_TYPE1);
        wallMap[6][7] = new Wall(Wall.WALL_TYPE0);

        wallMap[7][0] = new Wall(Wall.WALL_TYPE0);
        wallMap[7][1] = new Wall(Wall.WALL_TYPE0);
        wallMap[7][2] = new Wall(Wall.WALL_TYPE4);
        wallMap[7][3] = new Wall(Wall.WALL_TYPE0);
        wallMap[7][4] = new Wall(Wall.WALL_TYPE0);
        wallMap[7][5] = new Wall(Wall.WALL_TYPE4);
        wallMap[7][6] = new Wall(Wall.WALL_TYPE0);
        wallMap[7][7] = new Wall(Wall.WALL_TYPE0);

        wallMap[8][0] = new Wall(Wall.WALL_TYPE1);
        wallMap[8][1] = new Wall(Wall.WALL_TYPE1);
        wallMap[8][2] = new Wall(Wall.WALL_TYPE0);
        wallMap[8][3] = new Wall(Wall.WALL_TYPE1);
        wallMap[8][4] = new Wall(Wall.WALL_TYPE1);
        wallMap[8][5] = new Wall(Wall.WALL_TYPE0);
        wallMap[8][6] = new Wall(Wall.WALL_TYPE1);
        wallMap[8][7] = new Wall(Wall.WALL_TYPE1);

        wallMap[9][0] = new Wall(Wall.WALL_TYPE1);
        wallMap[9][1] = new Wall(Wall.WALL_TYPE1);
        wallMap[9][2] = new Wall(Wall.WALL_TYPE0);
        wallMap[9][3] = new Wall(Wall.WALL_TYPE1);
        wallMap[9][4] = new Wall(Wall.WALL_TYPE1);
        wallMap[9][5] = new Wall(Wall.WALL_TYPE0);
        wallMap[9][6] = new Wall(Wall.WALL_TYPE1);
        wallMap[9][7] = new Wall(Wall.WALL_TYPE1);

        wallMap[10][2] = new Wall(Wall.WALL_TYPE0);
        wallMap[10][3] = new Wall(Wall.WALL_TYPE1);
        wallMap[10][4] = new Wall(Wall.WALL_TYPE1);
        wallMap[10][5] = new Wall(Wall.WALL_TYPE0);

        wallMap[11][2] = new Wall(Wall.WALL_TYPE0);
        wallMap[11][3] = new Wall(Wall.WALL_TYPE1);
        wallMap[11][4] = new Wall(Wall.WALL_TYPE1);
        wallMap[11][5] = new Wall(Wall.WALL_TYPE0);

        wallMap[12][2] = new Wall(Wall.WALL_TYPE0);
        wallMap[12][3] = new Wall(Wall.WALL_TYPE0);
        wallMap[12][4] = new Wall(Wall.WALL_TYPE0);
        wallMap[12][5] = new Wall(Wall.WALL_TYPE0);

        return wallMap;
    }


    public static Wall[][] createShuimianMap() {
        Wall[][] wallMap = new Wall[GameConstants.HENG][GameConstants.SHU];
        for (int i = 0; i < GameConstants.HENG; i++) {
            for (int j = 0; j < GameConstants.SHU; j++) {
                wallMap[i][j] = null;
            }
        }

        wallMap[0][0] = new Wall(Wall.WALL_TYPE5);
        wallMap[0][1] = new Wall(Wall.WALL_TYPE3);
        wallMap[0][5] = new Wall(Wall.WALL_TYPE3);
        wallMap[0][6] = new Wall(Wall.WALL_TYPE9);
        wallMap[0][7] = new Wall(Wall.WALL_TYPE7);

        wallMap[1][3] = new Wall(Wall.WALL_TYPE3);
        wallMap[1][6] = new Wall(Wall.WALL_TYPE5);
        wallMap[1][7] = new Wall(Wall.WALL_TYPE9);

        wallMap[2][1] = new Wall(Wall.WALL_TYPE3);
        wallMap[2][5] = new Wall(Wall.WALL_TYPE3);
        wallMap[2][7] = new Wall(Wall.WALL_TYPE8);

        wallMap[3][0] = new Wall(Wall.WALL_TYPE5);
        wallMap[3][3] = new Wall(Wall.WALL_TYPE3);
        wallMap[3][7] = new Wall(Wall.WALL_TYPE3);

        wallMap[4][0] = new Wall(Wall.WALL_TYPE7);
        wallMap[4][1] = new Wall(Wall.WALL_TYPE3);
        wallMap[4][5] = new Wall(Wall.WALL_TYPE3);

        wallMap[5][0] = new Wall(Wall.WALL_TYPE9);
        wallMap[5][3] = new Wall(Wall.WALL_TYPE3);
        wallMap[5][7] = new Wall(Wall.WALL_TYPE3);

        wallMap[6][0] = new Wall(Wall.WALL_TYPE8);
        wallMap[6][1] = new Wall(Wall.WALL_TYPE3);
        wallMap[6][5] = new Wall(Wall.WALL_TYPE3);

        wallMap[7][3] = new Wall(Wall.WALL_TYPE3);
        wallMap[7][7] = new Wall(Wall.WALL_TYPE3);

        wallMap[8][1] = new Wall(Wall.WALL_TYPE3);
        wallMap[8][5] = new Wall(Wall.WALL_TYPE3);

        wallMap[9][3] = new Wall(Wall.WALL_TYPE3);
        wallMap[9][7] = new Wall(Wall.WALL_TYPE3);

        wallMap[10][1] = new Wall(Wall.WALL_TYPE3);
        wallMap[10][5] = new Wall(Wall.WALL_TYPE3);
        wallMap[11][0] = new Wall(Wall.WALL_TYPE5);
        wallMap[11][3] = new Wall(Wall.WALL_TYPE3);

        wallMap[12][0] = new Wall(Wall.WALL_TYPE9);
        wallMap[12][1] = new Wall(Wall.WALL_TYPE7);
        wallMap[12][5] = new Wall(Wall.WALL_TYPE9);
        wallMap[12][6] = new Wall(Wall.WALL_TYPE7);
        wallMap[12][7] = new Wall(Wall.WALL_TYPE8);

        return wallMap;
    }

    public static Wall[][] createKuangdongMap() {
        Wall[][] wallMap = new Wall[GameConstants.HENG][GameConstants.SHU];
        for (int i = 0; i < GameConstants.HENG; i++) {
            for (int j = 0; j < GameConstants.SHU; j++) {
                wallMap[i][j] = null;
            }
        }

        wallMap[0][0] = new Wall(Wall.WALL_TYPE6);
        wallMap[0][1] = new Wall(Wall.WALL_TYPE2);
        wallMap[0][2] = new Wall(Wall.WALL_TYPE2);
        wallMap[0][3] = new Wall(Wall.WALL_TYPE2);
        wallMap[0][4] = new Wall(Wall.WALL_TYPE2);
        wallMap[0][5] = new Wall(Wall.WALL_TYPE2);
        wallMap[0][6] = new Wall(Wall.WALL_TYPE2);
        wallMap[0][7] = new Wall(Wall.WALL_TYPE6);

        wallMap[1][0] = new Wall(Wall.WALL_TYPE2);
        wallMap[1][7] = new Wall(Wall.WALL_TYPE2);

        wallMap[2][0] = new Wall(Wall.WALL_TYPE2);
        wallMap[2][1] = new Wall(Wall.WALL_TYPE6);
        wallMap[2][6] = new Wall(Wall.WALL_TYPE6);
        wallMap[2][7] = new Wall(Wall.WALL_TYPE2);

        wallMap[3][0] = new Wall(Wall.WALL_TYPE2);
        wallMap[3][3] = new Wall(Wall.WALL_TYPE2);
        wallMap[3][7] = new Wall(Wall.WALL_TYPE2);

        wallMap[4][0] = new Wall(Wall.WALL_TYPE2);
        wallMap[4][2] = new Wall(Wall.WALL_TYPE2);
        wallMap[4][3] = new Wall(Wall.WALL_TYPE2);
        wallMap[4][4] = new Wall(Wall.WALL_TYPE2);
        wallMap[4][7] = new Wall(Wall.WALL_TYPE2);

        wallMap[5][0] = new Wall(Wall.WALL_TYPE2);
        wallMap[5][2] = new Wall(Wall.WALL_TYPE2);
        wallMap[5][3] = new Wall(Wall.WALL_TYPE2);
        wallMap[5][4] = new Wall(Wall.WALL_TYPE6);
        wallMap[5][5] = new Wall(Wall.WALL_TYPE2);
        wallMap[5][7] = new Wall(Wall.WALL_TYPE2);

        wallMap[6][0] = new Wall(Wall.WALL_TYPE2);
        wallMap[6][2] = new Wall(Wall.WALL_TYPE2);
        wallMap[6][3] = new Wall(Wall.WALL_TYPE2);
        wallMap[6][4] = new Wall(Wall.WALL_TYPE6);
        wallMap[6][5] = new Wall(Wall.WALL_TYPE6);
        wallMap[6][6] = new Wall(Wall.WALL_TYPE2);
        wallMap[6][7] = new Wall(Wall.WALL_TYPE2);

        wallMap[7][0] = new Wall(Wall.WALL_TYPE2);
        wallMap[7][2] = new Wall(Wall.WALL_TYPE2);
        wallMap[7][3] = new Wall(Wall.WALL_TYPE2);
        wallMap[7][4] = new Wall(Wall.WALL_TYPE6);
        wallMap[7][5] = new Wall(Wall.WALL_TYPE2);
        wallMap[7][7] = new Wall(Wall.WALL_TYPE2);

        wallMap[8][0] = new Wall(Wall.WALL_TYPE2);
        wallMap[8][2] = new Wall(Wall.WALL_TYPE2);
        wallMap[8][3] = new Wall(Wall.WALL_TYPE2);
        wallMap[8][4] = new Wall(Wall.WALL_TYPE2);
        wallMap[8][7] = new Wall(Wall.WALL_TYPE2);

        wallMap[9][0] = new Wall(Wall.WALL_TYPE2);
        wallMap[9][3] = new Wall(Wall.WALL_TYPE2);
        wallMap[9][7] = new Wall(Wall.WALL_TYPE2);

        wallMap[10][0] = new Wall(Wall.WALL_TYPE2);
        wallMap[10][1] = new Wall(Wall.WALL_TYPE6);
        wallMap[10][6] = new Wall(Wall.WALL_TYPE6);
        wallMap[10][7] = new Wall(Wall.WALL_TYPE2);

        wallMap[11][0] = new Wall(Wall.WALL_TYPE2);
        wallMap[11][7] = new Wall(Wall.WALL_TYPE2);

        wallMap[12][0] = new Wall(Wall.WALL_TYPE6);
        wallMap[12][1] = new Wall(Wall.WALL_TYPE2);
        wallMap[12][2] = new Wall(Wall.WALL_TYPE2);
        wallMap[12][3] = new Wall(Wall.WALL_TYPE2);
        wallMap[12][4] = new Wall(Wall.WALL_TYPE2);
        wallMap[12][5] = new Wall(Wall.WALL_TYPE2);
        wallMap[12][6] = new Wall(Wall.WALL_TYPE2);
        wallMap[12][7] = new Wall(Wall.WALL_TYPE6);

        return wallMap;
    }

    public static Wall[][] copyWallMap(Wall[][] oldWallMap) {

        Wall[][] copyWallMap = new Wall[GameConstants.HENG][GameConstants.SHU];
        for (int i = 0; i < GameConstants.HENG; i++) {
            for (int j = 0; j < GameConstants.SHU; j++) {
                copyWallMap[i][j] = oldWallMap[i][j] == null ? null : new Wall(oldWallMap[i][j].getWallType());
            }
        }
        return copyWallMap;
    }
}
