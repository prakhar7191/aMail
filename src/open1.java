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

@WebServlet("/open1")
public class open1 extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws IOException,ServletException{
		
		response.setContentType("text/html");
		PrintWriter pout=response.getWriter();
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
		String id=request.getParameter("id");
		try{
			Connection con=connection.getCon();
			String sql="select * from message where id='"+id+"'";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			boolean a=false;
			String message=null;
			pout.println("<html><title>Compose</title><style>span{color:gray;font-family:verdana;padding:15px;}input[type=text]{outline:none;border:none;width:85%;font-family:Arial;font-size:20px;}textarea{width:100%;height:300px;display:block;font-family:Arial;font-size:20px;}button{width:20%;height:50px;color:black;}button:hover{background-color:black;color:white;border:3px solid darkorange;}</style><head>");
			
			while(rs.next())
			{
				if(rs.getBoolean("is_read")==false)
				{
					String sql2="update message set is_read=1 where id='"+id+"'";
					Statement stmt2=con.createStatement();
					int i=stmt2.executeUpdate(sql2);
					if(i>0)
						response.sendRedirect("open1?id='"+id+"'");
				}
				pout.println("<body><h1><i>Inbox Message</i></h1>");
				pout.println("<hr>");
				pout.println("<input type='hidden' name='id' value='"+rs.getString("id")+"'>");
				pout.println("<span><b>From:</b></span><input type='text' name='to' value='"+rs.getString("creator_id")+"' readonly><br><hr><br>");
				pout.println("<span><b>Subject:</b></span><input type='text' name='subject' value='"+rs.getString("subject")+"' readonly><br><hr><br>");
				pout.println("<label for='upload'><textarea name='message' readonly>"+rs.getString("message_body")+"</textarea><input type='file' id='upload' multiple></label><br><hr><br></body>");
				pout.println("<form action='reply?id="+id+"' method='post'><button type='submit' value='send'>Reply</button></form>");
			}
		}
		catch(Exception e)
		{
			pout.println("some error occur. Please try again.");
		}
		}
	}
}