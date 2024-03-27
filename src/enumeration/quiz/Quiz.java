package enumeration.quiz;

import lombok.Getter;

@Getter
public enum Quiz {
    Q1("자금의 수요자와 공급자를 연결해주는 역할을 하는 곳의 총칭", "금융회사"),
    Q2("예금, 적금, 공과금 수납, 대출, 환전 등 다양한 서비스를 제공하는 금융회사", "은행"),
    Q3("돈을 빌리고 빌려주는 과정에서 이루어지는 돈의 흐름", "금융"),
    Q4("은행에 돈을 맡긴(또는 빌린) 대가로 받는(주는) 돈", "이자"),
    Q5("여러 회사들이 발행한 주식을 사고 팔 수 있도록 거래를 도와주는 금융회사", "증권회사"),
    Q6("미래에 발생할 수 있는 위험에 대비하기 위한 보험상품을 개발하여 판매하는 회사", "보험회사"),
    Q7("미래를 위해 현재의 소비를 줄이고 돈을 모으는 것", "저축"),
    Q8("돈을 빌리거나 물건 또는 서비스를 사용한 뒤 약속한 날짜에 값을 수 있는 능력", "신용"),
    Q9("정해진 모양에 금액을 표시한 것으로 물건을 사고 팔 때 사용하는 것", "화폐"),
    Q10("우리가 금융회사에서 활용하는 것을 부르는 총칭", "금융상품");
    private String question;
    private String answer;
    Quiz(String question, String answer){
        this.question = question;
        this.answer = answer;
    }
}
