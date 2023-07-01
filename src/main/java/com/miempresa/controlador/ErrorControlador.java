package com.miempresa.controlador;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorControlador implements ErrorController {
	private static final String ERROR_PATH = "/error";
	
	@RequestMapping(ERROR_PATH)
	public String handleError(Model model) {
		return "error";
	}
	//@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}
