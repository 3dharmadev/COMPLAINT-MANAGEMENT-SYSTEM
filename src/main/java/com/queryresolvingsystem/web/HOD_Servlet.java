package com.queryresolvingsystem.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.lang.*;
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;
import com.queryresolvingsystem.bean.Engineer;
import com.queryresolvingsystem.bean.RaisedComplain;
import com.queryresolvingsystem.dao.Dao_Implements;
import com.queryresolvingsystem.dao.Dao_Interface;
import com.queryresolvingsystem.exceptions.EmployeeException;
import com.queryresolvingsystem.exceptions.EngineerException;




@WebServlet("/")
public class HOD_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private Dao_Interface dao;
    
	public void init() throws ServletException {
		  dao= new Dao_Implements();
	}
    
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		 String action=request.getServletPath();
	
		 try {
				switch (action) {
			
					case "/RegisterEngineer":
						insert_Data(request, response);
						break;
						case "/assignTask":
							assign_Query(request, response);
					 break;
					 case "/login":
						 login(request, response);
				      break;
				}
			} catch (SQLException ex) {
				throw new ServletException(ex);
			}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action=request.getServletPath();
          
		 try {
				switch (action) {
				case "/complainlist":
					get_Query_Data(request, response);
					break;
				case "/engineerdata":
					get_Data(request, response);
					break;
				case "/delete":
					delete_Data(request, response);	
					break;
					 
				case "/logout":
					logout(request, response);
					break;
				}
			} catch (SQLException ex) {
				throw new ServletException(ex);
			}
	}
	
	
       
             
          private void insert_Data(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException  {
        	  response.setContentType("text/html; charset=UTF-8");
              String user_name=request.getParameter("email");	 
              String password=request.getParameter("password");	 
              int pass_trim=Integer.parseInt(password.trim());
              String Etype=request.getParameter("Etype");	 
              
           
               Engineer engineer=new Engineer();
               engineer.setEmail(user_name);
               engineer.setPassword(pass_trim);
               engineer.setType(Etype);
               
              try {
            	  String messageString=dao.register_Engineer(engineer);
				if(messageString.equals("Sign up successfully")) {
					response.sendRedirect("engineerdata");
					 
					System.out.println("SUCESS");
				}
			} catch (EngineerException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
              
        	  
             }

          
          private void delete_Data(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException  {

  	        
              String user_name=request.getParameter("email");	 

 
              try {
            	  System.out.println(user_name);
            	  String messageString=dao.delete_Engineer(user_name);
			   	if(messageString.equals("Deleted successfully")) {
			   	 response.sendRedirect("engineerdata");
			   	}
			} catch (EngineerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
        	  
             }
          
          
          private void get_Data(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException  {

        	 
              try {
            	  List<Engineer> list=	dao.list_Of_Engineers();
			 request.setAttribute("engineer_list", list);
			 RequestDispatcher dispatcher=request.getRequestDispatcher("engineerlist.jsp");
			 dispatcher.forward(request, response);
			} catch (EngineerException e) {
				e.printStackTrace();
			}
              
              
             
        	  
             }
          
          
          private void get_Query_Data(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException {
  
        	  
        	  try {
        	  List<RaisedComplain>  list=	dao.all_Raised_Complains();

				  System.out.println(list);
			 
			 request.setAttribute("query_list", list);
			 RequestDispatcher dispatcher=request.getRequestDispatcher("querylist.jsp");
			 dispatcher.forward(request, response);
        	  }
        	  catch (Exception e) {
				// TODO: handle exception
        		  System.out.println(e.getMessage());
			}
        	   
             }


	private void assign_Query(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException  {


              String user_name=request.getParameter("username");	
              String complainIdString= request.getParameter("complainId");
              //System.out.println(user_name+" "+complainIdString);
              int Id=Integer.parseInt(complainIdString.trim());
              try {
                  if(dao.Assign_Complain_To_Engineer(user_name,Id).equals("Assigned successfully")) {

                    //  System.out.println(username+" "+complainId);
                      HttpSession session= request.getSession();

                      session.setAttribute("username", user_name);

                      response.sendRedirect("assignedSuccessful.jsp");
                     // System.out.println("success");
                  }
                  else {
                      response.sendRedirect("ErrorAssignTask.jsp");
                     // System.out.println("failed");
                  }
              } catch (EngineerException e) {
                  response.sendRedirect("ErrorAssignTask.jsp");
                 // System.out.println(e.getMessage());
              }
              
             }
      
	private void login(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException {
		  String userNameString=request.getParameter("username");
		  String passwordString=request.getParameter("password");
		  String messasgeString=dao.login_HOD(Integer.parseInt(passwordString.trim()), userNameString.trim());
		if( messasgeString.equals("Login Successful")) {
			
		    System.out.print(messasgeString);
		       HttpSession session=request.getSession();
		       session.setAttribute("username", userNameString);
		       
			response.sendRedirect("afterlogin.jsp");
		}
		else {
			response.sendRedirect("HOD_login.jsp");
		}
	}
	private void logout(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException{

		HttpSession session=request.getSession();
		session.removeAttribute("username");
		session.invalidate();
		response.sendRedirect("HOD_login.jsp");
		
	}
   
}
	
       