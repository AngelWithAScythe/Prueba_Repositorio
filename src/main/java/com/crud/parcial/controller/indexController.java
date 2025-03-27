package com.crud.parcial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class indexController {

	@RequestMapping
	public String inicio() {
		return "index";
	}
}
