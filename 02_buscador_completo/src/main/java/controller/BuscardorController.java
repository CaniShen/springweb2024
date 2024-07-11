package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import model.Resultado;
import service.BuscadorService;
@Controller
public class BuscardorController {
	@Autowired
	BuscadorService service;
	@GetMapping(value="buscar")
	public String buscarResultados(@RequestParam("tematica") String tematica,HttpServletRequest request)  {
		List<Resultado>resultados=service.buscar(tematica);
		request.setAttribute("resultados", resultados);
		return "resultados";
	
	}
	@PostMapping(value="alta")
	public String altaResultado(@RequestParam("url") String url,
								@RequestParam("tematica") String tematica,
								@RequestParam("descripcion")String descripcion) {
		Resultado resultado=new Resultado(url,tematica,descripcion);
		return service.agregar(resultado)?"inicio":"error";

	}
	@GetMapping(value="toBuscarResultados")
	public String paginaBuscar() {
		return "buscarResultados";
	}
	
	@GetMapping(value="toAlta")
	public String paginaAlta() {
		return "alta";
	}
	@GetMapping(value={"toInicio","/"})
	public String paginaInicio() {
		return "inicio";
	}
	@GetMapping(value= {"toBuscarUrl"})
	public String paginaBuscarUrl() {
		return "buscarUrl";
		
	}
	@GetMapping(value="buscarUrl")
	public String buscarResultado(@RequestParam("url") String url, HttpServletRequest request) {
		Resultado res=service.buscarPorUrl(url);
		request.setAttribute("resultado", res);
		return "resultado";
	}
	@PostMapping(value= {"eliminar"})
	public String eliminarResultado(@RequestParam("url") String url) {
		service.eliminar(url);
		return "inicio";
	}
	@GetMapping(value="toEliminar")
	public String toEliminar() {
		return "eliminar";
	}

}
