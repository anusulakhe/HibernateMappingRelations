package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class OnetoOneMain {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        Laptop laptop1 = new Laptop();
        laptop1.setName("Acer");
        laptop1.setId(101);

        Student student1 = new Student();
        student1.setStudentnme("Ram");
        student1.setMarks(85);
        student1.setStudentID(1);


        laptop1.setStudent(student1);

        Configuration con = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();

        SessionFactory sf = con.buildSessionFactory(serviceRegistry);
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();
        session.save(student1);
        session.save(laptop1);
        tx.commit();

        session.close();
        sf.close();
        System.out.println("Student saved: " + student1.getStudentnme());
    }
}
