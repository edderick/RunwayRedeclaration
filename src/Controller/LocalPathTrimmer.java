package Controller;

import java.io.File;

public class LocalPathTrimmer {

	public static String trimLocalPath(File f){
		String path = f.toString();
		String pwd = System.getProperty("user.dir");

		if (path.contains(pwd)){
			path = path.substring(pwd.length() + 1, path.length());
		}
		return path;
	}

}
