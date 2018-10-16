/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatek.connector;

import imatekerp.controller.SumbitController;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 *
 * @author 49724
 */
public class SqlHelper {

    private Object preparedStatement;

    public SqlHelper() {
    } 
    public ArrayList<Object> q(Map<String, String> parameters, String query){
         ArrayList<Object> list =new ArrayList<>();    
         
         Org.imatek.connector.ImatekConnect connect = new Org.imatek.connector.ImatekConnect();
         Connection connection = connect.mysqlConnect();
        try{
            if(!parameters.isEmpty()){
                if(parameters.size()>1){
                    query += " where ";
                    System.out.println(query);
                    List<String> keys = new ArrayList<>(parameters.keySet());
// get all value key and then put in a new list
                   for( int i = 0; i < keys.size();i++){
                       if(i == 0){
                           query += keys.get(i) +"="+"'"+parameters.get(keys.get(i))+"'";
                       }else{
                           query += "and "+keys.get(i) +"="+"'"+parameters.get(keys.get(i))+"'";
                       }
                       System.out.println(query);
                   }
                }else{
                    String key = parameters.keySet().iterator().next();
                    query += " where "+key +" = "+"'"+parameters.get(key)+"'";
                   
                }
            }
            PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery();
             Arraylist arraylist = new Arraylist(rs);
             list = arraylist.list;
             System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
   public ArrayList<Object> a(Map<String, String> parameters, String query){
       ArrayList<Object> list = new ArrayList<>();
        String p =new String();
        Org.imatek.connector.ImatekConnect connect = new Org.imatek.connector.ImatekConnect();
        Connection connection = connect.mysqlConnect();
        try{
            query+="(";
            p+="value (";
           List<String> keys = new ArrayList<>(parameters.keySet());
           for( int i = 0; i < keys.size()-1;i++){
             query +=keys.get(i) +",";  
             p+="'"+parameters.get(keys.get(i))+"'"+",";
             if(i==keys.size()-2){
                query+=keys.get(i+1)+")";
                p+="'"+parameters.get(keys.get(i+1))+"'"+")";
                
             }
           
           }
           System.out.println(query);
           System.out.println(p);
           query = query+p;
           System.out.println(query);
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           int rs = preparedStatement.executeUpdate();
     
}catch (SQLException ex) {
            Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }
 
        return list;
    }

   public Object queryStock(String s) {
       Object b =new Object(); 
       Org.imatek.connector.ImatekConnect connect = new Org.imatek.connector.ImatekConnect();
       Connection connection = connect.mysqlConnect();
         try{
              String query = "select\n" +
                             "(select(sum(imatek.stockin.prod_quantity)+imatek.stock.prod_quantity) AS quantityin\n" +
                             "from  imatek.stock, imatek.stockin\n" +
                             "where  imatek.stock.stock_id in (select max(stock_id) from imatek.stock\n" +
                                                            "  where prod_id =?) and imatek.stockin.prod_id =? \n" +
                                                               "and imatek.stockin.inroll_date BETWEEN (select inroll_date from imatek.stock\n" +
                                                                                                      " where stock_id in (select max(stock_id)\n" +
                                                                                                                          "from imatek.stock\n" +
                                                                                                                        "  where prod_id = ?))\n" +
                                                                                                                          "and now())\n" +
                             "-\n" +
                             "(select sum(imatek.stockout.prod_quantity) AS quantityout from   imatek.stockout\n" +
                              "where  imatek.stockout.prod_id =? and imatek.stockout.inroll_date BETWEEN (select inroll_date from imatek.stock\n" +
                                                                                                            "  where stock_id in (select max(stock_id)\n" +
                                                                                                                                 " from imatek.stock\n" +
                                                                                                                                   "where prod_id = ?))\n" +
                                                                                                                                    "and now() ) ;";
        
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             if(query.contains("prod_id")){
                preparedStatement.setString(1, s);
                preparedStatement.setString(2, s);
                preparedStatement.setString(3, s);
                preparedStatement.setString(4, s);
                preparedStatement.setString(5, s);
            }             
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            b =rs.getString(1);            
}catch (SQLException ex) {
            Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return b; 
   }



   

    public ArrayList<Object> afficherAdd(int i) {
        int a =0;
        a=i;
        ArrayList<Object> list = new ArrayList<>();
        Org.imatek.connector.ImatekConnect connect = new Org.imatek.connector.ImatekConnect();
        Connection connection = connect.mysqlConnect();
        List<String> c= new ArrayList(); 
        c.add("imatek.product");
        c.add("imatek.stockin");
        c.add("imatek.stockout");
        c.add("imatek.stock");
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyyMMdd ");
            String currentDate = dateFormat.format(new Date());
            System.out.println(currentDate);
            String query = "select * from ";
            String where = " where DATE_FORMAT(inroll_date,'%Y%m%d')=";
            query = query+c.get(a)+where+currentDate+";";
            System.out.println(query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            Arraylist arraylist = new Arraylist(rs);
            list = arraylist.list;
           
         }catch (SQLException ex) {
            Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return list;
}
    public ArrayList<Object> afficherTout(int i) {
        int a =0;
        a=i;
        ArrayList<Object> list = new ArrayList<>();
        Org.imatek.connector.ImatekConnect connect = new Org.imatek.connector.ImatekConnect();
        Connection connection = connect.mysqlConnect();
        List<String> c= new ArrayList(); 
        c.add("imatek.product");
        c.add("imatek.stockin");
        c.add("imatek.stockout");
        c.add("imatek.stock");
        try {
            String query = "select * from ";
            query = query+c.get(a)+" where is_deleted=0;";
            System.out.println(query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            Arraylist arraylist = new Arraylist(rs);
            list = arraylist.list;
         }catch (SQLException ex) {
            Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return list;
}
public Integer  modify(List<String>s,String query ) {
       int rs =0;
      Org.imatek.connector.ImatekConnect connect = new Org.imatek.connector.ImatekConnect();
        Connection connection = connect.mysqlConnect();
         try{
              PreparedStatement preparedStatement = connection.prepareStatement(query);
              for(int i=0;i<s.size();i++){
              preparedStatement.setString(i+1,s.get(i));
              }
              rs = preparedStatement.executeUpdate();
     
}catch (SQLException ex) {
            Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return rs; 
   }
public Integer  dmodify(List<String>s,String query ) {
       int rs =0;
      Org.imatek.connector.ImatekConnect connect = new Org.imatek.connector.ImatekConnect();
        Connection connection = connect.mysqlConnect();
         try{
              PreparedStatement preparedStatement = connection.prepareStatement(query);
              preparedStatement.setString(1,s.get(rs));
              rs = preparedStatement.executeUpdate();
     
}catch (SQLException ex) {
            Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return rs; 
   }
public List<Object> demand(List<String> s, String query) throws SQLException{
         List<Object>a = new ArrayList<>();    
         Object b = new Object();
         Org.imatek.connector.ImatekConnect connect = new Org.imatek.connector.ImatekConnect();
         Connection connection = connect.mysqlConnect();
         String w = new String();
         w =query;
        try{
                   for( int i = 0; i < s.size();i++){
                     query = query + "'"+s.get(i)+"'"+";";
                     PreparedStatement preparedStatement = connection.prepareStatement(query);
                     ResultSet rs = preparedStatement.executeQuery();
                     rs.next();
                     if(rs.getString(1)==null){
                     b=0;}
                     else {b =rs.getString(1);}
                     a.add(i,b); 
                     query =w;
                       }
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
public Map<String,String> sale(String query) throws SQLException{
         Object b = new Object();
         Object f = new Object();
         Map<String,String> m = new HashMap<>();
         Org.imatek.connector.ImatekConnect connect = new Org.imatek.connector.ImatekConnect();
         Connection connection = connect.mysqlConnect(); 
        try{
                     PreparedStatement preparedStatement = connection.prepareStatement(query);
                     ResultSet rs = preparedStatement.executeQuery();
                     while(rs.next()){
                         b=rs.getString(1);
                         f=rs.getString(2);
                         m.put(b.toString(), f.toString());   
                        
                     }
                           
        } catch (SQLException ex) {
            Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
        
    }

}
//      public ArrayList<Object> query(Map<String, String> parameters){
//        ArrayList<Object> list = null;
//         Org.imatek.connector.ImatekConnect connect = new Org.imatek.connector.ImatekConnect();
//        Connection connection = connect.mysqlConnect();
//        String query = "";
//        try{
//            if(!parameters.isEmpty()){
//                if(parameters.size()>1){
//                    query += " where ";
//                    List<String> keys = new ArrayList<>(parameters.keySet());
//                   for( int i = 0; i < keys.size();i++){
//                       if(i == 0){
//                           query += keys.get(i) +"="+parameters.get(keys.get(i));
//                       }else{
//                           query += " or "+keys.get(i) +"="+parameters.get(keys.get(i));
//                       }
//                   }
//                }else{
//                    String key = parameters.keySet().iterator().next();
//                    query += " where "+key +" = "+parameters.get(key);
//                }
//            }
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//             ResultSet rs = preparedStatement.executeQuery();
//
//            int rowN = 0;
//            while (rs.next()) {
//                rowN++;
//            }
//            rs.beforeFirst();
//            int colN =  rs.getMetaData().getColumnCount();
//
//            Object[] header = new Object[colN];
//            Object[][] data = new Object[rowN][colN];
//            for (int i = 1; i < colN+1; i++) {
//                header[i - 1] = rs.getMetaData().getColumnName(i);
//            }
//            System.out.println(rowN + "" + colN);
//            if (rowN == 0) {
//                list.add(header);
//            }
//            int k = 1;
//            while (rs.next()) {
//                for (int j = 1; j < colN+1; j++) {
//                    data[k - 1][j - 1] = rs.getString(j);
//                }
//                k++;
//            }
//            list.add(header);
//            list.add(data);
//        } catch (SQLException ex) {
//            Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
//    public ArrayList<Object> findProduct(String pdtid, String prodname) {
//        ArrayList<Object> list = new ArrayList<>();
////为什么arraylist设为object
//        Org.imatek.connector.ImatekConnect connect = new Org.imatek.connector.ImatekConnect();
//        Connection connection = connect.mysqlConnect();
//        int indexId=1, indexName=1;
////index的数字排序代表着第几个？ 查询句式为prod_id = ? 此时要把pdtid正确放在对应问号位置即preparedStatement.setString(indexId, pdtid)
//        
//        try {
//            String query = "select * from imatek.product";
//            // add parameter
//            if (pdtid != null && !pdtid.trim().isEmpty() || (prodname != null && !prodname.trim().isEmpty())) {
// //pdtid != null 输入了东西 不管是空格还是字符
// //!pdtid.trim().isEmpty() 输入的东西不是空格 trim 表示删除空白符
//                query += " where ";
//                if (pdtid != null && !pdtid.trim().isEmpty()) {
//                    query = query + " prod_id = ?";
//                    indexId = 1;
//                    if(prodname != null && !prodname.trim().isEmpty()){
//                        query += " or prod_name = ?";
//                        indexName = 2;
//                    }
//                        
//                }else{
//                     if(prodname != null && !prodname.trim().isEmpty()){
//                           query += " prod_name = ?";
//                           indexName = 1;
//                     }
//                      
//                     
//                }
//            }
//            //
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            if(query.contains("prod_id")){
//                preparedStatement.setString(indexId, pdtid);
//            }
//            if(query.contains("prod_name")){
//                preparedStatement.setString(indexName, prodname);
//            }
//            
//
//            ResultSet rs = preparedStatement.executeQuery();
//            
//
//            int rowN = 0;
//            while (rs.next()) {
//                rowN++;
//            }
//            rs.beforeFirst();
//            int colN =  rs.getMetaData().getColumnCount();
//
//            Object[] header = new Object[colN];
//            Object[][] data = new Object[rowN][colN];
//            for (int i = 1; i < colN+1; i++) {
//                header[i - 1] = rs.getMetaData().getColumnName(i);
//            }
//            System.out.println(rowN + "" + colN);
//            if (rowN == 0) {
//                list.add(header);
//            }
//            int k = 1;
//            while (rs.next()) {
//                for (int j = 1; j < colN+1; j++) {
//                    data[k - 1][j - 1] = rs.getString(j);
//                }
//                k++;
//            }
//            list.add(header);
//            list.add(data);
//        } catch (SQLException ex) {
//            Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
//            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
//
//        return list;
//
//    }
//    public ArrayList<Object> findStockin(String pdtid, String time) {
//        ArrayList<Object> list = new ArrayList<>();
//
//        Org.imatek.connector.ImatekConnect connect = new Org.imatek.connector.ImatekConnect();
//        Connection connection = connect.mysqlConnect();
//        int indexId=1, indexTime=1;
//        
//        try {
//            String query = "select * from imatek.stockin";
//            // add parameter
//            if (pdtid != null && !pdtid.trim().isEmpty() || (time != null && !time.trim().isEmpty())) {
//
//                if (pdtid != null && !pdtid.trim().isEmpty()) {
//                    query = query + " where prod_id = ?";
//                    indexId = 1;
//                    if(time != null && !time.trim().isEmpty()){
//                        query += "GROUP BY DATE_FORMAT(inputtime,'%Y-%m-%d') = ?";
//                        indexTime = 2;
//                    }
//                        
//                }else{
//                     if(time != null && !time.trim().isEmpty()){
//                           query += " GROUP BY DATE_FORMAT(inputtime,'%Y-%m-%d') = ?";
//                           indexTime = 1;
//                     }
//                      }
//            }
//            //
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            if(query.contains("prod_id")){
//                preparedStatement.setString(indexId, pdtid);
//            }
//            if(query.contains("GROUP BY DATE_FORMAT(inputtime,'%Y-%m-%d')")){
//                preparedStatement.setString(indexTime, time);
//            }
//            
//
//            ResultSet rs = preparedStatement.executeQuery();
//            System.out.println(rs.toString());
//
//            int rowN = 0;
//            while (rs.next()) {
//                rowN++;
//            }
//            rs.beforeFirst();
//            int colN = rs.getMetaData().getColumnCount();
//
//            Object[] header = new Object[colN];
//            Object[][] data = new Object[rowN][colN];
//            for (int i = 1; i < colN + 1; i++) {
//                header[i - 1] = rs.getMetaData().getColumnName(i);
//            }
//            System.out.println(rowN + "" + colN);
//            if (rowN == 0) {
//                list.add(header);
//            }
//            int k = 1;
//            while (rs.next()) {
//                for (int j = 1; j < colN + 1; j++) {
//                    data[k - 1][j - 1] = rs.getString(j);
//                }
//                k++;
//            }
//            list.add(header);
//            list.add(data);
//            System.out.println(list.get(1).toString());
//        } catch (SQLException ex) {
//            Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
//            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
//
//        return list;
//    }
//    public ArrayList<Object> findStockout(String pdtid, String time,String client) {
//        ArrayList<Object> list = new ArrayList<>();
//
//        Org.imatek.connector.ImatekConnect connect = new Org.imatek.connector.ImatekConnect();
//        Connection connection = connect.mysqlConnect();
//        int indexId=1, indexTime=1,indexClient = 1;
//        
//        try {
//            String query = "select * from imatek.stockout";
//            // add parameter
//            if (pdtid != null && !pdtid.trim().isEmpty() || (time != null && !time.trim().isEmpty())||(client!= null && !client.trim().isEmpty())) {
//
//                if (pdtid != null && !pdtid.trim().isEmpty()) {
//                    query = query + " where prod_id = ?";
//                    indexId = 1;
//                    if(time != null && !time.trim().isEmpty()){
//                        query += "GROUP BY DATE_FORMAT(outputtime,'%Y-%m-%d') = ?";
//                        indexTime = 2;
//                        if(client != null && !client.trim().isEmpty()){
//                            query+="where maniere =?";
//                            indexClient=3;
//                        }
//                    }else if(client != null && !client.trim().isEmpty()){
//                            query+="or maniere =?";
//                            indexClient=2; 
//                       }        
//               }else{
//                     if(time != null && !time.trim().isEmpty()){
//                           query += " GROUP BY DATE_FORMAT(outputtime,'%Y-%m-%d') = ?";
//                           indexTime = 1;
//                        if(client != null && !client.trim().isEmpty()){
//                            query+="where maniere =?";
//                            indexClient=2; 
//                     }
//                      }
//                            }
//                }
//            
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            if(query.contains("prod_id")){
//                preparedStatement.setString(indexId, pdtid);
//            }
//            if(query.contains("GROUP BY DATE_FORMAT(outputtime,'%Y-%m-%d')")){
//                preparedStatement.setString(indexTime,time);
//            }
//            if(query.contains("maniere")){
//                preparedStatement.setString(indexClient, client);
//            }
//            
//            ResultSet rs = preparedStatement.executeQuery();
//
//            int rowN = 0;
//            while (rs.next()) {
//                rowN++;
//            }
//            rs.beforeFirst();
//            int colN = rs.getMetaData().getColumnCount();
//
//            Object[] header = new Object[colN];
//            Object[][] data = new Object[rowN][colN];
//            for (int i = 1; i < colN + 1; i++) {
//                header[i - 1] = rs.getMetaData().getColumnName(i);
//            }
//            System.out.println(rowN + "" + colN);
//            if (rowN == 0) {
//                list.add(header);
//            }
//            int k = 1;
//            while (rs.next()) {
//                for (int j = 1; j < colN + 1; j++) {
//                    data[k - 1][j - 1] = rs.getString(j);
//                }
//                k++;
//            }
//            list.add(header);
//            list.add(data);
//    }catch (SQLException ex) {
//            Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
//            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
//
//        return list;
//    }
//    public ArrayList<Object> findInventory(String stockid,String pdtid, String time) {
//        ArrayList<Object> list = new ArrayList<>();
//
//        Org.imatek.connector.ImatekConnect connect = new Org.imatek.connector.ImatekConnect();
//        Connection connection = connect.mysqlConnect();
//        int indexSId=1,indexPId=1, indexTime=1;
//        
//        try {
//            String query = "select * from imatek.stock";
//            // add parameter
//            if (stockid!= null && !stockid.trim().isEmpty()||(pdtid != null && !pdtid.trim().isEmpty()) || (time != null && !time.trim().isEmpty())) {
//
//                if (stockid != null && !stockid.trim().isEmpty()) {
//                    query = query + " where stock_id = ?";
//                    indexSId = 1;
//                    if(pdtid != null && !pdtid.trim().isEmpty()){
//                        query += " where prod_id = ?";
//                        indexPId = 2;
//                        if(time != null && !time.trim().isEmpty()){
//                            query += "GROUP BY DATE_FORMAT(checktime,'%Y-%m-%d') = ?";
//                            indexTime=3;
//                        }
//                    }else if(time != null && !time.trim().isEmpty()){
//                            query += "GROUP BY DATE_FORMAT(checktime,'%Y-%m-%d') = ?";
//                            indexTime=2; 
//                       }        
//               }else{
//                     if(pdtid != null && !pdtid.trim().isEmpty()){
//                           query += " where prod_id = ?";
//                           indexPId = 1;
//                        if(time != null && !time.trim().isEmpty()){
//                            query += "GROUP BY DATE_FORMAT(checktime,'%Y-%m-%d') = ?";
//                            indexTime=2; 
//                     }
//                      }
//                            }
//                }
//            System.out.println(query);
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            if(query.contains("stock_id")){
//                preparedStatement.setString(indexSId, stockid);
//            }
//            if(query.contains("GROUP BY DATE_FORMAT(checktime,'%Y-%m-%d')")){
//                preparedStatement.setString(indexTime,time);
//            }
//            if(query.contains("prod_id")){
//                preparedStatement.setString(indexPId, pdtid);
//            }
//            
//            ResultSet rs = preparedStatement.executeQuery();
////? 这段是？
//            int rowN = 0;
//            while (rs.next()) {
//                rowN++;
//            }
//            
//            rs.beforeFirst();
//            int colN = rs.getMetaData().getColumnCount();
//
//            Object[] header = new Object[colN];
//            Object[][] data = new Object[rowN][colN];
//            for (int i = 1; i < colN+1; i++) {
//                header[i - 1] = rs.getMetaData().getColumnName(i);
//            }
//            System.out.println(rowN + "" + colN);
//            if (rowN == 0) {
//                list.add(header);
//            }
//            int k = 1;
//            while (rs.next()) {
//                for (int j = 1; j < colN+1; j++) {
//                    data[k - 1][j - 1] = rs.getString(j);
//                }
//                k++;
//            }
//            list.add(header);
//            list.add(data);
//    }catch (SQLException ex) {
//            Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
//            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
//
//        return list;
//    }
//        preparedStatement.setString(1, pdid);
//        preparedStatement.setString(2, pdname);
//        preparedStatement.setString(3, pdprice);
//        preparedStatement.setString(4, pddesc);
