package aplicacao_swing;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fachada.Fachada;

public class TelaPrincipal {

	private JFrame frmDb;
	private final JLabel lblNewLabel = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmDb.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frmDb = new JFrame();
		frmDb.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				frmDb.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

				Fachada.inicializar();

				frmDb.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
				JOptionPane.showMessageDialog(frmDb, "banco fechado !");
			}
		});
		frmDb.setResizable(false);
		frmDb.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/imagem/icon.png")));
		frmDb.setTitle("BdTube");
		frmDb.setBounds(100, 100, 450, 300);
		frmDb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmDb.setJMenuBar(menuBar);

		JMenu mnNewMenu_1_1 = new JMenu("Cadastro");
		menuBar.add(mnNewMenu_1_1);

		JMenuItem mntmCadastroDeVdeo = new JMenuItem("cadastro de video");
		mntmCadastroDeVdeo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				TelaCadastroVideo window = new TelaCadastroVideo();
				window.getFrmCadastroVideo().setVisible(true);
			}
		});
		mnNewMenu_1_1.add(mntmCadastroDeVdeo);

		JMenu mnNewMenu_1_2 = new JMenu("Registro ");
		menuBar.add(mnNewMenu_1_2);

		JMenuItem mntmRegistroDeVisualizao = new JMenuItem("registro de visualizacoes");
		mntmRegistroDeVisualizao.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				TelaRegistroVisualizacao window = new TelaRegistroVisualizacao();
				window.getFrmRegistroVisu().setVisible(true);
			}
		});
		mnNewMenu_1_2.add(mntmRegistroDeVisualizao);

		JMenu mnNewMenu_1_2_1 = new JMenu("Listagem");
		menuBar.add(mnNewMenu_1_2_1);

		JMenuItem mntmListagemDeVisualizaes = new JMenuItem("listagem de visualizacoes");
		mntmListagemDeVisualizaes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				TelaListagemVisualizacao window = new TelaListagemVisualizacao();
				window.getFrmListagemVisu().setVisible(true);
			}
		});
		mnNewMenu_1_2_1.add(mntmListagemDeVisualizaes);

		JMenuItem mntmListagemDeVdeos = new JMenuItem("listagem de videos");
		mntmListagemDeVdeos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				TelaListagemVideo window = new TelaListagemVideo();
				window.getFrmListagemVideo().setVisible(true);
			}
		});
		mnNewMenu_1_2_1.add(mntmListagemDeVdeos);

		frmDb.getContentPane().setLayout(null);
		lblNewLabel.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagem/db.png")));
		lblNewLabel.setBounds(0, 0, 436, 241);
		frmDb.getContentPane().add(lblNewLabel);
	}
}