package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Modeler {	
	private String cRet = System.getProperty("line.separator");
	
	
	public List<String> modeling(List<String> modelList){
		String modelBasePath = "app/BaseModel.php";
		File modelBase = new File(modelBasePath);
		System.out.println("base:"+modelBase.getAbsolutePath());
		FileReader fr;
		for(String modelName : modelList){
			//新しいモデル(temp)
			File newModel = new File("app/"+modelName+"tmp.php");

			try {
				fr = new FileReader(modelBase);
				BufferedReader br = new BufferedReader(fr);
				StringBuffer modelStr = new StringBuffer( "");
				String firstA = modelName.substring(0, 1);
				String afterA = modelName.substring(1);
				firstA = firstA.toUpperCase();
				String newClassName = firstA + afterA;
				File newModelRenameTo = new File ("app/"+newClassName+".php");;
				if(modelBase.exists()){
					System.out.println("modeling continue:");
					//Modelのリストを受けとり TODO 
					//BaseModelを読み込んで受け取ったモデル名で置き換え、ファイルもコピーしてリネームする
					while(br.ready()){
						String line =br.readLine();
						
						line = line.replaceAll("BaseModel", newClassName);
						System.out.println(" line :"+ line);
	
						modelStr.append(line);
						modelStr.append(cRet);
					}

					br.close();
					fr.close();
					FileWriter fw = new FileWriter(newModel);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(modelStr.toString());
					bw.flush();
					bw.close();
					fw.close();
					newModel.renameTo(newModelRenameTo);
					
					
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	
	public static void main(String[] args){
		String path = "/Users/matsuiyoshikazu/Desktop/201702/larahelo/tp_beginner2_4/";
		File directory = new File(path);
		ListingFiles listfiles = new ListingFiles();
		listfiles.ListFiles(directory);
		for (String modelName : listfiles.getModels()) {
			System.out.println("model:" + modelName);

		}
		Modeler modeler = new Modeler();
		modeler.modeling(listfiles.getModels());
		
	}
}
