package service;

import java.util.List;

import model.CursoDto;

public interface CursosService {
	public boolean nuevo(CursoDto curso);
	
	public List<CursoDto> precioCursoMax(double precioMax);
	
	public void eliminar(String nombre);
	
	public void modificarDuracion(String nombre, int duracion);

}
