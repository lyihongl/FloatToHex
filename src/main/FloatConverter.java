package main;

public class FloatConverter {

	public static int calculateSign(double input) {
		if (input < 0)
			return 1;
		else
			return 0;
	}

	public static int calculateExponent(double input) {
		if(input<0)
			input*=-1;
		int expo = (int) (Math.log(input) / Math.log(2));

		while (Math.pow(2, expo) > input) {
			expo--;
		}

		return expo + 127;
	}

	public static String intToBinary(int input, int length) {

		String output = Integer.toBinaryString(input);
		String append = "";
		if (output.length() < length) {
			int i = length - output.length();
			for (int j = 0; j < i; j++) {
				append += "0";
			}
		}
		return append + output;

	}

	public static int getFirstNum(double input) {
		return (int) (input - input % 1);
	}

	public static String fractionalDecimalToBinary(double input) {
		if(input<0)
			input*=-1;
		String output = "";

		double num = input % 1;

		while (output.length() < 23) {

			num *= 2;
			output += getFirstNum(num);
			if (num >= 1)
				num--;

		}

		return output;
	}

	public static String binary4ToHex(String binary4) {

		int number = Integer.parseInt(binary4);
		String output = "";
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			sum += number % 2 * Math.pow(2, i);
			number /= 10;
		}
		if (sum <= 9) {
			output += sum;
		} else {
			switch (sum) {
			case 10:
				output += "a";
				break;
			case 11:
				output += "b";
				break;
			case 12:
				output += "c";
				break;
			case 13:
				output += "d";
				break;
			case 14:
				output += "e";
				break;
			case 15:
				output += "f";
				break;
			default:
				break;

			}
		}
		return output;

	}

	public static String binary32ToHex(String binary32) {
		String output = "";
		for (int i = 0; i < 8; i++) {
			output += binary4ToHex(binary32.substring(i * 4, i * 4 + 4));
		}

		return output;
	}

	public static int hexToInt(String hex) {

		int output = 0;
		String reverse = "";

		for (int i = hex.length() - 1; i >= 0; i--) {
			reverse = reverse + hex.charAt(i);
		}
		for (int i = hex.length() - 1; i > 0; i--) {
			if ((reverse.charAt(i) + "").matches("\\d")) {
				output += Integer.parseInt((reverse.charAt(i) + "")) * Math.pow(16, i);
			} else if ((reverse.charAt(i) + "").matches("\\w")) {
				switch (reverse.charAt(i)) {
				case 'a':
					output += 10 * Math.pow(16, i);
					break;
				case 'b':
					output += 11 * Math.pow(16, i);
					break;
				case 'c':
					output += 12 * Math.pow(16, i);
					break;
				case 'd':
					output += 13 * Math.pow(16, i);
					break;
				case 'e':
					output += 14 * Math.pow(16, i);
					break;
				case 'f':
					output += 15 * Math.pow(16, i);
					break;
				default:
					break;

				}
			}
		}

		return output;
	}
	
}
