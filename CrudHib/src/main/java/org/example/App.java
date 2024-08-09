package org.example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javax.security.auth.login.AppConfigurationEntry;
//import javax.security.auth.login.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try{
            Employee emp = new Employee(111,"SewakKaur", 500f);

            Configuration cfg=new Configuration() ;
            cfg.configure("hibernate.cfg.xml");

            SessionFactory factory= cfg.buildSessionFactory();
            Session session=factory.openSession();

            Transaction tr=session.beginTransaction();

            session.save(emp);
            tr.commit();
            session.close();
            factory.close();
            System.out.println("Record inserted");


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
