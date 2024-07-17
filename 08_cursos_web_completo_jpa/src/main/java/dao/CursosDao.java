package dao;

import java.util.List;

import entities.Curso;

public interface CursosDao {
	public void save(Curso curso);
	public List<Curso> findByPrecioMax(double precioMax);
	public void delete(String nombre);
	public Curso findByNombre(String nombre);

}
