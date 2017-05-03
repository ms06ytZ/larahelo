package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CreateController {

	private String cRet = System.getProperty("line.separator");

	private boolean isCrudController= true;
	
	public void create(List<String> controllerNames) {

		for (String ctrName : controllerNames) {
			StringBuffer buf = new StringBuffer();
			buf.append("<?php");
			buf.append(cRet);
			buf.append("namespace App\\Http\\Controllers;");
			buf.append(cRet);
			buf.append(cRet);
			buf.append("class ");
			buf.append(ctrName);
			buf.append(" extends Controller");

			buf.append(cRet);
			buf.append("{");
			buf.append(cRet);
			buf.append("  public function index(){");
			buf.append(cRet);
			buf.append(cRet);
			// TODO フォルダ名を抽出してV家wの名前の前に
			int indexCtl = ctrName.indexOf("Controller");
			String tmpName = ctrName.substring(0, indexCtl);
			tmpName = tmpName.toLowerCase();
			if ("index".equals(tmpName)) {

				buf.append("   return view('index');");
			} else {

				buf.append("   return view('" + tmpName + ".index');");
			}

			buf.append(cRet);

			buf.append("     }");
			buf.append(cRet);
			// TODO CrudController以外はelseを実装すればいい
			if (isCrudController) {
				//Create
				buf.append("  public function create(){");
				buf.append(cRet);
				buf.append("   return view('" + tmpName + ".create');");
				buf.append(cRet);
				buf.append("     }");
				buf.append(cRet);
				//Read(Detail)
				buf.append("  public function detail(){");
				buf.append(cRet);
				buf.append("$id = $request->input('id');");
				buf.append(cRet);
				buf.append("$"+tmpName+" = "+tmpName+"::find($id);");
				buf.append(cRet);
				buf.append("   return view('" + tmpName
						+ ".detail')->with('"+tmpName+"',$"+tmpName+");");
				buf.append(cRet);
				buf.append("     }");
				buf.append(cRet);
				//Update
				buf.append("  public function update(){");
				buf.append(cRet);
				buf.append("   return view('" + tmpName + ".update');");
				buf.append(cRet);
				buf.append("     }");
				buf.append(cRet);
				//Delete
				buf.append("  public function delete(){");
				buf.append(cRet);
				buf.append("   return view('" + tmpName + ".delete');");
				buf.append(cRet);
				buf.append("     }");
				buf.append(cRet);
			}
			buf.append("}");
			buf.append(cRet);

			buf.append(cRet);
			File route = new File("app/Http/Controllers");
			if (!route.exists()) {
				route.mkdirs();
			}
			File web = new File(route.getAbsolutePath() + "/" + ctrName + ".php");
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

	}

}
