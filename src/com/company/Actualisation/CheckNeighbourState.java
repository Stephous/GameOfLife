package com.company.Actualisation;

import java.util.ArrayList;
import java.util.List;

public class CheckNeighbourState {

    public static List<List<Integer>> Checker(int width, int height, int[][] Grid, int x, int y, int count, int gamechoose){

        List<Integer> ListEtatvoisin = new ArrayList<>();
        for (int xvoisin = -1; xvoisin < 2 ; xvoisin++)
        {
            for (int yvoisin = -1; yvoisin < 2 ; yvoisin++)
            {
                if (xvoisin == 0 && yvoisin ==0) continue;
                int nx = x+ xvoisin;
                int ny = y+ yvoisin;
                if(nx >=0 && ny > 0 && nx < width && ny < height && Grid[nx][ny]>=1){
                    count++;
                    if (gamechoose==2){
                        ListEtatvoisin.add(Grid[nx][ny]);
                    }
                }
            }
        }


        List<Integer> ReturnCount = new ArrayList<>();
        ReturnCount.add(count);

        List<List<Integer>> Return = new ArrayList<>();
        Return.add(ListEtatvoisin);
        Return.add(ReturnCount);

        return Return;
    }


}
