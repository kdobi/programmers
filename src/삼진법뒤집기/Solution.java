package 삼진법뒤집기;

class Solution {
    public int solution(int n) {
        int answer = 0;
        String s = "";

        while (n != 0) {
            s += n % 3;
            n /= 3;
        }

        for (int i = s.length(), j = 0; i > 0; i--, j++) {
            answer += (int)((s.charAt(j) - '0') * Math.pow(3, i - 1));
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(45));
    }
}

