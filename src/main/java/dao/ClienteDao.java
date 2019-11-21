package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conector.ConexionDB;
import modelo.Cliente;

public class ClienteDao {
	private ConexionDB conexion;

	public boolean insertar(Cliente cli) {
		conexion = new ConexionDB();
		String retorno = "";
		PreparedStatement statement = null;
		try {
			String sql = "insert into Cliente (id,cedula,nombres,apellidos,fechaN) values( ?,?,?,?,?)";
			statement = conexion.connect().prepareStatement(sql);
			statement.setInt(1, cli.getCodigo());
			statement.setString(2, cli.getCedula());
			statement.setString(3, cli.getNombre());
			statement.setString(4, cli.getApellido());
			statement.setString(5, cli.getFechaN());
			statement.executeUpdate();

		} catch (SQLException ex) {
			System.out.println(ex.getSQLState());
		} finally {
			
			try {
				statement.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			conexion.cerrarConexion();

		}
		return true;

	}

	public ArrayList<Cliente> obtenerClientes(String cedula) {
		conexion= new ConexionDB();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<Cliente> lista = new ArrayList<>();
		try {
			String sql = "SELECT id, cedula, nombres,apellidos,fechaN FROM Cliente where cedula = ?;";
			statement= conexion.connect().prepareStatement(sql);
			statement.setString(1, cedula);
			resultSet= statement.executeQuery();
			while (resultSet.next()) {
				Cliente cli = new Cliente();
				cli.setCodigo(resultSet.getInt(1));
				cli.setCedula(resultSet.getString(2));
				cli.setNombre(resultSet.getString(3));
				cli.setApellido(resultSet.getString(4));
				cli.setFechaN(resultSet.getString(5));
				lista.add(cli);
			}
		
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			try {
				resultSet.close();
				statement.close();
				conexion.cerrarConexion();
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return lista;
	}

	public Cliente read(String cedula) {

		Cliente unCliente = new Cliente();
		Connection con = conexion.connect();
		PreparedStatement statement = null;
		ResultSet rs = null;
		String sql = "SELECT id,cedula,nombres from cliente where cedula =?;";

		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, cedula);
			rs = statement.executeQuery();
			while (rs.next()) {
				unCliente.setCodigo(rs.getInt(1));
				unCliente.setCedula(rs.getNString(2));
				unCliente.setCedula(rs.getNString(3));
				unCliente.setCedula(rs.getNString(4));
				unCliente.setCedula(rs.getNString(5));

			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			conexion.cerrarConexion();
		}
		System.out.println(unCliente.toString());
		return unCliente;
	}

}