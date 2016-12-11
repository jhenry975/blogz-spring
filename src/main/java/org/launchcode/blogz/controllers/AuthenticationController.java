package org.launchcode.blogz.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.blogz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController extends AbstractController {
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm() {
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model) {
		
		// TODO - implement signup
		
		//MY CODE//
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");
		
		if(User.isValidUsername(username) && User.isValidPassword(password))
		{
			if(verify.equals(password))
			{
				User user = new User(username, password);
				
				HttpSession session = request.getSession();
				setUserInSession( session, user);
				userDao.save(user);
			}
		}
		
		//END MY CODE//
		
		return "redirect:blog/newpost";
		//return "redirect:newpost";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		
		// TODO - implement login
		
		//MY CODE//
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//System.console();
		
		User u = userDao.findByUsername(username);
		if(u.isMatchingPassword(password))
		{
				HttpSession session = request.getSession();
				setUserInSession( session, u);
		}
		else
		{
			model.addAttribute("${error}", "Incorrect Password");
			return "redirect:/blog/login";
		}
		//END MY CODE//
		
		return "redirect:blog/newpost";
		//return "newpost";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
        request.getSession().invalidate();
		return "redirect:/";
        //return "redirect:/blog/login";
	}
}
