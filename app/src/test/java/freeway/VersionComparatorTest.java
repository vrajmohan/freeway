package freeway;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class VersionComparatorTest {
    @ParameterizedTest
    @MethodSource("intArrayAndStringProvider")
    public void testGetVersion(int[] version, String versionString) {
        assertArrayEquals(version, new VersionComparator().getVersion(new File(versionString)));
    }

    @ParameterizedTest
    @CsvSource({
            "V001__foo, V002__foo, -1",
            "V001__foo, V1__foo, 0",
            "V91__foo, V115__foo, -1",
            "V1.12.14__foo, V1.12.15__foo, -1",
            "V1.10__foo, V1.10.1__foo, -1",
    })

    public void testCompare(String s1, String s2, int expected) {
        assertEquals(expected, new VersionComparator().compare(new File(s1), new File(s2)));
    }

    static Stream<Arguments> intArrayAndStringProvider() {
        return Stream.of(
                arguments(new int[]{1, 2, 3}, "V1.2.3__some_description.sql"),
                arguments(new int[]{1}, "V001__some_description.sql"),
                arguments(new int[]{2013,1,15,11,35,56}, "V2013.1.15.11.35.56__some_description.sql"),
                arguments(new int[]{1}, "V1__some_description.sql")
        );
    }
}