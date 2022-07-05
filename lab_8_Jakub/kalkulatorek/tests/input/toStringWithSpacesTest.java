package input;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class toStringWithSpacesTest {
    public static Collection<Object[]> stringsProvider() {
        return Arrays.asList(new Object[][] {
                { "1+2=", "1 + 2 = " },
                { "1.0+2.0=", "1.0 + 2.0 = " },
                { "1-2=", "1 - 2 = " },
                { "(1.0)=", "( 1.0 ) = "},
                { "8.7*2.3=", "8.7 * 2.3 = "},
                { "12+2*(3*4+10/5)=", "12 + 2 * ( 3 * 4 + 10 / 5 ) = "},
                { "(1+2)*4+5-3=", "( 1 + 2 ) * 4 + 5 - 3 = "}
        });
    }
    @ParameterizedTest
    @MethodSource("stringsProvider")
    public void normalTest(String inputStr, String outputStr){
        Input inp = new Input();
        assertEquals(outputStr, inp.toStringWithSpaces(inputStr));
    }
}