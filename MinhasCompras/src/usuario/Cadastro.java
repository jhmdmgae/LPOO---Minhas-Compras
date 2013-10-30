/*
 * Classe com suporte a GUI com formul�rio de cadastro de um usu�rio.
 */
package usuario;

import usuario.UsuarioBanco;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cadastro extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private JTextField textFieldCPF;
	private JTextField textFieldSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro frame = new Cadastro();
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
	public Cadastro() {
		setTitle("Cadastro de Usu\u00E1rio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelForm = new JPanel();
		
		JPanel panelBot�es = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panelForm, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelBot�es, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelForm, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panelBot�es, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
		);
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(20);
		
		JLabel lblNome = new JLabel("Nome");
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(20);
		
		textFieldCPF = new JTextField();
		textFieldCPF.setColumns(20);
		
		textFieldSenha = new JTextField();
		textFieldSenha.setColumns(20);
		
		JLabel lblEmail = new JLabel("E-mail");
		
		JLabel lblCpf = new JLabel("CPF");
		
		JLabel lblSenha = new JLabel("Senha");
		GroupLayout gl_panelForm = new GroupLayout(panelForm);
		gl_panelForm.setHorizontalGroup(
			gl_panelForm.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelForm.createSequentialGroup()
					.addContainerGap(137, Short.MAX_VALUE)
					.addGroup(gl_panelForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNome)
						.addComponent(lblEmail)
						.addComponent(lblCpf)
						.addComponent(lblSenha))
					.addGroup(gl_panelForm.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelForm.createSequentialGroup()
							.addGap(42)
							.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelForm.createSequentialGroup()
							.addGap(42)
							.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelForm.createSequentialGroup()
							.addGap(42)
							.addComponent(textFieldCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelForm.createSequentialGroup()
							.addGap(42)
							.addComponent(textFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(129))
		);
		gl_panelForm.setVerticalGroup(
			gl_panelForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelForm.createSequentialGroup()
					.addGroup(gl_panelForm.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelForm.createSequentialGroup()
							.addGap(46)
							.addComponent(lblNome)
							.addGap(18))
						.addGroup(Alignment.TRAILING, gl_panelForm.createSequentialGroup()
							.addContainerGap(46, Short.MAX_VALUE)
							.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addGroup(gl_panelForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmail)
						.addGroup(gl_panelForm.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panelForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCpf)
						.addComponent(textFieldCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSenha)
						.addComponent(textFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panelForm.setLayout(gl_panelForm);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Usuario novoUsuario = new Usuario();
				
				//Cadastre o usuário
				
				//Nome não pode passar o limite definido no SGBD, 255 caracteres incluindo os espaços
				novoUsuario.setNome(textFieldNome.getText());
				//O e-mail também não deve ultrapassar os 255 definidos e deve atender os requisitos de validação de e-mail
				novoUsuario.setEmail(textFieldEmail.getText());
				//O CPF não deve ultrapassar os 11 caracteres e deve atender os requisitos de validação de cpf
				novoUsuario.setCpf(textFieldCPF.getText());
				//A senha deve ter no mínimo 8 caracteres e no máximo 16 caracteres
				novoUsuario.setSenha(textFieldSenha.getText());
				
				UsuarioBanco inserirUsuario = new UsuarioBanco();
				inserirUsuario.incluirUsuario(novoUsuario);
				showMessageDialog(null, "Usu�rio "+textFieldNome.getText()+" inserido com sucesso! ");
				
			}

			private void showMessageDialog(Object object, String string) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNome.setText("");
				textFieldEmail.setText("");
				textFieldCPF.setText("");
				textFieldSenha.setText("");
			}
		});
		GroupLayout gl_panelBot�es = new GroupLayout(panelBot�es);
		gl_panelBot�es.setHorizontalGroup(
			gl_panelBot�es.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBot�es.createSequentialGroup()
					.addGap(172)
					.addComponent(btnCadastrar)
					.addGap(18)
					.addComponent(btnLimpar)
					.addContainerGap(64, Short.MAX_VALUE))
		);
		gl_panelBot�es.setVerticalGroup(
			gl_panelBot�es.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBot�es.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelBot�es.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrar)
						.addComponent(btnLimpar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelBot�es.setLayout(gl_panelBot�es);
		contentPane.setLayout(gl_contentPane);
	}
}
