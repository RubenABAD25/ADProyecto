package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Libro;
import negocio.GestionLibrosRemote;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;


public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtTitulo;

	GestionLibrosRemote gl;
	private JTextArea txtLista;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		
		try {
			this.conectarInstancias();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(76, 43, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(76, 96, 86, 20);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
			}
		});
		btnGuardar.setBounds(76, 138, 89, 23);
		contentPane.add(btnGuardar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(172, 43, 252, 186);
		contentPane.add(scrollPane);
		
		txtLista = new JTextArea();
		scrollPane.setViewportView(txtLista);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(25, 46, 46, 14);
		contentPane.add(lblCdigo);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setBounds(25, 99, 46, 14);
		contentPane.add(lblTtulo);
	}

	protected void guardar() {
		// TODO Auto-generated method stub
		int codigo = Integer.parseInt(txtCodigo.getText());  
		String titulo =  txtTitulo.getText();  
		gl.guardarLibro(codigo, titulo);
		listar();
	}
	
	void listar() {
		txtLista.setText("");
		List<Libro> libros = gl.getLibros();
		for(Libro l : libros) {
			txtLista.append(l.toString()+ "\n");
		}
	}
	
	public void conectarInstancias() throws Exception {
		try {  
            final Hashtable<String, Comparable> jndiProperties =  
                    new Hashtable<String, Comparable>();  
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  
                    "org.wildfly.naming.client.WildFlyInitialContextFactory");  
            jndiProperties.put("jboss.naming.client.ejb.context", true);  
              
            jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");  
            jndiProperties.put(Context.SECURITY_PRINCIPAL, "ejb");  
            jndiProperties.put(Context.SECURITY_CREDENTIALS, "ejb");  
              
            final Context context = new InitialContext(jndiProperties);  
              
            final String lookupName = "ejb:/pruebaAbadRuben/GestionCliente!negocio.GestionClienteRemote";
             
            
            this.gl = (GestionLibrosRemote) context.lookup(lookupName);
            
              
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw ex;  
        }  
	}

}
