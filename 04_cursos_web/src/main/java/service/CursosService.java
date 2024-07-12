package service;

import java.util.ArrayList;


import model.Curso;

public interface CursosService {
	public boolean nuevo(Curso curso);
	
	public ArrayList<Curso> precioCursoMax(double precioMax);
	
	public void eliminar(String nombre);
	
	public void modificarDuracion(String nombre, int duracion);

}
