package zgh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Test1 {

	public static void main(String[] args) {
		try {
			Date date = new Date();
			SimpleDateFormat fmt = new SimpleDateFormat("MMM d, yyyy HH:mm:ss", Locale.ENGLISH);
			String str = fmt.format(date);
			
			Date date2 = fmt.parse(str);
			System.out.println(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
