package com.studycafe.utils.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public String handleArgumentException(IllegalArgumentException ilEx, Model model) {
		
		model.addAttribute("error", ilEx.getMessage());
		
		return "/error/500";
	}
	
	@ExceptionHandler(value = AccessDeniedException.class)
	public String handleAccessDeniedException(AccessDeniedException ilEx, Model model) {
		
		model.addAttribute("error", ilEx.getMessage());
		
		return "/error/accessdenied";
	}
}
