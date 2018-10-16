/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatekerp.controller;



import imatekerp.JPanel.AnalysisSales;

import imatekerp.JPanel.InputAdd;
import imatekerp.Menu1;
import imatekerp.JPanel.InputModify;
import imatekerp.JPanel.InputQuery;
import imatekerp.JPanel.InventoryAdd;
import imatekerp.JPanel.InventoryQuery;
import imatekerp.JPanel.OutputAdd;
import imatekerp.JPanel.OutputModify;
import imatekerp.JPanel.OutputQuery;
import imatekerp.JPanel.ProductAdd;
import imatekerp.JPanel.ProductModify;
import imatekerp.JPanel.ProductQuery;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author 49724
 */
public class Controlmenu {

    Map<String, JPanel> control;
    String[] listname = new String[1];
    
    public Controlmenu() {

        // initial
        initial();

    }

    private void initial() {
        control = new HashMap<>();
        control.put("ProductQuery", new ProductQuery());
        control.put("ProductAdd", new ProductAdd());
        control.put("ProductModify", new ProductModify()); 
        control.put("InputQuery", new InputQuery());
        control.put("InputAdd", new InputAdd());
        control.put("InputModify", new InputModify());
        control.put("OutputQuery", new OutputQuery());
        control.put("OutputAdd", new OutputAdd());
        control.put("OutputModify", new OutputModify());
        control.put("InventoryQuery", new InventoryQuery());
        control.put("InventoryAdd", new InventoryAdd());
        control.put("AnalysisSales", new AnalysisSales());
         
    }
        
   
    public JInternalFrame showpage(Object[] para) {
        if(para.length>2){
            listname[0]=para[1].toString()+para[2].toString();
            System.out.println(listname[0]);
        }
        JInternalFrame frame = new JInternalFrame();
        frame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        frame.setResizable(true);
        frame.setClosable(true);
        frame.add(control.get(listname[0]));
        frame.setPreferredSize(new Dimension(1024,768));
        frame.setMaximizable(true);
        frame.setVisible(true);
        frame.pack();
        return frame;
    }
//    public JFrame closepage(int i=0){
//         
//    }

    public static Controlmenu getInstance() {
        return ControlmenuHolder.INSTANCE;
    }

    private static class ControlmenuHolder {

        private static final Controlmenu INSTANCE = new Controlmenu();
    }
}


//       for ( String p : pages) 
//{
//
//            switch (p) {
 //               case "ProductQuery":
  //                  control.put( p, new ProductQuery());
    //            case "ProductAdd":
      //              control.put( p, new ProductAdd());
        //        case "ProductModify":
          //          control.put( p, new ProductModify());  
            //    case "InputQuery":
              //      control.put( p, new InputQuery());
//            }
          

//    public Controlmenu(JInternalFrame frame) {
//
////        Object name[] = null;
////        name [0] = new Object[1];
////        name[0]= menu[1]+ menu[2];
////        if(count = 3){
////            if (name[0].[control].bExists ){
////             showpage();
////            }
////        }
//    }