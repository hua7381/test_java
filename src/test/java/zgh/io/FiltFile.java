package zgh.io;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class FiltFile {
    
    @Test
    public void xxx() {
        File dir = new File("c:/test");
        for (File f : dir.listFiles(new MyFilter1())) {
            System.out.println(f.getName());
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(f.lastModified())));
        }
    }

    private class MyFilter1 implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().startsWith("aaa");
        }
    }
    
}
