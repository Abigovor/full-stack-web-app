package com.blog.rest.resources.asm;

import com.blog.core.entities.BlogEntry;
import com.blog.rest.mvc.BlogEntryController;
import com.blog.rest.resources.BlogEntryResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
/**
 * Created by Single on 14.01.2016.
 */
public class BlogEntryResourceAsm extends ResourceAssemblerSupport<BlogEntry, BlogEntryResource> {

    public BlogEntryResourceAsm() {
        super(BlogEntry.class, BlogEntryResource.class);
    }

    @Override
    public BlogEntryResource toResource(BlogEntry blogEntry) {
        BlogEntryResource res = new BlogEntryResource();
        res.setTitle(blogEntry.getTitle());
        Link link = linkTo(BlogEntryController.class).slash(blogEntry.getId()).withSelfRel();
        res.add(link.withSelfRel());
        return res;
    }
}
