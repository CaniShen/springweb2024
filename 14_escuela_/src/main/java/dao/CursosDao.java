package dao;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.Curso;

public interface CursosDao extends JpaRepository<Curso, Integer> {
	@Query("select c from Curso c join c.alumnos a where a.usuario=?1")
	Set<Curso> findCursosByUsuario(String usuario);
	@Query("select c from Curso c where c not in(select c from Curso c join c.alumnos a where a.usuario=?1)")
	Set<Curso> findCursosByUsuarioNoMatriculado(String usuario);
	@Query("select c from Curso c where c.nombre=?1 and c.fechaInicio=?2")
	Curso findByNombreAndFechaInicio(String x, LocalDate fecha);
}
