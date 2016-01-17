package com.blog.core.repositories;

import com.blog.core.models.entities.Account;

import java.util.List;

/**
 * Created by Single on 17.01.2016.
 */
public interface AccountRepo {
    public List<Account> findAllAccounts();

    public Account findAccount(Long id);

    public Account findAccountByName(String name);

    public Account createAccount(Account data);
}
