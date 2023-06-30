package com.danjoh2019.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ScoreBoard {

    private static String sumSingleNumberDice(int number, List<Die> dice) {
        int sum = 0;
        for (Die die : dice) {
            if (die.getValue() == number) {
                sum += die.getValue();
            }
        }
        return Integer.toString(sum);
    }

    private static String bonus(int total) {
        if (total >= 63) {
            return Integer.toString(50);
        }
        return Integer.toString(0);
    }

    private static String xOfAKind(int number, List<Die> dice) {
        Map<Integer, Integer> counter = new HashMap<>();

        for (Die die : dice) {
            if (!counter.containsKey(die.getValue())) {
                counter.put(die.getValue(), 1);
            } else {
                int count = counter.get(die.getValue());
                counter.put(die.getValue(), count + 1);
            }
        }

        Iterator<Entry<Integer, Integer>> it = counter.entrySet().iterator();

        while (it.hasNext()) {
            Entry<Integer, Integer> entry = it.next();

            if (entry.getValue() >= number) {
                if (number == 5) {
                    return Integer.toString(50);
                }
                return Integer.toString(entry.getKey() * number);
            }
        }
        return Integer.toString(0);
    }

    private static String onePair(List<Die> dice) {
        Map<Integer, Integer> faceValueCounts = getFaceValueCount(dice);

        int highestPairValue = 0;
        for (Map.Entry<Integer, Integer> entry : faceValueCounts.entrySet()) {
            int count = entry.getValue();
            if (count >= 2) {
                int faceValue = entry.getKey();
                highestPairValue = Math.max(highestPairValue, faceValue * 2);
            }
        }

        return Integer.toString(highestPairValue);
    }

    private static String twoPairs(List<Die> dice) {
        Map<Integer, Integer> faceValueCounts = getFaceValueCount(dice);

        List<Integer> pairs = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : faceValueCounts.entrySet()) {
            int count = entry.getValue();
            if (count >= 2) {
                int faceValue = entry.getKey();
                pairs.add(faceValue);
            }
        }

        Collections.sort(pairs, Collections.reverseOrder());

        if (pairs.size() >= 2) {
            return Integer.toString(pairs.get(0) * 2 + pairs.get(1) * 2);
        } else {
            return Integer.toString(0);
        }
    }

    private static Map<Integer, Integer> getFaceValueCount(List<Die> dice) {
        Map<Integer, Integer> faceValueCount = new HashMap<>();

        for (Die die : dice) {
            int faceValue = die.getValue();
            int count = faceValueCount.getOrDefault(faceValue, 0);
            faceValueCount.put(faceValue, count + 1);
        }

        return faceValueCount;
    }

    private static String chance(List<Die> dice) {
        int scoreTotal = 0;

        for (Die die : dice) {
            scoreTotal += die.getValue();
        }

        return Integer.toString(scoreTotal);
    }

    private static String fullHouse(List<Die> dice) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int sum = 0;

        for (Die die : dice) {
            countMap.put(die.getValue(), countMap.getOrDefault(die.getValue(), 0) + 1);
            sum += die.getValue();
        }

        boolean hasThreeOfAKind = false;
        boolean hasTwoOfAKind = false;

        for (int count : countMap.values()) {
            if (count == 3) {
                hasThreeOfAKind = true;
            } else if (count == 2) {
                hasTwoOfAKind = true;
            }
        }

        if (hasThreeOfAKind && hasTwoOfAKind) {
            return Integer.toString(sum);
        }

        return Integer.toString(0);
    }

    private static String smallStraight(List<Die> dice) {
        Set<Integer> uniqueDice = new HashSet<>();

        for (Die die : dice) {
            uniqueDice.add(die.getValue());
        }

        if (uniqueDice.containsAll(Arrays.asList(1, 2, 3, 4, 5))) {
            return Integer.toString(15);
        }

        return Integer.toString(0);
    }

    private static String largeStraight(List<Die> dice) {
        Set<Integer> uniqueDice = new HashSet<>();

        for (Die die : dice) {
            uniqueDice.add(die.getValue());
        }

        if (uniqueDice.containsAll(Arrays.asList(2, 3, 4, 5, 6))) {
            return Integer.toString(20);
        }

        return Integer.toString(0);
    }

    public static String updateScores(Map<Integer, Integer> scores, int position, List<Die> dice) {
        String result = Integer.toString(0);

        if (scores.containsKey(position)) {
            return Integer.toString(scores.get(position));
        }

        switch (position) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                result = ScoreBoard.sumSingleNumberDice(position, dice);
                break;

            case 7:
                int sumOneToSix = 0;

                for (int i = 1; i <= 6; i++) {
                    if (scores.get(i) != null) {
                        sumOneToSix += scores.get(i);
                    }
                    sumOneToSix += 0;
                }

                result = Integer.toString(sumOneToSix);
                break;

            case 8:
                int sumBonus = 0;

                for (int i = 1; i <= 6; i++) {
                    if (scores.get(i) != null) {
                        sumBonus += scores.get(i);
                    }
                    sumBonus += 0;
                }

                result = ScoreBoard.bonus(sumBonus);
                break;

            case 9:
                result = xOfAKind(3, dice);
                break;

            case 10:
                result = xOfAKind(4, dice);
                break;

            case 11:
                result = fullHouse(dice);
                break;

            case 12:
                result = smallStraight(dice);
                break;

            case 13:
                result = largeStraight(dice);
                break;

            case 14:
                result = chance(dice);
                break;

            case 15:
                result = xOfAKind(5, dice);
                break;

            case 16:
                int sum = 0;
                int addBonus = 0;

                if (!scores.isEmpty()) {
                    for (int val : scores.values()) {
                        sum += val;
                    }
                }

                for (int i = 1; i <= 6; i++) {
                    if (scores.get(i) != null) {
                        addBonus += scores.get(i);
                    }
                    addBonus += 0;
                }

                sum += Integer.parseInt(ScoreBoard.bonus(addBonus));

                result = Integer.toString(sum);
                break;

            case 17:
                result = onePair(dice);
                break;
            case 18:
                result = twoPairs(dice);
                break;
        }

        return result;
    }

    // private boolean checkSize(List<Die> dice) {
    // if (dice.size() != 5) {
    // return false;
    // }
    // return true;
    // }
}
