package model;

public class TemaDto {
	private int idTema;
	private String tema;
	public TemaDto(int idTema, String tema) {
		super();
		this.idTema = idTema;
		this.tema = tema;
	}
	public TemaDto() {
		super();
	}
	public int getIdTema() {
		return idTema;
	}
	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	

}
