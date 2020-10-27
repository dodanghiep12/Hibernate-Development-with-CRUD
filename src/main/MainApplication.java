package main;

import entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class MainApplication {

    private static void save () {}

    private static void retrieve () {}

    private static void query () {}

    private static void delete (int Id) {
        //create session factory this is for hibernate
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            //Retrieve student based on the id: primary key
            Employee myEmployee =  session.get(Employee.class, Id);

            //Delete the student based on the id: primary key
            session.delete(myEmployee);

            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }


    public static void main(String[] args) {

        Scanner getInput = new Scanner(System.in);

    }
}
