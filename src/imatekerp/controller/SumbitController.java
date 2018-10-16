/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatekerp.controller;

import imatek.connector.SqlHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author 49724
 */
public class SumbitController {
    
        Map<String, String> parametersHolder = new HashMap<>();
        Map<String, String> param = new HashMap<>();
        String query = new String();
        ArrayList<Object> table = new ArrayList<>();
        String p = new String();
        public ArrayList<Object>SumbitController(Map<String, String> p1,String s) {
             this.parametersHolder = p1;
             this.query=s;
             parametersHolder.keySet().forEach(key -> {
                if (!parametersHolder.get(key).trim().isEmpty()) {
                    param.put(key, parametersHolder.get(key).trim());
                }
            });
             Submit();
            return null;          
        }       
        public void Submit() {
            SqlHelper sq = new SqlHelper();
            ArrayList<Object> list = sq.q(param, query);
            this.table=list;           
        }

        public ArrayList<Object> l(){
            return this.table;
            
        }
        public int warn(String w) {
            int i =0;
            this.p=w;
            
            if(this.p.trim().isEmpty()){
              javax.swing.JOptionPane.showMessageDialog(null,"Prod_id can not be null!");  
              i =1;
            } 
            return i;
     
}
}
       
    

//    public SumbitController(JPanel w) {
//        this.jpanel=w;
////        if(this.jpanel==pq){
//        
//        Map<String, String> p1 = new HashMap<>();       
//        p1.put("prod_id",ProductQuery.jPanel4.Prod_id.getText()); 
//        p1.put("prod_name", pq.getProd_name().getText());
//        submit = new Submit(p1);   
//        System.out.println("1");
//        
//        }
////    }
//    public class Submit{

//            this.parametersHolder = parameters;
//            String query_pq = new String();
//            query_pq="select * from imatek.product";
//
//            Map<String, String> param = new HashMap<>();
//            parametersHolder.keySet().forEach(key -> {
//                if (!parametersHolder.get(key).trim().isEmpty()) {
//                    param.put(key, parametersHolder.get(key).trim());
//                }
//            });
//            System.out.println("2");
//        public SumbitController(ArrayList<Object> o){
//            this.table=o;
//            System.out.println(this.table.size());
//            l();
//        }
    

    

