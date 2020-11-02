import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Manditory: name, phone number, email address, and password
//Optional:shipping address info (street, city, state, and zip code)
//Optiopnal:payment info (card type, number, expiration date)
@WebServlet("/EditProfileServlet")
public class EditProfile extends HttpServlet{
	private String header;
	
	public void init() throws ServletException{
		header = "Edit Profile";
	}
	
	//grabs the customer info and displays it
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the information needed
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h1>" + header + "</h1>");
		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("psw");
		
		//display the customer's current profile/info
		out.println("<p>" + "Name: " + firstName + " " + lastName + "</p>");
		out.println("<p>" + "Email: " + email + "</p>");
		out.println("<p>" + "Phone: " + phone + "</p>");
		out.println("<p>" + "Password: " + password + "</p>");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String firstName = request.getParameter("name-first");
        String lastName = request.getParameter("name-last");
        String email = request.getParameter("email");
        String pass = request.getParameter("psw");
        String phone = request.getParameter("phone");
        pass = "aes_encrypt('" + pass + "', '4050')";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","lkjhlkjh");

            PreparedStatement ps = con.prepareStatement("insert into user values(?,?,?,?,?)");

            ps.setString(1,"test");
            ps.setString(2, pass);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, "'C'");
            
            PreparedStatement psc = con.prepareStatement("insert into Customer values(?,?,?,?,?)");

            psc.setString(1,"test");
            psc.setString(2,"test");
            psc.setString(3, email);
            psc.setString(4, phone);
            psc.setString(5, "'A'");

            
            PrintWriter out1 = response.getWriter(); 
            out1.println("<html><body><b>Successfully Inserted"
                        + "</b></body></html>");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        }
	
	
	public void destroy() {
		//does nothing?
	}
}
