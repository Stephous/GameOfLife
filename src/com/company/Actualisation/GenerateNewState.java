package com.company.Actualisation;

import java.util.List;

public class GenerateNewState {


    public static int Generator(int x, int y, int gamechoose, int count, int[][] Grid, List<Integer> ListEtatvoisin){

        int newGridReturn=0;
        // Jeu de la vie classique :
        if(gamechoose==0){
            newGridReturn = Grid[x][y]==1 ? (count == 2 || count == 3)?1:0 : (count == 3)?1:0;
        }
        // Jeu de la vie jour et nuit :
        else if (gamechoose==1){
            newGridReturn = Grid[x][y]==1 ? (count == 3 || count == 4 || count == 6 || count == 7 | count == 8)?1:0 : ( count == 3 || count == 6 || count == 7 || count == 8)?1:0;
        }
        // Jeu de la vie QuadLife :
        else if (gamechoose==2){
            if(Grid[x][y]>=1){
                newGridReturn = (count == 2 || count == 3)?Grid[x][y]:0;
            }
            else if (count == 3) {
                int voisin1 = ListEtatvoisin.get(0);
                int voisin2 = ListEtatvoisin.get(1);
                int voisin3 = ListEtatvoisin.get(2);

                if (voisin1 == voisin2 || voisin1 == voisin3 || voisin2 == voisin3) {
                    int Majority;
                    if (voisin1 == voisin2) {
                        Majority = voisin1;
                    } else {
                        Majority = voisin3;
                    }
                    newGridReturn = Majority;
                } else {
                    if (voisin1 != 4 && voisin2 != 4 && voisin3 != 4) {
                        newGridReturn = 4;
                    } else if (voisin1 != 3 && voisin2 != 3 && voisin3 != 3) {
                        newGridReturn = 3;
                    } else if (voisin1 != 2 && voisin2 != 2 && voisin3 != 2) {
                        newGridReturn = 2;
                    } else {
                        newGridReturn = 1;
                    }

                }
            }
        }
        return newGridReturn;
    }
}
