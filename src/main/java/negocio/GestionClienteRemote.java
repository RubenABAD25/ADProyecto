package negocio;

import java.util.List;

import javax.ejb.Remote;

import modelo.Cliente;

@Remote
public interface GestionClienteRemote {
	
	public void guardarCliente(Cliente cli);
	public List<Cliente> getCliente(String cedula);

}
