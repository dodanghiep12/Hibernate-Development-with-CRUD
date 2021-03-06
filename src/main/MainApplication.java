package main;

import entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class MainApplication {

    private static Scanner getInput = new Scanner(System.in);

    private static void save (String firstName, String lastName, String company) {

        //create session factory this is for hibernate
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try (factory) {
            Session session = factory.getCurrentSession();
            Employee myEmployee = new Employee(firstName, lastName, company);
            session.beginTransaction();

            session.save(myEmployee);

            session.getTransaction().commit();

        }
    }

    private static void updateFirstName (int id, String firstName) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Employee myEmployee = session.get(Employee.class, id);
            myEmployee.setFirstName(firstName);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    private static void updateLastName (int id, String lastName) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Employee myEmployee = session.get(Employee.class, id);
            myEmployee.setLastName(lastName);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    private static void updateCompany (int id, String company) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Employee myEmployee = session.get(Employee.class, id);
            myEmployee.setCompany(company);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    private static void retrieve (int id) {

        //create session factory this is for hibernate
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try (factory) {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            //Retrieve student based on the id: primary key
            Employee myEmployee = session.get(Employee.class, id);

            System.out.println(myEmployee);

            session.getTransaction().commit();

        }
    }

    private static void query (String company) {

        //create session factory this is for hibernate
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            List<Employee> theEmployees = session.createQuery("from Employee where company='" + company + "\'").getResultList();
            displayEmployee(theEmployees);

            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    private static void delete (int id) {

        //create session factory this is for hibernate
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            //Retrieve student based on the id: primary key
            Employee myEmployee =  session.get(Employee.class, id);

            //Delete the student based on the id: primary key
            session.delete(myEmployee);

            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    private static void displayEmployee (List<Employee> theEmployees) {
        for (Employee tempEmployee: theEmployees) {
            System.out.println(tempEmployee);
        }
    }

    private static void updateChose () {
        System.out.println();
        System.out.print("Enter the id of the employee who you want to update: ");
        int id = getInput.nextInt();
        System.out.println();
        System.out.println("What do you want to do with the employee information?");
        System.out.println("\t1.Update first name");
        System.out.println("\t2.Update last name");
        System.out.println("\t3.Update company");
        int chose = getInput.nextInt();

        if (chose == 1) {
            System.out.print("Enter the employee new first name: ");
            String firstName = getInput.next();
            updateFirstName(id, firstName);
        } else if (chose == 2) {
            System.out.print("Enter the employee new last name: ");
            String lastName = getInput.next();
            updateLastName(id, lastName);
        } else if (chose == 3) {
            System.out.print("Enter the employee new company name: ");
            String company = getInput.next();
            updateCompany(id, company);
        } else {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
    }

    private static void startApplication () {
        System.out.println();
        System.out.println("What do you want to do with the Employee database?");
        System.out.println("\t1.Add employee information");
        System.out.println("\t2.Get employee information by id");
        System.out.println("\t3.Get employee information by company");
        System.out.println("\t4.Delete employee information");
        System.out.println("\t5.Update employee information");

        int chose = getInput.nextInt();

        if (chose == 1) {
            System.out.print("Enter employee firstname: ");
            String firstName = getInput.next();
            System.out.print("Enter employee lastname: ");
            String lastName = getInput.next();
            System.out.print("Enter employee company: ");
            String company = getInput.next();
            save(firstName, lastName, company);
        } else if (chose == 2) {
            System.out.print("Enter employee id: ");
            int id = getInput.nextInt();
            retrieve(id);
        } else if (chose == 3) {
            System.out.print("Enter employee company: ");
            String company = getInput.next();
            query(company);
        } else if (chose == 4) {
            System.out.print("Enter employee id: ");
            int id = getInput.nextInt();
            delete(id);
        } else if (chose == 5) {
            updateChose();
        } else {
            System.out.println("invalid input");
            System.exit(-1);
        }
    }


    public static void main(String[] args) {
        String answer = "";
        do {

            startApplication();

            System.out.println("Do you want to continue: (yes or no)\n");
            answer = getInput.next();
        } while (answer.equals("yes"));
    }
}
