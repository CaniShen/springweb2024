package service;

import java.util.List;

import org.springframework.stereotype.Service;

import dao.CursosDao;
import entities.Curso;
import model.CursoDto;
import utilidades.Mapeador;
@Service
public class CursosServiceImpl implements CursosService {
	CursosDao cursosDao;
	Mapeador mapeador;
	
	public CursosServiceImpl(CursosDao cursosDao, Mapeador mapeador) {
		super();
		this.cursosDao = cursosDao;
		this.mapeador = mapeador;
	}

	@Override
	public boolean nuevo(CursoDto curso) {

		if(cursosDao.findByNombre(curso.getNombre())==null){
			cursosDao.save(mapeador.cursoDtoToEntity(curso));
			return true;
		}

		return false;
	}

	@Override
	public List<CursoDto> precioCursoMax (double precioMax) {
		return cursosDao.findByPrecioMax(precioMax).stream()
				.map(c->mapeador.cursoEntityToDto(c))
				.toList();
	}
			
		

	@Override
	public void eliminar(String nombre) {
				cursosDao.delete(nombre);

	}

	@Override
	public void modificarDuracion(String nombre, int duracion) {
			cursosDao.findByNombre(nombre).setDuracion(duracion);

	}

}
