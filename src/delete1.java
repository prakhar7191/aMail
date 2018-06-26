import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.connection;
@WebServlet("/delete1")
public class delete1 extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws IOException,ServletException{
		String id=request.getParameter("id");
		try{
			Connection con=connection.getCon();
			String sql="update message set is_in_trash=1 where id='"+id+"'";
			Statement stmt=con.createStatement();
			int i = stmt.executeUpdate(sql);
			if(i>0)
			{
				response.sendRedirect("inbox");
			}
		}
		catch(Exception e)
		{
			
		}
	}
}