package negocio;

import java.util.List;

import javax.ejb.Stateless;

import dao.ClienteDao;
import modelo.Cliente;


@Stateless
public class GestionCliente implements GestionClienteRemote, GestionLocal{
	private ClienteDao dao= new ClienteDao();

	@Override
	public void guardarCliente(Cliente cli) {
		dao.insertar(cli);
	}

	@Override
	public List<Cliente> getCliente(String cedula) {
		return dao.obtenerClientes(cedula);
	}

	

}
