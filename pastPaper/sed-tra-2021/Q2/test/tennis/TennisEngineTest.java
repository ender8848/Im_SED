package tennis;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TennisEngineTest {
    TennisEngine backend = new TennisEngine();

    public JUnitRuleMockery context = new JUnitRuleMockery();
    Updatable observer = context.mock(Updatable.class);

    @Test
    public void GameEndsWhenOneReachesFourPointsAndMarginGreaterTwo() {
        for (int i = 0; i < 3; ++i) {
            backend.playerOneWinsPoint();
        }
        assertTrue(!backend.gameHasEnded());
        backend.playerOneWinsPoint();
        assertTrue(backend.gameHasEnded());
    }

    @Test
    public void GameDoesNotEndIfMarginSmallerThanTwo() {
        for (int i = 0; i < 4; ++i) {
            backend.playerOneWinsPoint();
            backend.playerTwoWinsPoint();
        }
        assertTrue(!backend.gameHasEnded());
        backend.playerOneWinsPoint();
        backend.playerOneWinsPoint();
        assertTrue(backend.gameHasEnded());
    }

    @Test
    public void showScoreWhenMaxPointsSmallerThanFour() {
        for (int i = 0; i < 2; ++i) {
            backend.playerOneWinsPoint();
        }
        assertEquals(backend.score(), "30 - Love");
    }



    @Test
    public void doesNotShowScoreWhenMaxPointsLargerThanFour() {
        for (int i = 0; i < 6; ++i) {
            backend.playerOneWinsPoint();
            backend.playerTwoWinsPoint();
        }
        assertEquals(backend.score(), "Deuce");
        backend.playerOneWinsPoint();
        assertEquals(backend.score(), "Advantage Player 1");
    }

    @Test
    public void canNotifyObserver() {
        backend.addObserver(observer);
        context.checking(new Expectations() {{
            exactly(1).of(observer).update("15 - Love");
        }});
        backend.playerOneWinsPoint();
    }
}
