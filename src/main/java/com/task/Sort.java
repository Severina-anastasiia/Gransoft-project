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

    public Sort(){

        //create X buttons with random number and list of this numbers
        createListOfNumberButtons();
        int[] array = new int[buttons.size()];
        for(int i = 0; i < buttons.size(); i++)
            array[i] = Integer.parseInt(buttons.get(i).getText());
        int low = 0;
        int high = buttons.size()-1;

        //create button for sort
        sort.setBounds(300, 30, 80, 20);
        sort.setBackground(Color.GREEN);
        sort.setForeground(Color.WHITE);
        sort.setFocusable(false);
        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                count++; //for each second click
                if(count%2 == 0){
                    quickSort(array, low, high);
                    for(int i =0 ; i< buttons.size(); i++){
                        buttons.get(i).setText(String.valueOf(array[i]));
                    }
                } else{
                    quickSort(array, low, high);
                    for(int i =0, j = buttons.size()-1; i< buttons.size(); i++){
                        buttons.get(i).setText(String.valueOf(array[j]));
                        j--;
                    }
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
        for( int i =0 ; i< buttons.size(); i++){
            frame.add(buttons.get(i));
        }
    }

    //check list of numbers if there are any less or equal to 30. if not change first
    private void checkForThirty(){
        for(int i =0 ; i< buttons.size(); i++){
            if(Integer.parseInt(buttons.get(i).getText()) <= 30){
                return;
            }
        }
        buttons.get(0).setText("30");
    }

    private void createListOfNumberButtons(){
        for(int i = 0, y = 30, x = 40; i < Main.userNumber; i++){
            JButton button = new JButton();
            if(i == 10) {
                x = 110;
                y = 30;
            } else if(i == 20){
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
                    if(Integer.parseInt(button.getText()) <= 30){
                        for(int i =0 ; i< buttons.size(); i++){
                            buttons.get(i).setText(String.valueOf(random.nextInt(1001)));
                        }
                        checkForThirty();
                    } else {
                        JOptionPane.showMessageDialog(frame,"Please select a value smaller or equal to 30");
                    }
                }
            });
            buttons.add(button);
            y += 30;
        }
        checkForThirty();
    }

    private void quickSort(int[] array,int low, int high){
        if(buttons.size() == 0)
            return;
        if(low == high)
            return;
        int middle = low + (high - low) / 2;
        int opora = array[middle];
        int i = low, j = high;
        while (i <= j){
            while (array[i]< opora)
                i++;
            while (array[j]>opora)
                j--;
            if(i <= j){
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if(low < j)
            quickSort(array, low, j);
        if(high > i)
            quickSort(array, i, high);
    }
}
