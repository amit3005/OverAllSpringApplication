package com.springoverallApplication.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.springoverallApplication.demo.model.AppCountry;
import com.springoverallApplication.demo.model.AppState;
import com.springoverallApplication.demo.model.AppUser;
import com.springoverallApplication.demo.services.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/signup")
	public String signUp(ModelMap modelMap) throws IOException {
		AppUser appUser = new AppUser();
		modelMap.addAttribute("countryList", loginService.getAllCountry());
		modelMap.addAttribute("stateList", loginService.getAllStates());
		modelMap.addAttribute("appUser", appUser);
		return "SignupPage";
	}
	
	@PostMapping("/registeruser")
	public String registerUser(ModelMap modelMap,@ModelAttribute("appUser") AppUser appUser,RedirectAttributes redirectAttributes,HttpSession session) throws IOException {
		appUser.setAppUserPassword(bCryptPasswordEncoder.encode(appUser.getAppUserPassword()));
		loginService.addAppUser(appUser);
		if(appUser.getAppUserId()!=0) {
			return "redirect:/otpgeneration/"+appUser.getAppUserEmailId();
		}else {
			redirectAttributes.addFlashAttribute("error","Operation Failed,Please Try again");
			return "redirect:/signup";
		}
	}
	
	@GetMapping("/otpgeneration/{emailId}")
	public String otpgeneration(ModelMap modelMap,@PathVariable("emailId") String emailId) throws IOException {
		String otp = loginService.generateOTP();
		modelMap.addAttribute("otp", otp);
		modelMap.addAttribute("emailId", emailId);
		loginService.sendEmail(emailId,otp);
		return "OtpPage";
	}
	
	@GetMapping("/getCountryWiseState/{countryId}")
	@ResponseBody
	public String getCountryWiseState(@PathVariable("countryId") int countryId) {
		Gson gson = new Gson();
		//List<AppState> list = loginService.getAllStatesByCountryId(countryId);
		List<Object[]> list = loginService.getAllStatesByCountryId(countryId);
		return gson.toJson(list).toString() ;
	}
	
	@GetMapping("/checkEmailIdExist/{emailId}")
	@ResponseBody
	public String checkEmailIdExist(@PathVariable("emailId") String emailId) {
		boolean isValid = loginService.isValidEmailId(emailId);
		return isValid ? "Valid" : "NotValid";
	}

	@GetMapping("/checkOtp/{generatedOtp}/{otp}")
	@ResponseBody
	public String getCountryWiseState(@PathVariable("generatedOtp") String generatedOtp,@PathVariable("otp") String otp) {
		if(generatedOtp.equalsIgnoreCase(otp)) {
			return "valid";
		}else {
			return "Not Valid";
		}
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/home")
	public String home() {
		return "Home";
	}
	
	@GetMapping("/logoutSuccess")
	public String logoutSuccess() {
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
}
