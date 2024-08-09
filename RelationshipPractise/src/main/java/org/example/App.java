package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entities.Place;
import org.example.entities.Student;
import org.example.persistence.CustomPersistence;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;
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
        String puname="persistence";
        Map<String,String> props=new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hbm2ddl.auto", "create");
        EntityManagerFactory emf= new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistence(puname),props);
        EntityManager em=emf.createEntityManager();
        do{
            System.out.println("1 for insert\n2 for update\n3 for delete\n4 for view all\n5 for view\0 for exit");
            System.out.println("Enter your choice: ");
            choice=sc.nextInt();
            switch(choice){
                case 1:{
                    try{
                    em.getTransaction().begin();
                    System.out.println("Insert into database");
                    System.out.println("Enter the student name: ");
                    String studentName=sc.next();
                    System.out.println("Enter the bench side: ");
                    String benchSide=sc.next();
                    Place p1=new Place();
                    p1.setSide(benchSide);
                    Student s1=new Student();
                    s1.setsName(studentName);
                    s1.setPlace(p1);
                    em.persist(s1);
                    em.getTransaction().commit();
                    System.out.println("Inserted successfully");
                    break;


                }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }while( true );
    }
}
