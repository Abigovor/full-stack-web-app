package com.blog.core.services;

import com.blog.core.models.entities.Account;
import com.blog.core.models.entities.Blog;
import com.blog.core.services.util.AccountList;
import com.blog.core.services.util.BlogList;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    public Account findAccount(Long id);

    public Account createAccount(Account data);

    public Blog createBlog(Long accountId, Blog data);

    public BlogList findBlogsByAccount(Long accountId);

    public AccountList findAllAccounts();

    public Account findByAccountName(String name);
}
