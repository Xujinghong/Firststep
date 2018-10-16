/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatekerp.controller;

import imatekerp.SimplePanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author 49724
 */
public class MVCstructure {
    public static void main(String[] args){
        JFrame frame = new JFrame("MVC");
        frame.setPreferredSize(new Dimension(600, 800));
        SimplePanel panle = new SimplePanel();
//        SumbitController controller = new SumbitController(panle);
        frame.add(panle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        JPanel p = new JPanel();
        
    }
}
