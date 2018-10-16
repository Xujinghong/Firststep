/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatek.connector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author 49724
 */
public class Arraylist  {
     ArrayList<Object> list = new ArrayList<>();
    public Arraylist(ResultSet rs) throws SQLException{
      
        int rowN = 0;
            while (rs.next()) {
                rowN++;
            }
            rs.beforeFirst();
            int colN =  rs.getMetaData().getColumnCount();

            Object[] header = new Object[colN];
            Object[][] data = new Object[rowN][colN];
            for (int i = 1; i < colN+1; i++) {
                header[i - 1] = rs.getMetaData().getColumnName(i);
            }
            System.out.println(rowN + "" + colN);
            if (rowN == 0) {
                list.add(header);
            }
            int k = 1;
            while (rs.next()) {
                for (int j = 1; j < colN+1; j++) {
                    data[k - 1][j - 1] = rs.getString(j);
                }
                k++;
            }
            list.add(header);
            list.add(data);

//            list();
            
        
    }
//   public void list(){
//       ArrayList<Object> l = new ArrayList<>();
//       l=list;     
//   }
   
//   private class list{
//       ArrayList<Object> t = new ArrayList<>();
//       public list(ArrayList<Object> m){
//          this.t=m;
//       }
//      
//   }
   }

