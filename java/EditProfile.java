import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


//Manditory: name, email address, and password
//Optional:shipping address info (street, city, state, and zip code)
//Optiopnal:payment info (card type, number, expiration date)
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet{

	//grabs the customer info and displays it
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the information needed
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h1>" + "Edit Profile" + "</h1>");
		String firstName = request.getParameter("name-first");
		String lastName = request.getParameter("name-last");
		String ePassword = request.getParameter("psw");
		String password = request.getParameter("psw-repeat");
		String password2 = request.getParameter("psw-repeat");
		String email = request.getParameter("email");
		
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root",4122);
            if(password.equals(password2)) {
            	PreparedStatement stmt1 = con.prepareStatement("insert into user values(name-first,name-last,psw) VALUES(?,?,?)");
            	stmt1.setString(1, firstName);
                stmt1.setString(2,lastName);
            	stmt1.setString(3,password);
            	stmt1.executeUpdate();
            	
            	PreparedStatement stmt2 = con.prepareStatement("insert into Customer values(email) VALUES(?)");
            	stmt2.setString(1,email);
            	stmt2.executeUpdate();
            	
            	out.println("<html><body><b>" + "Successfully Updated" + "</b></body></html>");
            }
            else {
            	out.println("Invalid information, Try again");
            }
        }	catch(SQLException | ClassNotFoundException e) {
        	e.printStackTrace();
        }
	}
}
