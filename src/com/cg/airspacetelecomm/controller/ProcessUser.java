package com.cg.airspacetelecomm.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cg.airspacetelecomm.bean.UserBean;
import com.cg.airspacetelecomm.dao.CustomerDAOImpl;
import com.cg.airspacetelecomm.exception.AirSpaceException;
import com.cg.airspacetelecomm.service.CustomerService;
import com.cg.airspacetelecomm.service.CustomerServiceImpl;
import com.cg.airspacetelecomm.service.CSI;

@WebServlet("*.obj")
public class ProcessUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int SNo = 0;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Creating required objects and initializing(If required)

		//HttpSession session = request.getSession(true);
		HttpSession session = request.getSession(false);
		String path = request.getServletPath().trim();
			
			
		
		
		//Target Web Files are pre-declared to avoid confusion later in the code
		
		String target = "";
		String targetSuccess = "views/Success.jsp";
		String targetHome= "views/CustomerHome.jsp";
		String targetPay="views/PayBill.jsp";
		String targetError = "views/Error.jsp";
		String targetdata = "views/Data.jsp";
		String targetlogin = "views/Login.jsp";
		
		UserBean user = new UserBean();
		CustomerService customerService = new CustomerServiceImpl();
		CustomerDAOImpl dao= new CustomerDAOImpl();
		
				
	
		
switch(path){
/////////////////////////////////////////////////////////INDEX		
case "/index.obj": { target=targetlogin;
			break;}
/////////////////////////////////////////////////////////AJAX VALIDATION		
case "/valid.obj": 
		{				
				try 
				{int code[]={0,0};
				code= dao.check(request.getParameter("userN"));
					response.setContentType("text/plain");
					response.getWriter().write(Integer.toString(code[0]));
					
					SNo=code[1] ;
					return;
				} 
				catch (AirSpaceException e) 
				{
					e.printStackTrace();
				}

				break;
		}
			
		
//////////////////////////////////////////////////////////////////		
case "/Login.obj":
		if(request.getParameter("name")==null && !request.getParameter("uname").equals("admin"))	
		{/////////////////////////////////////////////////////////LOGIN
			HttpSession sessionnew = request.getSession(true);
			String uName = request.getParameter("uname");
			String pwd =  request.getParameter("pwd");
			System.out.println(uName);
			CSI datagetter = new CSI();
			int i=0;
			ArrayList<UserBean> udatalog;
			try {
				//udatalog = datagetter.fakeget();
				udatalog = datagetter.get();
				UserBean currentf=null;
				target = targetlogin;
				for (UserBean current : udatalog)
				{
				///////////////////////////////////////IF USERNAME AND PASSWORD MATCH	
						if (uName.equals(current.getUserName())&& pwd.equals(current.getPwd()) )
						{ currentf=current; 
						System.out.println(currentf.getUserName());
						sessionnew.setAttribute("user", currentf);
						sessionnew.setAttribute("info", "R");
						target = targetHome;i=1;
						break;
						}
                ///////////////////////IF USERNAME MATCHES AND PASSWORD DOES NOT MATCH	
						if (uName.equals(current.getUserName())&& !pwd.equals(current.getPwd()) )
						{ 
						sessionnew.setAttribute("info", "WRONG PASS");
						sessionnew.setAttribute("unret", uName);
						i=2;
						break;
						}
						}
				////////////////////// IF NOTHING MATCHES
						if (i==0){
						sessionnew.setAttribute("info", "NR");
						break;}
				
			} catch (AirSpaceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
									
			break;}
//////////////////////////////////////////////////////FEATURE FOR ADMINISTRATOR	
			else if (request.getParameter("name")==null && request.getParameter("uname").equals("admin"))
			{	
					if(request.getParameter("pwd").equals("admin"))
					{
						CSI dataget = new CSI();
						try{
						//ArrayList<UserBean> udata=dataget.fakeget();
						ArrayList<UserBean> udata=dataget.get();
						session.setAttribute("data", udata);
						session.setAttribute("length",udata.size() );
						target= targetdata;
						}
						catch (AirSpaceException e){}
						break;
					
					}
					else 
					{ 
						session.setAttribute("info", "WRONG PASS");
						session.setAttribute("unret", "admin");
						target=targetlogin;
						break;
					}
			
			}
		  
			else /////////////////////////////////////////REGISTRATION
			{  // SNo = Integer.parseInt(request.getParameter("SNo"));
				String name  = request.getParameter("name");
				String uName = request.getParameter("uname");
				String mobileNo = request.getParameter("mobileno");
				String pwd =  request.getParameter("pwd");
				if(request.getParameter("bill")!=null)
				{String bill= request.getParameter("bill");
				user.setBill(bill);}
				//User details are added to the bean object and then added to Database by calling Service Layer.
				user.setSNo(SNo);
				user.setName(name);
				user.setUserName(uName);
				user.setPwd(pwd);
				user.setMobileNo(mobileNo);
				
			
			try 
			{
				customerService.addUser(user);
				session.setAttribute("user", user);
			} 
			catch (AirSpaceException e) 
			{
				session.setAttribute("error", e.getMessage());
				RequestDispatcher dispatcher = request.getRequestDispatcher(targetError);
				dispatcher.forward(request, response);
				System.err.println(e.getMessage());
			}
			target = targetHome;
			break;}
	
			
		//Forwards to payment page
		case "/Payment.obj":
			{
				target = targetPay;
				break;
			}
		
		//Success page is invoked
		case "/Result.obj":
			{
				Integer price = Integer.parseInt(request.getParameter("result"));
				UserBean use = (UserBean) session.getAttribute("user");
				Integer Balance = Integer.parseInt(use.getBill())-price;
				try {
					customerService.Adjustbill(use,Balance);
					} 
				catch (AirSpaceException e) 
				{
					e.printStackTrace();
				}
				session.setAttribute("balance", Balance);
				target = targetSuccess;
				break;
			}
			
		case "/Back.obj":
			{session.setAttribute("error", null);
			target = targetlogin;
			break;}
		
		case "/Editdata.obj": {
			///////////
			SNo = Integer.parseInt(request.getParameter("SNo").trim());
			String name  = request.getParameter("Name");
			String uName = request.getParameter("User Name");
			String mobileNo = request.getParameter("Mobile No");
			String pwd =  request.getParameter("Password");
			String bill =  request.getParameter("Amount Due");
			
			//User details are added to the bean object and then added to Database by calling Service Layer.
			user.setSNo(SNo);
			user.setName(name);
			user.setUserName(uName);
			user.setPwd(pwd);
			user.setMobileNo(mobileNo);
			user.setBill(bill);
		
		try 
		{
			dao.editUser(user);
			///
			CSI dataget = new CSI();
			try{
			//ArrayList<UserBean> udata=dataget.fakeget();
			ArrayList<UserBean> udata=dataget.get();
			session.setAttribute("data", udata);
			session.setAttribute("length",udata.size() );
			target= targetdata;
			}
			catch (AirSpaceException e){}
			break;
			//
			
		} 
		catch (AirSpaceException e) 
		{
			session.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher(targetError);
			dispatcher.forward(request, response);
			System.err.println(e.getMessage());
		}
		
			//////////
			
				
		} 
			
		}

		
		//InterServlet Communication is done by using RequesDispatcher and then forwarding the response.
		RequestDispatcher dispatcher = request.getRequestDispatcher(target);
		dispatcher.forward(request, response);
	
}}
