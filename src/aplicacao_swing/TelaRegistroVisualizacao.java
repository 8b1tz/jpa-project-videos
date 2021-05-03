package aplicacao_swing;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fachada.Fachada;

public class TelaRegistroVisualizacao extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmRegistroVisu;
	private JTextField link;
	private JTextField email;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaRegistroVisualizacao window = new TelaRegistroVisualizacao();
//					window.frmRegistroVisu.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//
//	}

	public TelaRegistroVisualizacao() {
		initialize();
	}

	private void initialize() {

		setTitle("Registro de Visualizacao");
		frmRegistroVisu = new JFrame();
		frmRegistroVisu.setIconImage(
				Toolkit.getDefaultToolkit().getImage(TelaRegistroVisualizacao.class.getResource("/imagem/icon.png")));
		frmRegistroVisu.setResizable(false);
		frmRegistroVisu.getContentPane().setLayout(null);
		frmRegistroVisu.setBounds(100, 100, 450, 300);

		link = new JTextField();
		link.setFont(new Font("Tahoma", Font.PLAIN, 16));
		link.setBounds(113, 79, 215, 27);
		link.setColumns(10);
		frmRegistroVisu.getContentPane().add(link);

		JLabel lblNewLabel = new JLabel("link");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNewLabel.setBounds(65, 85, 38, 13);
		frmRegistroVisu.getContentPane().add(lblNewLabel);

		JLabel saida = new JLabel("");
		saida.setFont(new Font("Tahoma", Font.PLAIN, 15));
		saida.setBounds(58, 218, 309, 35);
		frmRegistroVisu.getContentPane().add(saida);
		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 16));
		email.setColumns(10);
		email.setBounds(113, 127, 215, 27);
		frmRegistroVisu.getContentPane().add(email);

		JLabel text = new JLabel("email");
		text.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		text.setBounds(51, 128, 56, 22);
		frmRegistroVisu.getContentPane().add(text);

		/*
		 * nota = new JTextField(); nota.setFont(new Font("Tahoma", Font.PLAIN, 16));
		 * nota.setColumns(10); nota.setBounds(194, 175, 38, 22);
		 * frmRegistroVisu.getContentPane().add(nota);
		 */

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "0", "1", "2", "3", "4", "5" }));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(113, 177, 61, 30);
		frmRegistroVisu.getContentPane().add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("nota");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(51, 175, 62, 18);
		frmRegistroVisu.getContentPane().add(lblNewLabel_1);

		JButton buttonRegistrar = new JButton("Registrar");
		buttonRegistrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					if (email.getText() != null) {
						System.out.println(comboBox.getSelectedItem().toString());
						Fachada.registrarVisualizacao(link.getText(), email.getText(),
								Integer.parseInt(comboBox.getSelectedItem().toString()));
						saida.setText("Visualização cadastrada!");
					} else {
						Fachada.registrarVisualizacao(link.getText(),
								Integer.parseInt(comboBox.getSelectedItem().toString()));
					}
//https://www.youtube.com/watch?v=XXYlFuWEuKI&list=RDMMXXYlFuWEuKI&start_radio=1
				} catch (Exception e1) {
					saida.setText(e1.getMessage());
					e1.printStackTrace();
				}

			}
		});
		buttonRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonRegistrar.setBounds(242, 178, 93, 19);
		frmRegistroVisu.getContentPane().add(buttonRegistrar);

		JLabel lblNewLabel_2 = new JLabel("REGISTRO DE VISUALIZA\u00C7\u00C3O");
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(93, 10, 224, 29);
		frmRegistroVisu.getContentPane().add(lblNewLabel_2);

	}

	public JFrame getFrmRegistroVisu() {
		return frmRegistroVisu;
	}

	public void setFrmRegistroVisu(JFrame frmListagemVisu) {
		this.frmRegistroVisu = frmListagemVisu;
	}
}
