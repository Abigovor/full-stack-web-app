package com.blog.core.services;

import com.blog.core.models.entities.BlogEntry;

public interface BlogEntryService {

    public BlogEntry findBlogEntry(Long id);

    public BlogEntry deleteBlogEntry(Long id);

    public BlogEntry updateBlogEntry(Long id, BlogEntry data);

}
