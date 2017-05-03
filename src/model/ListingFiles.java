package model;

import java.util.List;
import java.io.File;
import java.util.ArrayList;

/**
 * @author matsuiyoshikazu 入力ファイルをリストアップする（処理１）
 */
public class ListingFiles {

	public List<String> getView() {
		return this.view;
	}

	public List<String> getResource() {
		return this.resource;
	}

	private List<String> view = new ArrayList<String>();
	private List<String> resource = new ArrayList<String>();

	private String[] html = { ".htm", ".html", ".HTM", ".HTML" };
	private String[] assets = { ".jpg", ".png", ".gif", ".jpeg", ".js", ".JavaScript", ".javascript", ".script",
			".css" };

	public boolean isView(File file) {
		for (String h : html) {
			String fileName = file.getName();
			int idx = fileName.indexOf(h);
			if (idx > 0) {
				return true;
			}
		}
		return false;
	}

	public boolean isAssets(File file) {
		for (String h : assets) {
			String fileName = file.getName();
			int idx = fileName.indexOf(h);
			if (idx > 0) {
				return true;
			}
		}

		return false;
	}

	public boolean isAssetsDir(File file) {
		String resFileName = file.getName();
		for (String h : assets) {
		
			int idxDt = h.indexOf(".");
			String fileName = h.substring(idxDt + 1);
			if (resFileName.equals(fileName)) {
				return true;
			}
		}

		return false;
	}

	public String resoucePath = null;
	private boolean isFirstCall = true;;

	private List<String> modelNameList = new ArrayList<String>();

	public String rootName = "";

	public List<String> getModels() {
		return modelNameList;
	}

	public void ListFiles(File directory) {
		resoucePath = directory.getAbsolutePath();
		File[] files = directory.listFiles();
		String directoryName = directory.getName();
		// 再帰呼び出しの最初の呼び出しのみルートを取得プロパティに設定する
		if (isFirstCall) {
			this.rootName = directoryName;
			isFirstCall = false;
		}
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				// FILE
				if (this.isAssets(files[i])) {
					resource.add(files[i].getPath());
				} else if (this.isView(files[i])) {
					// list up MODeL
					String name = files[i].getName();
					String parentName = files[i].getParent();
					File parentDir = new File(parentName);
					parentName = parentDir.getName();
					if (this.rootName.equals(parentName)) {
						// index.html以外のhtmlを追加する
						int indexDtHtml = name.indexOf(".");
						String nameForModel = name.substring(0, indexDtHtml);
						this.modelNameList.add(nameForModel);

					} else {

					}
					view.add(files[i].getPath());
				}
			} else {
				// Directory
				String dirName = files[i].getName();
				if (!this.rootName.equals(dirName)) {
					// ディレクトリの名前を追加する
					// モデルの候補がリスト化される
					// サブディレクトリからのモデル抽出

					if (!this.isAssets(files[i])) {
						// JSなどでない場合
						if (isAssetsDir(files[i])) {

						} else {
							// may be MODEL
							this.modelNameList.add(dirName);

						}
					} else {
						System.out.println("isAssets name:" + dirName);

					}

				}
				this.ListFiles(files[i]);
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println("Y:\\");
		String path = "/Users/matsuiyoshikazu/Desktop/201702/larahelo/tp_beginner2_4/";
		File directory = new File(path);

		System.out.println(directory.getAbsolutePath());
		// 1 ViewとAssetsをリストアップする
		ListingFiles listfiles = new ListingFiles();
		listfiles.ListFiles(directory);
		for (String modelName : listfiles.getModels()) {
			System.out.println("model:" + modelName);

		}

		// //2
		// Copy2LaraTmp lara2 = new Copy2LaraTmp(path);
		//
		// lara2.copyView(listfiles.getView());
		//
		// lara2.copyResource(listfiles.getResource());
		// //3
		// ResourceFiler rFiler = new ResourceFiler();
		// rFiler.filing();
		// //4
		// ViewFiler vwFiler = new ViewFiler();
		// vwFiler.filing(lara2.getViewList());
		// //5
		// List<String> views = vwFiler.getViewList();
		// RouteCreater creater = new RouteCreater();
		// creater.create(views);
		// List<String> controllerNames = creater.getControllerNames();
		//
		// //6
		// CreateController createCtl = new CreateController();
		// createCtl.create(controllerNames);//
		// //7
		//
		// ResourceCoordinator cordinate = new ResourceCoordinator();
		// for(String view:views){
		// System.out.println("view:"+view);
		// }
		// cordinate.codinate(views);
		//
		//

		System.out.println("終了");
	}

}
