import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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

@WebServlet("/editcompose")
public class edit_compose extends HttpServlet{
	
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws IOException,ServletException{
		response.setContentType("text/html");
		PrintWriter pout=response.getWriter();
		String id=request.getParameter("id");
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
		//pout.println(email);
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
		try{
			Connection con=connection.getCon();
			String sql="select*from message where id='"+id+"'";

			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql); //insert,update,delete operation give integer ;value of table updates
			pout.println("<html><title>Compose</title><style>span{color:gray;font-family:verdana;padding:15px;}input[type=text]{outline:none;border:none;width:85%;font-family:Arial;font-size:20px;}textarea{width:100%;height:300px;display:block;font-family:Arial;font-size:20px;}button{width:20%;height:50px;color:black;}button:hover{background-color:black;color:white;border:3px solid darkorange;}#btns{text-align:center;display:inline;}form{display:inline;}</style><head></html><body><form action='message?id="+id+"' method='post'>");
			
			while(rs.next()){
				System.out.println("inserted");
				pout.println("<hr>");
				pout.println("<input type='hidden' name='id' value='"+rs.getString("id")+"'>");
				pout.println("<span><b>To:</b></span><input type='text' name='to' value='"+rs.getString("recepient_id")+"'><br><hr><br>");
				pout.println("<span><b>Subject:</b></span><input type='text' name='subject' value='"+rs.getString("subject")+"'><br><hr><br>");
				pout.println("<label for='upload'><textarea name='message'>"+rs.getString("message_body")+"</textarea><input type='file' id='upload' multiple></label><br><hr><br><div id='btns'>");
				pout.println("<button type='submit' name='btn' value='send'>Send</button><button type='submit' name='btn' value='Draft'>Save As Draft</button><button type='button' value='attach'>Attach</button>");
				pout.println("</div></form>");
				//pout.println("<form action='draftmessage' method='post'><div id='btns'><button type='submit' value='Draft'>Save As Draft</button><br><hr><br></div></form>");
				
				//response.sendRedirect("display");
			}
			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
			pout.print("some problem occurred.Please try again");
		}
		pout.println("</body></html>");
	}
	}
}