package 데이터분석_250121;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {

    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int index = extToInt(ext);
        int sortIndex = extToInt(sort_by);

        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            if (data[i][index] < val_ext) {
                list.add(data[i]);
            }
        }
        list.sort(Comparator.comparingInt(row -> row[sortIndex]));

        return list.toArray(new int[list.size()][]);
    }

    private int extToInt (String ext){
        switch (ext) {
            case "code":
                return 0;

            case "date":
                return 1;

            case "maximum":
                return 2;

            case "remain":
                return 3;

            default:
                return -1;
        }
    }
}
/*

테이터 분석작업 중
데이터 = [코드번호, 제조일, 최대수량, 현재수량]
[code, date, maximum, remain]
조건을 만족하는 데이터만 추출후 정렬
ex) 제조일이 2030년 이전인 물건을 현재 수량이 적은 순서로

data - 정렬한 데이터들이 담긴 이차원 정수 리스트
ext - 어떤 정보와 기준으로 데이터를 뽑아낼지 의미하는 문자열
val_ext - 뽑아낼 정보의 기준값을 나타내는 정수
sort_by - 해당하는 값을 기준으로 오름차순 정렬
-> 얘는 다음중 한가지를 가진다네
code, date, maximum, remain이고 순서대로 정렬기준은
code, date, maximum, remain인거임. 이상

로직설계 🪄
그니까 조건을 만족하는 데이터만 뽑아서 정렬하는 프로그램인거네
이때 조건변수가 문자열 ext인거고, (date)
그 값이 정수형 val_ext인거고 (20300501)
정렬기준 = sort_by

그러면 일단 이차원 배열 date를 전부 돌면서 값을 추출하는 반복문이 하나 필요

for

switch : ext
case : code
index = 0

case = date
index = 1

case = maximum
index = 2

case = remain
index = 3
*/