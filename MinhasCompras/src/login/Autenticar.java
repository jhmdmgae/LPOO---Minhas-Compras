package login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Autenticar extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Autenticar frame = new Autenticar();
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
	public Autenticar() {
		setTitle("Autenticação");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelForm = new JPanel();
		
		JPanel panelBotoes = new JPanel();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panelForm, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addComponent(panelBotoes, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelForm, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelBotoes, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
		);
		
		JButton btnEntrar = new JButton("Entrar");
		GroupLayout gl_panelBotoes = new GroupLayout(panelBotoes);
		gl_panelBotoes.setHorizontalGroup(
			gl_panelBotoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBotoes.createSequentialGroup()
					.addGap(181)
					.addComponent(btnEntrar)
					.addContainerGap(180, Short.MAX_VALUE))
		);
		gl_panelBotoes.setVerticalGroup(
			gl_panelBotoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBotoes.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnEntrar)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelBotoes.setLayout(gl_panelBotoes);
		
		JLabel lblNome = new JLabel("Nome");
		
		textField = new JTextField();
		textField.setColumns(20);
		
		JLabel lblSenha = new JLabel("Senha");
		
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		GroupLayout gl_panelForm = new GroupLayout(panelForm);
		gl_panelForm.setHorizontalGroup(
			gl_panelForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelForm.createSequentialGroup()
					.addGap(73)
					.addGroup(gl_panelForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNome)
						.addComponent(lblSenha))
					.addGap(26)
					.addGroup(gl_panelForm.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(129, Short.MAX_VALUE))
		);
		gl_panelForm.setVerticalGroup(
			gl_panelForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelForm.createSequentialGroup()
					.addGap(75)
					.addGroup(gl_panelForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNome)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelForm.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenha)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(70, Short.MAX_VALUE))
		);
		panelForm.setLayout(gl_panelForm);
		contentPane.setLayout(gl_contentPane);
	}
}
