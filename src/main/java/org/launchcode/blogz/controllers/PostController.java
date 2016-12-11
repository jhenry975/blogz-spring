package org.launchcode.blogz.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController extends AbstractController {

	@RequestMapping(value = "/blog/newpost", method = RequestMethod.GET)
	public String newPostForm() {
		return "newpost";
	}
	
	@RequestMapping(value = "/blog/newpost", method = RequestMethod.POST)
	public String newPost(HttpServletRequest request, Model model) {
		
		// TODO - implement newPost
		
		//MY CODE//
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		if(title != null  && body != null)
		{
			model.addAttribute("title", title);
			model.addAttribute("body", body);
			User author = getUserFromSession(request.getSession());
			Post post = new Post(title, body, author);
			String username = author.getUsername();
			postDao.save(post);
			int postid = post.getUid();
			
			return "redirect:/blog/"+username+"/"+postid;
		}
		else
		{
			model.addAttribute("${error}");
			return "redirect:/blog/newpost";
		}
		//END MY CODE//
		
		//return "redirect:index"; // TODO - this redirect should go to the new post's page  
	
	}
	
	@RequestMapping(value = "/blog/{username}/{uid}", method = RequestMethod.GET)
	public String singlePost(@PathVariable String username, @PathVariable int uid, Model model) {
		
		// TODO - implement singlePost
		
		//MY CODE//
		Post p = postDao.findByUid(uid);
		//User author = userDao.findByUid(uid);
		//List<Post> p = postDao.findByAuthor(author);
		//List<Post> p = postDao.findByAuthor(uid);
		model.addAttribute("post", p);
		//return "redirect:/blog/post";
		//FINISH POST.HTML
		
		//END MY CODE//
		return "post";
		//return "redirect:/blog/post";
	}
	
	@RequestMapping(value = "/blog/{username}", method = RequestMethod.GET)
	public String userPosts(@PathVariable String username, Model model) {
		
		// TODO - implement userPosts
		
		//MY CODE//
		User u = userDao.findByUsername(username);
		List<Post> p = u.getPosts();
		model.addAttribute("u", u);
		model.addAttribute("posts", p);
		//return "redirect:/blog";
		//END MY CODE//
		
		return "blog";
		//return "redirect:/blog";
	}
	
}
