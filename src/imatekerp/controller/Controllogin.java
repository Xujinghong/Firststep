/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatekerp.controller;

import imatekerp.Login;
import imatekerp.Menu1;
import imatekerp.Menu2;
import imatekerp.Menu3;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;


/**
 *
 * @author 49724
 */
public class Controllogin {
    Map<String, JFrame> login;
    String[] user = {""};
    int i =0;
   public Controllogin() {
        initial();
    }

    private void initial() {
        login = new HashMap<>();
        login.put("1728xujinghong", new Menu1());
        login.put("0134simon", new Menu2());
        login.put("0134stock", new Menu3());
             
    } 
    public JFrame acceder(String name, char[] pass) {
        for(i=0;i<pass.length;i++){
            user[0] += pass[i];
        }
        user[0]+=name;
        System.out.println(user[0]);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame = login.get(user[0]);
        frame.setPreferredSize(new Dimension(1024,768));
        frame.setVisible(true);
        frame.pack();
        return frame;
    }
}
