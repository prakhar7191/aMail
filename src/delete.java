import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.connection;

@WebServlet("/delete")
public class delete extends HttpServlet{
	
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws IOException,ServletException{
		response.setContentType("text/html");
		PrintWriter pout=response.getWriter();
		String id=request.getParameter("id");
		try{
			Connection con=connection.getCon();
			String sql="delete from message where id ='"+id+"'";
			Statement stmt=con.createStatement();
			int i=stmt.executeUpdate(sql); //insert,update,delete operation give integer ;value of table updates
			if(i>0){
					//pout.println("Inserted");
					//System.out.println("inserted");
					response.sendRedirect("draft_display");
				}
				con.close();
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
			//pout.println("</table>");
		}
}