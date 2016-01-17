package com.blog.core.repositories;

import com.blog.core.models.entities.Account;
import com.blog.core.models.entities.Blog;

/**
 * Created by Single on 17.01.2016.
 */
public interface AccountRepo {
    public Account findAccount(Long id);

    public Account createAccount(Account data);

    public Blog createBlog(Long accountId, Blog data);
}
