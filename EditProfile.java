import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import jakarta.servlet.http.HttpServletRequest;

//Manditory: name, phone number, email address, and password
//Optional:shipping address info (street, city, state, and zip code)
//Optiopnal:payment info (card type, number, expiration date)
public class EditProfile extends HttpServlet{
	private String header;
	
	public void init() throws ServletException{
		header = "Edit Profile";
	}
	
	//grabs the customer info and displays it
	public void doGet(HttpServletRequest request, HttpServlet response) throws ServletException, IOException {
		//get the information needed
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>" + header + "</h1>");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email_address");
		String phone = request.getParameter("phone_number");
		String password = request.getParameter("password");
		
		//display the customer's current profile/info
		out.println("<p>" + "Name: " + firstName + " " + lastName + "</p>");
		out.println("<p>" + "Email: " + email + "</p>");
		out.println("<p>" + "Phone: " + phone + "</p>");
		out.println("<p>" + "Password: " + password + "</p>");
	}
	
	
	public void destroy() {
		//does nothing?
	}
}
