package com.blog.mvc;

import com.blog.entities.BlogEntry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogEntryController {
    @RequestMapping(value="/home", method = RequestMethod.POST)
    public @ResponseBody BlogEntry test(@RequestBody BlogEntry entry)
    {
        return entry;
    }
}
