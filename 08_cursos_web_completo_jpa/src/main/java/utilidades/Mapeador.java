package utilidades;

import org.springframework.stereotype.Component;

import entities.Curso;
import model.CursoDto;
@Component
public class Mapeador {
	public CursoDto cursoEntityToDto(Curso curso) {
		return new CursoDto(curso.getNombre(),curso.getTematica(),curso.getDuracion(),curso.getPrecio());
	}
	
	public Curso cursoDtoToEntity(CursoDto curso) {
		return new Curso(0,curso.getNombre(),curso.getTematica(),curso.getDuracion(),curso.getPrecio());
	}
}
