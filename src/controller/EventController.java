package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.Copy2LaraTmp;
import model.CreateController;
import model.ListingFiles;
import model.ResourceCoordinator;
import model.ResourceFiler;
import model.RouteCreater;
import model.ViewFiler;
import view.MainMenu;

public class EventController implements ActionListener {
	//private String cRet = System.getProperty("line.separator");

	private boolean isActivate(){
//		
//		//evaluate activate 
//		File file = new File("test.txt");
//		System.out.println("activate file" +file.getAbsolutePath());
//		if(file.exists()){
//			
//			return true;
//		}else{
//			int ret =JOptionPane.showConfirmDialog(this.controllView, "アクティベーションのファイルが認識できなかったよー");
//			if(ret==JOptionPane.CANCEL_OPTION){
//				int ret2 =JOptionPane.showConfirmDialog(this.controllView, "ビジネスで利用されますか？");
//				if(ret2==JOptionPane.CANCEL_OPTION){
//					JOptionPane.showConfirmDialog(this.controllView, "ビジネスでなくてもアクティベートしてください。"+cRet+"ma21yosh1ka2u@gmail.comまで問い合わせ先を明記の上、ご連絡ください。"+cRet+"お支払い先を返信、いたしますので、ライセンス料をお支払いいただきたく存じます。");
//				}else{
//					JOptionPane.showConfirmDialog(this.controllView, "ma21yosh1ka2u@gmail.comまで問い合わせ先を明記の上、ご連絡ください。"+cRet+"お支払い先を返信、いたしますので、ライセンス料をお支払いいただきたく存じます。");
//						
//				}
//			}else{
//				JOptionPane.showConfirmDialog(this.controllView, "10日間だけ試用期間を延長したよ〜");
//							return true;
//			}
//		}
		return true;
	}

	private MainMenu controllView = null;

	public EventController(MainMenu view) {
		controllView = view;
	}

	private String cRet = System.getProperty("line.separator");

	/***
	 * 命令の受け取りとロジックの選択
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//
		
		String command = e.getActionCommand();
		if ("btnSelect".equals(command)) {
			

			if(! isActivate()){
				JOptionPane.showConfirmDialog(this.controllView,"ライセンスエラー");
				System.exit(-2);
				return;
			}
			JFileChooser filechooser = new JFileChooser();
			filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int selected = filechooser.showOpenDialog(controllView);
			if (selected == JFileChooser.APPROVE_OPTION) {
				System.out.println("selected");
				System.out.println(filechooser.getName());
				System.out.println(filechooser.getSelectedFile());
				File selectedFile = filechooser.getSelectedFile();
				if (selectedFile.isFile()) {
					JOptionPane.showMessageDialog(this.controllView, "ディレクトリをえらんでね");
				} else {
					controllView.setRootText(selectedFile.getAbsolutePath());
					System.out.println("set root :" + selectedFile.getAbsolutePath());
				}
			}
		}

		if ("btnConfirmEnd".equals(command)) {
			// TODO プロセスを終了させる
			this.process.destroy();

			StringBuffer sb = new StringBuffer();
			sb.append("すいませんプロセスを終了させるコマンドがうまく動かないので、以下のコマンドでPIDを調べて、");
			sb.append(this.cRet);
			sb.append("killしてください。");
			sb.append(this.cRet);
			sb.append("ps alx | grep artisan");

			JOptionPane.showConfirmDialog(this.controllView, sb);

		}
		if ("btn2Lara".equals(command)) {

			// System.out.println("Y:\\");
			String path = controllView.getRootText();
			File directory = new File(path);
			//0.untitledを解答する
			try {
				System.out.println("unzip");
				File untitledZip = new File("untitled.zip");
				File untitledCommand = new File("unzip_untitled.sh");
				if(untitledZip.exists() && untitledCommand.exists() ){
					String cline = "sh ./unzip_untitled.sh";// 
					System.out.println("unzip execute");
					Runtime.getRuntime().exec(cline);
					
				}
				
				System.out.println("unzip end");
			} catch (IOException e1) {

				e1.printStackTrace();
			}
			
			// 1 ViewとAssetsをリストアップする
			ListingFiles listfiles = new ListingFiles();
			listfiles.ListFiles(directory);
			// 2
			Copy2LaraTmp lara2 = new Copy2LaraTmp(path);

			lara2.copyView(listfiles.getView());
			lara2.copyResource(listfiles.getResource());
			// 3
			ResourceFiler rFiler = new ResourceFiler();
			rFiler.filing();
			
			List<String> resList = rFiler.getResources();
			// 4
			ViewFiler vwFiler = new ViewFiler();
			vwFiler.filing(lara2.getViewList());
			// 5
			List<String> views = vwFiler.getViewList();
			RouteCreater creater = new RouteCreater();
			creater.create(views);
			List<String> controllerNames = creater.getControllerNames();

			// 6
			CreateController createCtl = new CreateController();
			createCtl.create(controllerNames);//
			// 7

			ResourceCoordinator cordinate = new ResourceCoordinator();

			cordinate.codinate(views);
			cordinate.codinateText(resList);
			// 8 Copy 2 Lara（コピーのコード面倒だったのでシェルにした）

			try {
				String cline = "sh ./cp_app.sh";// "test.bat"はCドライブ直下に配置済みであること

				Runtime.getRuntime().exec(cline);

			} catch (IOException e1) {

				e1.printStackTrace();
			}
			
			//9.untitledを更新する
			try {
				System.out.println("composer update");
				File untitled = new File("untitled");
				File updateCommand = new File("composer_update.sh");
				if(untitled.exists() && updateCommand.exists() ){
					String cline = "sh ./composer_update.sh";// 
					System.out.println("update execute");
					Runtime.getRuntime().exec(cline);
					
				}
				
				System.out.println("update end");
			} catch (IOException e1) {

				e1.printStackTrace();
			}
			

			System.out.println("終了");

		}
		if ("btnConfirm".equals(command)) {
			// Process process = r.exec(new String[]{ "java", "-version" });
			try {
				// String[] cmd = {"java","-version",""};
				String untitledPath = "../larahelo/untitled";
				File unTitledFile = new File(untitledPath);
				String[] cmd = { "php", unTitledFile.getAbsolutePath() + "/artisan", "serve" };
				// ExecProgram execPg = new ExecProgram();

				File log = new File("./tmp.log");
				proc = new ProcessBuilder(cmd); // コマンド実行用のプロセスを準備する。
				proc.redirectErrorStream(true); // 標準出力と標準エラー出力の出力先を同じにする。
				proc.redirectOutput(log); // 標準出力と標準エラー出力の出力先ファイルを設定。

				process = proc.start();

			} catch (IOException e1) {

				e1.printStackTrace();
			}

			StringBuffer sb = new StringBuffer();
			sb.append("php artisan serve起動したよ");
			sb.append(this.cRet);
			sb.append("ブラウザを起動して以下のURLにアクセスしてね。");
			sb.append(this.cRet);
			sb.append("http://localhost:8000/");

			JOptionPane.showConfirmDialog(this.controllView, sb);

		}

		if ("btnClose".equals(command)) {
			this.controllView.setVisible(false);
			System.exit(0);
		}

		if ("btnExport".equals(command)) {
			// todo

			String ret = JOptionPane.showInputDialog("プロジェクト名を入力してください");
			JOptionPane.showMessageDialog(this.controllView,
					"すいません。コピーがうまく動かないので、laraheloフォルダ内のuntitiledプロジェクトを好きなところにコピーして、名前を変えてください。");
			// TODO 下記Eclipseではうまく動くのに、シェルからだと動かないエラーになら

			// try {
			// String ret = JOptionPane.showInputDialog("プロジェクト名を入力してください");
			// String cmd = "cp -a ./untitled "+ret;
			// File cmdF = new File("cp_pj.sh");
			// FileWriter fw = new FileWriter(cmdF);
			// BufferedWriter bw = new BufferedWriter(fw);
			// bw.write(cmd);
			// bw.flush();
			// bw.close();
			// fw.close();
			// String cline = "sh ./cp_pj.sh";//"test.bat"はCドライブ直下に配置済みであること
			// Runtime.getRuntime().exec(cline);
			// //Runtime.getRuntime().exec(cmd);
			// System.out.println("btnExport projectName:"+ret);
			//
			// System.out.println("btnExport end");
			// } catch (IOException e1) {
			//
			// e1.printStackTrace();
			// }
		}

	}

	private ProcessBuilder proc = null;
	private Process process = null;

}
