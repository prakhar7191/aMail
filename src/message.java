import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import bean.connection;
@WebServlet("/message")
public class message extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws IOException , ServletException{
	
		response.setContentType("text/html");
		PrintWriter pout=response.getWriter();
		String bt=request.getParameter("btn");
		String id=request.getParameter("id");
		//System.out.println(id);
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
				
				String to=request.getParameter("to");
				String subject=request.getParameter("subject");
				String message=request.getParameter("message");
				
				Connection con=connection.getCon();
				int flag=0;
				String sql1="select email from user";
				Statement stmt1=con.createStatement();
				ResultSet rs=stmt1.executeQuery(sql1);
				while(rs.next())
				{
					if(rs.getString("email").equals(to))
							{
							flag=1;
							break;
							}
				}
				if(flag==1)
				{
					if(bt.equals("send"))
						{	
							if(id.equals(null))
							{
								String sql="insert into message(subject,creator_id,message_body,create_date,recepient_id,parent_message_id,is_read,is_in_trash,is_del_creator,is_del_recepient,is_draft) values('"+subject+"','"+email+"','"+message+"','"+new Date()+"','"+to+"','',0,0,0,0,0);";
								System.out.println("in");
								Statement stmt=con.createStatement();
								int i=stmt.executeUpdate(sql);
								if(i>0)
								{
									response.sendRedirect("sent");
								}
							}
							else{
								String sql2="delete from message where id='"+id+"'";
								String sql="insert into message(subject,creator_id,message_body,create_date,recepient_id,parent_message_id,is_read,is_in_trash,is_del_creator,is_del_recepient,is_draft) values('"+subject+"','"+email+"','"+message+"','"+new Date()+"','"+to+"','',0,0,0,0,0);";
								//System.out.println("in");
								Statement stmt=con.createStatement();
								
								int i2=stmt.executeUpdate(sql2);
								int i=stmt.executeUpdate(sql);
								if(i>0)
								{
									response.sendRedirect("sent");
								}
							}
						}
					else if(bt.equals("Draft"))
					{
						String sql3="delete from message where id='"+id+"'";
						String sql="insert into message(subject,creator_id,message_body,create_date,recepient_id,parent_message_id,is_read,is_in_trash,is_del_creator,is_del_recepient,is_draft) values('"+subject+"','"+email+"','"+message+"','"+new Date()+"','"+to+"','',0,0,0,0,1);";
						System.out.println("in");
						Statement stmt=con.createStatement();
						int i2=stmt.executeUpdate(sql3);
						int i=stmt.executeUpdate(sql);
						if(i>0)
						{
							response.sendRedirect("draft_display");
						}
					}
				}
				else{
					pout.println("<h1 style=\"color:red;\">No such sender email exists</h1>");
					RequestDispatcher rd=request.getRequestDispatcher("compose.html");//this will return the object
					rd.include(request, response);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			String ImageFile="";
			String itemName = "";
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (!isMultipart)
			{
			}
			else
			{
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = null;
			try
			{
				items = upload.parseRequest(new RequestContext() {
					
					@Override
					public InputStream getInputStream() throws IOException {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public String getContentType() {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public String getCharacterEncoding() {
						// TODO Auto-generated method stub
						return null;
					}
				});
			}
			catch (FileUploadException e)
			{
				e.getMessage();
			}
	 
			Iterator itr = items.iterator();
			while (itr.hasNext())
			{
				FileItem item = (FileItem) itr.next();
				if (item.isFormField())
				{
					String name = item.getFieldName();
					String value = item.getString();
					if(name.equals("ImageFile"))
					{
						ImageFile=value;
					}
	 
				}
				else
				{
					try
					{
					itemName = item.getName();
	File savedFile = new File("C:/"+"image\\"+itemName);
	item.write(savedFile);
	}
	catch (Exception e)
	{
	pout.println("Error"+e.getMessage());
	}
	}
	}
	try
	{
		Connection con=connection.getCon();
		Statement st=con.createStatement();
		st.executeUpdate("insert into message(files) values ('"+itemName+"')");
	 
	}
	catch(Exception el)
	{
	pout.println("Inserting error"+el.getMessage());
	}
	}
	}
	catch (Exception e){
	pout.println(e.getMessage());
	}
	}
}