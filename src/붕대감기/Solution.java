package 붕대감기;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {

        int maxHealth = health;
        int attackIndex = 0;
        int continuousHeal = 0;
        int lastTime = attacks[attacks.length - 1][0];

        for (int time = 1; time <= lastTime; time++) {

            // 공격 시간
            if (attackIndex < attacks.length && time == attacks[attackIndex][0]) {
                health -= attacks[attackIndex][1];

                if (health <= 0) {return -1;}
                continuousHeal = 0;
                attackIndex++;
                continue;
            }

            // 공격이 없는 경우 heal
            health = Math.min(maxHealth, health + bandage[1]);
            continuousHeal++;

            // 연속 성공 추가 회복
            if (continuousHeal == bandage[0]) {
                health = Math.min(maxHealth, health + bandage[2]);
                continuousHeal = 0;
            }
        }
        return health;
    }
}

/*
bandage - 추가 회복량을 담은 1차원 정수 배열
[시전시간, 초당 회복량, 추가 회복량]

health - 최대 체력을 의미하는 정수

attacks - 몬스터의 공격 시간과 피해량을 담은 2차원 정수 배열
[공격시간, 피해량], [공격시간, 피해량], [공격시간, 피해량]

🪄로직 설계
반복문을 얼마나 돌것인가?
일단 얼마나 돌아야하나면 attacks의 마지막 배열의 0번쨰 인덱스까지 돌아야해
그리고 이떄의 attacks[.length][0] 이때까지 돌고 그전에 체력이 음수면 반환해주면 됨

for(0부터 attcks의 마지막 배열의 첫번째 인덱스){
공격확인
}
*/
