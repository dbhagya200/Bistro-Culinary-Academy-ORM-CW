package lk.ijse.bistroculinaryacademyorm.config;

import lk.ijse.bistroculinaryacademyorm.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfig {

    private static SessionFactoryConfig factoryConfiguration;
    private final SessionFactory sessionFactory;
    private SessionFactoryConfig() throws IOException {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(Student_Course_Details.class);


        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance() throws IOException {
        return (factoryConfiguration==null) ? factoryConfiguration=new SessionFactoryConfig():factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }

}
