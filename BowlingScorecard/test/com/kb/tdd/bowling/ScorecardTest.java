/**
 * 
 */
package com.kb.tdd.bowling;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author kbennett
 * 
 */
public class ScorecardTest {

    private Scorecard scorecard;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        scorecard = new Scorecard();
    }

    private void addMultipleBalls(int pins, int balls) throws Exception {
        for (int i = 0; i < balls; i++) {
            scorecard.addBall(pins);
        }
    }

    private void throwStrike() {
        scorecard.addBall(10);
    }

    private void throwSpare() {
        scorecard.addBall(3);
        scorecard.addBall(7);
    }

    @Test
    public void canAddMaximumBalls() throws Exception {
        addMultipleBalls(0, 21);
        assertEquals(0, scorecard.getCurrentScore());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotAddMoreThanMaxBalls() throws Exception {
        addMultipleBalls(1, 25);
        assertEquals(24, scorecard.getCurrentScore());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotScoreMoreThanTen() throws Exception {
        scorecard.addBall(15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotScorelessThanZero() throws Exception {
        scorecard.addBall(-1);
    }

    @Test
    public void canScoreSpare() throws Exception {
        throwSpare();

        assertEquals(10, scorecard.getCurrentScore());

        scorecard.addBall(3);

        assertEquals(16, scorecard.getCurrentScore());
    }

    @Test
    public void canScoreStrike() throws Exception {
        throwStrike();

        assertEquals(10, scorecard.getCurrentScore());

        scorecard.addBall(3);
        scorecard.addBall(4);

        assertEquals(24, scorecard.getCurrentScore());
    }

    @Test
    public void cannotScoreMoreThan300() throws Exception {

        for (int i = 0; i < 13; i++) {
            throwStrike();
            scorecard.getCurrentScore();
        }

        assertEquals(300, scorecard.getCurrentScore());

        throwStrike();

        assertEquals(300, scorecard.getCurrentScore());
    }

}
