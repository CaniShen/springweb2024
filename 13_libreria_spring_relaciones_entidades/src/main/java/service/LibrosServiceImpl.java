package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dao.LibrosDao;
import dao.TemasDao;
import entities.Libro;
import entities.Tema;
import model.LibroDto;
import model.TemaDto;
import utilidades.Mapeador;
@Service
public class LibrosServiceImpl implements LibrosService {
	LibrosDao librosDao;
	Mapeador mapeador;
	TemasDao temasDao;



	public LibrosServiceImpl(LibrosDao librosDao, Mapeador mapeador, TemasDao temasDao) {
	
		this.librosDao = librosDao;
		this.mapeador = mapeador;
		this.temasDao = temasDao;
	}

	@Override
	public List<TemaDto> getTemas() {
		return temasDao.findAll().stream()
				.map(t->mapeador.temaEntityToDto(t)) //stream<TemaDto>
				.toList();
	}

	@Override
	public List<LibroDto> librosTema(int idTema) {
		if(idTema!=0) {
			return librosDao.findByIdTema(idTema).stream()
					.map(l->mapeador.libroEntityToDto(l))
					.toList();
		}else {
			return librosDao.findAll().stream()
					.map(l->mapeador.libroEntityToDto(l))
					.toList();
		}
	}

	@Override
	public LibroDto getLibro(int isbn) {
		Optional<Libro> opLibro=librosDao.findById(isbn);
		return mapeador.libroEntityToDto(opLibro.isPresent()?opLibro.get():new Libro());
	}

	@Override
	public TemaDto getTema(int idTema) {
		Optional<Tema> opTema=temasDao.findById(idTema);
		return mapeador.temaEntityToDto(opTema.isPresent()?opTema.get():new Tema());
	}

	@Override
	public boolean guardarLibro(LibroDto libro) {
		
		if(librosDao.findById(libro.getIsbn()).isPresent()) {
			return false;
		}
		librosDao.save(mapeador.libroDtoToEntity(libro));
		return true;
	}

	@Override
	public TemaDto buscarTemaTitulo(String titulo) {
			return  mapeador.temaEntityToDto(temasDao.findyByTituloLibro(titulo));
	}

}
