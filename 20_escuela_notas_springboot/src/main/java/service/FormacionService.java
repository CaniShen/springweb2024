package service;

import java.util.List;


import model.AlumnoDto;
import model.AlumnoMatriculadoDto;
import model.CursoDto;

public interface FormacionService {
	List<CursoDto> cursos();
	List<AlumnoMatriculadoDto> buscarAlumnosMatriculados(int idCurso); 
	boolean altaCurso(CursoDto curso);
	double notaMediaCurso(int idCurso);
	boolean altaAlumno(AlumnoDto alumno);

}
