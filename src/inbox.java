import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.connection;
@WebServlet("/inbox")
public class inbox extends HttpServlet{
	
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
			String sql="select*from message where recepient_id='"+email+"'";

			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql); //insert,update,delete operation give integer ;value of table updates
			pout.println("<h1 style='text-align:center;color:darkorange;text-decoration:underline;'><i>INBOX</i></h1>");
			
			pout.println("<table border='1' style='width:100%'><tr><th>MessageID</th><th>From</th><th>Subject</th><th>TIME</th><th colspan='2'>ACTION</th></tr>");
			while(rs.next()){
				if(rs.getBoolean("is_in_trash")||rs.getBoolean("is_draft"))
					continue;
				pout.println("<tr>");
				pout.println("<td>"+rs.getString("id")+"</td>");
				pout.println("<td>"+rs.getString("creator_id")+"</td>");
				pout.println("<td>"+rs.getString("subject")+"</td>");
				pout.println("<td>"+rs.getString("create_date")+"</td>");
				pout.println("<td><a href='open1?id="+rs.getString(1)+"'>OPEN</td>");
				pout.println("<td><a href='delete1?id="+rs.getString(1)+"'>DELETE</td>");
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
	
		pout.println("<table width='100%' border='0'>");
		ResultSet rs=null;
		try
		{
		Connection con=connection.getCon();
		Statement st=con.createStatement(); 
		rs=st.executeQuery("select image from image");
		 
		while(rs.next())
		{
		
			pout.println("<table width='70%' height=\"160\" border=\"1\" align=\"center\">");
			pout.println("<tr>");
		 
			pout.println("<td><img src='c:/image/"+rs.getString("files")+"'"+ " width=\"115\" height=\"128\" /></td></tr></table>");
		}
		}
		catch(Exception e)
		{
		pout.print(""+e.getMessage());
		}

	}
}