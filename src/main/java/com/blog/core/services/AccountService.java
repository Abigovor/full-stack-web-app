package com.blog.core.services;

import com.blog.core.models.entities.Account;
import com.blog.core.models.entities.Blog;

public interface AccountService {
    public Account findAccount(Long id);

    public Account createAccount(Account data);

    public Blog createBlog(Long accountId, Blog data);
}
