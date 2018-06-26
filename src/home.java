import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/home")
public class home extends HttpServlet{
	
	public void service(HttpServletRequest request,HttpServletResponse response)
			throws IOException , ServletException {
		response.setContentType("text/html");
		PrintWriter pout = response.getWriter();
		//get cookies from client
		String email=null;
		Cookie[] ck = request.getCookies();
		if(ck!=null)
		{
			for(Cookie c:ck)
			{
				//pout.println(c.getName()+" = "+c.getValue());
				if(c.getName().equals("email")){
				email=c.getValue();
				break;}
			}
		}
		HttpSession session=request.getSession();
		session.setAttribute("email", email);
		if(email==null)
		{
			response.sendRedirect("index.html");
		}
		RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
		rd.include(request, response);
	}
}