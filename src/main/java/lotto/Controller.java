package lotto;

import java.util.*;

import camp.nextstep.edu.missionutils.Randoms;

public class Controller {
    public int getCount(int money) {
        int count = 0;
        if (money % 1000 != 0) {
            System.out.println("[ERROR] 1,000원 단위가 아닙니다.");
            throw new IllegalArgumentException();
        }
        count = money / 1000;
        return count;
    }

    public List<Integer>[] issueLotto(int count) {
        List<Integer>[] issuedList = new List[count];
        for (int i = 0; i < count; i++) {
            List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            Collections.sort(numbers);
            issuedList[i] = numbers;
        }
        return issuedList;
    }

    public Lotto issueWinningNumber(List<Integer> numbers) {
        Lotto winningLotto = new Lotto(numbers);
        return winningLotto;
    }

    public HashMap<Rank,Integer> getRankMap(List<Integer>[] resultList){
        HashMap<Rank,Integer> rankMap = new HashMap<>(6);
        for (List<Integer> result: resultList) {
            int count = result.get(0);
            int bonus = result.get(1);
            Rank rank = getRank(count, bonus);
            if (rankMap.get(rank)==null) {
                rankMap.put(rank,1);
                continue;
            }
            rankMap.put(rank, rankMap.get(rank)+1);
        }
        return rankMap;
    }

    public Rank getRank(int count, int bonus) {
        if (count==6){
            return Rank.FIRST;
        }
        if (count==5 && bonus==1){
            return Rank.SECOND;
        }
        if (count==5 && bonus==0) {
            return Rank.THIRD;
        }
        if (count==4) {
            return Rank.FOURTH;
        }
        if (count==3) {
            return Rank.FIFTH;
        }
        return Rank.NOTHING;
    }
}
