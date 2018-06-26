import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/logout")
public class logout extends HttpServlet{
	protected void service(HttpServletRequest request,HttpServletResponse response)
	throws IOException,ServletException{
		Cookie email=null;
		Cookie ck[]=request.getCookies();
		
		if(ck!=null){
		for(Cookie c:ck){
				if(c.getName().equals("email")){
					email=c;
					break;
				}
			}
		}
		if(email!=null){
			email.setMaxAge(0);
			response.addCookie(email);
		}
		HttpSession session=request.getSession();
		session.invalidate();
		response.sendRedirect("index.html");
	}
}