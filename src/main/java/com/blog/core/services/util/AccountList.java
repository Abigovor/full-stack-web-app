package com.blog.core.services.util;

import com.blog.core.models.entities.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Single on 17.01.2016.
 */

public class AccountList {

    private List<Account> accounts = new ArrayList<Account>();

    public AccountList(List<Account> list) {
        this.accounts = list;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}