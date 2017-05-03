package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import config.ConfigInfo;

/**
 * @author matsuiyoshikazu 
 * Route.php生成（処理５）
 */
public class RouteCreater {
	private List<String> controllerNames = new ArrayList<String>();
	
	public List<String> getControllerNames(){
		return controllerNames;
	}
	private boolean isRoot(File file){
		File parent = file.getParentFile();
		String viewRootPath = new ConfigInfo().distination_view;
		File viewRoot = new File(viewRootPath);
		String parentPath =parent.getAbsolutePath();
	
		if(viewRoot.getAbsolutePath().equals(parentPath)){
			System.out.println("root:");
			return true;
		}
		
		return false;
	}
	
	private String geParentName(File file){
		File parent = file.getParentFile();
		return parent.getName();
		
		//return null;
	}
	
	private String cRet = System.getProperty("line.separator");
	
	public void create(List<String> viewlist) {
		StringBuffer buf = new StringBuffer();
		buf.append("<?php");
		buf.append(cRet);
		for(String path :viewlist){
			File file = new File(path);
			System.out.println(path);
			if(isRoot(file)){
				String controllerName = "IndexController";
				controllerNames.add(controllerName);
				buf.append("Route::get('/', '"+controllerName+"@index');");
				buf.append(cRet);
			}else{
				String pathName = geParentName( file);
				System.out.println("path:"+pathName);
				String first = pathName.substring(0,1).toUpperCase();
				String next = pathName.substring(1,pathName.length());
				String controllerName = first+ next+"Controller";
				controllerNames.add(controllerName);
				String str = "Route::get('/"+pathName+"','"+controllerName+"@index');";
				buf.append(str);
				buf.append(cRet);
			}
		}
		buf.append(cRet);
		File route = new File("routes");
		if(!route.exists()){
			route.mkdirs();
		}
		File web = new File(route.getAbsolutePath()+ "/"+"web.php");
		FileWriter fw = null;
		try {
			fw = new FileWriter(web);

			fw.write(buf.toString());
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(buf);
	}
	public static void main(String[] arg){
		RouteCreater creater = new RouteCreater();
		List<String> views = new ArrayList<String>();
		views.add("/Users/matsuiyoshikazu/Desktop/201702/larahelo/app/resource/view/index.html");
		views.add("/Users/matsuiyoshikazu/Desktop/201702/larahelo/app/resource/view/item/index.html");
		
		creater.create(views);
		List<String> controllerNames = creater.getControllerNames();
		for(String name :controllerNames){
			System.out.println("controller:"+name);
		}
		CreateController createCtl = new CreateController();
		createCtl.create(controllerNames);
		
		
		
	}
}
