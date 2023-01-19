package com.queryresolvingsystem.web;

import java.awt.List;
import java.io.IOException;
import java.sql.SQLException;

import com.mysql.cj.Session;
import com.queryresolvingsystem.bean.*;
import java.util.*;
import java.lang.*;
import java.io.*;

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
 
@WebServlet({"/engineerlogin","/engineerlogout","/assignQueries","/updateStatus","/attemptedqueries","/updateEngpassword"})
public class EngineerServlet extends HttpServlet {
	
	private Dao_Interface dao;
	
	public  void init() throws ServletException {
		dao= new Dao_Implements();
	}
	
       
     
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action=request.getServletPath();
		 response.setContentType("text/html");
		 
		  try {
			switch (action) {
           
			case "/engineerlogout":
				logout(request, response);
				break;
			case "/assignQueries":
				getAssignQueries(request, response);
				break;
				
			case "/attemptedqueries":
				attemptQueries(request, response);
				break;
				
			default:
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
			case "/engineerlogin":
				login(request, response);
				break;
			case "/updateStatus":
				updateStatus(request, response);
				break;
				case "/updateEngpassword":
					changePassword(request, response);
					break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void getAssignQueries(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException {
		    
		    HttpSession session=request.getSession();
		   String usernameString= (String) session.getAttribute("Eusername");
		   System.out.println(usernameString);
			java.util.List<RaisedComplain> assignTasksComplains=dao.list_Of_Complains_MapTo_Engineer(usernameString);
			System.out.println(assignTasksComplains);
		     request.setAttribute("new_assign_task", assignTasksComplains);
		     RequestDispatcher dispatcher=request.getRequestDispatcher("newtasktoengineer.jsp");
		     dispatcher.forward(request, response);
	}

	private void login(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException {
		  String userNameString=request.getParameter("username");
		  String passwordString=request.getParameter("password");
		  String messasgeString = "";
		try {
			messasgeString = dao.login_Engineer(userNameString.trim(), Integer.parseInt(passwordString.trim()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (EngineerException e) {
			e.printStackTrace();
		}
		if( messasgeString.equals("Login Successful")) {
			
		    System.out.print(messasgeString);
		       HttpSession session=request.getSession();
		       session.setAttribute("Eusername", userNameString);
		       session.setAttribute("Epassword",passwordString);
			response.sendRedirect("engineernavbar.jsp");
		}
		else {
			response.sendRedirect("engineerlogin.jsp");
		}
	}
   
	private void logout(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException{
		HttpSession session=request.getSession();
		session.removeAttribute("Eusername");
		session.invalidate();
		response.sendRedirect("engineerlogin.jsp");
	}

    private void updateStatus(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException{
    	
    	String idString=request.getParameter("complainId");
    	String statuString=request.getParameter("status");
    	 int id=Integer.parseInt(idString.trim());
    	 try {
			String message= dao.update_Status(id, statuString.trim());
			if(message.equals("Status update successfully")) {
				//System.out.print("SUCESS");
				response.sendRedirect("success.jsp");
			}
			else response.sendRedirect("Error.jsp");
		} catch (EmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }


    private void attemptQueries(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException {
		HttpSession session=request.getSession();
		String usernameString=(String) session.getAttribute("Eusername");
    	java.util.List<RaisedComplain> complains=dao.open_Complains(usernameString);
    	
    	if(complains.size()!=0) {
    		request.setAttribute("attempt_queries", complains);
    		System.out.print(complains);
    		RequestDispatcher dispatcher=request.getRequestDispatcher("attemptedqueries.jsp");
    		try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
    	
	}
    
    private void changePassword(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException  {
		HttpSession session=request.getSession();
		int new_password=Integer.parseInt(request.getParameter("new_password")) ;
		String usernameString=(String) session.getAttribute("Eusername");
		
		int Epassword=Integer.parseInt((String) session.getAttribute("Epassword"));
    	try {
			String message=dao.Update_Password_Engineer(usernameString, Epassword, new_password);
		   
			if(message.equals("password Updated Successfully..!")) {
				response.sendRedirect("successP.jsp");
			}
			else {
				response.sendRedirect("Error.jsp");
			}
    	
    	} catch (EngineerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
