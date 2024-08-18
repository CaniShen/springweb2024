package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import model.ClienteDto;
import model.MovimientoDto;
import service.BancaService;

@Controller
public class BancaController {
	
	private BancaService bancaService;
	
	public BancaController(BancaService bancaService){
		this.bancaService = bancaService;
	}
	
	@GetMapping(value="validarCuenta", produces=MediaType.TEXT_PLAIN_VALUE)
	public String validarCuenta(@RequestParam("numCuenta") int numCuenta, HttpSession sesion) {
		
		if(bancaService.validarCuenta(numCuenta)) {
			sesion.setAttribute("titular",bancaService.titularesCuenta(numCuenta).get(0));
			sesion.setAttribute("numCuenta", numCuenta);
			return "menu";
		}else {
			return "error";
		}
		
		
	}
	
	@GetMapping(value="ingresar", produces=MediaType.TEXT_PLAIN_VALUE)
	public String ingresar(@SessionAttribute("numCuenta") int numCuenta, double cantidad,Model model) {
		bancaService.ingreso(numCuenta, cantidad);
		model.addAttribute("cantidad", cantidad);
		model.addAttribute("operacion", "ingreso");
		return "exito";
	}
	
	@GetMapping(value="extraccion", produces=MediaType.TEXT_PLAIN_VALUE)
	public String extraccion(@SessionAttribute("numCuenta") int numCuenta,@RequestParam("cantidad") double cantidad,Model model) {
		bancaService.extraccion(numCuenta, cantidad);
		model.addAttribute("cantidad", cantidad);
		model.addAttribute("operacion", "extraccion");
		return "exito";
	}
	
	@GetMapping(value="transferencia", produces=MediaType.TEXT_PLAIN_VALUE)
	public String extraccion(@SessionAttribute("numCuenta") int numCuentaOrigen, @RequestParam("numCuentaDestino") int numCuentaDestino , double cantidad,Model model) {
		bancaService.transferencia(numCuentaOrigen, numCuentaDestino, cantidad);
		model.addAttribute("cantidad", cantidad);
		model.addAttribute("operacion", "transferencia");
		return "exito";
	}
	
	@GetMapping(value="consultaMovimientos", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MovimientoDto> consultaMovimientos(@SessionAttribute("numCuenta") int numCuenta, @RequestParam("fecha") String fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime fechaHora = LocalDateTime.parse(fecha, formatter);
		return bancaService.movimientosCuentaFecha(numCuenta, fechaHora);
	}
	
	@GetMapping(value="toTitulares", produces=MediaType.TEXT_PLAIN_VALUE)
	public String consultaTitulares(@SessionAttribute("numCuenta") int numCuenta,Model model) {
		model.addAttribute("titulares", bancaService.titularesCuenta(numCuenta));
		return "titulares";
	}
	
	@GetMapping(value="toLogout", produces=MediaType.TEXT_PLAIN_VALUE)
	public String logout(HttpSession sesion) {
		sesion.invalidate();
		return "inicio";
	}
	
}
