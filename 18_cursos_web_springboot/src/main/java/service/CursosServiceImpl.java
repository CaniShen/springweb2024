package service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import model.Curso;
@Service
public class CursosServiceImpl implements CursosService {
	private static ArrayList<Curso> cursos=new ArrayList<>();
	
	@Override
	public boolean nuevo(Curso curso) {
		if(!cursos.stream()
				.anyMatch(c->c.getNombre().equals(curso.getNombre()))) {
			cursos.add(curso);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Curso> precioCursoMax (double precioMax) {
		ArrayList<Curso> cursosAux=new ArrayList<>();
			for(Curso c:cursos) {
				if(c.getPrecio()<=precioMax) {
					cursosAux.add(c);
				}
			}
			return cursosAux;
	}
			
		

	@Override
	public void eliminar(String nombre) {
			cursos.removeIf(c->c.getNombre().equals(nombre));

	}

	@Override
	public void modificarDuracion(String nombre, int duracion) {
			for(Curso c:cursos) {
				if(c.getNombre().equals(nombre)) {
					c.setDuracion(duracion);
					break;
				}
			}

	}

}
