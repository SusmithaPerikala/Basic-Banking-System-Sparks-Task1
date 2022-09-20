import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;

public class tranServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        String cus1 = req.getParameter("cus1");
        String acc1 = req.getParameter("acc1");
        String cus2 = req.getParameter("cus2");
        String acc2 = req.getParameter("acc2");
        String amount = req.getParameter("amount");
        int amt = Integer.parseInt(amount);
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
            	String n = rs.getString(1);
                int bal = Integer.parseInt(n);
            	if(bal - amt > 0)
            	{
            		String q = "update customers set balance = balance +'"+amt+"' where name='"+cus2+"'";
                    int x = stm.executeUpdate(q);
                    String q1 = "update customers set balance = balance - '"+amt+"' where name='"+cus1+"'";
                    int x2 = stm.executeUpdate(q1);
                    out.print("<html><br><br><center><img src = https://www.pngitem.com/pimgs/m/341-3416354_blue-tick-icon-success-icon-png-transparent-png.png width = 400 height = 400<br></center></html>");
                    out.println("<center><h2>Transaction Successful</center></h2>");
            	}
            	else
            	{
            		out.print("<html><br><br><center><img src = https://jumeirahroyal.com/wp-content/uploads/d7e50cb89c.png width = 400 height = 400 <br></center></html>");
                    out.println("<center><h2>Transaction Unsuccessful due to Insufficient Balance...</h2></center>");

            	}
                out.print("<div align = center><button class = btn1 ><a href = index.html>Back to home</a></button></div>");
            }
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