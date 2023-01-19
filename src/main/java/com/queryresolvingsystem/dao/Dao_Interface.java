package com.queryresolvingsystem.dao;

import java.util.List;

import com.queryresolvingsystem.bean.*;
import com.queryresolvingsystem.exceptions.*;

public interface Dao_Interface {

      String login_HOD(int password,String username);

      String login_Employee(String username,int password) throws EmployeeException;

       String signup_Employee(String username,int password)  throws EmployeeException;

      String login_Engineer(String username,int password)  throws EngineerException;

       String register_Engineer(Engineer engineer) throws EngineerException;

       String Assign_Complain_To_Engineer(String username,int complainId) throws EngineerException;

       List<RaisedComplain>  list_Of_Complains_MapTo_Engineer(String email);

 List<RaisedComplain>  list_Of_Complains_Attend_By_Engineer(String email);

       List<RaisedComplain>  open_Complains(String email);

       List<RaisedComplain> all_Raised_Complains() throws EmployeeException;

       List<Engineer> list_Of_Engineers() throws EngineerException;

       String delete_Engineer(String username) throws EngineerException;

       String update_Status(int complainId,String updateMessage) throws EmployeeException;

       String Update_Password_Engineer(String username,int password,int new_Password) throws EngineerException;

       String Update_Password_Employee(String username,int password,int new_Password) throws EmployeeException;

       RaisedComplain check_Complain_Status(int complainId,String username) throws EmployeeException;

       String  Raise_A_Complain(String complain_Type,String complainDetails,String username);

       List<RaisedComplain> check_All_Raised_Complain(String username);




       


}
