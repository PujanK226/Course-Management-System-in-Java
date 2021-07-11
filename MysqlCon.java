package com.company;
import java.sql.*;
public class MysqlCon {
    Connection con;
    Statement stat;
    public MysqlCon(){
        try{

            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/JavaCoursework","root","");
            stat=con.createStatement();

        }catch(Exception e){ System.out.println(e);}
    }
}
