package com.crud.parcial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administradores")
public class AdministradorController {

	@RequestMapping
	public String viewAdministrador() {
		return "vista-administrador";
	}
}
