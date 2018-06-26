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

@WebServlet("/delete3")
public class delete3 extends HttpServlet{
	
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws IOException,ServletException{
		response.setContentType("text/html");
		PrintWriter pout=response.getWriter();
		String id=request.getParameter("id");
		try{
			Connection con=connection.getCon();
			String sql1="select is_del_creator,is_del_recepient from message where id='"+id+"'";
			Statement stmt1=con.createStatement();
			ResultSet rs=stmt1.executeQuery(sql1);
			
			boolean r=false,c=false;
			while(rs.next())
			{
				r=rs.getBoolean("is_del_recepient");
				c=rs.getBoolean("is_del_creator");
			}
			if(r)
			{
				String sql="delete from message where id ='"+id+"'";
				Statement stmt=con.createStatement();
				int i=stmt.executeUpdate(sql); //insert,update,delete operation give integer ;value of table updates
				if(i>0){
					//pout.println("Inserted");
					//System.out.println("inserted");
					response.sendRedirect("sent");
				}
				con.close();
			}
			else{
				String sql2="update message set is_del_creator=1 where id='"+id+"'";
				int i=stmt1.executeUpdate(sql2);
				if(i>0)
				{
					response.sendRedirect("sent");
				}
			}
		}
			catch(Exception e){
				e.printStackTrace();
				
			}
			pout.println("</table>");
		}
}