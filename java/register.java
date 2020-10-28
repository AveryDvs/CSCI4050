import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/RegistrationServlet")
public class Register extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String nameF = request.getParameter("name-first");
        String nameL = request.getParameter("name-last");
        String email = request.getParameter("email");
        String pass = request.getParameter("psw");
        String phone = request.getParameter("phone");
        pass = "aes_encrypt('" + pass + "', '4050')";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");

            PreparedStatement ps = con.prepareStatement("insert into user values(?,?,?,?,?)");

            ps.setInt(1,1);
            ps.setString(2, pass);
            ps.setString(3, nameF);
            ps.setString(4, nameL);
            ps.setString(5, "'C'");
            
            PreparedStatement psc = con.prepareStatement("insert into Customer values(?,?,?,?,?)");

            psc.setInt(1,1);
            psc.setInt(2,1);
            psc.setString(3, email);
            psc.setString(4, phone);
            psc.setString(5, "'A'");

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        }
}



