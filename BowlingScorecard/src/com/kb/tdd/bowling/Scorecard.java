/**
 * 
 */
package com.kb.tdd.bowling;

import org.apache.log4j.Logger;

/**
 * @author kbennett
 * 
 */
public class Scorecard {

    private static final Logger log = Logger.getLogger(Scorecard.class);

    private static final int MAX_FRAME_SCORE = 10;
    private static final int FRAME_COUNT = 10;

    private static final int MAX_BALL_COUNT = (FRAME_COUNT * 2) + 1;

    private int currentBall = 0;

    private int[] balls = new int[MAX_BALL_COUNT];

    public int getCurrentScore() {

        int score = 0;
        int firstBallInFrame = 0;

        for (int i = 0; i < FRAME_COUNT; i++) {

            if (isStrike(firstBallInFrame)) {

                score += 10 + balls[firstBallInFrame + 1] + balls[firstBallInFrame + 2];
                firstBallInFrame++;
            }
            else if (isSpare(firstBallInFrame))
            {
                score += 10 + balls[firstBallInFrame + 2];

                firstBallInFrame += 2;

            } else {

                score += balls[firstBallInFrame] + balls[firstBallInFrame + 1];
                firstBallInFrame += 2;
            }
        }

        log.info("Current Score is [" + score + "]");
        return score;
    }

    private boolean isStrike(int firstBallInFrame) {
        return balls[firstBallInFrame] == MAX_FRAME_SCORE;
    }

    private boolean isSpare(int firstBallInFrame) {
        return balls[firstBallInFrame] + balls[firstBallInFrame + 1] == MAX_FRAME_SCORE;
    }

    public void addBall(int pins) throws IllegalArgumentException {

        if (pins > MAX_FRAME_SCORE) {
            throw new IllegalArgumentException("Score too high");
        } else if (pins < 0) {
            throw new IllegalArgumentException("Score Too Low");
        }

        if (currentBall < MAX_BALL_COUNT) {

            balls[currentBall++] = pins;

            log.info("Added Ball with a score of [" + pins + "]");

        } else {
            throw new IllegalArgumentException("The game has ended");
        }

    }

}
