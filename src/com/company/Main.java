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

    private boolean[][] Grid;

    public Main(int width, int height,int n)
    {
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

    private void generateRandomAlive(int x){
        Random rand = new Random();

        List<String> cantPlace = new ArrayList<>();

        while ( x > 0){
            int rx = rand.nextInt(width);
            int ry = rand.nextInt(height);

            if(cantPlace.contains(rx+"-"+ry)) continue;

            cantPlace.add(rx+"-"+ry);

            Grid[rx][ry] = true;

            x--;
        }
    }
    private void run ()
    {
        long nanoSecond = System.nanoTime();
        double tick = 1000000000.0/20.0;
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

    private void update ()
    {
        boolean[][] newGrid = new boolean[width][height];

        for( int x = 0; x < width ; x++)
        {
            for( int y = 0; y < height ; y++)
            {
                int count = 0;

                for (int xo = -1; xo < 2 ; xo++)
                {
                    for (int yo = -1; yo < 2 ; yo++)
                    {
                        if (xo == 0 && yo ==0) continue;
                        int nx = x+ xo;
                        int ny = y+ yo;
                        count += (nx >=0 && ny > 0 && nx < width && ny < height && Grid[nx][ny]) ? 1 : 0;
                    }

                }
                newGrid[x][y] = Grid[x][y] ? (count == 2 || count == 3) : count == 3;

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
        new Main(128,72,1000);
    }
}
