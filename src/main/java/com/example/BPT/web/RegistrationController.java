package com.example.BPT.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.BPT.domain.SignUp;
import com.example.BPT.domain.User;
import com.example.BPT.domain.UserRepository;

@Controller
public class RegistrationController {

	@Autowired
	private UserRepository urepository;

	@GetMapping("/registration")
    public String addStudent(Model model){
    	model.addAttribute("signupform", new SignUp());
	    return "registration";
	}
	
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignUp signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { 
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { 
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	
		    	if (urepository.findByUsername(signupForm.getUsername()) == null) {
		    		urepository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "error.userexists", "Username already exists");    	
	    			return "registration";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Password do not match");    	
    			return "registration";
    		}
    	}
    	else {
    		return "registration";
    	}
    	return "redirect:/login?signup=true";    	
    }    
}
