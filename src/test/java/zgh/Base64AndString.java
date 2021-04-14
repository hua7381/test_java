package zgh;

import java.util.Base64;

import org.junit.Test;

public class Base64AndString {
    
    @Test
    public void xxx() {
        String str = "abc你好阿斯蒂芬fasdfa";
        String base64Str = new String(Base64.getEncoder().encode(str.getBytes()));
        String str2 = new String(Base64.getDecoder().decode(base64Str));
        System.out.println(str);
        System.out.println(base64Str.length());
        System.out.println(str2);
    }

}
