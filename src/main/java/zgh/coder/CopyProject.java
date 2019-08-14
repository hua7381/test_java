package zgh.coder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * CopyCode
 * 拷贝纯净的工程目录, 仅包含src与pom.xml
 */
public class CopyProject {

    public static void main(String[] args) {
        CopyProject obj = new CopyProject();

        obj.del(new File("c:/zgh/temp/copy"));

        // obj.copy("c:/zgh/code/github/_case/ms-case-base", "c:/zgh/temp/copy/_case/ms-case-base");
        // obj.copy("c:/zgh/code/github/_case/ms-caseDispatch", "c:/zgh/temp/copy/_case/ms-caseDispatch");
        // obj.copy("c:/zgh/code/github/_case/ms-caseNotify", "c:/zgh/temp/copy/_case/ms-caseNotify");
        // obj.copy("c:/zgh/code/github/_case/ms-caseQuery", "c:/zgh/temp/copy/_case/ms-caseQuery");
        // obj.copy("c:/zgh/code/github/_case/ms-caseReply", "c:/zgh/temp/copy/_case/ms-caseReply");
        
        // obj.copy("c:/zgh/code/github/ms-synergy", "c:/zgh/temp/copy/ms-synergy");
        // obj.copy("c:/zgh/code/github/ms-caseCreator", "c:/zgh/temp/copy/ms-caseCreator");
        
        // obj.copy("c:/zgh/code/github/ms-caseSchedule", "c:/zgh/temp/copy/ms-caseSchedule");

        
        obj.copy("c:/zgh/code/github/kc-login-api", "c:/zgh/temp/copy/kc-login-api");

        obj.copy("c:/zgh/code/github/kc-cjzd", "c:/zgh/temp/copy/kc-cjzd");

    }

    public void copy(String sourceDir, String targetDir) {

        (new File(targetDir)).mkdirs();

        try {
            copyDir(sourceDir+"/src", targetDir+"/src");
            copyFile(new File(sourceDir+"/pom.xml"), new File(targetDir+"/pom.xml"));
            System.out.println("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }

    // 复制文件夹
    public void copyDir(String sourceDir, String targetDir) throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] files = (new File(sourceDir)).listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                // 源文件
                File sourceFile = files[i];
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + files[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (files[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + files[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + files[i].getName();
                copyDir(dir1, dir2);
            }
        }
    }

    public void copyFile(File sourceFile, File targetFile) throws IOException {
        // 新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);

        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);

        // 缓冲数组
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }

        // 刷新此缓冲的输出流
        outBuff.flush();

        // 关闭流
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }

    private void del(File file) {
		if(file.isDirectory() && file.listFiles().length > 0) {
			for(File sub : file.listFiles()) {
				del(sub);
			}
		}
		try {
			file.delete();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}