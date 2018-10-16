package imatek.connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import Org.imatek.connector.ImatekConnect;
import imatekerp.Login;
import javax.swing.JFrame;



public class Imatekerp {
	public static void main(String[] args) throws SQLException {
		ImatekConnect connect = new ImatekConnect();
		Connection connection = connect.mysqlConnect();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from product");
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		int colums = rs.getMetaData().getColumnCount();
		for(int i = 1;i<colums+1;i++ ) {
			System.out.println(rs.getMetaData().getColumnName(i)+" "+rs.getString(i));
		}

                
	}

}
