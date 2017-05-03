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
 * @author matsuiyoshikazu Viewを整理する（処理４）
 */
public class ViewFiler {
	private List<String> viewList = new ArrayList<String>();
	private List<String> viewError = new ArrayList<String>();

	public List<String> getViewList() {
		return viewList;
	}

	public List<String> getViewErrorList() {
		return viewError;
	}

	private String[] htmls = { "htm", "html" };

	private boolean isHtml(File file) {
		String fileName = file.getName();
		for (String html : htmls) {
			int idx = fileName.indexOf(html);
			if (idx >= 0) {
				return true;
			}

		}
		return false;
	}

	public void filing(List<String> views) {
		String viewBase = new ConfigInfo().distination_view;
		for (String viewPath : views) {
			File view = new File(viewPath);
			// TODO HTMLの場合
			String fileName = view.getName();
			if (this.isHtml(view)) {

				if ("index.html".equals(fileName)) {
					System.out.println("equalsequals:");
					File parent = view.getParentFile();
					String parentPath = parent.getAbsolutePath();
					System.out.println("parentPath:" + parentPath);
					System.out.println("viewBase:" + viewBase);
					File base = new File(viewBase);
					String parentName = parent.getName();
					String baseName = base.getName();
					
					if (baseName.equals(parentName)) {
						// rootの
						File dist = new File(viewBase + "/index.blade.php");
						if (copy(view, dist)) {
							// view.delete();
							viewList.add(dist.getAbsolutePath());
							view.delete();
							
							continue;
						}
					} else {
						// フォルダ以下の
						File distParent = new File(viewBase + parentName);
						System.out.println("distParent:" + distParent);
						distParent.mkdirs();

						File dist = new File(viewBase + parentName + "/index.blade.php");
						if (copy(view, dist)) {
							
							viewList.add(dist.getAbsolutePath());
							 view.delete();
							continue;
						}
					}

				} else {
					// TODO index.blade.phpにして名前をディレクトリに
					int idxExt = fileName.indexOf(".");
					if (idxExt == -1) {
						continue;
					}
					String nameTrim = fileName.substring(0, idxExt);
					System.out.println("name:" + nameTrim);
					String create = viewBase + "/" + nameTrim;
					File createDir = new File(create);
					createDir.mkdirs();
					File dist = new File(createDir + "/index.blade.php");
					if (copy(view, dist)) {

						viewList.add(dist.getAbsolutePath());
						 view.delete();
					} else {
						System.out.println("not copy:" + view.getName());
						viewError.add(view.getAbsolutePath());
					}

				}

			}

		}

	}

	@SuppressWarnings("resource")
	private boolean copy(File res, File dist) {

		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			inChannel = new FileInputStream(res).getChannel();
			outChannel = new FileOutputStream(dist).getChannel();
			System.out.println("res:" + res.getPath());
			;
			System.out.println("dist:" + dist.getPath());
			;
			inChannel.transferTo(0, inChannel.size(), outChannel);
			return true;
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

		return false;
	}

	public static void main(String[] arguments) {
		ListingFiles listfiles = new ListingFiles();
		String path = "/Users/matsuiyoshikazu/Desktop/201702/larahelo/onassis/Theme";
		File directory = new File(path);
		listfiles.ListFiles(directory);
		List<String> list = listfiles.getView();
		for (String view : list) {
			System.out.println(view);
		}
		System.out.println("listing end");
		Copy2LaraTmp copyLaratmp = new Copy2LaraTmp(path);
		copyLaratmp.copyView(listfiles.getView());

		ViewFiler filer = new ViewFiler();

		// File file = new File(Viewbase)
		filer.filing(copyLaratmp.getViewList());
		// filer.filing2();
		list = filer.getViewList();
		for (String view : list) {
			System.out.println(view);
		}
		copyLaratmp.copyResource(listfiles.getResource());
		ResourceFiler rFiler =  new ResourceFiler();

		rFiler.filing();
		
	}
}
