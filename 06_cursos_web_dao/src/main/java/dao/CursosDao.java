package dao;

import java.util.List;

import model.Curso;

public interface CursosDao {
	public void save(Curso curso);
	public List<Curso> findyByPrecioMax(double precioMax);
	public void deleteByNombre(String nombre);
	public Curso findByNombre(String nombre);
	double averageByTematica(String tematica);
}
