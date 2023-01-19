package com.queryresolvingsystem.web;

import java.awt.List;
import java.io.IOException;
import java.sql.SQLException;

import com.mysql.cj.Session;
import com.queryresolvingsystem.bean.*;
import java.util.*;
import java.lang.*;
import java.io.*;

import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.queryresolvingsystem.dao.Dao_Implements;
import com.queryresolvingsystem.dao.Dao_Interface;
import com.queryresolvingsystem.exceptions.EmployeeException;
import com.queryresolvingsystem.exceptions.EngineerException;
import com.queryresolvingsystem.exceptions.EmployeeException;
 
@WebServlet({"/registerComplain","/signupemp","/loginemp","/emplogout","/checkstatus","/allrcomplains","/updateEmppassword"})
public class EmployeeServlet extends HttpServlet {
	
	private Dao_Interface dao;
	
	public  void init() throws ServletException {
		dao= new Dao_Implements();
	}
	
       
     
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action=request.getServletPath();
		   
		  try {
			switch (action) {
			case "/emplogout":
            	   logout(request,response);
               break;
               case "/allrcomplains":
            	   complain_History(request, response);
            	   break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action=request.getServletPath();
		 response.setContentType("text/html");
		 
		  try {
			switch (action) {
			case "/signupemp":
				createNewAccount(request, response);
				break;
				case "/loginemp":
					login(request, response);
					break;
				case "/registerComplain":
					register_Complain(request, response);
					break;
				case "/checkstatus":
					check_Status(request, response);
					break;
					case"/updateEmppassword":
						changePassword(request, response);
						break;
		 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createNewAccount(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException {
		     String usernameString=request.getParameter("username");
		     int password=Integer.parseInt(request.getParameter("password").trim());
		    try {
				String messageString=dao.signup_Employee(usernameString, password);
				if(messageString.equals("Sign up Successful")) {
					response.sendRedirect("loginemployee.jsp");
				}
				else {
					response.sendRedirect("Error.jsp");
				}
				
			} catch (EmployeeException e) {
				response.sendRedirect("Error.jsp");
				e.printStackTrace();
			}
	}

	private void login(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException {
		  String userNameString=request.getParameter("username");
		  String passwordString=request.getParameter("password");
		  String messasgeString = "";
		 
				try {
					messasgeString = dao.login_Employee(userNameString.trim(), Integer.parseInt(passwordString.trim()));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EmployeeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
		if( messasgeString.equals("Login Successful")) {
			
		    System.out.print(messasgeString);
		       HttpSession session=request.getSession();
		       session.setAttribute("Empusername", userNameString);
		       session.setAttribute("Emppassword",passwordString);
			response.sendRedirect("employeenavbar.jsp");
		}
		else {
			response.sendRedirect("loginemployee.jsp");
		}
	}
 
	private void logout(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException{
		HttpSession session=request.getSession();
		session.removeAttribute("Eusername");
		session.invalidate();
		response.sendRedirect("engineerlogin.jsp");
	}

    private void register_Complain(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException{
         HttpSession session=request.getSession();
    	String usernameString=(String) session.getAttribute("Empusername");
    	
    	String problemsummaryString=request.getParameter("Etype");
    	String summaryString=request.getParameter("details");
    	String messageString=dao.Raise_A_Complain(problemsummaryString, summaryString, usernameString);
    	 
    	if(messageString.contains("Success")) {
    	String[] splitarrStrings=	messageString.split(",");
    		session.setAttribute("generatedCID",splitarrStrings[1]);
    		response.sendRedirect("generatedTicketId.jsp");
    	}
    	
    }


    private void check_Status(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException {
		 HttpSession session=request.getSession();
		 
    	int id=Integer.parseInt(request.getParameter("statusId").trim());
    	String usernameString=(String) session.getAttribute("Empusername");
    	
    	System.out.println(usernameString);
    	try {
			RaisedComplain complain=dao.check_Complain_Status(id, usernameString);
			System.out.println(complain);
			if(complain!=null) {
				System.out.println(id+"  "+usernameString);
			   request.setAttribute("getcomplain", complain);
			   RequestDispatcher dispatcher=request.getRequestDispatcher("searchresult.jsp");
			   dispatcher.forward(request, response);
			}
		} catch (EmployeeException | ServletException e) {
			e.printStackTrace();
		}
    	
    	
	}
    private void complain_History(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException {
    	 HttpSession session=request.getSession();
		 
     	String usernameString=(String) session.getAttribute("Empusername");
    	 System.out.println(usernameString);
    	java.util.List<RaisedComplain> complains=dao.check_All_Raised_Complain(usernameString);
    	request.setAttribute("allcomplains", complains);
    	RequestDispatcher dispatcher=request.getRequestDispatcher("allcomplains.jsp");
    	try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
   	}
    
    private void changePassword(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException  {
    	HttpSession session=request.getSession();
		int new_password=Integer.parseInt(request.getParameter("new_password")) ;
		String usernameString=(String) session.getAttribute("Empusername");
	        
		int Epassword=Integer.parseInt((String) session.getAttribute("Emppassword"));
    	try {
			String message=dao.Update_Password_Employee(usernameString, Epassword, new_password);
		   
			if(message.equals("password Updated Successfully..!")) {
				System.out.println("SUCCESS");
				response.sendRedirect("successP.jsp");
			}
			else {
				response.sendRedirect("Error.jsp");
			}
    	
    	} catch ( EmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

