package vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import modelo.Cliente;
import negocio.GestionLocal;

@ManagedBean
public class ClienteBeans {
	@Inject
	private GestionLocal gl;
	
	/* Beans properties */
	private Cliente aux;
	private String cedula;
	private List<Cliente> clientes;
	

	@PostConstruct
	public void init() 
	{
		this.aux=new Cliente();
	}
	

	public Cliente getAux() {
		return aux;
	}


	public void setAux(Cliente aux) {
		this.aux = aux;
	}


	public List<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String buscar() {
		this.clientes=gl.getCliente(this.cedula);
		return null;
	}
}
