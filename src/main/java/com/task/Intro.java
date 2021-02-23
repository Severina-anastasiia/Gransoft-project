package com.task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Intro{

    private JFrame frame = new JFrame("Intro");
    private JLabel jLabel = new JLabel("How many numbers to display?");
    private JTextField jTextField = new JTextField("", 5);
    private JButton jButton = new JButton("Enter");

    public Intro(){

        jLabel.setBounds(100, 100, 250, 20);

        jTextField.setBounds(130, 130, 150, 20);

        jButton.setBounds(160, 160, 90, 20);
        jButton.setBackground(Color.BLUE);
        jButton.setForeground(Color.WHITE);
        jButton.addActionListener(new ListenerActionButton());

        //add params for window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400, 450);
        frame.setLayout(null);
        frame.add(jLabel);
        frame.add(jTextField);
        frame.add(jButton);
    }

    class ListenerActionButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try{
                Main.userNumber = Integer.parseInt(jTextField.getText());
            }catch (Exception e){
                JOptionPane.showMessageDialog(frame,"Please enter the number");
                jTextField.setText("");
                throw new NumberFormatException();
            }
            if(actionEvent.getSource() == jButton){
                Sort sort = new Sort();
            }
        }
    }

}
