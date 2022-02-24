package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<?> getByModel(String model) {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<User>userList=session.createQuery("from User ").list();
      userList.stream().filter(user -> user.getCar().getModel().equals(model)).forEach(System.out::println);
      session.getTransaction().commit();
      session.close();
      return userList;
   }

   @Override
   public List<?> getBySeries(int series) {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<User>userList=session.createQuery("from User ").list();
      userList.stream().filter(user -> user.getCar().getSeries() == series).forEach(System.out::println);
      session.getTransaction().commit();
      session.close();
      return userList;
   }

}
