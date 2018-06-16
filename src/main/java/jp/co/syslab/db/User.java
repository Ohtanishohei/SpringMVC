package jp.co.syslab.db;

import java.sql.*;

import org.springframework.jdbc.core.JdbcTemplate;
 
public class User {
//	public static void main(String[] args) {
//		User.create("123", "123", "123", 0, "321321321321");
//	}
	
    public void create(JdbcTemplate jdbcTemplate,String username, String psw, String email, int flag, String token) {
        //Connection connection = null;
        //PreparedStatement statement = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");
            
            String sql1 = "insert into user(username,psw,email,verify_flag,verify_token) values (?,?,?,?,?)";
            //statement = connection.prepareStatement(sql1);
            jdbcTemplate.update(sql1, username,psw,email,flag,token);
            //statement.setString(1, username);
            //statement.setString(2, psw);
            //statement.setString(3, email);
            //statement.setInt(4, flag);
            //statement.setString(5, token);
            //statement.execute();

        //} catch (ClassNotFoundException e) {
        //    e.printStackTrace();
        //}catch (SQLException e) {
        //    e.printStackTrace();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        finally{
           // if(statement!=null){
           //     try {
           //         statement.close();
           //     } catch (SQLException e) {
                    // TODO Auto-generated catch block
            //        e.printStackTrace();
            //    }
            //}

            //if(connection!=null){
             //   try {
              //      connection.close();
              //  } catch (SQLException e) {
                    // TODO Auto-generated catch block
               //     e.printStackTrace();
               // }
            //}
        }
    }
    public void check1(JdbcTemplate jdbcTemplate,String token) {
    	try {
    		String sql2 = "UPDATE user SET verify_flag = 1 WHERE verify_token = ? ";
    		jdbcTemplate.update(sql2,token);
    	}catch (Exception ex) {
        	ex.printStackTrace();
        }
    }
    
    public void reset(JdbcTemplate jdbcTemplate,String token,String psw) {
    	try {
    		String sql3 = "UPDATE user SET psw = psw WHERE verify_token = ? ";
    		jdbcTemplate.update(sql3,token);
    	}catch (Exception ex) {
        	ex.printStackTrace();
        }
    } 
}