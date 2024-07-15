package service;

import java.util.List;
import org.springframework.stereotype.Service;
import dao.CursosDao;
import model.Curso;
@Service
public class CursosServiceImpl implements CursosService {

	CursosDao cursosDao;
	public CursosServiceImpl(CursosDao cursosDao) {
		super();
		this.cursosDao = cursosDao;
	}

	@Override
	public boolean nuevo(Curso curso) {
		if(cursosDao.findByNombre(curso.getNombre())==null) {
			cursosDao.save(curso);
			return true;
		}
		return false;
		
	}

	@Override
	public List<Curso> precioCursoMax (double precioMax) {
			return cursosDao.findyByPrecioMax(precioMax);
	}
			
		

	@Override
	public void eliminar(String nombre) {
		cursosDao.deleteByNombre(nombre);

	}





}
