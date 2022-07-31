package util;

import entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                Configuration configuration = new Configuration();

                Properties properties = new Properties();
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/hql_data_base");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "Rares2010");
                properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.SHOW_SQL, true);
                properties.put(Environment.HBM2DDL_AUTO, "create-drop");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(Student.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return sessionFactory;
    }
}