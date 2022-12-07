public class SumOctal {
    public static void main(String[] args) {
	    int ans = 0;		
		for (int i = 0; i < args.length; i++) {
			for(int k = 0; k < args[i].length(); k++) {
				if(!(Character.isWhitespace(args[i].charAt(k)))){
					int start = k;
					while ((k < args[i].length()) && !(Character.isWhitespace(args[i].charAt(k)))) {
						k++;
					}
					if (args[i].charAt(k-1) == 'O' || args[i].charAt(k-1) == 'o') {
						ans += Integer.parseUnsignedInt(args[i].substring(start, k - 1), 8);
					} else {
						ans += Integer.parseInt(args[i].substring(start, k));
					}
				}						
			}
		}							
		System.out.println(ans);
	}
}
