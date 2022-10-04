/*public class SumOctal {
    public static void main(String[] args) {
	    int ans = 0;		
		for (int i = 0; i < args.length; i++) {
			boolean started = false;
			int radix = 10;
			int beginning = 0;
			int end = 0;
            for (int j = 0; j < args[i].length(); j++) {
                if (!(Character.isWhitespace(args[i].charAt(j))) && (j != args[i].length() - 1)) {
                    if (!started) {
						started = true;
						beginning = j;
                    } else {
						if (args[i].charAt(j) == 'O' || args[i].charAt(j) == 'o') {
							radix = 8;
						}
					}
				} else {
					if (started) {
						ans += Integer.parseInt(args[i].substring(beginning, end + 1), radix);
					}
					radix = 10;
					started = false;
				}
			}			
		}				
		System.out.println(ans);
	}
}
*/
public class SumOctal {
    public static void main(String[] args) {
	    int ans = 0;		
		for (int i = 0; i < args.length; i++) {
			for(int k = 0; k < args[i].length(); k++) {
				if(!(Character.isWhitespace(args[i].charAt(k)))){
					int start = k;
					while ((k<args[i].length()) && !(Character.isWhitespace(args[i].charAt(k)))) {
						k++;
					}
					if (args[i].charAt(k-1) == 'O' || args[i].charAt(k-1) == 'o') {
						ans += Integer.parseInt(args[i].substring(start, k-1), 8);
					} else {
						ans += Integer.parseInt(args[i].substring(start, k));
					}
				}						
			}
		}							
		System.out.println(ans);
	}
}
