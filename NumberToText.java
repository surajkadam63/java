package com.java.string;

import java.util.Scanner;

//conver number to word in +/- 9999999999 only and 0 for exit with User readable formated number with ,
public class NumberToText {
	static String unique[] = { "", "ONE ", "TWO ", "THREE ", "FOUR ", "FIVE ", "SIX ", "SEVEN ", "EIGHT ", "NINE ",
			"TEN ", "ELEVEN ", "TWELVE ", "THIRTEEN ", "FORTEEN ", "FIFTEEN ", "SIXTEEN ", "SEVENTEEN ", "EIGHTEEN ",
			"NIGHTEEN ", "TWENTY " };// for unit and unique word from 1 to 20
	static String decimal[] = { "", "TEN ", "TWENTY ", "THIRTY ", "FORTY ", "FIFTY ", "SIXTY ", "SENVENTY ", "EIGHTY ",
			"NINTY " };// for decimal 2digits 10,20,...90
	static String places[] = { "", "", "HUNDRED ", "THOUSAND ", "THOUSAND ", "LAC ", "LAC ", "CRORE ", "CRORE " };// places

	public static String twoDigits(String dd) {
		// System.out.println("DD"+dd);
		String s = "";
		int n = Integer.parseInt(dd);
		if (n <= 20) {
			return unique[n];// 1-20 ranged value
		} else {
			s += decimal[n / 10];// decimal place  25=2,Twenty
			s += unique[n % 10];// unit place  5=>Five
		}
		return s;//Twenty Five
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			long number = 9890212282l;// 989,02,12,282=>999cr=10(9),99cr=9(8/7),lac(6/5),thousand(4/3),hundred2
			System.out.println("Enter Number to convert in text(0 for exit)::");
			number = in.nextLong();

			String result = "";
			if (number == 0) {
				System.out.println("Zero.\n Exited...");
				in.close();
				return;
			}
			String formated = "";
			if (number < 0) {
				result += "Minus ";
				number = number * (-1);// conver in positive
				formated = "-";
			}
			if (number > 9999999999l) {// 999,99,99,999
				System.err.println(
						"Sorry!!!, Entered Number is Out of Range for converting....\nPlease Enter Valid number in range(-9999999999 to 9999999999)only");
				continue;
			}
			//70 '0'=> 75-70 = '5'=>5
			String num = "" + number;
			char digits[] = num.toCharArray();// 1,00,00,00=>{'1','0','0',0,0,0,0}
			int len = digits.length;
			int l = len;
			// {1,6,7,8,9,0,8,9,0}//len=9=>0,8 ///999
			if (len == 10) { //100
				formated += digits[l - len] + "" + digits[l - len + 1] + "" + digits[l - len + 2] + ",";//123
				result += unique[(int)digits[l - len]] + places[2]
						+ twoDigits(digits[l - len + 1] + "" + digits[l - len + 2]) + places[len - 2] + ",";// due to
				len -= 3;
			}
			if (len == 9) {// cr
				formated += digits[l - len] + "" + digits[l - len + 1] + ",";
				result += twoDigits(digits[l - len] + "" + digits[l - len + 1]) + places[len - 1] + ",";
				len -= 2;
			}
			if (len == 8) {// cr 1cr
				formated += digits[l - len] + ",";
				result += unique[digits[l - len] - '0'] + places[len - 1] + ",";// digit is array of char stores
				len -= 1;
			}
			if (len == 7) {// lac
				formated += digits[l - len] + "" + digits[l - len + 1] + ",";
				result += twoDigits(digits[l - len] + "" + digits[l - len + 1]) + places[len - 1] + ",";
				len -= 2;
			}
			if (len == 6) {// lac
				formated += digits[l - len] + ",";
				result += unique[digits[l - len] - '0'] + places[len - 1] + ",";
				len -= 1;
			}
			if (len == 5) {// thousand
				formated += digits[l - len] + "" + digits[l - len + 1] + ",";
				result += twoDigits(digits[l - len] + "" + digits[l - len + 1]) + places[len - 1] + ",";
				len -= 2;
			}
			if (len == 4) {//// thousand
				formated += digits[l - len] + ",";
				result += unique[digits[l - len] - '0'] + places[len - 1] + ",";
				len -= 1;
			}
			if (len == 3) {// hundred
				formated += digits[l - len];
				if (digits[l - len] != '0')// if 0 then no need to put
					result += unique[digits[l - len] - '0'] + " " + places[len - 1]; // - '0' for convert char to int
				len -= 1;
			}
			if (len == 2) {// decimal last 2 digits or double digit
				formated += digits[l - len] + "" + digits[l - len + 1];
				result += twoDigits(digits[l - len] + "" + digits[l - len + 1]);
				len -= 2;
			}
			if (len == 1) {// single digit
				formated += digits[l - len];
				result += unique[digits[l - len] - '0'];
				len = 0;
			}
			System.out.println("Readable Formated Number: " + formated + "=> " + result + ".");
			System.out.println("++++++++++++++++++++++++++++++++++End Of Output+++++++++++++++++++++++++++++++++++++++++++");
		} // while
	}
}
