package Org.imatek.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ImatekConnect {
	private String url = "jdbc:mysql://localhost:3306/imatek?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String usename = "jinghongxu";
	private String password = "20121728";
	private String driver = "com.mysql.cj.jdbc.Driver";

	public ImatekConnect() {

	}

	public ImatekConnect(String url, String usename, String password, String driver) {
		this.url = url;
		this.usename = usename;
		this.password = password;
		this.driver = driver;
	}

	public Connection mysqlConnect() {
		
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, usename, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

    /**
     *
     * @return
     */
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsename() {
		return usename;
	}

	public void setUsename(String usename) {
		this.usename = usename;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
