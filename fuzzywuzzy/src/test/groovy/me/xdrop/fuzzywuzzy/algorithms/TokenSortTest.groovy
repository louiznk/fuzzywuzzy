package me.xdrop.fuzzywuzzy.algorithms

import me.xdrop.fuzzywuzzy.Ratio
import me.xdrop.fuzzywuzzy.StringProcessor
import me.xdrop.fuzzywuzzy.ratios.PartialRatio
import me.xdrop.fuzzywuzzy.ratios.SimpleRatio

import static org.easymock.EasyMock.*

class TokenSortTest extends GroovyTestCase {

    void testUsesStringProcessor() {

        def ts = new TokenSort()

        def mock = createMock(StringProcessor)

        expect(mock.process(eq("notthesame")))
                .andReturn("thesame")

        expect(mock.process(eq("thesame")))
                .andReturn("thesame")

        replay(mock)

        assertEquals 100, ts.apply("notthesame", "thesame", mock)

    }

    void testUsesRatio() {

        def ts = new TokenSort();

        def mock = createMock(Ratio)

        expect(mock.apply(anyObject(String), anyObject(String)))
                .andReturn(0)
                .anyTimes()

        replay(mock)

        assertEquals 0, ts.apply("one two", "one three", mock)

    }

    void testTokenSort() {

        def ts = new TokenSort();

        assertEquals 75, ts.apply("test", "pesticide", new PartialRatio())
        assertEquals 46, ts.apply("test", "pesticide", new SimpleRatio())

    }

}
