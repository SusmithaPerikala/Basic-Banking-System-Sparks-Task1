import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;

public class checkServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        String cus1 = req.getParameter("cus1");
        String acc1 = req.getParameter("acc1");
        try
        { 
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb?useSSL=false","root","sql$143");
            Statement stm=con.createStatement();
            ResultSet rs = stm.executeQuery("select balance from customers where name='"+cus1+"'");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">"); 
            out.println("<style>");  
            out.println(".btn1 {"); 
            out.println("color:white;");
            out.println("background-color:skyblue;");
            out.println("border: none;");
            out.println("padding: 13px;");
            out.println("text-decoration:none;");
            out.println("font-size: 16px;");
            out.println("border-radius: 12px;");
            out.println("}"); 
            out.println("a {");
            out.println("cursor:pinter;");
            out.println("text-decoration:none;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            if(rs.next())
            {
            	out.println("<center><h1>Your account balance is:"+rs.getString(1)+"</h1></center>");
            }
            else
            {
                out.print("<h1>Please enter correct details</h1");
            }
            out.print("<div align = center><button class = btn1 ><a href = index.html>Back to home</a></button></div>");
            out.println("</body>");
            out.println("</html>");
            con.close();
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
	}
}