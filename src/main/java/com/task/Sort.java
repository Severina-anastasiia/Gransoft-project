package com.task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Sort extends JPanel{

    private JFrame frame = new JFrame();
    private JButton sorted = new JButton("Sort");
    private JButton reset = new JButton("Reset");
    private ArrayList<JButton> buttons = new ArrayList<>();
    private Random random = new Random();
    private int count = 0;
    private int index;
    private Color colorB;
    private Color colorF;
    private ButtonsAction buttonsAction = new ButtonsAction();
    private ButtonsActionSort buttonsActionSort = new ButtonsActionSort();

    public Sort() {
        //create X buttons with random number and list of this numbers
        createListOfNumberButtons();

        //create button for sort
        sorted.setBounds(300, 30, 80, 20);
        sorted.setBackground(Color.GREEN);
        sorted.setForeground(Color.WHITE);
        sorted.setFocusable(false);
        sorted.addActionListener(new SortedAction());

        //create button to back to intro screen
        reset.setBounds(300, 60, 80, 20);
        reset.setBackground(Color.GREEN);
        reset.setForeground(Color.WHITE);
        reset.setFocusable(false);
        reset.addActionListener(new ResetAction());

        //add params for window
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLayout(null);
        frame.add(sorted);
        frame.add(reset);
        for (int i = 0; i < buttons.size(); i++) {
            frame.add(buttons.get(i));
        }
        frame.add(this);
        frame.setVisible(true);
    }

    //check list of numbers if there are any less or equal to 30. if not change first
    private void checkForThirty () {
        for (int i = 0; i < buttons.size(); i++) {
            if (Integer.parseInt(buttons.get(i).getText()) <= 30) {
                return;
            }
        }
        buttons.get(0).setText("30");
    }

    private void createListOfNumberButtons () {
        for (int i = 0, y = 30, x = 40; i < Main.userNumber; i++, y+=30) {
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
            button.addActionListener(buttonsAction);
            buttons.add(button);
        }
        checkForThirty();
    }

    private void quickSort (ArrayList < JButton > buttons,int low, int high) {
        if (buttons.isEmpty())
            return;
        if (low == high) {
            return;
        }
        int middle = low + (high - low) / 2;
        int opora = Integer.parseInt(buttons.get(middle).getText());
        int i = low;
        int j = high;
        index = middle;
        colorB = Color.DARK_GRAY;
        colorF = Color.WHITE;
        buttons.get(index).doClick();
        while (i <= j) {
            index = i;
            colorB = Color.GREEN;
            colorF = Color.ORANGE;
            buttons.get(index).doClick();
            while (Integer.parseInt(buttons.get(i).getText()) < opora) {
                index = i;
                colorF = Color.WHITE;
                buttons.get(index).doClick();
                i++;
            }
            index = j;

            colorF = Color.ORANGE;
            buttons.get(index).doClick();
            while (Integer.parseInt(buttons.get(j).getText()) > opora) {
                index = j;
                colorF = Color.WHITE;
                buttons.get(index).doClick();
                j--;
            }
            if (i <= j) {
                int temp = Integer.parseInt(buttons.get(i).getText());
                buttons.get(i).setText(buttons.get(j).getText());
                buttons.get(j).setText(String.valueOf(temp));
                index = i;
                colorB = Color.BLUE;
                colorF = Color.WHITE;
                buttons.get(index).doClick();
                index = j;
                buttons.get(index).doClick();
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(buttons, low, j);
        if (high > i)
            quickSort(buttons, i, high);
        for (int k = 0; k < buttons.size(); k++) {
            index = i;
            colorB = Color.BLUE;
            colorF = Color.WHITE;
            buttons.get(index).doClick();
        }
    }

    private void quickSortRe (ArrayList < JButton > buttons,int low, int high){
        if (buttons.isEmpty())
            return;
        if (low == high) {
            return;
        }
        int middle = low + (high - low) / 2;
        int opora = Integer.parseInt(buttons.get(middle).getText());
        int i = low;
        int j = high;
        index = middle;
        colorB = Color.DARK_GRAY;
        colorF = Color.WHITE;
        buttons.get(index).doClick();
        while (i <= j) {
            index = i;
            colorB = Color.GREEN;
            colorF = Color.ORANGE;
            buttons.get(index).doClick();
            while (Integer.parseInt(buttons.get(i).getText()) > opora) {
                index = i;
                colorF = Color.WHITE;
                buttons.get(index).doClick();
                i++;
            }
            index = j;
            colorF = Color.ORANGE;
            buttons.get(index).doClick();
            while (Integer.parseInt(buttons.get(j).getText()) < opora) {
                index = j;
                colorF = Color.WHITE;
                buttons.get(index).doClick();
                j--;
            }
            if (i <= j) {
                int temp = Integer.parseInt(buttons.get(j).getText());
                buttons.get(j).setText(buttons.get(i).getText());
                buttons.get(i).setText(String.valueOf(temp));
                index = i;
                colorB = Color.BLUE;
                colorF = Color.WHITE;
                buttons.get(index).doClick();
                index = j;
                buttons.get(index).doClick();
                i++;
                j--;
            }
        }
        if (low < j)
            quickSortRe(buttons, low, j);
        if (high > i)
            quickSortRe(buttons, i, high);
        for (int k = 0; k < buttons.size(); k++) {
            index = k;
            colorB = Color.BLUE;
            colorF = Color.WHITE;
            buttons.get(index).doClick();
        }
    }

    class SortedAction implements ActionListener{
        int low = 0;
        int high = Main.userNumber - 1;
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            count++; //for each second click
            if (count % 2 == 0) {
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setBackground(Color.GREEN);
                    buttons.get(i).removeActionListener(buttonsAction);
                    buttons.get(i).addActionListener(buttonsActionSort);
                }
                quickSortRe(buttons, low, high);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).removeActionListener(buttonsActionSort);
                    buttons.get(i).addActionListener(buttonsAction);
                }
            } else {
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setBackground(Color.GREEN);
                    buttons.get(i).removeActionListener(buttonsAction);
                    buttons.get(i).addActionListener(buttonsActionSort);
                }
                quickSort(buttons, low, high);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).removeActionListener(buttonsActionSort);
                    buttons.get(i).addActionListener(buttonsAction);
                }
            }
        }
    }

    class ResetAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            frame.dispose();
        }
    }

    class ButtonsActionSort implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JButton buttonEvent = (JButton) actionEvent.getSource();
            buttonEvent.setBackground(colorB);
            buttonEvent.setForeground(colorF);
            repaint();
            validate();
        }
    }

    class ButtonsAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JButton buttonEvent = (JButton) actionEvent.getSource();
            if (Integer.parseInt(buttonEvent.getText()) <= 30) {
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setText(String.valueOf(random.nextInt(1001)));
                }
                checkForThirty();
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a value smaller or equal to 30");
            }
        }
    }
}