package com.task;

import javax.swing.*;

public class Main {
    public static int userNumber;
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Intro intro = new Intro();
            }
        });
    }
}
