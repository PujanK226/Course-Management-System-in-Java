package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher {
    MysqlCon conn = new MysqlCon();
    ResultSet res;
    Scanner scan = new Scanner(System.in);

    public Teacher() {
        System.out.println("Welcome to teacher panel");
        System.out.println("-------------------------");
        System.out.println("-------------------------");
        System.out.println("Please provide your email for further:");
        TeacherMain();

    }

    private void TeacherMain() {
        String email = scan.nextLine();
        String choose = "Select * from teacherval where email = '" + email + "' ;";
        try {
            ArrayList detect = new ArrayList();
            ArrayList course = new ArrayList();
            ArrayList level = new ArrayList();
            ArrayList sem = new ArrayList();

            res = conn.stat.executeQuery(choose);
            String name = "";
            while (res.next()) {
                name = res.getString(2);
                detect.add(res.getString(3));
                course.add(res.getString(4));
                level.add(res.getString(5));
                sem.add(res.getString(6));
            }
            if (!name.isEmpty()) {
                System.out.println("Email : " + email);
                System.out.println("Full Name " + name);
                for (int i = 0; i < detect.size(); i++) {
                    System.out.println("Teacher: " + i + 1);
                    System.out.println("Course: " +course.get(i));
                    System.out.println("Level: "+level.get(i));
                    System.out.println("Semester: "+sem.get(i));
                    System.out.println(detect.get(i));

                }
                System.out.println("choice show be less then " + detect.size() );
                int choice = scan.nextInt();
                String ce="";
                String le="";
                String sems="";
                String sub="";
                if (choice <= detect.size()) {
                    int colm = choice;
                    colm = colm - 1;
                    ce = (String) course.get(colm);
                    le = (String) level.get(colm);
                     sems = (String) sem.get(colm);
                    sub = (String) detect.get(colm);
                }
                    ArrayList student = new ArrayList();
//                    System.out.println("select student number");
                    if (le.equals("6")) {
                        res = conn.stat.executeQuery("select email from oldstudent where course ='" + ce + "' and level = " + le+ " and semester ='" + sems + "' and OptionalSubject = '" + sub + "' ; ");

                        int i = 1;
                        while (res.next()) {
                            String pp = res.getString(1);
                            student.add(pp);
                            System.out.println(i + " " + pp);
                        }
                        System.out.println("select student number to enter marks");
                        int num = scan.nextInt();
                        if (num <= student.size()) {
                            String em = (String) student.get(num - 1);
                            System.out.println("enter marks:");
                            int mark = scan.nextInt();
                            conn.stat.executeUpdate("Insert into resultval  values ('" + em + "','" + sub + "','" + ce + "'," + le+ ",'" + sems + "','" + mark + "');");

                    }

                    } else {
                        res = conn.stat.executeQuery("select email from oldstudent where course ='" + ce + "' and level = '" + le + "' and semester ='" + sems + "' ; ");

                        int i=1;
                        while (res.next()) {
                            String pp = res.getString(1);
                            student.add(pp);
                            System.out.println(i +" " + pp);
                        }
                        System.out.println("select which student marks to be added.. Provide Number");
                        int num = scan.nextInt();
                        if (num <= student.size()){
                            String em = (String) student.get(num-1);
                            System.out.println("enter marks:");
                            int mark = scan.nextInt();
                            conn.stat.executeUpdate("Insert into resultval  values ('"+em+"','"+sub+"','"+ce+"','"+le+"','"+sems+"','"+mark+"');");
                            System.out.println("done");
                        }


                    }



            }else {
                System.out.println("Email is incorrect.. provide correct one!!");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

