package dao;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.Alumno;

public interface AlumnosDao extends JpaRepository<Alumno, String> {
	@Query("select distinct(a) from Alumno a join a.cursos c where c.idCurso=?1")
	Set<Alumno> findByIdCurso(int idCurso);
	@Query("select distinct(a) from Alumno a join a.cursos c where c.fechaInicio>=?1")
	Set<Alumno> findyAlumnosByMatriculadosDesdeFecha(LocalDate fecha);
	
	
}
