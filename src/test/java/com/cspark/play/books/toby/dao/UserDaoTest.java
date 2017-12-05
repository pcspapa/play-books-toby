package com.cspark.play.books.toby.dao;

import com.cspark.play.books.toby.config.TobyConfig;
import com.cspark.play.books.toby.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by cspark on 2017. 12. 5..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TobyConfig.class)
@DirtiesContext
public class UserDaoTest {
    @Autowired
    ApplicationContext context;

    private UserDao dao;

    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() {
        this.dao = this.context.getBean("userDao", UserDao.class);

        this.user1 = new User("pcspapa1", "Name1", "password1");
        this.user2 = new User("pcspapa2", "Name2", "password2");
        this.user3 = new User("pcspapa3", "Name3", "password3");

    }

    @Test
    public void andAndGet() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName(), is(user1.getName()));
        assertThat(userget1.getPassword(), is(user1.getPassword()));

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName(), is(user2.getName()));
        assertThat(userget2.getPassword(), is(user2.getPassword()));
    }

    @Test(expected=EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.get("unknown_id");
    }


    @Test
    public void count() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));
    }
}