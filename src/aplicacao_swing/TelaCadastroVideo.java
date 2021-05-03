package aplicacao_swing;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fachada.Fachada;

public class TelaCadastroVideo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmCadastroVideo;
	private JTextField link;
	private JTextField nome;
	private JTextField palavra;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroVideo window = new TelaCadastroVideo();
//					window.frmCadastroVideo.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//
//	}

	public TelaCadastroVideo() {
		initialize();
	}

	private void initialize() {
		setFrmCadastroVideo((new JFrame()));
		getFrmCadastroVideo().setBounds(100, 100, 450, 300);
		frmCadastroVideo.getContentPane().setLayout(null);

		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroVideo.class.getResource("/imagem/icon.png")));
		setTitle("Cadastrar v\u00EDdeo");
		frmCadastroVideo.getContentPane().setLayout(null);

		link = new JTextField();
		link.setFont(new Font("Tahoma", Font.PLAIN, 16));
		link.setBounds(116, 75, 231, 25);
		frmCadastroVideo.getContentPane().add(link);
		link.setColumns(10);

		JLabel text = new JLabel("link");
		text.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		text.setBounds(52, 75, 53, 32);
		frmCadastroVideo.getContentPane().add(text);

		JLabel text_1 = new JLabel("nome");
		text_1.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		text_1.setBounds(52, 117, 53, 32);
		frmCadastroVideo.getContentPane().add(text_1);

		nome = new JTextField();
		nome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nome.setColumns(10);
		nome.setBounds(116, 117, 231, 25);
		frmCadastroVideo.getContentPane().add(nome);

		JLabel text_1_1 = new JLabel("palavra");
		text_1_1.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		text_1_1.setBounds(35, 159, 70, 32);
		frmCadastroVideo.getContentPane().add(text_1_1);

		palavra = new JTextField();
		palavra.setFont(new Font("Tahoma", Font.PLAIN, 16));
		palavra.setColumns(10);
		palavra.setBounds(116, 152, 117, 25);
		frmCadastroVideo.getContentPane().add(palavra);

		JLabel lblNewLabel = new JLabel("CADASTRAR V\u00CDDEO");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 22));
		lblNewLabel.setBounds(129, 10, 272, 39);
		frmCadastroVideo.getContentPane().add(lblNewLabel);
		JLabel saida = new JLabel("");
		saida.setFont(new Font("Tahoma", Font.PLAIN, 16));
		saida.setBounds(21, 201, 212, 32);
		frmCadastroVideo.getContentPane().add(saida);
		JButton buttonCadastrar = new JButton("Cadastrar");
		buttonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if (palavra.getText() != null) {
						try {
							Fachada.cadastrarVideo(link.getText(), nome.getText(), palavra.getText());
							saida.setText("Sucesso no Cadastro !!");
						}catch (Exception e2) {
							saida.setText(e2.getMessage());
						}
						} else {
						try {
							Fachada.cadastrarVideo(link.getText(), nome.getText());
							saida.setText("Sucesso no Cadastro !!");
						}catch (Exception e3) {
							saida.setText(e3.getMessage());
						}
					}
				
			}
		});
		buttonCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonCadastrar.setBounds(267, 207, 111, 32);
		frmCadastroVideo.getContentPane().add(buttonCadastrar);

	}

	public JFrame getFrmCadastroVideo() {
		return frmCadastroVideo;
	}

	public void setFrmCadastroVideo(JFrame frmListagemVisu) {
		this.frmCadastroVideo = frmListagemVisu;
		frmCadastroVideo.setResizable(false);
		frmCadastroVideo.setIconImage(
				Toolkit.getDefaultToolkit().getImage(TelaCadastroVideo.class.getResource("/imagem/icon.png")));
		frmCadastroVideo.setTitle("Criacao");
	}
}
