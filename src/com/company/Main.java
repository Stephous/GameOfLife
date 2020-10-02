package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends JPanel {

    private final JFrame frame;

    private final int width,height;


    private int gamechoose=1;

    private boolean[][] Grid;

    public Main(int width, int height,int n){

        this.width = width;
        this.height = height;
        Grid = new boolean[width][height];

        generateRandomAlive(n);

        frame = new JFrame("Game oF Life");
        frame.setSize(1280,720);
        frame.setUndecorated(true);
        frame.setResizable(true);


        frame.setLocationRelativeTo(null);

        frame.setContentPane(this);
        frame.setBackground(Color.black);

        frame.setVisible(true);


        run();
    }

    private void generateRandomAlive(int nbCell){ // Initialise aléatoirement des cellules vivantes dans le tableau
        Random rand = new Random();

        List<String> cantPlace = new ArrayList<>();

        while ( nbCell > 0){
            int randx = rand.nextInt(width);
            int randy = rand.nextInt(height);

            if(cantPlace.contains(randx+"-"+randy)) continue;

            cantPlace.add(randx+"-"+randy);

            Grid[randx][randy] = true;

            nbCell--;
        }
    }

    private void run (){

        long nanoSecond = System.nanoTime();
        double tick = 1000000000.0/40.0;
        int tps=0,fps=0;
        long lastTime = System.currentTimeMillis();

        while (true){
            if(System.nanoTime() - nanoSecond > tick){
                nanoSecond+=tick;
                tps++;
                update();
            }
            else
            {
                fps++;
                frame.repaint();
            }
            if(System.currentTimeMillis() - lastTime > 1000){
                lastTime = System.currentTimeMillis();
                System.out.println(tps+" TPS - "+fps+" FPS");
                tps=0;
                fps=0;
                System.gc();
            }
        }
    }

    private void update (){
        boolean[][] newGrid = new boolean[width][height];

        for( int x = 0; x < width ; x++)
        {
            for( int y = 0; y < height ; y++)
            {
                int count = 0;

                for (int xvoisin = -1; xvoisin < 2 ; xvoisin++)
                {
                    for (int yvoisin = -1; yvoisin < 2 ; yvoisin++)
                    {
                        if (xvoisin == 0 && yvoisin ==0) continue;
                        int nx = x+ xvoisin;
                        int ny = y+ yvoisin;
                        count += (nx >=0 && ny > 0 && nx < width && ny < height && Grid[nx][ny]) ? 1 : 0;
                    }

                }
                // Jeu de la vie classique :
                if(gamechoose==0){
                    newGrid[x][y] = Grid[x][y] ? (count == 2 || count == 3) : count == 3;
                }
                // Jeu de la vie jour et nuit :
                else if (gamechoose==1){
                    newGrid[x][y] = Grid[x][y] ? (count == 3 || count == 4 || count == 6 || count == 7 | count == 8) : ( count == 3 || count == 6 || count == 7 || count == 8);
                }

            }
        }
        Grid = newGrid;
    }

    protected void paintComponent (Graphics g){

        int xOffset = 1280 / width;
        int yOffset = 720 / height;


        g.setColor(Color.GRAY);

        for (int x=0;x<width;x++){

            for (int y=0;y<height;y++){

                if (Grid[x][y]){

                    g.fillRect(x*xOffset,y*yOffset,xOffset,yOffset);
                }
            }
        }


    }

    public static void main(String[] args)
    {
        new Main(128,72,5000);
    }
}
