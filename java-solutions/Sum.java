public class Sum {
    public static void main(String[] args) {
	    int ans = 0;		
		for (int i = 0; i < args.length; i++) {
            String number = "";
            for (int j = 0; j < args[i].length(); j++) {
                if (Character.isWhitespace(args[i].charAt(j))) {
                    if (number.length() >= 1) {
							ans += Integer.parseInt(number);
							number = "";
						
                    }
				} else {
					number += args[i].charAt(j);
					if (j == args[i].length() - 1) {
						ans += Integer.parseInt(number);
					}
				}
			}
		}
		System.out.println(ans);
	}
}