package service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AlumnosDao;
import dao.CursosDao;
import model.AlumnoDto;
import model.CursoDto;
import utilidades.Mapeador;
@Service
public class FormacionServiceImpl implements FormacionService {
	Mapeador mapeador;
	CursosDao cursosDao;
	AlumnosDao alumnosDao;

	public FormacionServiceImpl(Mapeador mapeador, CursosDao cursosDao, AlumnosDao alumnosDao) {
		this.mapeador = mapeador;
		this.cursosDao = cursosDao;
		this.alumnosDao = alumnosDao;
	}
	//estq anotación se pone para evitar que hibernate cierre la sesión al obtener el 
	//y así no falle cuando se recuperen los objetos relacionados
	@Transactional
	@Override
	public List<CursoDto> cursos() {

		return cursosDao.findAll().stream().map(c -> mapeador.cursoEntityToDto(c)).toList();
	}

	@Override
	public List<AlumnoDto> buscarAlumnosMatriculados(int idCurso) {
		return alumnosDao.findByIdCurso(idCurso).stream()
				.map(a->mapeador.alumnoEntityToDto(a))
				.toList(); 
	}
	@Override
	public boolean altaCurso(CursoDto curso) {
		if(cursosDao.findByNombreAndFechaInicio(curso.getNombre(), curso.getFechaInicio())==null)  {
			cursosDao.save(mapeador.cursoDtoToEntity(curso));
		}
		return false;
	}
	@Override
	public List<AlumnoDto> alumnos() {
		return alumnosDao.findAll().stream().map(a -> mapeador.alumnoEntityToDto(a)).toList();
	}
	@Override
	public int matricular(CursoDto curso, AlumnoDto alumno) {
		if(alumnosDao.findByAlumno(alumno.getUsuario()) == null) {
			alumnosDao.save(mapeador.alumnoDtoToEntity(alumno));
			cursosDao.save(mapeador.cursoDtoToEntity(curso));
			return 0;
		}else if(cursosDao.findByUsuario(alumno.getUsuario()) == null) {
			cursosDao.save(mapeador.cursoDtoToEntity(curso));
			return 1;
		} else {
			return 2;
		}
		
	}

}
