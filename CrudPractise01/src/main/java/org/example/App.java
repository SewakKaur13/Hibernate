package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int choice;
        Scanner sc = new Scanner(System.in);
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();

        do{
            System.out.println("1 for insert\n2 for update\n3 for delete\n4 for view\n5 for view by id\n0 gor exit");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            switch(choice) {
                case 1:{
                    try {
                        System.out.println("Insert into database");
                        System.out.println("Enter name: ");
                        String name = sc.next();
                        em.getTransaction().begin();
                        Student s1 = new Student();
                        s1.setSname(name);
                        em.persist(s1);
                        em.getTransaction().commit();
                        System.out.println("Inserted succesfully");
                        break;
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                case 2:{
                    try{
                        System.out.println("Update into database");
                        System.out.println("Enter id to be updated  : ");
                        int id = sc.nextInt();
                        System.out.println("Enter updated name: ");
                        String name = sc.next();
                        em.getTransaction().begin();
                        Student s2 =em.find(Student.class, id);
                        s2.setSname(name);
                        em.getTransaction().commit();
                        System.out.println("Update successful");
                        break;

                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                case 3:{
                    try{
                        System.out.println("Delete into database");
                        System.out.println("Enter id to be deleted  : ");
                        int id = sc.nextInt();
                        em.getTransaction().begin();
                        Student s3 = em.find(Student.class, id);
                        em.remove(s3);
                        em.getTransaction().commit();
                        System.out.println("Delete successful");
                        break;
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                case 4:{
                    try{
                        System.out.println("View into database");
                        String hql="from Student";
                        ArrayList<Student> students = (ArrayList<Student>) em.createQuery(hql).getResultList();
                        System.out.println("Student Details");
                        for(Student s:students){
                            System.out.println(s.getSid()+" "+s.getSname());

                        }
                        System.out.println("----------------------------");
                        System.out.println("Print succesfully");
                        break;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case 5:{
                    try{
                        System.out.println("View by id into database");
                        System.out.println("Enter id to be viewed  : ");
                        int id = sc.nextInt();
                        Student s3 = em.find(Student.class, id);
                        if(s3!=null){
                            System.out.println("Student details");
                            System.out.println(s3.getSid()+" "+s3.getSname());
                            System.out.println("----------------------------");
                        }
                        else{
                            System.out.println("Student not found");
                        }
                        break;
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                case 0:{
                    System.out.println("Goodbye");
                    System.exit(0);
                }
                default:{
                    System.out.println("Wrong choice");
                }

            }
        }while (true);
    }
}
