package com.blog.core.services;

import com.blog.core.models.entities.Blog;
import com.blog.core.models.entities.BlogEntry;
import com.blog.core.services.util.BlogEntryList;
import com.blog.core.services.util.BlogList;

public interface BlogService {
    /**
     * @param blogId the id of the blog to add this BlogEntry to
     * @param data the BlogEntry containing the data to be used for creating the new entity
     * @return the created BlogEntry with a generated ID
     * @throws com.blog.core.services.exceptions.BlogNotFoundException if the blog to add to cannot be found
     */
    public BlogEntry createBlogEntry(Long blogId, BlogEntry data);

    public BlogList findAllBlogs();

    public BlogEntryList findAllBlogEntries(Long blogId);

    public Blog findBlog(Long id);
}
