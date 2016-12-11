package org.launchcode.blogz.controllers;

import java.util.List;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController extends AbstractController {

	@RequestMapping(value = "/")
	public String index(Model model){
		
		// TODO - fetch users and pass to template
		
		//MY CODE//
		List<User> u = userDao.findAll();
		model.addAttribute("users", u);
		//return "redirect:/blog/index";
		//return "redirect:index";
		//END MY CODE//
		
		return "index";
		//return "redirect:/index";
	}
	
	@RequestMapping(value = "/blog")
	public String blogIndex(Model model) {
		
		// TODO - fetch posts and pass to template
		
		//MY CODE//
		List<Post> p = postDao.findAll();
		model.addAttribute("posts", p);
		//return "redirect:blog";
		//END MY CODE//
		
		return "blog";
		//return "redirect:/blog";
	}
	
}
