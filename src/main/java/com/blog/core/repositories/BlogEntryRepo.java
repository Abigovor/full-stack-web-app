package com.blog.core.repositories;

import com.blog.core.models.entities.BlogEntry;

import java.util.List;

/**
 * Created by Single on 17.01.2016.
 */

public interface BlogEntryRepo {
    public BlogEntry findBlogEntry(Long id);

    public BlogEntry deleteBlogEntry(Long id);

    public BlogEntry updateBlogEntry(Long id, BlogEntry data);

    public BlogEntry createBlogEntry(BlogEntry data);

    public List<BlogEntry> findByBlogId(Long blogId);
}