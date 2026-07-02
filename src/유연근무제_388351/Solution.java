package 유연근무제_388351;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        for (int i = 0; i < schedules.length; i++) {
            int count = 0;

            for (int j = 0; j < 7; j++) {

                if ((j + startday) % 7 == 6 || j + startday == 7 ) continue; // ‼️13이 되는 변수를 고려 안해서 좀 오래걸렸네

                int check = schedules[i] + 10;
                if(check % 100 >= 60) {check += 40;}

                if(timelogs[i][j] > check) break;
                count++;
            }
            if(count == 5) answer++;
        }
        return answer;
    }
}

// 늦지 않는 직원의 수를 반환 할거임

// sehedules은 직원들이 출근 희망사항을 입력한 시간임.
// timelogs의 가로는 cloum은 7(일주일)이고, 세로 Row는 위에 sehedules의 입력된 직원의 수만큼 있을거임
// startday는 기준 날짜를 말함.

/*
직관상 이중 for문인것 같은데 몇번 돌것인지 설계해보자

일단 바깥쪽 반복문은 직원의 수만큼 돌아야함. 그래야 모든 직원을 체크할 수 있으니까
그리고 안쪽 반복문은 7번 돌껀데 받는 startDay 변수에 따라서 6,7번 인덱스는 무시해야겠네

지각한 시간 로직은 어떻게되지?
일단 10분 늦으면 그거는 지각인거임

그럼 일단 분쪽에 10분을 더하고 만약 60을 초과하면 40을 추가로 더하는 로직을 만들어야겠다.


 */