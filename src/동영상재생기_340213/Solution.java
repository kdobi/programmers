package 동영상재생기_340213;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        int videoLen =toSeconds(video_len);
        int posLen =toSeconds(pos);
        int opStartLen =toSeconds(op_start);
        int opEndLen =toSeconds(op_end);

        for(String command : commands){

            posLen = openingCheck(opStartLen, opEndLen, posLen);

            if(command.equals("next")){
                posLen += 10;
                if(posLen > videoLen){ // 비디오 길이보다 길 경우
                    posLen = videoLen;
                }
            }
            if(command.equals("prev")){
                posLen -= 10;
                if(posLen < 0){ // 0보다 작아질 경우
                    posLen = 0;
                }
            }
            posLen = openingCheck(opStartLen, opEndLen, posLen);
        }
        return toTimeString(posLen);
    }

    // 문자열 숫자로 변환
    private int toSeconds(String time) {
        String[] parts = time.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return minutes * 60 + seconds;
    }

    // 순자 문자로 변환
    private String toTimeString(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private int openingCheck(int openingStart, int openingEnd, int current) {
        if(openingStart <= current && current <= openingEnd) { return openingEnd;}
        else return current;
    }
}

/*
동영상 재생기는 3가지 기능을 제공함
1. 10초 전으로 이동 ( prev 입력 -> 10초 전 이동, 10초 전이면 0초 )
2. 10초 후로 이동 ( next 입력 -> 10초 후로 이동, 남은시간 부족이면 끝으로 이동 )
3. 오프닝 건너뛰기 현재 재생 위치가 오프닝 구간(op_start ≤ 현재 재생 위치 ≤ op_end)인 경우 자동으로 오프닝이 끝나는 위치로 이동

video_len : 동영상 길이 ( 문자열임 )
pos : 기능 수행 직전의 재생위치 ( 문자열임 )
op~~ : 오프닝 시작과 끝
commands : 사용자가 입력하는거

최종적으로 사용자의 입력이 다 끝나면 mm:ss 형식으로 return

---
로직 🪄

일단 기본적으로 문자열 equals를 사용해서 next랑 prev면 10초 +- 해주는거고
신경써야 할건

1. 시간이 오프닝 사이인가
2. 시간이 10초 미만인가 ( pos < 10 ) pos == 0
3. 시간이 10초 이상 안남았던가 ( pos + 10 > video_len ) pos == video_len
*/
