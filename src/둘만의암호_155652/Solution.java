package 둘만의암호_155652;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int count = 0;

            while (count < index) {
                c++;
                if (c > 'z'){ c = 'a';}
                if (skip.indexOf(c) == -1) {count++;}
            }
            answer += c;
        }
        return answer;
    }
}

/*
s = 기본 문자열
index = 이 수만큼 뒤로 이동
skip = 여기있는 문자는 제외하고

몇번 돌아야할까

일단 전체적으로는 s.length 만큼의 반복문은 돌아야하고

각 문자에서 어떻게 skip을 걸러낼까
answer += ('a' + (c - 'a' + index) % 26); 기본적인 알파뱃 로직은 이거임


*/
