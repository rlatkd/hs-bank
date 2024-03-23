package View;

import java.io.IOException;

public class InitialView extends View{

	@Override
	protected void start() {
		System.out.println("초기 화면 시작");
	}

	@Override
	protected void proceed() {
		while(true) {
			System.out.println("1. 고객\n2. 관리자\n3. 종료");
			System.out.print("입력 : ");
			try {
				switch(br.readLine()) {
					case "1" :
						new ClientView().display();
						break;
					case "2" :
						new AdminView().display();
						break;
					case "3" :
						System.out.println("시스템 종료");
						return;
					default :
						throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException | IOException | NullPointerException e) {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

	@Override
	protected void end() {
		System.out.println("초기 화면 종료");
	}

}
