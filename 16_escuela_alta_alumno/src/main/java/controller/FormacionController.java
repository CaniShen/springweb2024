package controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.AlumnoDto;
import model.CursoDto;
import service.FormacionService;

@Controller
public class FormacionController {
	FormacionService formacionService;

	public FormacionController(FormacionService formacionService) {
		this.formacionService = formacionService;		
	}
	@GetMapping(value="/cursos")
	public String inicial(Model model) {
		
		model.addAttribute("cursos",formacionService.cursos());
		return "cursos";
		
		
	}
	
	@GetMapping(value="/matricular")
	public String inicialMatricular(Model model) {
		
		model.addAttribute("cursos",formacionService.cursos());
		model.addAttribute("alumnos",formacionService.alumnos());
		return "matricular";
		
		
	}
	@GetMapping(value="buscarAlumnosPorCurso", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AlumnoDto> alumnosPorCurso(@RequestParam("idCurso") int idCurso) {
		return formacionService.buscarAlumnosMatriculados(idCurso);
	}
	
	@PostMapping(value="altaCurso",produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String altaCurso(@ModelAttribute CursoDto curso){
		return String.valueOf(formacionService.altaCurso(curso));
	}
	
	@PostMapping(value="matriculacion",produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String matriculacion(@ModelAttribute CursoDto curso, @ModelAttribute AlumnoDto alumno){
		return String.valueOf(formacionService.matricular(curso, alumno));
	}
	
	
	
	
	
	
	
}
