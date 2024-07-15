package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import model.Curso;
@Repository
public class CursosDaoImpl implements CursosDao {
	
	JdbcTemplate template;
	
	
	public CursosDaoImpl(JdbcTemplate template) {
		super();
		this.template = template;
	}

	@Override
	public void save(Curso curso) {
		String sql="insert into cursos (nombre,tematica,duracion,precio) value (?,?,?,?)";
		template.update(sql,curso.getNombre(),curso.getTematica(),curso.getDuracion(),curso.getPrecio());
	}

	@Override
	public List <Curso> findyByPrecioMax(double precioMax) {
		String sql="select * from cursos where precio<=?";
		RowMapper <Curso> mapper=(r,f)->new Curso(r.getInt("codCurso"),
												r.getString("nombre"),
												r.getString("tematica"),
												r.getInt("duracion"),
												r.getDouble("precio"));
		
		return template.query(sql, mapper,precioMax);
	
	}

	@Override
	public void deleteByNombre(String nombre) {
		 	template.update("delete from cursos where nombre=?",nombre);

	}

	@Override
	public Curso findByNombre(String nombre) {
		/*String sql="select * from cursos where nombre=?";
		List <Curso> cursos=template.query(sql, (r,f)->new Curso(r.getInt("codCurso"),
																r.getString("nombre"),
																r.getString("tematica"),
																r.getInt("duracion"),
																r.getDouble("precio")),nombre);
		return cursos.size()>0?cursos.get(0):null;
		*/
		String sql="select * from cursos where nombre=?";
		try {
			return template.queryForObject(sql, (r,f)->new Curso(r.getInt("codCurso"),
					r.getString("nombre"),
					r.getString("tematica"),
					r.getInt("duracion"),
					r.getDouble("precio")),nombre);
			
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
	
	}

	@Override
	public double averageByTematica(String tematica) {
		String sql="select avg(precio) from cursos where tematica=?";
		return template.queryForObject(sql, Double.class,tematica);
		
	}


}
