package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import config.ConfigInfo;

/**
 * @author matsuiyoshikazu ファイルをコピーする（処理２）
 */
public class Copy2LaraTmp {

	private String resourcePath = null;

	public Copy2LaraTmp(String path) {
		this.resourcePath = path;
	}

	private List<String> viewList = new ArrayList<String>();

	private void createParent() {
		File base = new File(new ConfigInfo().distination_base);
		if (!base.exists()) {
			if (base.mkdirs()) {
				File view = new File(new ConfigInfo().distination_view);
				if (!view.exists()) {
					if (view.mkdirs()) {

					}
				}
				File resource = new File(new ConfigInfo().distination_resource);
				if (!resource.exists()) {
					if (resource.mkdirs()) {

					}
				}

			}
		}
	}

	private void createParent(File file) {
		if (!file.exists()) {

			File paret = file.getParentFile();
			if (paret.mkdirs()) {

			}
		}
	}

	public Copy2LaraTmp() {
		initiate();
	}

	private void initiate() {
		File app = new File("app");
		if (!app.exists()) {
			app.mkdirs();
		}
		File resoure = new File("resources");
		if (!resoure.exists()) {
			resoure.mkdirs();
		}
		File resoureVew = new File("resources/views");
		if (!resoureVew.exists()) {
			resoureVew.mkdirs();
		}
		File pblic = new File("public");
		if (!pblic.exists()) {
			pblic.mkdirs();
		}
	}

	public List<String> getViewList() {
		return this.viewList;
	}

	//

	@SuppressWarnings("resource")
	public void copyView(List<String> subject) {

		File resourveBase = new File(this.resourcePath);

		for (String path : subject) {
			File res = new File(path);
			String name = res.getName();
			// 親がディレクトリの場合
			// File parent = file.getParentFile();
			File parent = res.getParentFile();
			File dist = new File(new ConfigInfo().distination_view + name);

			FileChannel inChannel = null;
			FileChannel outChannel = null;

			if (parent.getName().equals(resourveBase.getName())) {

			} else {
				// 親ディレクトリを作成した先
				dist = new File(new ConfigInfo().distination_view + parent.getName() + "/" + name);

			}

			try {
				inChannel = new FileInputStream(res).getChannel();
				if (!dist.exists()) {
					createParent();
					createParent(dist);
					dist.createNewFile();
				}
				outChannel = new FileOutputStream(dist).getChannel();

				inChannel.transferTo(0, inChannel.size(), outChannel);
				viewList.add(dist.getAbsolutePath());

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (inChannel != null) {
						inChannel.close();
					}
					if (outChannel != null) {
						outChannel.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	@SuppressWarnings("resource")
	public void copyResource(List<String> subject) {
		for (String path : subject) {

			File res = new File(path);
			String name = res.getName();
			File dist = new File(new ConfigInfo().distination_resource + name);
			FileChannel inChannel = null;
			FileChannel outChannel = null;
			try {
				inChannel = new FileInputStream(res).getChannel();
				outChannel = new FileOutputStream(dist).getChannel();
				inChannel.transferTo(0, inChannel.size(), outChannel);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (inChannel != null)
						inChannel.close();
					if (outChannel != null)
						outChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("開始：");
		String path = "/Users/matsuiyoshikazu/Desktop/201702/larahelo/onassis/Theme";
		File directory = new File(path);

		System.out.println(directory.getAbsolutePath());
		// 1 ViewとAssetsをリストアップする
		ListingFiles listfiles = new ListingFiles();
		listfiles.ListFiles(directory);
		// 2
		Copy2LaraTmp lara2 = new Copy2LaraTmp(path);
		lara2.copyView(listfiles.getView());
		lara2.copyResource(listfiles.getResource());

		for (String name : listfiles.getResource()) {
			System.out.println("list in assets:" + name);
		}
		for (String name : lara2.getViewList()) {
			System.out.println("list in view:" + name);
		}

		System.out.println("終了");
	}

}
