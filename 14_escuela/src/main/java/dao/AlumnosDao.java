package dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Alumno;

public interface AlumnosDao extends JpaRepository<Alumno, String> {
	Set<Alumno> findAlumnosByIdCurso(int idCurso);
	
}
