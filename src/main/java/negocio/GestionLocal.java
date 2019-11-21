package negocio;

import java.util.List;

import javax.ejb.Local;

import modelo.Cliente;

@Local
public interface GestionLocal {
		
		public void guardarCliente(Cliente cli);
		public List<Cliente> getCliente(String cedula);

	
}
