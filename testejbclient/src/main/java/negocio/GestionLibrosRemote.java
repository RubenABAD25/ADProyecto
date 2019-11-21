package negocio;

import java.util.List;


import modelo.Libro;

public interface GestionLibrosRemote {
	
	public void guardarLibro(int codigo, String titulo);
	public List<Libro> getLibros();

}
