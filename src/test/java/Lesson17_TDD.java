import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class Lesson17_TDD {
    private Calculator calculator;
    private static Logger logger = LogManager.getLogger(Lesson17_TDD.class);

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("test abs")
    public void testAbs() {
        Integer x = -8;
        Integer expected = 8;
        assert calculator.abs(x).equals(expected);
        logger.info("abs()= " + calculator.abs(x));
    }

    @Test
    @DisplayName("test pow")
    public void testPow() {
        Integer a = 3;
        Integer b = 4;
        Double expected = 81.0;
        assert calculator.pow(a, b).equals(expected);
        logger.info("abs()= " + calculator.pow(a, b));
    }

    @Test
    @DisplayName("test sqrt")
    public void testSqrt() {
        Integer d = 9;
        Double expected = 3.0;
        assert calculator.sqrt(d).equals(expected);
        logger.info("abs()= " + calculator.sqrt(d));
    }

}