package sterbap1.splitshappen;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        for (String input : args) {
            int score = main.calculateScoreForGame(input);
            System.out.println("Game '" + input + "' scored a " + score);
        }
    }

    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String MISS = "-";
    //helps with the ending strike/spare "extra" frames
    private static final int EXPECTED_FRAMES = 10;

    /**
     * given a game of bowling, calculate the score. 
     * *strike is marked as X 
     * *spare is marked as / 
     * *miss is marked as - 
     * *all other integers from 1 to 9 are valid
     *
     * @param scoreString - the String representation of the game
     * @return - score as calculated per rules
     */
    public int calculateScoreForGame(String scoreString) {
        List<String> scoreList = Arrays.asList(scoreString.split(""));

        int totalScore = 0;
        //index into the actual rolls
        int i = 0;
        //tracks the number of frames played
        int frame = 0;
        int scoreListSize = scoreList.size();

        do {
            String currentScore = scoreList.get(i);
            if (STRIKE.equals(currentScore)) {
                //verify that the end of the game is not going to interfere with indices
                if ((i + 2) < scoreListSize) {
                    totalScore += 10 + convertScore(scoreList.get(i + 1), scoreList.get(i + 2));
                } else if ((i + 1) < scoreListSize) {
                    totalScore += 10 + convertScore(scoreList.get(i + 1));
                } else {
                    totalScore += 10;
                }
            } else if ((i + 1) < scoreListSize) {
                //potentially a spare in the next roll
                if (SPARE.equals(scoreList.get(i + 1))) {
                    //spares get 10 points for the current and next score, plus
                    //points for the next roll, which cannot be a 
                    //spare and cannot be past the end of the play scores 
                    //so no need to check for null
                    totalScore += 10 + convertScore(scoreList.get(i + 2));
                } //otherwise it is just two scores added together as one score
                else {
                    totalScore += convertScore(currentScore, scoreList.get(i + 1));
                }
                //keeps the frames together
                i++;
            } else {
                totalScore += convertScore(currentScore);
            }
            i++;
            frame++;
        } while (i < scoreListSize && frame < EXPECTED_FRAMES);

        return totalScore;
    }

    /**
     * determines the direct score for the next two scores, aka this was a
     * strike. adjusts for a spare requiring the previous roll by taking in both
     * rolls and checking for a Spare first, otherwise just adding the scores
     * individually
     *
     * @param firstRoll - next score: can never be a spare
     * @param secondRoll - next next score: can be a spare
     * @return score of the next two scores
     */
    private int convertScore(String firstRoll, String secondRoll) {
        if (SPARE.equals(secondRoll)) {
            return 10;
        }
        return convertScore(firstRoll) + convertScore(secondRoll);
    }

    /**
     * convert from string input to an int. cannot account for a spare due to
     * that score being the difference from the previous roll and 10
     *
     * @param roll - string input
     * @return integer value for the string input
     */
    private int convertScore(String roll) {
        switch (roll) {
            case STRIKE:
                return 10;
            case MISS:
                return 0;
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                return Integer.parseInt(roll);
            default:
                System.out.println("no score found");
                break;
        }
        return -1;
    }
}
