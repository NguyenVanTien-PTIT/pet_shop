package com.migi.migi_project.repository.user;

import com.migi.migi_project.entity.Product;
import com.migi.migi_project.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT u.* FROM User u WHERE u.username=?1 AND u.password=?2", nativeQuery = true)
    Optional<User> checkUser(String username, String password);

    User findByUsername(String username);

    @Query(value = "SELECT u.* FROM user as u WHERE u.username=?1", nativeQuery = true)
    Optional<User> findUserByUsername(String username);
}

//abstract class UserRepositoryImpl implements UserRepository {
//    private SessionFactory sessionFactory =new Configuration().configure().buildSessionFactory();
//
//    @Override
//    public User findByUsername(String username){
//        Session session = sessionFactory.openSession();
//        String hql = "SELECT U FROM User LEFT JOIN FETCH U.userRolesById as WHERE U.username="+ username;
//        User u = (User) session.createQuery(hql).uniqueResult();
//        return u;
//    }
//}
