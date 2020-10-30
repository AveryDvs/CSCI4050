import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/LoginServlet")
public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("email");
        String pass = request.getParameter("psw");
        String query = "select user_id, cast(aes_decrypt(password, \"4050\") as char(100)), User_Type from bookstore.user;";
        String userType = "";
        
        boolean valid = false;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","4122");
			Statement st = con.createStatement();
			ResultSet rs =st.executeQuery(query);
			
			while(rs.next() && !valid) {
				if (rs.getString(1).equalsIgnoreCase(email) && rs.getString(2).equals(pass)) {
					valid = true;
					userType = rs.getString(3);
				}
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if (valid) {
        	if (userType.equals("A")) {
        		RequestDispatcher rs = request.getRequestDispatcher("admin.html");
                rs.include(request, response);
        	} else if (userType.equals("C")) {
        		RequestDispatcher rs = request.getRequestDispatcher("index.html");
                rs.include(request, response);
        	}
        } else {
        	out.println("Username and Password incorrect");
        	RequestDispatcher rs = request.getRequestDispatcher("login.html");
            rs.include(request, response);
        }
	}
}
