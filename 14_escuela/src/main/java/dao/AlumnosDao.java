package dao;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.Alumno;

public interface AlumnosDao extends JpaRepository<Alumno, String> {
	@Query("select a from Alumno a where a.curso.idCurso=?1")
	Set<Alumno> findAlumnosByIdCurso(int idCurso);
	@Query("select a from Alumno a where a.curso.fechaInicio>=?1")
	Set<Alumno> findyAlumnosByMatriculadosDesdeFecha(LocalDate fecha);
	
	
}
