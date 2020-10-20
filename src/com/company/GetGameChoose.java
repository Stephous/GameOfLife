package com.company;

import java.io.IOException;

public class GetGameChoose {

    public static int gamechooser(){
        int gamechoose;
        System.out.println("Saisir 0 Pour le jeu classique, 1 pour le jeu Jour & Nuit et 2 pour le jeu QuadLife");
        try {
            String readConsole = (String.valueOf(System.in.read()));
            gamechoose=Integer.parseInt(readConsole)-48;
        } catch (
                IOException e) {
            gamechoose=0;
        }

        if (gamechoose > 2) gamechoose = 2;
        else if(gamechoose <0) gamechoose = 0;
        return gamechoose;

    }

}
