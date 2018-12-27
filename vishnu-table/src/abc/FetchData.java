package abc;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.sun.corba.se.pept.transport.Connection;

/**
 * Servlet implementation class FetchData
 */
@WebServlet("/FetchData")
public class FetchData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
 //   public FetchData() {
     //   super();
        // TODO Auto-generated constructor stub
  //  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		 // Connection conn = null;
		try {
			response.setContentType("text/html");
			Class.forName("org.postgresql.Driver");
			Connection conn =   DriverManager.getConnection("jdbc:postgresql://ec2-54-243-150-10.compute-1.amazonaws.com:5432/drcm2e9vgvj94?sslmode=require&user=vvovjsmvtfihfv&password=98795d56f73eb4298da9d07eb93002895e7475e9c982a2c127887e90980cd7b7");
			if(conn!=null) {
				System.out.println("Connection Established");
			}
			PreparedStatement ps = ((java.sql.Connection) conn).prepareStatement("select * from"+" salesforce." +"\"MyCase\""+"order by id");
			
			ResultSet rs = ps.executeQuery();
			PrintWriter out = response.getWriter();
			out.println("<html><body><table border=1><tr><td>subject</td><td>Case Number</td><td>type</td><td>priority</td><td>sfid</td><td>ownerid</td><td>contactid</td><td>accountid</td><td>Reason</td><td>id</td><td>status</td></tr>");
			while(rs.next()) {
			out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td><td>"+rs.getString(11)+"</td></tr>");
			}
			out.println("</table></body></html>");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}
 
}
