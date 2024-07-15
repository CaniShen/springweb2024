package service;

import java.util.List;

import model.Curso;

public interface CursosService {
	public boolean nuevo(Curso curso);
	
	public List<Curso> precioCursoMax(double precioMax);
	
	public void eliminar(String nombre);
	

}
