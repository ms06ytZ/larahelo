package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author matsuiyoshikazu
 * bladeないのjsやjpegなどのアドレス、aタグのリンク先を訂正する
 * TODO 機能が多すぎるので、分割したほうがいい
 * TODO aタグの処理にバグがある
 *
 */
public class ResourceCoordinator {

	private String[] assetsFiles = { ".js", ".css", ".jpeg", ".jpg", ".png", ".gif", ".html", ".php" };
	private String[] css = { ".css"};
	private String[] img = { ".jpeg",".jpg",".png",".gif"};
	

	private boolean isFile(String mayBeFileName) {

		for (String f : assetsFiles) {
			int indexDt = mayBeFileName.indexOf(f);
			if (indexDt != -1) {
				return true;
			}
		}
		return false;
	}
	private boolean isImg(String mayBeFileName) {

		for (String f : img) {
			int indexDt = mayBeFileName.indexOf(f);
			if (indexDt != -1) {
				return true;
			}
		}
		return false;
	}
	private boolean isCss(String mayBeFileName) {

		for (String f : css) {
			int indexDt = mayBeFileName.indexOf(f);
			if (indexDt != -1) {
				return true;
			}
		}
		return false;
	}

	private String getExt(String fileName) {
		int indexDt = fileName.lastIndexOf(".");
		return fileName.substring(indexDt + 1, fileName.length());

	}

	private List<String> tempList = new ArrayList<String>();
	
	public void codinate(List<String> views) {

		try {
			for (String path : views) {
				System.out.println(path);
				File view = new File(path);
				String viewFileName = view.getName();
				String viewName = viewFileName.substring(0, viewFileName.indexOf("."));
				String encoding = "UTF-8";

				Document doc = Jsoup.parse(view, encoding);

				// link tag
				Elements linktags = doc.getElementsByTag("link");

				for (Element linktag : linktags) {
					// linkタグのテキスト
					String src = linktag.attr("href");

					// File名取得
					int lidx = src.lastIndexOf("/");

					if (lidx == -1) {

						linktag.removeAttr("href");
						linktag.attr("href", ".." + "/" + getExt(src) + src);
					} else {

						String fileName = src.substring(src.lastIndexOf("/"), src.length());
						if (this.isFile(fileName)) {

							linktag.removeAttr("href");
							linktag.attr("href", ".." + "/" + getExt(fileName) + fileName);
						}
					}
				}

				// script tag
				Elements scripttags = doc.getElementsByTag("script");
				for (Element scripttag : scripttags) {
					// linkタグのテキスト
					String src = scripttag.attr("src");

					// File名取得
					int lidx = src.lastIndexOf("/");

					if (lidx == -1) {

						scripttag.removeAttr("src");
						scripttag.attr("src", ".." + "/" + getExt(src) + src);
					} else {

						String fileName = src.substring(src.lastIndexOf("/"), src.length());
						if (this.isFile(fileName)) {

							scripttag.removeAttr("src");
							scripttag.attr("src", ".." + "/" + getExt(fileName) + fileName);
						}
					}
				}

				// img tag
				Elements imgtags = doc.getElementsByTag("img");
				for (Element imgtag : imgtags) {
					// linkタグのテキスト
					String src = imgtag.attr("src");

					// File名取得
					int lidx = src.lastIndexOf("/");

					if (lidx == -1) {

						imgtag.removeAttr("src");
						imgtag.attr("src", ".." + "/image" + src);
					} else {

						String fileName = src.substring(src.lastIndexOf("/"), src.length());
						if (this.isFile(fileName)) {

							imgtag.removeAttr("src");
							imgtag.attr("src", ".." + "/image" + fileName);
						}
					}
				}

				// a tag
				Elements atags = doc.getElementsByTag("a");
				for (Element atag : atags) {
					// linkタグのテキスト
					String src = atag.attr("href");

					String startChar = src.substring(0, 1);
					if ("#".equals(startChar)) {
						continue;
					}
					// File名取得
					int lidx = src.lastIndexOf("/");

					if (lidx == -1) {
						int indxDt = src.lastIndexOf(".");
						String name = src.substring(0, indxDt);
						String after = src.substring(indxDt + 5);

						if (viewName.equals(name)) {
							atag.removeAttr("href");
							atag.attr("href", "" + "/" + after);
						} else {
							atag.removeAttr("href");
							atag.attr("href", "" + "/" + name + "/" + after);
						}

					} else {

						String fileName = src.substring(src.lastIndexOf("/"), src.length());
						
						if(this.isImg(fileName)){
							//TODO 動画
							atag.removeAttr("href");
							atag.attr("href", ".." + "/image" + fileName);
							
						}else{							
							if (viewName.equals(fileName)) {
								// 自分のパス名にする
								int nameIdx = src.indexOf(fileName);
								String after = src.substring(nameIdx + ".html".length() + fileName.length());
	
								atag.removeAttr("href");
								atag.attr("href", "." + "/" + after);
	
							} else {
	
								if (this.isFile(fileName)) {
	
									atag.removeAttr("href");
									atag.attr("href", ".." + "/" + getExt(fileName) + fileName);
								}
							}
						}
					}
				}
				// FileOut
				// System.out.println("toStr :"+doc.toString());
				File newFile = new File(view + "tmp");
				FileWriter fw = new FileWriter(newFile);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(doc.toString());
				bw.close();
				fw.close();

				// DeleteCopy TODO
				tempList.add(newFile.getAbsolutePath());
				view.delete();
				newFile.renameTo(view);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// 例外処理
		}
	}
	private List<String> tempTextList = new ArrayList<String>();
	public void codinateText(List<String> resurce) {

		try {
			for (String path : resurce) {
				System.out.println(path);
				File resourceFile = new File(path);
//				String viewFileName = resourceFile.getName();
			
				FileReader Reader = new FileReader(resourceFile);
				BufferedReader br = new BufferedReader(Reader);
				//StringBuffer sb = new StringBuffer();
				if(isCss( resourceFile.getName())) {
					System.out.println("is css");
					File newFile = new File(resourceFile + "tmp");
					FileWriter fw = new FileWriter(newFile);
					BufferedWriter bw = new BufferedWriter(fw);
					while(br.ready()){
						String readLine = br.readLine();
						
						 if(isImg( readLine)){
							 System.out.println("before:"+readLine);
							
								readLine = readLine.replaceAll("/img", "/image");
								 System.out.println("after:"+readLine);
						 }
						
						bw.write(readLine);
					}
					bw.close();
					fw.close();
					br.close();
					Reader.close();
					// DeleteCopy TODO
					tempTextList.add(newFile.getAbsolutePath());
					resourceFile.delete();
					newFile.renameTo(resourceFile);
					 System.out.println("cssfile:"+resourceFile);
				}
			

			}

		} catch (Exception e) {
			e.printStackTrace();
			// 例外処理
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO 自動生成されたメソッド・スタブ
		// System.out.println("Y:\\");
		String path = "/Users/matsuiyoshikazu/Desktop/201702/larahelo/tp_beginner2_4";
		File directory = new File(path);

		System.out.println(directory.getAbsolutePath());
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


		// 7

		ResourceCoordinator cordinate = new ResourceCoordinator();

		cordinate.codinate(views);
		cordinate.codinateText(resList);
		for (String view : resList) {
			System.out.println("view:" + view);
		}
		
		

		System.out.println("終了");
	}

}
