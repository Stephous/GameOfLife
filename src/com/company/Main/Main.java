package com.company.Main;

import com.company.Initialisation.GetGameChoose;
import com.company.Actualisation.UpdateClass;
import com.company.Initialisation.generateRandomAlive;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {

    private final JFrame frame;
    private final int width,height;
    private final int gamechoose;
    private int[][] Grid;

    public Main(int width, int height,int n){

        this.width = width;
        this.height = height;
        Grid = new int[width][height];

        gamechoose = GetGameChoose.gamechooser();
        Grid = generateRandomAlive.Randomizers(n, gamechoose, width, height, Grid);


        frame = new JFrame("Game oF Life");
        frame.setSize(1280,720);
        frame.setUndecorated(true);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(this);
        frame.setBackground(Color.black);
        frame.setVisible(true);
        run();
        frame.dispose();
    }

    private void run (){

        long nanoSecond = System.nanoTime();
        double tick = 1000000000.0/50.0000;
        int tps=0,fps=0;
        int numberloop=2500;
        long lastTime = System.currentTimeMillis();

        while (numberloop>0){
            if(System.nanoTime() - nanoSecond > tick){
                nanoSecond+=tick;
                tps++;
                Grid = UpdateClass.update(width,height,gamechoose,Grid);
                //update();
                numberloop--;
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

    protected void paintComponent (Graphics g){

        int xOffset = 1280 / width;
        int yOffset = 720 / height;

        for (int x=0;x<width;x++){

            for (int y=0;y<height;y++){
                switch (Grid[x][y]) {
                    case 1 -> {
                        g.setColor(Color.RED);
                        g.fillRect(x * xOffset, y * yOffset, xOffset, yOffset);
                    }
                    case 2 -> {
                        g.setColor(Color.YELLOW);
                        g.fillRect(x * xOffset, y * yOffset, xOffset, yOffset);
                    }
                    case 3 -> {
                        g.setColor(Color.GREEN);
                        g.fillRect(x * xOffset, y * yOffset, xOffset, yOffset);
                    }
                    case 4 -> {
                        g.setColor(Color.cyan);
                        g.fillRect(x * xOffset, y * yOffset, xOffset, yOffset);
                    }
                }
            }
        }


    }

    public static void main(String[] args)
    {
            new Main(128,72,2500);
            //new Main(256,144,5000);
            //new Main(640,360,25000);
    }
}
