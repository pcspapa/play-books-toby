package com.cspark.play.books.toby.dao;

import com.cspark.play.books.toby.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by cspark on 2017. 12. 5..
 */
public class UserDaoTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("pcspapa");
        user.setName("Name");
        user.setPassword("password");

        dao.add(user);

        System.out.println(user.getId() + " 등록 완료.");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getId() + " 조회 완료.");
    }
}