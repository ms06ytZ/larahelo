package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Copy2Lara {
	String SEPARATOR = "/";

	public boolean copy(String res, String dist) {
		File resF = new File(res);
		File distF = new File(dist);
		if (copyDirectoryC(resF, distF)) {

			System.out.println("copyDirectoryC");
			
			return true;
		}

		return false;
	}

	public Boolean copyDirectoryC(File dirFrom, File dirTo) {
		File[] fromFile = dirFrom.listFiles();
		dirTo = new File(dirTo.getPath() + SEPARATOR + dirFrom.getName());
		dirTo.mkdir();
		if (fromFile != null) {
			System.out.println("fromFile != null");
			for (File f : fromFile) {
				System.out.println("for (File f : fromFile) {");
				if (f.isFile()) {
					if (!copyFile(f, dirTo)) {
						System.out.println("!copyFile(f, dirTo)");
						return false;
					}
				} else {
					if (!copyDirectoryC(f, dirTo)) {
						return false;
					}
				}
			}
		}
		System.out.println("copyDirectoryC() true");
		return true;
	}

	@SuppressWarnings("resource")
	public Boolean copyFile(File file, File dir) {
		File copyFile = new File(dir.getPath() + SEPARATOR + file.getName());
		FileChannel channelFrom = null;
		FileChannel channelTo = null;
		System.out.println("to copyFile"+copyFile.getAbsolutePath());
	
		if(".DS_Store".equals(file.getName())){
			return true;
		}
		try {
			copyFile.createNewFile();
			channelFrom = new FileInputStream(file).getChannel();
			channelTo = new FileOutputStream(copyFile).getChannel();
			System.out.println("from file");
			System.out.println("to copyFile");
			channelFrom.transferTo(0, channelFrom.size(), channelTo);
			System.out.println("transferTo@copyFile");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (channelFrom != null) {
					channelFrom.close();
				}
				if (channelTo != null) {
					channelTo.close();
				}
			} catch (IOException e) {
				return false;
			}
		}
	}
	
	public static void main(String[] arguments){
		Copy2Lara cop = new Copy2Lara();
	//TODO できない
		String resPath = "/Users/matsuiyoshikazu/Desktop/201702/larahelo/untitled";
		
		String distPath = "/Users/matsuiyoshikazu/Desktop/201702/larahelo/temp";
		System.out.println(resPath);
		System.out.println(distPath);
		
		if(cop.copy(resPath, distPath)){
			System.out.println("copy");
		}else{
			System.out.println("not copy");
		}
		System.out.println("end");
		File distF = new File(distPath);
		System.out.println(distF.getAbsolutePath());
		//app
		 resPath = "/Users/matsuiyoshikazu/Desktop/201702/larahelo/app";
		 distPath = "/Users/matsuiyoshikazu/Desktop/201702/larahelo/untitled/";
		if(cop.copy(resPath, distPath)){
			System.out.println("copy");
		}else{
			System.out.println("not copy");
		}
		 resPath = "/Users/matsuiyoshikazu/Desktop/201702/larahelo/public";
		 distPath = "/Users/matsuiyoshikazu/Desktop/201702/larahelo/untitled/";
		if(cop.copy(resPath, distPath)){
			System.out.println("copy");
		}else{
			System.out.println("not copy");
		}
		 resPath = "/Users/matsuiyoshikazu/Desktop/201702/larahelo/resources";
		 distPath = "/Users/matsuiyoshikazu/Desktop/201702/larahelo/untitled/";
		if(cop.copy(resPath, distPath)){
			System.out.println("copy");
		}else{
			System.out.println("not copy");
		}
		 resPath = "/Users/matsuiyoshikazu/Desktop/201702/larahelo/routes";
		 distPath = "/Users/matsuiyoshikazu/Desktop/201702/larahelo/untitled/";
		if(cop.copy(resPath, distPath)){
			System.out.println("copy");
		}else{
			System.out.println("not copy");
		}
	}
}
