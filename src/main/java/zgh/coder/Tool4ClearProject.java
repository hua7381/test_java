package zgh.coder;

import java.io.File;

/**
 * 清除工程目录
 * 可指定工程目录本身，或其上一级
 * @author zhangguihua
 *
 */
public class Tool4ClearProject {
	private static String[] delArr = {"target", ".classpath", ".project", ".settings"};
	private static int total = 0;
	private static int delNum = 0;
	
	public static void main(String[] args) {
		Tool4ClearProject obj = new Tool4ClearProject();
		obj.clearProjects("c:/zgh/code/github");
	}
	
	public void clearProjects(String path) {
		total = 0;
		delNum = 0;
		
		File dir = new File(path);
		
		clearProjectDicr(dir);
		for(File subDir : dir.listFiles()) {
			clearProjectDicr(subDir);
		}
		
		System.out.println("finished. total: "+total+", delNum: "+delNum);
	}
	
	private void clearProjectDicr(File dir) {
		if(dir.isDirectory()) {
			for(File subFile : dir.listFiles()) {
				checkOne(subFile);
			}
		}
	}

	private void checkOne(File sub) {
		for(String del : delArr) {
			if(sub.getName().equals(del)) {
				del(sub);
			}
		}
	}

	private void del(File file) {
		if(file.isDirectory() && file.listFiles().length > 0) {
			for(File sub : file.listFiles()) {
				del(sub);
			}
		}
		System.out.println("del: "+file.getPath());
		total += 1;
		try {
			boolean succes  = file.delete();
			if(succes) {
				delNum += 1;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
