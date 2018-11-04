package com.zoo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zoo.web.DAO.AnimalDAO;
import com.zoo.web.DAO.SectorDAO;
import com.zoo.web.DAO.TipoDAO;

@Controller
@RequestMapping("/animal")
public class AnimalController {

	@Autowired
	private AnimalDAO aDAO;
	
	@Autowired
	private SectorDAO sDAO;
	
	@Autowired 
	private TipoDAO tDAO;
	
	@GetMapping("/listar")
	public String listar(Model model)
	{
		model.addAttribute("animales", aDAO.crud().findAll());
		return "listar.html";
	}
	@GetMapping("/eliminar")
	public String eliminar(Model model,
			RedirectAttributes ra,
			@RequestParam("id")int id)
	{
		
		String mensaje="";
		
		try
		{
			aDAO.crud().deleteById(id);
			mensaje= "Eliminado correctamente";
		}
		catch (Exception ex) 
		{
			mensaje="Error al eliminar";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		return "redirect:listar";
	}
	
	@GetMapping("/crear")
	public String crear(Model model)
	{
		model.addAttribute("tipos", tDAO.crud().findAll());
		model.addAttribute("sectores", sDAO.crud().findAll());
		
		return "agregar.html";
	}
	
}
