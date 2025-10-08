package programmers.java.ch08_Hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import programmers.java.ch05_Array.Array;

// 베스트앨범
// https://school.programmers.co.kr/learn/courses/30/lessons/42579
public class P22 {

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        P22 init = new P22();
        System.out.println(Arrays.toString(init.solution(genres, plays)));
    }

    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        ArrayList<Integer> answerList = new ArrayList<>();
        HashMap<String, Integer> totalPlays = new HashMap<>();
        HashMap<String, ArrayList<Node>> playsList = new HashMap<>();

        // 장르 정리
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];
            Node node = new Node(i, play);

            if(totalPlays.containsKey(genre)) {
                totalPlays.put(genre, totalPlays.get(genre) + play);
            } else {
                totalPlays.put(genre, play);
            }

            if(!playsList.containsKey(genre)) {
                playsList.put(genre, new ArrayList<>());
            }
            playsList.get(genre).add(node);
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(totalPlays.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 곡 정리
        for(String key : playsList.keySet()) {
            ArrayList<Node> list = playsList.get(key);
            Collections.sort(list);
        }

        // 베스트앨범 정렬
        for(Map.Entry<String, Integer> entry : entryList) {
            String key = entry.getKey();

            answerList.add(playsList.get(key).get(0).idx);
            if(playsList.get(key).size() > 1) {
                answerList.add(playsList.get(key).get(1).idx);
            }
        }

        answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    class Node implements Comparable {
        int idx;
        int play;

        Node(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }

        @Override
        public String toString() {
            return "(" + idx + ", " + play + ")";
        }

        @Override
        public int compareTo(Object o) {
            Node other = (Node) o;
            return this.play == other.play ?
                Integer.compare(this.idx, other.idx) :
                Integer.compare(other.play, this.play);
        }
    }

}