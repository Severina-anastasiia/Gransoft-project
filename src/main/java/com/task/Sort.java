package com.task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Sort {

    private JFrame frame = new JFrame();
    private JButton sort = new JButton("Sort");
    private JButton reset = new JButton("Reset");
    private ArrayList<JButton> buttons = new ArrayList<>();
    private Random random = new Random();
    private int count = 0;


    public Sort() {

        //create X buttons with random number and list of this numbers
        createListOfNumberButtons();
        int low = 0;
        int high = Main.userNumber - 1;
        //create button for sort
        sort.setBounds(300, 30, 80, 20);
        sort.setBackground(Color.GREEN);
        sort.setForeground(Color.WHITE);
        sort.setFocusable(false);
        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                count++; //for each second click
                if (count % 2 == 0) {
                    for(int i = 0; i < buttons.size(); i++)
                        buttons.get(i).setBackground(Color.GREEN);
                    quickSortRe(buttons, low, high);
                } else {
                    for(int i = 0; i < buttons.size(); i++)
                        buttons.get(i).setBackground(Color.GREEN);
                    quickSort(buttons, low, high);
                }
            }
        });

        //create button to back to intro screen
        reset.setBounds(300, 60, 80, 20);
        reset.setBackground(Color.GREEN);
        reset.setForeground(Color.WHITE);
        reset.setFocusable(false);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
            }
        });

        //add params for window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400, 450);
        frame.setLayout(null);
        frame.add(sort);
        frame.add(reset);
        for (int i = 0; i < buttons.size(); i++) {
            frame.add(buttons.get(i));
        }
    }

    //check list of numbers if there are any less or equal to 30. if not change first
    private void checkForThirty() {
        for (int i = 0; i < buttons.size(); i++) {
            if (Integer.parseInt(buttons.get(i).getText()) <= 30) {
                return;
            }
        }
        buttons.get(0).setText("30");
    }

    private void createListOfNumberButtons() {
        for (int i = 0, y = 30, x = 40; i < Main.userNumber; i++) {
            JButton button = new JButton();
            if (i == 10) {
                x = 110;
                y = 30;
            } else if (i == 20) {
                x = 180;
                y = 30;
            }
            button.setBounds(x, y, 70, 20);
            button.setText(String.valueOf(random.nextInt(1001)));
            button.setBackground(Color.BLUE);
            button.setForeground(Color.WHITE);
            button.setFocusable(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (Integer.parseInt(button.getText()) <= 30) {
                        for (int i = 0; i < buttons.size(); i++) {
                            buttons.get(i).setText(String.valueOf(random.nextInt(1001)));
                        }
                        checkForThirty();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please select a value smaller or equal to 30");
                    }
                }
            });
            buttons.add(button);
            y += 30;
        }
        checkForThirty();
    }

    private void quickSort(ArrayList<JButton> buttons,int low, int high){
        if(buttons.size() == 0)
            return;
        if(low == high) {
            return;
        }
        int middle = low + (high - low) / 2;
        int opora = Integer.parseInt(buttons.get(middle).getText());
        int i = low, j = high;
        buttons.get(middle).setBackground(Color.GRAY);

        while (i <= j){
            buttons.get(i).setForeground(Color.ORANGE);

            while (Integer.parseInt(buttons.get(i).getText())< opora) {
                buttons.get(i).setForeground(Color.WHITE);
                i++;
            }
            buttons.get(j).setForeground(Color.ORANGE);
            while (Integer.parseInt(buttons.get(j).getText())>opora) {
                buttons.get(j).setForeground(Color.WHITE);
                j--;
            }
            if(i <= j){
                int temp = Integer.parseInt(buttons.get(i).getText());
                buttons.get(i).setText(buttons.get(j).getText());
                buttons.get(j).setText(String.valueOf(temp));

                buttons.get(i).setForeground(Color.WHITE);
                buttons.get(i).setBackground(Color.BLUE);

                buttons.get(j).setForeground(Color.WHITE);
                buttons.get(j).setBackground(Color.BLUE);
                i++;
                j--;
            }
        }
        if(low < j)
            quickSort(buttons, low, j);
        if(high > i)
            quickSort(buttons, i, high);
        for (int k = 0; k < buttons.size(); k++) {
            buttons.get(k).setForeground(Color.WHITE);
            buttons.get(k).setBackground(Color.BLUE);
        }
    }

    private void quickSortRe(ArrayList<JButton> buttons,int low, int high){
        if(buttons.size() == 0)
            return;
        if(low == high) {
            return;
        }
        int middle = low + (high - low) / 2;
        int opora = Integer.parseInt(buttons.get(middle).getText());
        int i = low, j = high;
        buttons.get(middle).setBackground(Color.GRAY);

        while (i <= j){
            buttons.get(i).setForeground(Color.ORANGE);

            while (Integer.parseInt(buttons.get(i).getText()) > opora) {
                buttons.get(i).setForeground(Color.WHITE);
                i++;
            }
            buttons.get(j).setForeground(Color.ORANGE);
            while (Integer.parseInt(buttons.get(j).getText())<opora) {
                buttons.get(j).setForeground(Color.WHITE);
                j--;
            }
            if(i <= j){
                int temp = Integer.parseInt(buttons.get(j).getText());
                buttons.get(j).setText(buttons.get(i).getText());
                buttons.get(i).setText(String.valueOf(temp));

                buttons.get(i).setForeground(Color.WHITE);
                buttons.get(i).setBackground(Color.BLUE);

                buttons.get(j).setForeground(Color.WHITE);
                buttons.get(j).setBackground(Color.BLUE);
                i++;
                j--;
            }
        }
        if(low < j)
            quickSortRe(buttons, low, j);
        if(high > i)
            quickSortRe(buttons, i, high);
        for (int k = 0; k < buttons.size(); k++) {
            buttons.get(k).setForeground(Color.WHITE);
            buttons.get(k).setBackground(Color.BLUE);
        }
    }
}
