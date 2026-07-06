package 달리기경주_178871;

// ‼️피드백 - 처음에 되게 쉬웠고 로직을 빠르게 많들었지만 이중 반복문 구조로 시간복잡도가 터저버렸다.
// 이 문제의 핵심은 Map을 이용해서 인덱스를 빠르게 바꿔주는데 있다.

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String[] solution(String[] players, String[] callings) {

        String[] answer = {};
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < players.length; i++) {
            map.put(players[i], i);
        }

        for(String call : callings){
            int currentIndex = map.get(call);
            int frontIndex = currentIndex - 1;

            String frontPlayer = players[frontIndex];

            players[frontIndex] = call;
            players[currentIndex] = frontPlayer;

            map.put(call, frontIndex);
            map.put(frontPlayer, currentIndex);

        }
        return players;
    }
}

/*
해설진들은 선수들이 자기 바로 앞 선수를 추월할 때 추월한 선수의 이름을 부름

players - 선수의 이름이 담긴 배열 [ 1등부터 현재 등수 배열 ]
callings - 해설진이 부른 이름을 담은 문자열 배열

로직설계 🪄
그니까 callings의 길이만큼 돌면서 temp해주면 되는거네
*/