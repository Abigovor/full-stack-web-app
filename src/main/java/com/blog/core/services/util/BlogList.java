package com.blog.core.services.util;

import com.blog.core.models.entities.Blog;

import java.util.ArrayList;
import java.util.List;

public class BlogList {

    private List<Blog> blogs = new ArrayList<Blog>();

    public BlogList(List resultList) {
        this.blogs = resultList;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}