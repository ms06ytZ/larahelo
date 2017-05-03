package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.EventController;

/**
 * @author 良和
 *
 */
public class MainMenu extends JFrame {
	
	private static MainMenu self = null;
	
	public static MainMenu getInstance(){
		if(self==null){
			self = new MainMenu();
		}
		
		return self;
	}

	private JLabel rootPath = new JLabel("コンテンツのパス");

private static JTextArea area = new JTextArea();

public JTextArea getArea (){
	
	return area;
}


	/**
	 * コンストラクタ
	 */
	public  MainMenu(){
		initiate();
	}

	/**
	 * 初期表示
	 */
	private void initiate(){
		//画面の大きさ
		this.setSize(new Dimension(800, 600));
		//コンテナ取得
		Container container = this.getContentPane();
		//コンポーネントの配置用パネル
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(null);
		container.add(centerPanel);
		//
		JLabel title = new JLabel("指定のコンテンツをLaravel化しちゃうぞ！");
		title.setBounds(new Rectangle(new Dimension(600, 20)));
		JLabel root = new JLabel("ルート");
		root.setBounds(0,0,100, 60);
		rootPath.setBounds(100,0,400, 60);

		JButton btnSelect = new JButton("えらぶ");
		JButton btn2Lara = new JButton("Laraか");//
		JButton btnConfirm = new JButton("かくにん");
		JButton btnConfirmEnd = new JButton("かくにんをおわる");
		
		JButton btnExport = new JButton("しゅつりょく");
		JButton btnClose = new JButton("おわる");

		btnSelect.setBounds(300,0,100, 40);
		btn2Lara.setBounds(0,80,400, 40);
		btnConfirm.setBounds(0,160,100, 40);
		btnConfirmEnd.setBounds(200,160,200,40);;
		btnExport.setBounds(150,240,100, 40);
		btnClose.setBounds(300,240,100, 40);


		btnSelect.setActionCommand("btnSelect");
		btn2Lara.setActionCommand("btn2Lara");
		btnConfirm.setActionCommand("btnConfirm");
		btnConfirmEnd.setActionCommand("btnConfirmEnd");
		btnExport.setActionCommand("btnExport");
		btnClose.setActionCommand("btnClose");


		centerPanel.add(title);
		centerPanel.add(root);
		centerPanel.add(rootPath);

		centerPanel.add(btnSelect);
		centerPanel.add(btn2Lara);
		centerPanel.add(btnConfirm);
		centerPanel.add(btnConfirmEnd);
		centerPanel.add(btnExport);
		centerPanel.add(btnClose);

		//controller(Listener)登録
		EventController controller = new EventController(this);
		btnSelect.addActionListener(controller);
		btn2Lara.addActionListener(controller);
		btnConfirm.addActionListener(controller);
		btnConfirmEnd.addActionListener(controller);
		btnExport.addActionListener(controller);
		btnClose.addActionListener(controller);

		//表する
		this.setVisible(true);

	}

	public String getRootText(){
		return this.rootPath.getText();

	}

	public void setRootText(String path){
		 this.rootPath.setText(path);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		new MainMenu();
	}

}
