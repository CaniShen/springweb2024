package dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.Curso;

public interface CursosDao extends JpaRepository<Curso, Integer> {
	@Query("select c from Curso c where c.alumnos.usuario=?1")
	Set<Curso> findCursosByUsuario(String usuario);
	@Query("select c from Curso c where c.alumnos.usuario!=?1")
	Set<Curso> findCursosByUsuarioNoMatriculado(String usuario);
}
