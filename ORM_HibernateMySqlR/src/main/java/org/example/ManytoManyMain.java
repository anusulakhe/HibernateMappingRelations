package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ManytoManyMain {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        Roles adminRole = new Roles();
        adminRole.setRoleId(101);
        adminRole.setRoleName("Admin");
        adminRole.getAttributes().add("READ_PRIVILEGES");
        adminRole.getAttributes().add("WRITE_PRIVILEGES");

        Roles managerRole = new Roles();
        managerRole.setRoleId(102);
        managerRole.setRoleName("Manager");
        managerRole.getAttributes().add("READ_PRIVILEGES");

        Roles userRole = new Roles();
        userRole.setRoleId(103);
        userRole.setRoleName("User");
        userRole.getAttributes().add("READ_PRIVILEGES");
        userRole.getAttributes().add("Access_PRIVILEGES");

        PhoneNumber user1Phone1 = new PhoneNumber();
        user1Phone1.setType("Home");
        user1Phone1.setNumber("123-456-7890");

        PhoneNumber user1Phone2 = new PhoneNumber();
        user1Phone2.setType("Work");
        user1Phone2.setNumber("123-456-7890");

        User user1 = new User();
        user1.setUserId(1);
        user1.setUserName("User1");
        user1.getPhoneNumbers().add(user1Phone1);
        user1.getPhoneNumbers().add(user1Phone2);

        user1.getRolesList().add(adminRole);
        user1.getRolesList().add(managerRole);
        adminRole.getUser().add(user1);
        managerRole.getUser().add(user1);

        User user2 = new User();
        user2.setUserId(2);
        user2.setUserName("User2");

        PhoneNumber user1Phone3 = new PhoneNumber();
        user1Phone3.setType("Mobile");
        user1Phone3.setNumber("123-456-7890");

        user2.getRolesList().add(userRole);
        user2.getPhoneNumbers().add(user1Phone3);
        userRole.getUser().add(user2);

        Configuration con = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Roles.class);
        //ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(con.getProperties());
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();

        SessionFactory sf = con.buildSessionFactory(serviceRegistry);
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        session.save(adminRole);
        session.save(managerRole);
        session.save(userRole);

        session.save(user1);
        session.save(user2);

        tx.commit();
        session.close();
        sf.close();
        System.out.println("User saved: " + user1.getUserName() +" , "+  user2.getUserName());
    }
}

