package 카드뭉치_159994;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {

        int count1 = 0;
        int count2 = 0;

        for(String word : goal) {

            if(count1 < cards1.length && word.equals(cards1[count1])) {
                count1++;
            }

            else if(count2 < cards2.length && word.equals(cards2[count2])) {
                count2++;
            }

            else return "No"; // 이렇게 하면 아래처럼 하는거보다 더 빨라지네 ㅇㅇ
        }

//        if(count1 + count2 == goal.length) {
//            return "Yes";
//        }

//        return "No"

        return "Yes";
    }
}

/*
코니는 카드뭉치 두개를 선물로 받음
그리고 아래 규칙으로 적힌 단어들을 사용해서 원하는 순서의 단 배열을 만들 수 있나 알고싶음.

1. 원하는 카드 뭉치에서 카드를 순서대로 한장씩 사용
2. 한 번 사용한 카드는 다시 사용 못함
3. 카드 스킵은 안됨
4. 카드 뭉치 단어 순서는 못바꿈

cards1 - 카드 배열 1
cards2 - 카드 배열 2
goal - 원하는 단어 배열

로직설계 🪄
일단 goal의 길이만큼

*/