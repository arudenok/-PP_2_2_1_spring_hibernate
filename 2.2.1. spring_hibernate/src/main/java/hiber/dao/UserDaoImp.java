package hiber.dao;

import hiber.model.Car;
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
   public User userCar(String model, int series) {
      String hql = "from User  where car.model = :model and car.series = :series";
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(hql);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getSingleResult();
   }
   //   @Override
//   public void addCarUSer(User user, Car car) {
//      Session session = sessionFactory.getCurrentSession();//нужно было создать сессию и из этой сессии добавить все ?
//      user.setCar(car);
//      session.save(user);
//      session.save(car);
//
//      sessionFactory.getCurrentSession().save(user);
//      sessionFactory.getCurrentSession().save(car);
//
//   }

}