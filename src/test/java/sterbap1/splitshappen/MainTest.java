package sterbap1.splitshappen;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

    private Map<String, Integer> testMap;
    private Main main;
    private Boolean allCorrect;

    @Before
    public void init() {
        main = new Main();
        allCorrect = Boolean.TRUE;
    }

    @Test
    public void runTests() {
        testMap = new HashMap<String, Integer>();
        testMap.put("XXXXXXXXXXXX", 300);
        testMap.put("9-9-9-9-9-9-9-9-9-9-", 90);
        testMap.put("5/5/5/5/5/5/5/5/5/5/5", 150);
        testMap.put("X7/9-X-88/-6XXX81", 167);
        testMap.put("5/418-61219/7/451-5/2", 90);
        testMap.put("5/418-61X9/7/451-5/2", 107);
        testMap.put("X5/X5/X5/X5/X5/X", 200);
        testMap.put("5/X5/X5/X5/X5/X5/", 200);
        testMap.put("5/X5/X5/X5/X5/XXX", 210);

        testMap.forEach((String s, Integer i) -> {
            if (!test(s, i)) {
                allCorrect = Boolean.FALSE;
            }
        });
        if (!allCorrect) {
            Assert.fail("\n\nOne or more tests failed; check the logs for more information\n\n");
        }
    }

    private Boolean test(String input, Integer expectedOutput) {
        Integer score = main.calculateScoreForGame(input);
        if (!score.equals(expectedOutput)) {
            System.out.println("FAILURE: '" + input + "' expected output is " + expectedOutput + " but calculated " + score);
            return Boolean.FALSE;
        }
        System.out.println("Bowling game '" + input + "' computed a correct score of " + score);
        return Boolean.TRUE;
    }
}
