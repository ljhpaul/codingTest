package programmers.java.ch08_Hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import programmers.java.ch05_Array.Array;

// 오픈채팅방
// https://school.programmers.co.kr/learn/courses/30/lessons/42888
public class P21 {

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi",
                            "Enter uid4567 Prodo",
                            "Leave uid1234",
                            "Enter uid1234 Prodo",
                            "Change uid4567 Ryan"};

        P21 p21 = new P21();
        System.out.println(Arrays.toString(p21.solution(record)));
    }

    public String[] solution(String[] record) {
        String[] answer = {};
        HashMap<String, String> users = new HashMap<>();
        ArrayList<String[]> alertMsg = new ArrayList<>();

        for(String rec : record) {
            String cmd = rec.split(" ")[0];
            String uid = rec.split(" ")[1];
            String nickname = "";
            String[] msg = {cmd, uid};

            if(!cmd.equals("Leave")) {
                nickname = rec.split(" ")[2];
            }

            switch (cmd) {
                case "Enter" : {
                    users.put(uid, nickname);
                    alertMsg.add(msg);
                    break;
                } case "Change" : {
                    users.put(uid, nickname);
                    break;
                } case "Leave" : {
                    alertMsg.add(msg);
                    break;
                }
            }
        }

        answer = new String[alertMsg.size()];
        for (int i = 0; i < alertMsg.size(); i++) {
            String cmd = alertMsg.get(i)[0];
            String uid = alertMsg.get(i)[1];
            String nickname = users.get(uid);

            switch (cmd) {
                case "Enter" : {
                    answer[i] = nickname + "님이 들어왔습니다.";
                    break;
                } case "Leave" : {
                    answer[i] = nickname + "님이 나갔습니다.";
                    break;
                }
            }
        }

        return answer;
    }

}