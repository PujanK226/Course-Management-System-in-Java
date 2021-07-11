package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    MysqlCon conn = new MysqlCon();
    Scanner scan = new Scanner(System.in);
    ResultSet result;
    String fullName;
    String phoneNumber;
    String Address;
    String Course;
    int Level;
    String optionalSub;
    String Semester;

    public  Student(){
        System.out.println("Welcome to student panel");
        System.out.println("-------------------------");
        System.out.println("-------------------------");
        System.out.println("Are you a new student?");
        String choice = scan.nextLine();
        if (choice.toLowerCase().equals("yes")){
            newStudent();
        }else {
            enrolledStudent();
        }


    }
    private void newStudent(){
        System.out.println("Please provide your email id for further: \n");
        String email =scan.next();
        String selqct = "Select * from newstudents where email = '"+email+"' ;" ;
        System.out.println(selqct);
        String name = "";
        String phNum = "";
        String add = "";
        try {
            ResultSet result =  conn.stat.executeQuery(selqct);
            while (result.next()){
                System.out.println("Hello");
                name = result.getString(2);
                phNum = result.getString(3);
                add = result.getString(4);
                System.out.println(name);
            }
            if (!name.isEmpty() && !phNum.isEmpty() && !add.isEmpty()){
                fullName = name;
                phoneNumber = phNum;
                Address = add;
                System.out.println("Full Name: "+fullName);
                System.out.println("Phone Number: "+phoneNumber);
                System.out.println("Address: " + Address);
                System.out.println("Select course: ");
                String q1 = "Select * from course;";
                result = conn.stat.executeQuery(q1);


                ArrayList cor = new ArrayList();
                while (result.next()){
                    cor.add(result.getString(1));
                }
                for (int i=0;i< cor.size();i++){
                    System.out.println(cor.get(i).toString().toUpperCase());
                }
                Course = scan.nextLine();
                while (!cor.contains(Course.toUpperCase())){
                    System.out.println("Course dosent exists");
                    System.out.println("Select course :");
                    Course = scan.next();
                }





                System.out.println(" Please Select Level: ");
                Level = scan.nextInt();
                System.out.println("Please Select Semester: ");
                Semester = scan.next();

                if (Level==6){
                    String qur1 = "Select name from subject where course ='"+Course+"' and semester = '"+Semester+"'  ;" ;
                    System.out.println(qur1);
                    result = conn.stat.executeQuery(qur1);
                    ArrayList opt = new ArrayList();
                    while (result.next()){
                        String option =   result.getString(1);
                        if (option.startsWith("opt")){
                            opt.add(option);
                        }

                    }
                    for (int i=0;i< opt.size();i++){
                        System.out.println(opt.get(i).toString());
                    }
                    System.out.println("Select optional subject: \n");
                    optionalSub = scan.next();

                    while (!opt.contains(optionalSub)){
                        System.out.println("Unavailablity of Optional Subject");
                        System.out.println("please Select optional module \n");
                        optionalSub = scan.nextLine();
                    }





                }else {
                    optionalSub = "null";
                }
                String insert = "Insert into oldstudent values('"+email+"','"+fullName+"','"+phoneNumber+"','"+Address+"','"+Course+"','"+String.valueOf(Level)+"','"+Semester+"','"+optionalSub+"');";
                conn.stat.executeUpdate(insert);
                String delete = "Delete from  newStudents where email = '"+email+"';";
                conn.stat.executeUpdate(delete);
                System.out.flush();
                enrolledStudent();
            }else {
                System.out.println("Mismatch of email address");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    private  void  enrolledStudent(){
        System.out.println("Plase provide your enrolled email id: \n");
        String email =scan.nextLine();
        String sqlqct = "Select * from oldstudent where email = '"+email+"' ;" ;

        try {
            result =  conn.stat.executeQuery(sqlqct);
            String fname = "";
            String phNum = "";
            String add = "";
            while (result.next()){
                fname = result.getString(2);
                phNum = result.getString(3);
                add = result.getString(4);
                Course = result.getString(5);
                Level = result.getInt(6);
                Semester = result.getString(7);
                optionalSub = result.getString(8);

            }
            if (!fname.isEmpty() && !phNum.isEmpty() && !add.isEmpty()){
                System.out.println("Email "+email);
                System.out.println("full name "+ fname);
                System.out.println("Phone Number "+ phNum);
                System.out.println("Address "+ add);
                System.out.println("Course " + Course);
                System.out.println("Level "+ Level);
                System.out.println("semester " + Semester);
                if (Level == 6){
                    System.out.println("Optional subject "+optionalSub);
                }

                ArrayList subj  = new ArrayList();
                result  = conn.stat.executeQuery("select Name from subject where course = '"+Course+"' and level = '"+Level+"' and semester = '"+Semester+"'  ;");
                while (result.next()){
                    subj.add(result.getString(1));                }

                System.out.println("List of modules are:\n ");
                for (int i=0;i<subj.size();i++){
                    System.out.println(subj.get(i));
                }

            }else {

                System.out.println("Incorrect Email!!!!!! ");



            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

}
