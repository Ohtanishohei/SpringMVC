package jp.co.syslab;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.syslab.db.*;
import jp.co.syslab.email.Javamail;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM user");
		
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		
		return "login";
	}
	
	@RequestMapping(value = "/verify",params = {"token"}, method = RequestMethod.GET)
	public String verify(@RequestParam(value = "token") String token,Locale locale, Model model) {
		User user = new User();
		user.check1(jdbcTemplate, token);	
		return "verify";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginpost(Locale locale, Model model) {
		
		return "login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String check(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("repassword") String repassword,
			@RequestParam("email") String email,
			@RequestParam("reemail") String reemail,
			Locale locale,
			Model model) {
		
		model.addAttribute("username_value", username);
		model.addAttribute("password_value", password);
		model.addAttribute("email_value", email);
		
		int error = 0;
		if (username == "") {
			model.addAttribute("username_error", "ユーザー名は空です");
			error = 1;
		}
		if (password == "") {
			model.addAttribute("password_error", "パスワードは空です");
			error = 1;
		}
		if (!repassword.equals(password)) {
			model.addAttribute("repassword_error", "再入力したパスワードは違います");
			error = 1;
		} 
		if (email == "") {
			model.addAttribute("email_error", "メールアドレスは空です");
			error = 1;
		}
		if (!reemail.equals(email)) {
			model.addAttribute("reemail_error", "再入力したメールアドレスは違います");
			error = 1;
		}
		if (error == 1) {
			return "register";
		}else{
			String token = generateLenString(20);
			User user = new User();
			user.create(jdbcTemplate,username, password, email, 0, token);
			Javamail mailSend = new Javamail();
			mailSend.send("JavaMail テストメール", "テストメールの本文 http://localhost:8080/syslab/verify?token="+token,email);
			return "login";
		}
	}
	
    private String generateLenString(int length) {  
        char[] cResult = new char[length];  
        int[] flag = { 0, 0, 0 }; // A-Z, a-z, 0-9  
        int i = 0;  
        while (flag[0] == 0 || flag[1] == 0 || flag[2] == 0 || i < length) {  
            i = i % length;  
            int f = (int) (Math.random() * 3 % 3);  
            if (f == 0)  
                cResult[i] = (char) ('A' + Math.random() * 26);  
            else if (f == 1)  
                cResult[i] = (char) ('a' + Math.random() * 26);  
            else  
                cResult[i] = (char) ('0' + Math.random() * 10);  
            flag[f] = 1;  
            i++;  
        }  
        return new String(cResult);  
    }  

	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Locale locale, Model model) {
		
		return "register";
	}
	
	@RequestMapping(value = "/forget", method = RequestMethod.POST)
	public String forgetpost(@RequestParam("email") String email,Locale locale, Model model) {
		model.addAttribute("email_value", email);
		String token = generateLenString2(20);
		Javamail mailSend = new Javamail();
		mailSend.resend("JavaMail テストメール", "テストメールの本文 http://localhost:8080/syslab/reset?token="+token,email);
		return "forget";
	}

	private String generateLenString2(int length) {  
	    char[] cResult = new char[length];  
	    int[] flag = { 0, 0, 0 }; // A-Z, a-z, 0-9  
	    int i = 0;  
	    while (flag[0] == 0 || flag[1] == 0 || flag[2] == 0 || i < length) {  
	        i = i % length;  
	        int f = (int) (Math.random() * 3 % 3);  
	        if (f == 0)  
	            cResult[i] = (char) ('A' + Math.random() * 26);  
	        else if (f == 1)  
	            cResult[i] = (char) ('a' + Math.random() * 26);  
	        else  
	            cResult[i] = (char) ('0' + Math.random() * 10);  
	        flag[f] = 1;  
	        i++;  
	    }  
    return new String(cResult);  
	}
	
	@RequestMapping(value = "/forget", method = RequestMethod.GET)
	public String forget(Locale locale, Model model) {
		
		return "forget";
	}
	
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String resetpsw(Locale locale, Model model) {
		
		return "login";
	}

	@RequestMapping(value = "/reset", params = {"token"},method = RequestMethod.POST)	
	public String resetpost(@RequestParam(value = "token") String token,
			@RequestParam("password") String password,
			Locale locale, 
			Model model) {
		User user = new User();
		user.reset(jdbcTemplate, token,password);
		return "login";
	}
}
