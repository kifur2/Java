package input;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class calculateTest {
    public static Collection<Object[]> stringsProvider() {
        return Arrays.asList(new Object[][] {
                { "1 2 +", 3.0 },
                { "1.0 2.0 +", 3.0 },
                { "1 2 -", -1.0},
                { "1.0", 1.0},
                { "8.7 2.3 *", 20.01 },
                { "12 2 3 4 * 10 5 / + * +", 40.0},
                { "1 2 + 4 * 5 + 3 -", 14.0}
        });
    }
    @ParameterizedTest
    @MethodSource("stringsProvider")
    public void normalTest(String inputStr, Double outputDou){
        Input inp = new Input();
        double eps = 0.000_000_1d;
        assertEquals(outputDou, Input.calculate(inputStr), eps);
    }
    @Test
    public void testDivisionByZero()
    {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                assertEquals( "inf", Input.calculate("2 0 /"));
            }
        });
    }
}