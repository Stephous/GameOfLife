package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

public class Main extends JPanel {

    private final JFrame frame;

    public Main()
    {
        frame = new JFrame("Game oF Life");
        frame.setSize(900,700);
        frame.setUndecorated(true);
        frame.setResizable(true);

        frame.setLocationRelativeTo(null);

        frame.setContentPane(this);
        frame.setBackground(Color.black);

        frame.setVisible(true);
        run();
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

    }

    protected void paintComponent (Graphics g){

    }

    public static void main(String[] args)
    {
        new Main();
    }
}
