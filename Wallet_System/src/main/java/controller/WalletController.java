package controller;

import java.io.IOException;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import WalletServiceImpl.WaleetServiceImpl;
import model.User;
import walletService.WalletService;


@WebServlet("/WalletController")
public class WalletController extends HttpServlet {
	
       
	@Resource(name = "jdbc/item" )
	private DataSource datasourse;
	
	WalletService walletService ;

	public void init() throws ServletException {
		super.init();
		walletService = new WaleetServiceImpl(datasourse);
		
	}
  
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(Objects.isNull(action))
		{
			action = "login";
		}
		
		 switch(action) {
		 case "homePage" : homePage(request , response);
		 break;
		 
		 case "signUp" : signUp(request , response);
		 break;
		 
		 case "login" : login(request , response);
		 break;
		 
		 case "deposit" : deposit(request , response);
		 break;
		 
		 case "balance" : balance(request , response);
		 break;
		 
		 case "transfer" : transfer(request , response);
		 break;
		 
		 case "withdraw" : withdraw(request , response);
		 break;
		 
		 case "logout":
			    logout(request,response);
			    break;
		 
		 
		 
		 }
	}

				
				private void balance(HttpServletRequest request,
			            HttpServletResponse response)
			throws ServletException, IOException {
			
			HttpSession session = request.getSession(false);
			
			if(session == null){
			
			response.sendRedirect(request.getContextPath()
			       + "/pages/login.jsp");
			
			return;
			
			}
			
			User user = (User) session.getAttribute("user");
			
			try {
			
			double balance =
			       walletService.getBalance(user.getId().intValue());
			
			request.setAttribute("balance", balance);
			
			request.getRequestDispatcher("/pages/balance.jsp")
			       .forward(request, response);
			
			} catch (Exception e) {
			
			e.printStackTrace();
			
			}
			
			}

				private void transfer(HttpServletRequest request,
	                      HttpServletResponse response)
	        throws IOException {

	    HttpSession session = request.getSession(false);

	    if(session == null){

	        response.sendRedirect(request.getContextPath()
	                + "/pages/login.jsp");

	        return;

	    }

	    User sender = (User) session.getAttribute("user");

	    String phone =
	            request.getParameter("phone");

	    double amount =
	            Double.parseDouble(request.getParameter("amount"));

	    String description =
	            request.getParameter("description");

	    try {

	        User receiver = walletService.findByPhone(phone);

	        if(receiver == null){

	            response.sendRedirect(request.getContextPath()
	                    + "/pages/transfer.jsp?user=notfound");

	            return;

	        }

	        boolean result = walletService.transfer(

	                sender.getId().intValue(),
	                receiver.getId().intValue(),
	                amount,
	                description);

	        if(result){

	            response.sendRedirect(request.getContextPath()
	                    + "/pages/home.jsp?transfer=success");

	        }else{

	            response.sendRedirect(request.getContextPath()
	                    + "/pages/transfer.jsp?error=1");

	        }

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	}


	private void withdraw(HttpServletRequest request,
            HttpServletResponse response)
throws IOException {

HttpSession session = request.getSession(false);

if(session == null){

response.sendRedirect(request.getContextPath()
      + "/pages/login.jsp");

return;

}

			User user = (User) session.getAttribute("user");
			
			double amount =
			  Double.parseDouble(request.getParameter("amount"));
			
			String description =
			  request.getParameter("description");
			
			try {
			
			boolean result = walletService.withdraw(
			      user.getId().intValue(),
			      amount,
			      description);
			
			if(result){
			
			  response.sendRedirect(request.getContextPath()
			          + "/pages/home.jsp?withdraw=success");
			
			}else{
			
			  response.sendRedirect(request.getContextPath()
			          + "/pages/withdraw.jsp?error=1");
			
			}
			
			} catch (Exception e) {
			
			e.printStackTrace();
			
			}
			
			}


			private void deposit(HttpServletRequest request,
		            HttpServletResponse response)
		throws IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session == null){
		
		response.sendRedirect(request.getContextPath()
		       + "/pages/login.jsp");
		
		return;
		}
		
		User user = (User) session.getAttribute("user");
		
		double amount =
		   Double.parseDouble(request.getParameter("amount"));
		
		String description =
		   request.getParameter("description");
		
		try {
		
		boolean result = walletService.deposit(
		       user.getId().intValue(),
		       amount,
		       description);
		
		if(result){
		
		   response.sendRedirect(request.getContextPath()
		           + "/pages/home.jsp?deposit=success");
		
		}else{
		
		   response.sendRedirect(request.getContextPath()
		           + "/pages/deposit.jsp?error=1");
		
		}
		
		} catch (Exception e) {
		
		e.printStackTrace();
		
		}
		
		}

	private void login(HttpServletRequest request, HttpServletResponse response)
	        throws IOException {

	    try {

	        String firstName = request.getParameter("first_name");
	        String password = request.getParameter("password");

	        User user = walletService.login(firstName, password);

	        if (user != null) {

	            HttpSession session = request.getSession();
	            session.setAttribute("user", user);

	            Cookie cookie = new Cookie("userName", user.getFirst_name());
	            cookie.setMaxAge(60 * 60 * 24);
	            response.addCookie(cookie);

	            response.sendRedirect(request.getContextPath() + "/pages/home.jsp");

	        } else {

	            response.sendRedirect(request.getContextPath()
	                    + "/pages/login.jsp?error=1");

	        }

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	}


	private void homePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.sendRedirect(request.getContextPath() + "/homePage.html");
		
	}


				private void signUp(HttpServletRequest request,
			            HttpServletResponse response)
			throws IOException {
			
			User user = new User();
			
			user.setFirst_name(request.getParameter("first_name"));
			user.setLast_name(request.getParameter("last_name"));
			user.setPhone(request.getParameter("phone"));
			user.setPassword(request.getParameter("password"));
			
			try {
			
			boolean result = walletService.signUp(user);
			
			if(result) {
			
			    response.sendRedirect(request.getContextPath()
			            + "/pages/login.jsp");
			
			} else {
			
			    response.sendRedirect(request.getContextPath()
			            + "/pages/signup.jsp?error=1");
			
			}
			
			} catch (Exception e) {
			
			e.printStackTrace();
			
			}
			
			}
				
				
				
				private void logout(HttpServletRequest request,
	                    HttpServletResponse response)
	        throws IOException {

	    HttpSession session =
	            request.getSession(false);

	    if(session != null){

	        session.invalidate();

	    }

	    Cookie cookie =
	            new Cookie("userName", "");

	    cookie.setMaxAge(0);

	    response.addCookie(cookie);

	    response.sendRedirect(request.getContextPath()
	            + "/pages/login.jsp");

	}
				


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
