import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.connection;
@WebServlet("/form2")
public class form2 extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response)
			throws IOException , ServletException {
		response.setContentType("text/html");
		PrintWriter pout = response.getWriter();
		//String n=request.getParameter("pwd");
		//String m=request.getParameter("cpwd");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String gender=request.getParameter("gender");
		String date=request.getParameter("da");
		String mob=request.getParameter("teli");
		//String emai=request.getParameter("emailID");
		//String user=request.getParameter("userID");
		String course=request.getParameter("course");
		String sem=request.getParameter("sem");
		String gitID=request.getParameter("gitID");
		String address=request.getParameter("aname");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String pincode=request.getParameter("pinCode");

		HttpSession session=request.getSession();
		session.setMaxInactiveInterval(1800);
		
		session.setAttribute("fname", fname);
		session.setAttribute("lname", lname);
		session.setAttribute("gender", gender);
		session.setAttribute("date", date);
		session.setAttribute("mob", mob);
		session.setAttribute("course", course);
		session.setAttribute("sem", sem);
		session.setAttribute("gitID", gitID);
		session.setAttribute("address", address);
		session.setAttribute("city", city);
		session.setAttribute("state", state);
		session.setAttribute("pincode", pincode);
		response.sendRedirect("form.html");
		
		/*try{
			Connection con=connection.getCon();
			String sql="insert into user(email,psswd,fname,lname,gender,dob,mob,course,sem,gitID,address,city,state)values('"+emai+"','"+n+"','"+fname+"','"+lname+"','"+gender+"','"+date+"','"+mob+"','"+course+"','"+sem+"','"+gitID+"','"+address+"','"+city+"','"+state+"')"; //if datatype is varchar then value in ' ';number without step

			Statement stmt=con.createStatement();
			int i=stmt.executeUpdate(sql); 
			if(i>0){
				//response.sendRedirect("login");
				//System.out.println("inserted");
				pout.println("<h1 style=\"color:red;\">Successfully Signed Up.Login to continue</h1>");
				RequestDispatcher rd=request.getRequestDispatcher("index.html");//this will return the object
				rd.include(request, response);
			}
			else{
				//pout.println("Choose another id");
			}
			con.close();
		}
		
		catch(Exception e){
			pout.println("<h1 style=\"color:red;\">CHOOSE ANOTHER EMAIL ID</h1>");
			RequestDispatcher rd=request.getRequestDispatcher("form.html");//this will return the object
			rd.include(request, response);
		}

		}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws IOException , ServletException {
		doGet(request,response);*/
	}
}