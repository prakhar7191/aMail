package bean;
import java.sql.Connection;
import java.sql.DriverManager;

public class connection{
	public static Connection con=null;
	public static Connection getCon(){
	try{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/amail","root","");
	}
	catch(Exception e){
		
	}
		return con;
	}
}