package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends JPanel { // initialisation variables globale

    private final JFrame frame;

    private final int width,height;


    private int gamechoose;

    private int[][] Grid;

    public Main(int width, int height,int n){ // génération de la grille de jeu, de l'affichage du jeu et appels des fonctions

        this.width = width;
        this.height = height;
        Grid = new int[width][height];


        getGamechoose();
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

    private void getGamechoose(){ // Sélection du jeu dans la console
        System.out.println("Saisir 0 Pour le jeu classique, 1 pour le jeu Jour & Nuit et 2 pour le jeu QuadLife");
        try {
            String readConsole = (String.valueOf(System.in.read()));
            gamechoose=Integer.parseInt(readConsole)-48;
        } catch (IOException e) {
            gamechoose=0;
        }

        if (gamechoose > 2) gamechoose = 2;
        else if(gamechoose <0) gamechoose = 0;
    }
    private void generateRandomAlive(int nbCell){ // Initialise aléatoirement des cellules vivantes avec leur etat aléatoire dans le tableau
        Random rand = new Random();
        int Etatcellule = 1;
        List<String> cantPlace = new ArrayList<>();

        while ( nbCell > 0) {
            if (gamechoose ==2) {
                Etatcellule=rand.nextInt(4)+1;
            }
            int randx = rand.nextInt(width);
            int randy = rand.nextInt(height);

            if (cantPlace.contains(randx + "-" + randy)) continue;

            cantPlace.add(randx + "-" + randy);

            Grid[randx][randy] = Etatcellule;

            nbCell--;

        }
    }



    private void run (){ // actualisation du jeu en fonction tu temps réel

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

    private void update (){ // Mise à jour de la grille en suivant les règles du jeu de la vie choisi
        int[][] newGrid = new int[width][height];

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
                        count += (nx >=0 && ny > 0 && nx < width && ny < height && Grid[nx][ny]==1) ? 1 : 0;
                    }

                }
                // Jeu de la vie classique :
                if(gamechoose==0){
                    newGrid[x][y] = Grid[x][y]==1 ? (count == 2 || count == 3)?1:0 : (count == 3)?1:0;
                }
                // Jeu de la vie jour et nuit :
                else if (gamechoose==1){
                    newGrid[x][y] = Grid[x][y]==1 ? (count == 3 || count == 4 || count == 6 || count == 7 | count == 8)?1:0 : ( count == 3 || count == 6 || count == 7 || count == 8)?1:0;
                }
                // Jeu de la vie QuadLife :
                else if (gamechoose==2){
                    newGrid[x][y] = Grid[x][y]==1 ? (count == 2 || count == 3)?1:0 :
                            ( count == 3 )?1:0;
                }

            }
        }
        Grid = newGrid;
    }

    protected void paintComponent (Graphics g){ // rend visible les cellules vivantes dans la grille

        int xOffset = 1280 / width;
        int yOffset = 720 / height;

        for (int x=0;x<width;x++){

            for (int y=0;y<height;y++){
                switch (Grid[x][y]){
                    case 1:
                        g.setColor(Color.RED);
                        g.fillRect(x*xOffset,y*yOffset,xOffset,yOffset);
                        break;
                    case 2:
                        g.setColor(Color.ORANGE);
                        g.fillRect(x*xOffset,y*yOffset,xOffset,yOffset);
                        System.out.println("cas ORANGE");
                        break;
                    case 3:
                        g.setColor(Color.yellow);
                        g.fillRect(x*xOffset,y*yOffset,xOffset,yOffset);
                        break;
                    case 4:
                        g.setColor(Color.GREEN);
                        g.fillRect(x*xOffset,y*yOffset,xOffset,yOffset);
                        break;
                }
            }
        }


    }

    public static void main(String[] args) // Génération de la taille des cases et du nombre de cases vivantes dans l'initialisation
    {
        new Main(128,72,5000);
    }
}
