package com.blog.rest.resources.asm;

import com.blog.core.services.util.AccountList;
import com.blog.rest.mvc.AccountController;
import com.blog.rest.resources.AccountListResource;
import com.blog.rest.resources.AccountResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

/**
 * Created by Single on 17.01.2016.
 */

public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList, AccountListResource> {
    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(AccountList accountList) {
        List<AccountResource> resList = new AccountResourceAsm().toResources(accountList.getAccounts());
        AccountListResource finalRes = new AccountListResource();
        finalRes.setAccounts(resList);
        return finalRes;
    }
}