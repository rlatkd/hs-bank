package utils;

//캡챠 숫자 이미지 enum
enum CaptchaNumbers {
    ZERO("  ___  \n / _ \\ \n| | | |\n| |_| |\n \\___/ "),
    ONE("  __ \n /_ |\n  | |\n  | |\n  |_|\n"),
    TWO("  ___  \n |__ \\ \n    ) |\n   / / \n  / /_ \n |____|\n"),
    THREE("  ____ \n |___ \\\n   __) |\n  |__ <\n  ___) |\n |____/\n"),
    FOUR(" _  _   \n| || |  \n| || |_ \n|__   _|\n   | |\n   |_|   \n"),
    FIVE(" ____  \n| ___| \n| |__\n|___ \\ \n ___) |\n|____/ \n"),
    SIX("   __   \n  / /   \n / /_   \n| '_ \\ \n| (_) |\n \\___/ "),
    SEVEN("  ______ \n |___  / \n    / /  \n   / /   \n  / /  \n /_/"),
    EIGHT("  ___  \n / _ \\\n| (_) |\n \\ _ / \n / _ \\ \n| (_) |\n \\___/ \n"),
    NINE("   ___  \n  / _ \\ \n | (_) |\n  \\__  |\n    / / \n   /_/  ");
	
    String numberImage;

    private CaptchaNumbers(String numberImage) {
        this.numberImage = numberImage;
    }

    //각 숫자들 그림과 매핑하기 위한 로직
    static CaptchaNumbers matchNumber(String inputNumber) {
        switch (inputNumber) {
            case "0":
                return ZERO;
            case "1":
                return ONE;
            case "2":
                return TWO;
            case "3":
                return THREE;
            case "4":
                return FOUR;
            case "5":
                return FIVE;
            case "6":
                return SIX;
            case "7":
                return SEVEN;
            case "8":
                return EIGHT;
            case "9":
                return NINE;
            default:
                return null;
        }
    }
}

public class CaptchaAuthentication {
	public static String generateCaptchaNumbers() {
		int randomNumbers = (int) (Math.random() * 9000) + 1000;  //1000~9999 숫자 생성
		
		String randomNumbersText = String.valueOf(randomNumbers);  //생성한 숫자 문자열로 변환

		return randomNumbersText;  //문자열 반환
	}
	
	public static void printCaptchaImage(String captchaNumbers) {
		//캡챠 이미지 출력 (랜덤 뽑은값이 1234면 하나씩 분리해서 캡챠 이미지와 매핑 후 해당하는 이미지 출력)
		for (String string : captchaNumbers.split(""))
			System.out.println(CaptchaNumbers.matchNumber(string).numberImage);
	}
}
