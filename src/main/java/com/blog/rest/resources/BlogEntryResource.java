package com.blog.rest.resources;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Single on 14.01.2016.
 */
public class BlogEntryResource extends ResourceSupport {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
