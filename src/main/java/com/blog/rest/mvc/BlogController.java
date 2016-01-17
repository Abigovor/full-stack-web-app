package com.blog.rest.mvc;

import com.blog.core.models.entities.Blog;
import com.blog.core.models.entities.BlogEntry;
import com.blog.core.services.exceptions.BlogNotFoundException;
import com.blog.core.services.util.BlogEntryList;
import com.blog.core.services.util.BlogList;
import com.blog.core.services.BlogService;
import com.blog.rest.exception.NotFoundException;
import com.blog.rest.resources.BlogEntryListResource;
import com.blog.rest.resources.BlogEntryResource;
import com.blog.rest.resources.BlogListResource;
import com.blog.rest.resources.BlogResource;
import com.blog.rest.resources.asm.BlogEntryListResourceAsm;
import com.blog.rest.resources.asm.BlogEntryResourceAsm;
import com.blog.rest.resources.asm.BlogListResourceAsm;
import com.blog.rest.resources.asm.BlogResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

@Controller
@RequestMapping("/rest/blogs")
public class BlogController {
    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BlogListResource> findAllBlogs() {
        BlogList blogList = blogService.findAllBlogs();
        BlogListResource blogListRes = new BlogListResourceAsm().toResource(blogList);
        return new ResponseEntity<BlogListResource>(blogListRes, HttpStatus.OK);
    }

    @RequestMapping(value = "/{blogId}",
            method = RequestMethod.GET)
    public ResponseEntity<BlogResource> getBlog(@PathVariable Long blogId) {
        Blog blog = blogService.findBlog(blogId);
        if (blog != null) {
            BlogResource res = new BlogResourceAsm().toResource(blog);
            return new ResponseEntity<BlogResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<BlogResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{blogId}/blog-entries",
            method = RequestMethod.POST)
    public ResponseEntity<BlogEntryResource> createBlogEntry(
            @PathVariable Long blogId,
            @RequestBody BlogEntryResource sentBlogEntry
    ) {
        BlogEntry createdBlogEntry = null;
        try {
            createdBlogEntry = blogService.createBlogEntry(blogId, sentBlogEntry.toBlogEntry());
            BlogEntryResource createdResource = new BlogEntryResourceAsm().toResource(createdBlogEntry);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
            return new ResponseEntity<BlogEntryResource>(createdResource, headers, HttpStatus.CREATED);
        } catch (BlogNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @RequestMapping(value = "/{blogId}/blog-entries")
    public ResponseEntity<BlogEntryListResource> findAllBlogEntries(
            @PathVariable Long blogId) {
        try {
            BlogEntryList list = blogService.findAllBlogEntries(blogId);
            BlogEntryListResource res = new BlogEntryListResourceAsm().toResource(list);
            return new ResponseEntity<BlogEntryListResource>(res, HttpStatus.OK);
        } catch (BlogNotFoundException exception) {
            throw new NotFoundException(exception);
        }
    }

}