package com.blog.rest.mvc;

import com.blog.core.models.entities.Account;
import com.blog.core.models.entities.Blog;
import com.blog.core.services.AccountService;
import com.blog.core.services.exceptions.AccountDoesNotExistException;
import com.blog.core.services.exceptions.AccountExistsException;
import com.blog.core.services.exceptions.BlogExistsException;
import com.blog.core.services.util.AccountList;
import com.blog.core.services.util.BlogList;
import com.blog.rest.exception.ConflictException;
import com.blog.rest.exception.NotFoundException;
import com.blog.rest.resources.AccountListResource;
import com.blog.rest.resources.AccountResource;
import com.blog.rest.resources.BlogListResource;
import com.blog.rest.resources.BlogResource;
import com.blog.rest.resources.asm.AccountListResourceAsm;
import com.blog.rest.resources.asm.AccountResourceAsm;
import com.blog.rest.resources.asm.BlogListResourceAsm;
import com.blog.rest.resources.asm.BlogResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/rest/accounts")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<AccountListResource> findAllAccounts(@RequestParam(value = "name", required = false) String name) {
        AccountList list = null;
        if (name == null) {
            list = accountService.findAllAccounts();
        } else {
            Account account = accountService.findByAccountName(name);
            if (account == null) {
                list = new AccountList(new ArrayList<Account>());
            } else {
                list = new AccountList(Arrays.asList(account));
            }
        }
        AccountListResource res = new AccountListResourceAsm().toResource(list);
        return new ResponseEntity<AccountListResource>(res, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccount(
            @RequestBody AccountResource sentAccount
    ) {
        try {
            Account createdAccount = accountService.createAccount(sentAccount.toAccount());
            AccountResource res = new AccountResourceAsm().toResource(createdAccount);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<AccountResource>(res, headers, HttpStatus.CREATED);
        } catch (AccountExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @RequestMapping(value = "/{accountId}",
            method = RequestMethod.GET)
    public ResponseEntity<AccountResource> getAccount(
            @PathVariable Long accountId
    ) {
        Account account = accountService.findAccount(accountId);
        if (account != null) {
            AccountResource res = new AccountResourceAsm().toResource(account);
            return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{accountId}/blogs",
            method = RequestMethod.POST)
    public ResponseEntity<BlogResource> createBlog(
            @PathVariable Long accountId,
            @RequestBody BlogResource res) {
        try {
            Blog createdBlog = accountService.createBlog(accountId, res.toBlog());
            BlogResource createdBlogRes = new BlogResourceAsm().toResource(createdBlog);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdBlogRes.getLink("self").getHref()));
            return new ResponseEntity<BlogResource>(createdBlogRes, headers, HttpStatus.CREATED);
        } catch (AccountDoesNotExistException exception) {
            throw new NotFoundException(exception);
        } catch (BlogExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @RequestMapping(value = "/{accountId}/blogs",
            method = RequestMethod.GET)
    public ResponseEntity<BlogListResource> findAllBlogs(
            @PathVariable Long accountId) {
        try {
            BlogList blogList = accountService.findBlogsByAccount(accountId);
            BlogListResource blogListRes = new BlogListResourceAsm().toResource(blogList);
            return new ResponseEntity<BlogListResource>(blogListRes, HttpStatus.OK);
        } catch (AccountDoesNotExistException exception) {
            throw new NotFoundException(exception);
        }
    }
}
