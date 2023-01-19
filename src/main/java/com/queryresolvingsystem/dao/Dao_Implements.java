package com.queryresolvingsystem.dao;



 import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.queryresolvingsystem.DB_util.DB_Connect;
import com.queryresolvingsystem.bean.Engineer;
import com.queryresolvingsystem.bean.RaisedComplain;
import com.queryresolvingsystem.exceptions.EmployeeException;
import com.queryresolvingsystem.exceptions.EngineerException;

public class Dao_Implements implements Dao_Interface {
    @Override
    public String login_HOD( int password,String username) {

        String message="Unable to login...";

        try (Connection conn= DB_Connect.getConnection()){

            PreparedStatement ps=conn.prepareStatement(" select * from HOD where username='deba' AND password=1234");

            ResultSet rs = ps.executeQuery();

            if(rs.next() && rs.getString("username").equals(username) && password==rs.getInt("password")){
                 message="Login Successful";
            }


        } catch (SQLException e) {
            e.printStackTrace();
            message=e.getMessage();
        }
        return message;
    }

    @Override
    public String login_Employee(String username, int password) throws EmployeeException {
        String message="Unable to login...";

        try (Connection conn= DB_Connect.getConnection()){

            PreparedStatement ps=conn.prepareStatement("select * from employee where username=? AND password=?");

            ps.setString(1,username);
            ps.setInt(2,password);

            ResultSet rs = ps.executeQuery();

            if(rs.next() && rs.getString("username").equals(username) && password==rs.getInt("password")){
                message="Login Successful";
            }
            else throw new EmployeeException("No employee exists with these details");

        } catch (SQLException e) {
            e.printStackTrace();
            message=e.getMessage();
        }
        return message;
    }

    @Override
    public String signup_Employee(String username, int password) throws EmployeeException {
        String message="Unable to register...";


        if(!username.contains("@gmail.com")){ message="Does not contains @gmail.com"; return message;}

        try (Connection conn= DB_Connect.getConnection()){

            PreparedStatement ps=conn.prepareStatement("insert into employee values(?,?)");

            ps.setString(1,username);
            ps.setInt(2,password);

            int rs = ps.executeUpdate();

            if(rs>0){
                message="Sign up Successful";
            }


        } catch (SQLException e) {
            e.printStackTrace();
            message=e.getMessage();
            throw new EmployeeException(e.getMessage());
        }
        return message;
    }

    @Override
    public String login_Engineer(String username, int password) throws EngineerException {
        String message="Unable to login...";


        try (Connection conn= DB_Connect.getConnection()){

            PreparedStatement ps=conn.prepareStatement("select * from engineer where email=? AND password=?");

            ps.setString(1,username);
            ps.setInt(2,password);


            ResultSet rs = ps.executeQuery();

            if(rs.next() && rs.getString("email").equals(username) && password==rs.getInt("password")){
                message="Login Successful";
            }
            else throw new EngineerException("No engineer exists with these details");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            message=e.getMessage();
        }
        return message;
    }

    @Override
    public String register_Engineer(Engineer engineer) throws EngineerException {

        String message="Unable to register fill the proper details";

        if(!engineer.getEmail().contains("@gmail.com")){
            throw new EngineerException("does not contains @gmail.com");
        }

        try(Connection conn=DB_Connect.getConnection()){

            PreparedStatement ps =conn.prepareStatement("insert into engineer values(?,?,?)");

            ps.setString(1, engineer.getEmail());
            ps.setInt(2,engineer.getPassword());
            ps.setString(3,engineer.getType());

            int result=ps.executeUpdate();

            if(result>0) message="Sign up successfully";

        }
        catch (SQLException e){
            throw new EngineerException(e.getMessage());
        }

        return message;
    }

    @Override
    public String Assign_Complain_To_Engineer(String username,int complainId) throws EngineerException {
        String message="Unable to assign try again...";

        if(!username.contains("@gmail.com")) {message="does not contain @gmail.com"; return message;}

        try(Connection conn=DB_Connect.getConnection()){

            PreparedStatement ps=conn.prepareStatement("update complain set solveby=? where complainID=?");

            ps.setString(1,username);
            ps.setInt(2,complainId);
            int result=ps.executeUpdate();

            if(result>0) message="Assigned successfully";

        }
        catch (SQLException e){
           message=e.getMessage();
        }
        return message;
    }

    @Override
    public List<RaisedComplain> list_Of_Complains_MapTo_Engineer(String email) {

        List<RaisedComplain> list=new ArrayList<>();

        /*Field           | Type        | Null | Key | Default | Extra |
+-----------------+-------------+------+-----+---------+-------+
| complainId      | int(11)     | NO   | PRI | NULL    |       |
| complainType    | varchar(30) | NO   |     | NULL    |       |
| complainDetails | varchar(80) | NO   |     | NULL    |       |
| raisedby        | varchar(20) | NO   |     | NULL    |       |
| solveby         | varchar(30) | YES  | MUL | NULL    |*/

        try(Connection conn=DB_Connect.getConnection()){
            PreparedStatement ps=conn.prepareStatement("select * from complain where status IS NULL and  solveby=?");
            ps.setString(1,email);

            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                RaisedComplain raisedComplain=new RaisedComplain();
                raisedComplain.setComplainId(rs.getInt("complainId"));
                raisedComplain.setComplainType(rs.getString("complainType"));
                raisedComplain.setComplainDetails(rs.getString("complainDetails"));
                raisedComplain.setRaisedBy(rs.getString("raisedby"));
                raisedComplain.setSolveBy(rs.getString("solveby"));
                list.add(raisedComplain);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<RaisedComplain> list_Of_Complains_Attend_By_Engineer(String email) {

        List<RaisedComplain> list=new ArrayList<>();


        try(Connection conn=DB_Connect.getConnection()){
            PreparedStatement ps=conn.prepareStatement(" select * from complain where status!='NULL' and  solveby=?");
            ps.setString(1,email);

            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                RaisedComplain raisedComplain=new RaisedComplain();
                raisedComplain.setComplainId(rs.getInt("complainId"));
                raisedComplain.setComplainType(rs.getString("complainType"));
                raisedComplain.setComplainDetails(rs.getString("complainDetails"));
                raisedComplain.setRaisedBy(rs.getString("raisedby"));
                raisedComplain.setSolveBy(rs.getString("solveby"));
                list.add(raisedComplain);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<RaisedComplain> open_Complains(String email) {
        List<RaisedComplain> list=new ArrayList<>();


        /*Field           | Type        | Null | Key | Default | Extra |
+-----------------+-------------+------+-----+---------+-------+
| complainId      | int(11)     | NO   | PRI | NULL    |       |
| complainType    | varchar(30) | NO   |     | NULL    |       |
| complainDetails | varchar(80) | NO   |     | NULL    |       |
| raisedby        | varchar(20) | NO   |     | NULL    |       |
| solveby         | varchar(30) | YES  | MUL | NULL    |*/
        try(Connection conn=DB_Connect.getConnection()){
            PreparedStatement ps=conn.prepareStatement("select * from complain where status!='NULL' AND solveby=?");

            ps.setString(1,email);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                RaisedComplain raisedComplain=new RaisedComplain();
                raisedComplain.setComplainId(rs.getInt("complainId"));
                raisedComplain.setComplainType(rs.getString("complainType"));
                raisedComplain.setComplainDetails(rs.getString("complainDetails"));
                raisedComplain.setRaisedBy(rs.getString("raisedby"));
                raisedComplain.setSolveBy(rs.getString("solveby"));
                  raisedComplain.setStatus(rs.getString("status"));
                list.add(raisedComplain);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return list;

    }

    @Override
    public List<RaisedComplain> all_Raised_Complains() throws EmployeeException {
       List<RaisedComplain> list = new ArrayList<>();

       /* Field           | Type        | Null | Key | Default | Extra |
        +-----------------+-------------+------+-----+---------+-------+
        | complainId      | int(11)     | NO   | PRI | NULL    |       |
        | complainType    | varchar(30) | NO   |     | NULL    |       |
        | complainDetails | varchar(80) | NO   |     | NULL    |       |
        | raisedby        | varchar(20) | NO   |     | NULL    |       |
        | solveby         | varchar(30) | YES  | MUL | NULL    |       |
        +-----------------+-------------+------+-----+---------+-------+*/

       try (Connection conn=DB_Connect.getConnection()){

           PreparedStatement ps =conn.prepareStatement("select * from complain");

           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               RaisedComplain raisedComplain=new RaisedComplain();

               raisedComplain.setComplainId(rs.getInt("complainId"));
               raisedComplain.setComplainType(rs.getString("complainType"));
               raisedComplain.setComplainDetails(rs.getString("complainDetails"));
               raisedComplain.setStatus(rs.getString("status"));
               raisedComplain.setRaisedBy(rs.getString("raisedby"));
               raisedComplain.setSolveBy(rs.getString("solveby"));
               list.add(raisedComplain);
           }

       }
       catch (SQLException e){
           System.out.println(e.getMessage());
       }
       return list;
    }

    @Override
    public List<Engineer> list_Of_Engineers() throws EngineerException {
        List<Engineer> list=new ArrayList<>();

        try(Connection conn=DB_Connect.getConnection()){
         PreparedStatement ps = conn.prepareStatement("select * from engineer");

         ResultSet rs=ps.executeQuery();

         /*+----------+-------------+------+-----+---------+-------+
            | Field    | Type        | Null | Key | Default | Extra |
            +----------+-------------+------+-----+---------+-------+
            | email    | varchar(30) | NO   | PRI | NULL    |       |
            | password | int(11)     | NO   |     | NULL    |       |
            | Type     | varchar(10) | NO   |     | NULL    |       |
            +----------+-------------+------+-----+---------+-------+*/

         while(rs.next()){
             Engineer engineer=new Engineer();
             engineer.setEmail(rs.getString("email"));
             engineer.setPassword(rs.getInt("password"));
             engineer.setType(rs.getString("Type"));
             list.add(engineer);
         }

        }
        catch (SQLException e){
            throw new EngineerException(e.getMessage());
        }

        return list;
    }

    @Override
    public String delete_Engineer(String username) throws EngineerException {
        String message="Username not found";

        try(Connection conn=DB_Connect.getConnection()) {


            PreparedStatement ps=conn.prepareStatement(" delete from engineer where email=?");
            ps.setString(1,username);
          int res1=  ps.executeUpdate();

          if(res1>0) message="Deleted successfully";

        }
        catch (SQLException e){
     throw new EngineerException(e.getMessage());
        }
        return message;
    }

    @Override
    public String update_Status(int complainId,String updateMessage) throws EmployeeException {
        String message="complainId is wrong..!";

        try(Connection conn=DB_Connect.getConnection()) {
            PreparedStatement ps= conn.prepareStatement("update  complain set status=? where complainid=?");
            ps.setString(1,updateMessage);
            ps.setInt(2,complainId);

            int res=ps.executeUpdate();
            if(res>0) message="Status update successfully";

        }
        catch (SQLException e){
           throw   new  EmployeeException(e.getMessage());
        }

        return message;
    }

    @Override
    public String Update_Password_Engineer(String username, int password,int new_Password) throws EngineerException {
        String message = "Wrong name..!";

        try (Connection conn =  DB_Connect.getConnection()) {

            PreparedStatement ps = conn.prepareStatement("update engineer set password=? where email=? and password=?");


            ps.setInt(1, new_Password);
            ps.setString(2, username);
            ps.setInt(3,password);


            int x = ps.executeUpdate();

            if (x > 0) message = "password Updated Successfully..!";

        } catch (SQLException e) {
            throw new EngineerException(e.getMessage());
        }

        return message;
    }

    @Override
    public String Update_Password_Employee(String username, int password,int new_Password) throws EmployeeException {
        String message = "Wrong name..!";

        try (Connection conn =  DB_Connect.getConnection()) {

            PreparedStatement ps = conn.prepareStatement("update employee set password=? where username=? and password=?");

            ps.setInt(1, new_Password);
            ps.setString(2, username);
            ps.setInt(3,password);


            int x = ps.executeUpdate();

            if (x > 0) message = "password Updated Successfully..!";

        } catch (SQLException e) {
            throw new EmployeeException(e.getMessage());
        }

        return message;
    }

    @Override
    public RaisedComplain check_Complain_Status(int complainId,String username) throws EmployeeException {
        RaisedComplain raisedComplain=new RaisedComplain();

        try (Connection conn =  DB_Connect.getConnection()) {

            PreparedStatement ps = conn.prepareStatement("select * from complain where complainid=? and raisedby=?");

            ps.setInt(1, complainId);
            ps.setString(2, username);



            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                raisedComplain.setComplainId(rs.getInt("complainId"));
                raisedComplain.setComplainType(rs.getString("complainType"));
                raisedComplain.setComplainDetails(rs.getString("complainDetails"));
                raisedComplain.setRaisedBy(rs.getString("raisedby"));
                raisedComplain.setSolveBy(rs.getString("solveby"));
            }

        } catch (SQLException e) {
            throw new EmployeeException(e.getMessage());
        }

        return raisedComplain;
    }

    @Override
    public String Raise_A_Complain(String complain_Type, String complainDetails,String username) {
        String message="Ohh ohh something went wrong..!";
        if(username.trim().equals("")) {
        	return message="does not contain username";
        }
        try(Connection conn=DB_Connect.getConnection()){
            System.out.println("Executing....");
            PreparedStatement ps=conn.prepareStatement("SELECT FLOOR(RAND() * 99999) AS random_num\n" +
                    "   FROM complain" +
                    " WHERE  'random_num' NOT IN (SELECT complainID FROM complain)\n" +
                    "         LIMIT 1");


            ResultSet rs=ps.executeQuery();
            int complainId=0;
            if(rs.next()){
              complainId=  rs.getInt("random_num");
                System.out.println("Your generated ticket id is "+complainId);
            }
            else return message;

            PreparedStatement ps1=conn.prepareStatement("insert into complain(complainid,complainType,complainDetails,raisedby) values(?,?,?,?)");
            ps1.setInt(1,complainId);
            ps1.setString(2,complain_Type);
            ps1.setString(3,complainDetails);
            ps1.setString(4,username);

            int res=ps1.executeUpdate();
            if(res>0) message="Success"+","+complainId;

        }
        catch (SQLException e){
      message=e.getMessage();
        }
        return message;
    }

    @Override
    public List<RaisedComplain> check_All_Raised_Complain(String username) {
        List<RaisedComplain> list=new ArrayList<>();


        try(Connection conn=DB_Connect.getConnection()){
            PreparedStatement ps=conn.prepareStatement("select * from complain where  raisedby=?");
            ps.setString(1,username);

            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                RaisedComplain raisedComplain=new RaisedComplain();
                raisedComplain.setComplainId(rs.getInt("complainId"));
                raisedComplain.setComplainType(rs.getString("complainType"));
                raisedComplain.setComplainDetails(rs.getString("complainDetails"));
                raisedComplain.setRaisedBy(rs.getString("raisedby"));
                raisedComplain.setSolveBy(rs.getString("solveby"));
                list.add(raisedComplain);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

	

	
}
