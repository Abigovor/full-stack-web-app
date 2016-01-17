package com.blog.core.repositories;

import com.blog.core.models.entities.Blog;

import java.util.List;

/**
 * Created by Single on 17.01.2016.
 */

public interface BlogRepo {
    public Blog createBlog(Blog data);

    public List<Blog> findAllBlogs();

    public Blog findBlog(Long id);

    public Blog findBlogByTitle(String title);

    public List<Blog> findBlogsByAccount(Long accountId);
}
