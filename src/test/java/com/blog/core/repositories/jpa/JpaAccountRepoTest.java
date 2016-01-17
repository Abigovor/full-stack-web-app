package com.blog.core.repositories.jpa;

import com.blog.core.models.entities.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/application-config.xml")
public class JpaAccountRepoTest {

    @Autowired
    JpaAccountRepo repo;

    private Account account;

    @Before
    @Transactional
    @Rollback(false)
    public void setUp() throws Exception {
        account = new Account();
        account.setName("name");
        account.setPassword("password");
        repo.createAccount(account);
    }


    @Test
    @Transactional
    public void testFindAccount() throws Exception {
        assertNotNull(repo.findAccount(account.getId()));
    }

    @Test
    public void testCreateAccount() throws Exception {

    }

    @Test
    public void testCreateBlog() throws Exception {

    }
}