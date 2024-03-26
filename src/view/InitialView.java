package view;

import java.io.IOException;

public class InitialView extends View{

	@Override
	protected void start() {
		System.out.println();
		System.out.println("██╗  ██╗███████╗    ██████╗  █████╗ ███╗   ██╗██╗  ██╗\r\n"
				+ "██║  ██║██╔════╝    ██╔══██╗██╔══██╗████╗  ██║██║ ██╔╝\r\n"
				+ "███████║███████╗    ██████╔╝███████║██╔██╗ ██║█████╔╝ \r\n"
				+ "██╔══██║╚════██║    ██╔══██╗██╔══██║██║╚██╗██║██╔═██╗ \r\n"
				+ "██║  ██║███████║    ██████╔╝██║  ██║██║ ╚████║██║  ██");
		System.out.println("         ___________________________________");
		System.out.println("         |                                 |");
		System.out.println("         | 인터넷 뱅킹 서비스를 이용해주셔서 감사합니다 |");
		System.out.println("         |      원하시는 메뉴를 선택 해주세요      |");
		System.out.println("         |_________________________________|");
		System.out.println();

	}

	@Override
	protected void proceed() {
		while(true) {
			System.out.println("[1] 고객       [2] 관리자       [0] 종료");
			System.out.println();
			System.out.print("> ");
			try {
				switch(br.readLine()) {
					case "1" :
						new ClientView().display();
						break;
					case "2" :
						new AdminView().display();
						break;
					case "0" :
						return;
					default :
						throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException | IOException | NullPointerException e) {
				System.out.println("잘못된 입력입니다.");
				// e.printStackTrace();
			}
		}
	}

	@Override
	protected void end() {
		System.out.println();
		System.out.println("         ___________________________________");
		System.out.println("         |                                 |");
		System.out.println("         |          서비스를 종료합니다          |");
		System.out.println("         |        이용해 주셔서 감사합니다        |");
		System.out.println("         |_________________________________|");
		System.out.println();
	}

}
