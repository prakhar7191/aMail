import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/compose")
public class compose extends HttpServlet{
	protected void service(HttpServletRequest request,HttpServletResponse response)
	throws IOException,ServletException{
		
		response.getContentType();
		PrintWriter pout= response.getWriter();
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
		RequestDispatcher rd=request.getRequestDispatcher("compose.html");
		rd.include(request, response);
		}
	}
}