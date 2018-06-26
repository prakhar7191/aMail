import java.io.IOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.connection;
public class Form extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws IOException , ServletException {
		response.setContentType("text/html");
		PrintWriter pout = response.getWriter();
		HttpSession session=request.getSession();
		
		String n=request.getParameter("pwd");
		//String m=request.getParameter("cpwd");
		String fname=(String)session.getAttribute("fname");
		String lname=(String)session.getAttribute("lname");
		String gender=(String)session.getAttribute("gender");
		String date=(String)session.getAttribute("date");
		String mob=(String)session.getAttribute("mob");
		String emai=request.getParameter("emailID");
		//String user=request.getParameter("userID");
		String course=(String)session.getAttribute("course");
		String sem=(String)session.getAttribute("sem");
		String gitID=(String)session.getAttribute("gitID");
		String address=(String)session.getAttribute("aname");
		String city=(String)session.getAttribute("city");
		String state=(String)session.getAttribute("state");
		String pincode=(String)session.getAttribute("pincode");
		System.out.println(pincode);
		int flag=0;
		try{
			Connection con=connection.getCon();
			String sql1="select email from user";
			Statement stmt1=con.createStatement();
			ResultSet rs=stmt1.executeQuery(sql1); 
			while(rs.next())
			{
				if((rs.getString("email")).equals(emai))
					{
					flag=1;
					break;
					}
			}
			if(flag==0)
			{
				String sql="insert into user(email,psswd,fname,lname,gender,dob,mob,course,sem,gitID,address,city,state,pincode)value('"+emai+"','"+n+"','"+fname+"','"+lname+"','"+gender+"','"+date+"','"+mob+"','"+course+"','"+sem+"','"+gitID+"','"+address+"','"+city+"','"+state+"','"+pincode+"')"; //if datatype is varchar then value in ' ';number without step
				
				//String sql="insert into user(email,pincode)values('"+emai+"','"+pincode+"')";
				
				Statement stmt=con.createStatement();
				
				int i=stmt.executeUpdate(sql); 
				System.out.println("check");
				if(i>0){
					
					//response.sendRedirect("login");
					//System.out.println("inserted");
					pout.println("<h1 style=\"color:red;\">Successfully Signed Up.Login to continue</h1>");
					RequestDispatcher rd=request.getRequestDispatcher("index.html");//this will return the object
					rd.include(request, response);
				}
			}
			else{
				pout.println("<h1 style=\"color:red;\">CHOOSE ANOTHER EMAIL ID</h1>");
				RequestDispatcher rd=request.getRequestDispatcher("form.html");//this will return the object
				rd.include(request, response);
				}
			con.close();
		}
		
		catch(Exception e){
			e.printStackTrace();
		}

		}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws IOException , ServletException {
		doGet(request,response);
	}
}