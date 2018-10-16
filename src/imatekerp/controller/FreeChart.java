/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatekerp.controller;

import imatek.connector.SqlHelper;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.SortOrder;
/**
 *
 * @author 49724
 */
public class FreeChart {
    List<String> l= new ArrayList(); 
    String s =new String("select count(maniere) from imatek.stockout where is_deleted=0 and (inroll_date between (select date_add(now(),interval-2 month)) and now() )and maniere =");
    String ss=new String("");
    String w =new String("select sum(prod_quantity) from imatek.stockout where is_deleted=0 and (inroll_date between (select date_add(now(),interval-2 month)) and now() ) and prod_id=");
    String r = new String("select prod_id,prod_price from imatek.product");
    int k =0;  
    double q =0;
    Object x = new Object();
    public void freechartdemand() throws SQLException{
        l.add("AMAZON");
        l.add("FNAC");
        l.add("CDISCOUNT");
        SqlHelper e = new SqlHelper();
        List<Object> o = e.demand(l, s);
        DefaultPieDataset set = new DefaultPieDataset();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i = 0; i < l.size();i++){
        k=Integer.parseInt(o.get(i).toString());
        set.setValue(l.get(i),k); 
        dataset.addValue(k,l.get(i), "Clien");        
}
        JFreeChart c =ChartFactory.createPieChart("Client", set, true, true, false);
        setPiePlotNum(c);
        JFreeChart chart = ChartFactory.createBarChart3D("Sale", "", "", dataset);
        ChartPanel f =new ChartPanel(chart);
        ChartPanel d = new ChartPanel(c);
        JFrame cf =new JFrame();
        cf.add(f,BorderLayout.EAST);
        cf.add(d,BorderLayout.CENTER);
        cf.pack();
        cf.setVisible(true);
    }

    private void setPiePlotNum(JFreeChart c) {
         PiePlot p = (PiePlot)c.getPlot();
         p.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}-{1}-{2}")); 

    }
    public void freechartsale() throws SQLException{
        SqlHelper e = new SqlHelper();
        Map<String,String> o = e.sale(r);
        List<String> keys = new ArrayList<>(o.keySet());
        List<Object> l = e.demand(keys, w);
        DefaultPieDataset set = new DefaultPieDataset();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i = 0; i < l.size();i++){
        k=Integer.parseInt(l.get(i).toString());
        q=Double.parseDouble(o.get(keys.get(i)));
        q=k*q;
        set.setValue(keys.get(i),q);
        dataset.addValue(q, keys.get(i), "");
        
        }
        JFreeChart c =ChartFactory.createPieChart("Sale", set, true, true, false);
        setPiePlotNum(c);
        JFreeChart chart = ChartFactory.createBarChart3D("Sale", "", "", dataset);
        ChartPanel f =new ChartPanel(chart);
        ChartPanel d = new ChartPanel(c);
        JFrame cf =new JFrame();
        cf.add(f,BorderLayout.EAST);
        cf.add(d,BorderLayout.CENTER);
        cf.pack();
        cf.setVisible(true);
    
        
    }
    public void freechartstock() throws SQLException{
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        SqlHelper e = new SqlHelper();
        Map<String,String> o = e.sale(r);
        List<String> keys = new ArrayList<>(o.keySet());
        for(int i = 0; i <keys.size();i++){
            x=e.queryStock(keys.get(i));
            if(x==null){
                k=0;
                dataset.addValue(k, keys.get(i), "stock");
                
            }else{k=Integer.parseInt(x.toString());} 
             dataset.addValue(k, keys.get(i), "stock");
             
        }
        JFreeChart chart = ChartFactory.createBarChart3D("Stock", "", "", dataset);
        ChartFrame cf = new ChartFrame("Stock",chart);
        cf.pack();
        cf.setVisible(true);

    }
}
   
        
 
        
        
        
        
        
   
    

