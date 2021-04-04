package zgh;

public class Test {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		for(int i=1; i<=19; i++) {
			sb.append(",G" + i);
		}
		System.out.println(sb.toString());

	}
}
