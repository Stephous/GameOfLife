package com.company.Actualisation;

import com.company.Actualisation.CheckNeighbourState;
import com.company.Actualisation.GenerateNewState;

import java.util.List;

public class UpdateClass {
    public static int[][] update(int width, int height, int gamechoose, int[][] Grid) {
        int[][] newGrid = new int[width][height];

        for( int x = 1; x < width-1 ; x++)
        {
            for( int y = 1; y < height-1 ; y++)
            {
                int count = 0;

                List<Integer> ListEtatvoisin;
                List<Integer> ReturnCount;

                List<List<Integer>> Return;

                Return = CheckNeighbourState.Checker(width,height,Grid,x,y,count,gamechoose);

                ListEtatvoisin = Return.get(0);
                ReturnCount = Return.get(1);
                count = ReturnCount.get(0);

                newGrid[x][y] = GenerateNewState.Generator(x,y,gamechoose,count,Grid,ListEtatvoisin);
            }
        }
        return newGrid;

    }
}
