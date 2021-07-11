package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseAdmin {
    MysqlCon conn = new MysqlCon();
    Scanner scan = new Scanner(System.in);
    ResultSet result;
    String Course;

    public CourseAdmin() {
        System.out.println("Please choose any one from the below!!!!");
        System.out.println("1 Course");
        System.out.println("2 Teacher");
        System.out.println("3 Result");
        System.out.println("4 Subject");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                courseMain();
                break;
            case 2:
                teacherMain();
                break;
            case 3:
                resultMain();
                break;
            case 4:
                subjectMain();
                break;
        }

    }

    private void courseMain() {
        System.out.println("Select your option ");
        System.out.println("1. Add ");
        System.out.println("2. Update ");
        System.out.println("3. Delete ");
        System.out.println("4 Cancel");

        int choi = scan.nextInt();
        System.out.println("Course Name:");
        String que = "Select * from course";
        try {
            result = conn.stat.executeQuery(que);
            ArrayList corse = new ArrayList();
            while (result.next()) {
                corse.add(result.getString(1).toUpperCase());
            }
            for (int i = 0; i < corse.size(); i++) {
                System.out.println(corse.get(i).toString().toUpperCase());
            }

            switch (choi) {
                case 1:
                    System.out.println("Please make sure to avoid above name ");
                    System.out.println("Enter the course name to add");
                    String corsename = scan.next();
                    try {
                        conn.stat.executeUpdate("Insert into course values ('" + corsename + "')");
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }

                    break;

                case 2:
                    System.out.println("Select course :");
                    Course = scan.nextLine();
                    while (!corse.contains(Course.toUpperCase())) {
//                        System.out.println("NO course!!");
                        System.out.println("Select course :");
                        Course = scan.nextLine();
                    }
                    System.out.println("enter new name to update ");
                    String Corse2 = scan.nextLine();
                    conn.stat.executeUpdate("update course set CourseName ='" + Corse2.toUpperCase() + " where CourseName = '" + Course + ";");
                    break;

                case 3:
                    System.out.println("Select course to delete:");
                    Course = scan.next();
                    while (!corse.contains(Course.toUpperCase())) {
                        System.out.println("No course available");
                        System.out.println("Select course :");
                        Course = scan.nextLine();
                    }
                    conn.stat.executeUpdate("Delete from course where CourseName ='" + Course + "' ;");
                    System.out.println("Successfully removed");
                    break;

                case 4:
                    System.out.println("Select course to cancel:");
                    Course = scan.next();
                    while (!corse.contains(Course.toUpperCase())) {
                        System.out.println("No course");
                        System.out.println("Select course :");
                        Course = scan.nextLine();
                    }

                    conn.stat.executeUpdate("insert into Cancelcourse  select  CourseName from course where  CourseName ='" + Course + "';");
                    conn.stat.executeUpdate("Delete from course where CourseName ='" + Course + "' ;");

                    System.out.println("Cancelled courses ");
                    result = conn.stat.executeQuery("select * from cancelcourse ");
                    while (result.next()) {
                        System.out.println(result.getString(1));
                    }

                    break;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    private void teacherMain() {

        System.out.println("1. Add ");
        System.out.println("2. Update ");
        System.out.println("3. Delete ");
        System.out.println("List of all teacher and with their respective courses");
        ArrayList ema = new ArrayList();
        ArrayList subname = new ArrayList();
        ArrayList frontname = new ArrayList();
        ArrayList course = new ArrayList();
        ArrayList lvl = new ArrayList();
        ArrayList sem = new ArrayList();
        try {

            result = conn.stat.executeQuery("Select * from teacherval");
            int a = 1;
            while (result.next()) {
                String email = result.getString(1);
                String FullName = result.getString(2);
                String Subject = result.getString(3);
                String Course1 = result.getString(4);
                String Level = result.getString(5);
                String Semester = result.getString(6);
                System.out.println("Number " + a);
                System.out.println(email);
                System.out.println(FullName);
                System.out.println(Subject);
                System.out.println(Course1);
                System.out.println(Level);
                System.out.println(Semester);
                ema.add(email);
                subname.add(FullName);
                frontname.add(Subject);
                course.add(Course1);
                lvl.add(Level);
                sem.add(Semester);
                a++;
            }
            System.out.println("Select your option ");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter teacher details to add teacher");
                    System.out.println("enter teacher email");
                    String emailname = scan.next();

                    System.out.println("enter teacher name");
                    String name = scan.next();

                    System.out.println("Select course :");
                    String qur = "Select * from course";
                    result = conn.stat.executeQuery(qur);


                    ArrayList co = new ArrayList();
                    while (result.next()) {
                        co.add(result.getString(1));
                    }
                    for (int i = 0; i < co.size(); i++) {
                        System.out.println(co.get(i).toString().toUpperCase());
                    }
                    String corse3 = scan.nextLine();
                    while (!co.contains(corse3.toUpperCase())) {
                        System.out.println("No course!");
                        System.out.println("Select course :");
                        corse3 = scan.nextLine();
                    }


                    System.out.println("Select course :");
                    String queryyy = "Select name from subject";
                    result = conn.stat.executeQuery(queryyy);

                    ArrayList su = new ArrayList();
                    while (result.next()) {
                        su.add(result.getString(1));
                    }
                    for (int i = 0; i < su.size(); i++) {
                        System.out.println(su.get(i).toString().toUpperCase());
                    }
                    String subject1 = scan.nextLine();
                    while (!su.contains(subject1.toUpperCase())) {
                        System.out.println("No subject available");
                        System.out.println("Select subject :");
                        subject1 = scan.nextLine();
                    }
                    System.out.println("enter level");
                    int lvel = scan.nextInt();

                    System.out.println("enter semester");
                    String semm = scan.next();

                    conn.stat.executeUpdate("insert into teacherval values ('" + emailname + "','" + name + "','" + subject1 + "','" + corse3 + "','" + lvel + "','" + semm + "');");
                    System.out.println("Successfully added teacher");

                    break;


                case 2:
                    System.out.println("select option from above list to update ");
                    int ch = scan.nextInt();
                    if (ch <= ema.size()) {
                        int coi = ch - 1;
                        String email = (String) ema.get(coi);
                        String fulname = (String) ema.get(coi);
                        System.out.println("Email " + email);
                        System.out.println("Fullname " + fulname);
                        System.out.println("enter teacher name to update ");
                        String nam = scan.next();

                        conn.stat.executeUpdate("update teacherval set fullname = '" + nam + "' where email = '" + email + "';");
                        System.out.println("update teacher");

                    } else {
                        System.out.println("large size ");
                    }
                    break;


                case 3:
                    System.out.println("select option from above list to delete ");
                    int cho = scan.nextInt();
                    if (cho <= ema.size()) {
                        int coo = cho - 1;
                        String em = (String) ema.get(coo);
                        String fullnam = (String) ema.get(coo);
                        System.out.println("Email " + em);
                        System.out.println("Fullname " + fullnam);

                        conn.stat.executeUpdate("delete from teacherval where email = '" + em + "';");
                        System.out.println("deleted teacher");

                    } else {
                        System.out.println("Large size ");
                    }
                    break;
            }


        } catch (SQLException exception) {
            exception.printStackTrace();
        }


    }

    private void resultMain() {
        System.out.println("Result panel");
        System.out.println("enter course ");
        String qur = "Select * from course";
        try {
            result = conn.stat.executeQuery(qur);
            ArrayList courses = new ArrayList();
            while (result.next()) {
                courses.add(result.getString(1));
            }
            for (int i = 0; i < courses.size(); i++) {
                System.out.println(courses.get(i).toString().toUpperCase());
            }
            Course = scan.next();
            while (!courses.contains(Course.toUpperCase())) {
                System.out.println("Course dosent exists");
                System.out.println("Select course :");
                Course = scan.next();
            }
            System.out.println("enter level ");
            int lvl = scan.nextInt();

            System.out.println("enter semester ");
            String semming = scan.next();

            ArrayList emailyy = new ArrayList();
            result = conn.stat.executeQuery("Select Email from oldstudent where Course = '" + Course + "' and Level = '" + lvl + "' and Semester = '" + semming + "';");

            while (result.next()) {
                emailyy.add(result.getString(1));
            }

            for (int i=0;i<emailyy.size();i++){
                System.out.println(i+1+" "+emailyy.get(i));
            }
            System.out.println("Choose number from above");
            int num = scan.nextInt();

            if (num<= emailyy.size()){
                int cols = num-1;
                String emm = (String) emailyy.get(cols);
                System.out.println("You will get your result soon!! "+ emm);

                result = conn.stat.executeQuery("Select * from resultval where email = '"+emm+"';");
                String resultText ="Result \n " +emm +"\n";
                while (result.next()){

                    resultText += result.getString(2)+"             ";
                    resultText += result.getString(6)+"\n";
                }

                try {
                    File myObj = new File(emm+".txt");
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                    } else {
                            System.out.println("Already Available.");
                    }
                } catch (IOException e) {
                    System.out.println("Error!!!.");
                    e.printStackTrace();
                }


                try {
                    FileWriter myWriter = new FileWriter(emm+".txt");
                    myWriter.write(resultText);
                    myWriter.close();
                    System.out.println("Your result is ready!!.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }


            }else {
                System.out.println("provide less number");
            }


        } catch (SQLException exception) {
            exception.printStackTrace();
        }


    }

    private void subjectMain() {
        System.out.println("Select your option ");
        System.out.println("1. Add ");
        System.out.println("2. Update ");
        System.out.println("3. Delete ");

        System.out.println("Total Subject");
        try {
            result= conn.stat.executeQuery("Select * from subject");
            ArrayList names = new ArrayList();
            ArrayList courses = new ArrayList();
            ArrayList lev = new ArrayList();
            ArrayList sems = new ArrayList();
            int a = 1;
            while (result.next()) {
                String nm = result.getString(1);
                String nc = result.getString(2);
                int nl = result.getInt(3);
                String ns = result.getString(4);
                System.out.println("Number " + a);
                System.out.println(nm);
                System.out.println(nc);
                System.out.println(nl);
                System.out.println(ns);
                a++;
                names.add(nm);
                sems.add(nm);
                courses.add(nm);
                lev.add(nm);
            }
            int cho = scan.nextInt();
            switch (cho) {
                case 1:
                    System.out.println("enter subject name");
                    String subname = scan.next();
                    System.out.println("course :");
                    String Course = scan.next();
                    String qur = "Select * from course";
                    try {
                        result = conn.stat.executeQuery(qur);
                        ArrayList course = new ArrayList();
                        while (result.next()) {
                            course.add(result.getString(1));
                        }
                        for (int i = 0; i < course.size(); i++) {
                            System.out.println(course.get(i).toString().toUpperCase());
                        }

                        String course1 = scan.nextLine();
                        while (!course.contains(course1.toUpperCase())) {
                            System.out.println("Course dosent exists");
                            System.out.println("Select course :");
                            course1 = scan.next();
                        }
                        System.out.println("enter level");
                        int selL = scan.nextInt();

                        System.out.println("enter semester");
                        String sem2 = scan.next();

                        if (selL == 6) {
                            System.out.println("1.compulsory");
                            System.out.println("2. optional");
                            int ch = scan.nextInt();
                            switch (ch) {
                                case 1:
                                    subname = subname;
                                    break;
                                case 2:
                                    subname = "opt." + subname;
                            }
                        }

                        conn.stat.executeUpdate("Insert into course values ('" + subname + "','" + course1 + "','" + selL+ "','" + sem2 + "');");
                        System.out.println("Subject is added!!!!");

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;

                case 2:

                    System.out.println("select the number from above list");
                    int sel = scan.nextInt();

                    if (sel <= names.size()) {
                        int col = sel - 1;
                        String namesubj = (String) names.get(col);
                        String cou = (String) courses.get(col);
                        String lvl = (String) lev.get(col);
                        String se = (String) sems.get(col);
                        System.out.println("you selected this to update :");
                        System.out.println(namesubj);
                        System.out.println(cou);
                        System.out.println(lvl);
                        System.out.println(se);

                        System.out.println("Enter new name to update subject ");
                        String subName = scan.next();

                        conn.stat.executeUpdate("update subject set Name ='" + subName + "' where Name = '" + namesubj + "' ;");
                        System.out.println("update done");

                    } else {
                        System.out.println("choose option out of list");
                    }

                    break;


                case 3:
                    System.out.println("select the number from above list");
                    int select= scan.nextInt();
                    if (select <= names.size()) {
                        int col =select- 1;
                        String namesubj = (String) names.get(col);
                        String cou = (String) courses.get(col);
                        String lvl = (String) lev.get(col);
                        String se = (String) sems.get(col);
                        System.out.println("you selected this to delete :");
                        System.out.println(namesubj);
                        System.out.println(cou);
                        System.out.println(lvl);
                        System.out.println(se);

                        conn.stat.executeUpdate("Delete  from subject where  Name ='" + namesubj + "'  ;");
                        System.out.println("update done");

                    } else {
                        System.out.println("Please choose correct option!!");
                    }

                    break;


            }




        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
