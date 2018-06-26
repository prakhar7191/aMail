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
@WebServlet("/login")
public class login extends HttpServlet{
	protected void service(HttpServletRequest request,HttpServletResponse response)
	throws IOException,ServletException{
		
		response.setContentType("text/html");
		PrintWriter pout=response.getWriter();
		String n=request.getParameter("emailID");
		String m=request.getParameter("pwd");
		
		try{
			Connection con=connection.getCon();
			String sql="select psswd from user where email='"+n+"'";
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String psswd=null;
			while(rs.next())
			{
				psswd=rs.getString("psswd");
			}
			if(m.equals(psswd))
			{
				Cookie ck=new Cookie("email",n);
				response.addCookie(ck);
				response.sendRedirect("home");
			}
			else{
				//pout.println("<body><script>document.getElementById('error')=\"Enter Correct Data\"</script><body>");
				pout.println("<h1 style=\"color:red;\">Enter Correct Data</h1>");
				RequestDispatcher rd=request.getRequestDispatcher("index.html");//this will return the object
				rd.include(request, response);
			}
		}
		catch(Exception e)
		{
			//pout.println("<h1 style=\"color:red;\">Enter Correct Data</h1>");
			//RequestDispatcher rd=request.getRequestDispatcher("index.html");//this will return the object
			//rd.include(request, response);
			pout.println("some error");
		}
	}
}