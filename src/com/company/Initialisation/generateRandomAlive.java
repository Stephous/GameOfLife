package com.company.Initialisation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class generateRandomAlive {


    public static int[][] Randomizers(int n, int game, int width, int height, int[][] Grid){
        Random rand = new Random();
        int Cellstate = 1;
        int nbCell = n;
        List<String> cantPlace = new ArrayList<>();

        while ( nbCell > 0) {
            if (game ==2) {
                Cellstate=rand.nextInt(4)+1;
            }
            int randx = rand.nextInt(width-2)+1;
            int randy = rand.nextInt(height-2)+1;

            if (cantPlace.contains(randx + "-" + randy)) continue;

            cantPlace.add(randx + "-" + randy);

            Grid[randx][randy] = Cellstate;

            nbCell--;

        }
        return Grid;
    }
}
