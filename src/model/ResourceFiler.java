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
 * @author matsuiyoshikazu
 * Resourceを整理する(処理３)
 */
public class ResourceFiler {
	private String resource_base = new ConfigInfo().distination_resource;
	private List<String> resourceList = new ArrayList<String>();
	public List<String> getResources(){
		return resourceList;
	}
	
	
	private boolean isJs(File file){
		String name = file.getName();
		int indexJ = name.indexOf(".js");
		if(indexJ>=0){
			return true;
		}
		return false;
	}
	private boolean isCss(File file){
		String name = file.getName();
		int indexCss = name.indexOf(".css");
		if(indexCss>=0){
			return true;
		}
		return false;
	}
	private String[] imageExt = {".jpg",".png",".gif",".jpeg"};
	private boolean isImage(File file){
		String name = file.getName();
		
		for(String image: imageExt){
			
			int indexImage = name.indexOf(image);
			
			if(indexImage>0){
				return true;
			}
			
		}
	
		return false;
	}
	public void filing(){
		File resourceBase = new File(resource_base);
		File[] resources = resourceBase.listFiles();
		
		for(File resource:resources){
			String name = resource.getName();
			if(isJs(resource)){
				//jsフォルダへコピー
				File jsDir = new File(resource_base+"js/");
				if(!jsDir.exists()){
					jsDir.mkdirs();
				}
				File js = new File(jsDir.getPath()+"/"+name);
				resourceList.add(js.getAbsolutePath());
				copyAndDel(resource,js);
			}
			if(isCss(resource)){
				//cssフォルダへコピー
				File cssDir = new File(resource_base+"css/");
				if(!cssDir.exists()){
					cssDir.mkdirs();
				}
				File css = new File(cssDir.getPath()+"/"+name);
				resourceList.add(css.getAbsolutePath());
				copyAndDel(resource,css);
			}
			if(isImage(resource)){
				//imageフォルダへコピー
				File imageDir = new File(resource_base+"image/");
				if(!imageDir.exists()){
					imageDir.mkdirs();
				}
				File img = new File(imageDir.getPath()+"/"+name);
				resourceList.add(img.getAbsolutePath());
				copyAndDel(resource,img);
			}
			
		}	
	}
	@SuppressWarnings("resource")
	private boolean copyAndDel(File res,File dist){

		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			inChannel = new FileInputStream(res).getChannel();
			outChannel = new FileOutputStream(dist).getChannel();
System.out.println("res:"+res.getPath());
System.out.println("dist:"+dist.getPath());
			inChannel.transferTo(0, inChannel.size(), outChannel);
			res.delete();
			
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
	public void main(String[] args){
		ResourceFiler filer = new ResourceFiler();
		filer.filing();
	}
	
}
