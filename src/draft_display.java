import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.connection;
@WebServlet("/draft_display")
public class draft_display extends HttpServlet{
	
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws IOException,ServletException
	{
		response.setContentType("text/html");
		PrintWriter pout=response.getWriter();
		try{

			String email=null;
			Cookie ck[]=request.getCookies();
			
			if(ck!=null)
			{
				for(Cookie c:ck){
					if(c.getName().equals("email")){
						email=c.getValue();
						break;
					}
				}
			}
			if(email==null)
			{
				pout.println("<base target=\"_parent\">");
				pout.println("<h1 style=\"text-align:center;color:red\">You have logged out successfully</h1>");
				pout.println("<form action=\"index.html\">");
				pout.println("<p align=center><input type=\"submit\" value=\"Move To Login Page\"></p>");
				pout.println("</form>");
				//response.sendRedirect("index.html");	
			}
			else{
			Connection con=connection.getCon();
			String sql="select*from message where creator_id='"+email+"'";

			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql); //insert,update,delete operation give integer ;value of table updates
			pout.println("<h1 style='text-align:center;color:darkorange;text-decoration:underline;'><i>DRAFT</i></h1>");
			pout.println("<table border='1' style='width:100%'><tr><th>MessageID</th><th>To</th><th>Subject</th><th>TIME</th><th colspan='2'>ACTION</th></tr>");
			while(rs.next()){
				if(rs.getBoolean("is_del_creator")|| rs.getBoolean("is_draft")==false)
					continue;
				pout.println("<tr>");
				pout.println("<td>"+rs.getString("id")+"</td>");
				pout.println("<td>"+rs.getString("recepient_id")+"</td>");
				pout.println("<td>"+rs.getString("subject")+"</td>");
				pout.println("<td>"+rs.getString("create_date")+"</td>");
				pout.println("<td><a href='editcompose?id="+rs.getString(1)+"'>COMPOSE</td>");
				pout.println("<td><a href='delete?id="+rs.getString(1)+"'>DELETE PERMANENTLY</td>");
				pout.println("</tr>");
				
				//System.out.println("inserted");
				//response.sendRedirect("display");
			}
			con.close();
		}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		pout.println("</table>");

	}
}