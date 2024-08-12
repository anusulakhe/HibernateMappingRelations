package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class OnetoManyMain {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        Employee employee1 = new Employee();
        employee1.setName("Emp1");
        employee1.setSalary(50000);

        Employee employee2 = new Employee();
        employee2.setName("Emp2");
        employee2.setSalary(60000);

        Department department = new Department();
        department.setName("IT Department");

        employee1.setDepartment(department);
        employee2.setDepartment(department);

        department.getEmployees().add(employee1);
        department.getEmployees().add(employee2);

        Configuration con = new Configuration().configure().addAnnotatedClass(Department.class).addAnnotatedClass(Employee.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();

        SessionFactory sf = con.buildSessionFactory(serviceRegistry);
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();
        session.save(department);
        session.save(employee1);
        session.save(employee2);
        tx.commit();

        session.close();
        sf.close();
        System.out.println("Department saved: " + department.getName());
    }
}