import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    private Trie oneDeep, twoDeep, threeDeep, varDeep;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        oneDeep = new Trie();
        twoDeep = new Trie();
        threeDeep = new Trie();
        varDeep = new Trie();
    }

    @org.junit.jupiter.api.Test
    void put() {
        oneDeep.put("a", new Word("a"));
        oneDeep.put("b", new Word("b"));
        assertEquals("a", oneDeep.searchWord("a").getScript());
        assertEquals("b", oneDeep.searchWord("b").getScript());

        twoDeep.put("aa", new Word("aa"));
        twoDeep.put("ab", new Word("ab"));
        twoDeep.put("ba", new Word("ba"));
        assertEquals("aa", twoDeep.searchWord("aa").getScript());
        assertEquals("ab", twoDeep.searchWord("ab").getScript());
        assertEquals("ba", twoDeep.searchWord("ba").getScript());

        threeDeep.put("aaa", new Word("aaa"));
        threeDeep.put("aab", new Word("aab"));
        threeDeep.put("aba", new Word("aba"));
        threeDeep.put("baa", new Word("baa"));
        assertEquals("aaa", threeDeep.searchWord("aaa").getScript());
        assertEquals("aab", threeDeep.searchWord("aab").getScript());
        assertEquals("aba", threeDeep.searchWord("aba").getScript());
        assertEquals("baa", threeDeep.searchWord("baa").getScript());

        varDeep.put("a", new Word("a"));
        varDeep.put("aa", new Word("aa"));
        varDeep.put("aab", new Word("aab"));
        varDeep.put("aba", new Word("aba"));
        varDeep.put("b", new Word("b"));
        varDeep.put("baa", new Word("baa"));
        varDeep.put("ba", new Word("ba"));
        assertEquals("a", varDeep.searchWord("a").getScript());
        assertEquals("aa", varDeep.searchWord("aa").getScript());
        assertEquals("aab", varDeep.searchWord("aab").getScript());
        assertEquals("aba", varDeep.searchWord("aba").getScript());
        assertEquals("b", varDeep.searchWord("b").getScript());
        assertEquals("baa", varDeep.searchWord("baa").getScript());
        assertEquals("ba", varDeep.searchWord("ba").getScript());
    }

    @org.junit.jupiter.api.Test
    void getChar() {
        oneDeep.put("a", null);
        assertEquals(oneDeep.searchTrie("a").getChar(), 'a');
        
        twoDeep.put("ab", null);
        twoDeep.put("az", null);
        assertEquals(twoDeep.searchTrie("ab").getChar(), 'b');
        assertEquals(twoDeep.searchTrie("az").getChar(), 'z');

        threeDeep.put("aaa", null);
        threeDeep.put("aab", null);
        threeDeep.put("aba", null);
        threeDeep.put("baa", null);
        assertEquals(threeDeep.searchTrie("aaa").getChar(), 'a');
        assertEquals(threeDeep.searchTrie("aab").getChar(), 'b');
        assertEquals(threeDeep.searchTrie("aba").getChar(), 'a');
        assertEquals(threeDeep.searchTrie("baa").getChar(), 'a');

        varDeep.put("a", null);
        varDeep.put("aa", null);
        varDeep.put("aab", null);
        varDeep.put("aba", null);
        varDeep.put("b", null);
        varDeep.put("baa", null);
        varDeep.put("ba", null);
        assertEquals(threeDeep.searchTrie("a").getChar(), 'a');
        assertEquals(threeDeep.searchTrie("aa").getChar(), 'a');
        assertEquals(threeDeep.searchTrie("aab").getChar(), 'b');
        assertEquals(threeDeep.searchTrie("aba").getChar(), 'a');
        assertEquals(threeDeep.searchTrie("b").getChar(), 'b');
        assertEquals(threeDeep.searchTrie("baa").getChar(), 'a');
        assertEquals(threeDeep.searchTrie("ba").getChar(), 'a');
    }

    @org.junit.jupiter.api.Test
    void getWord() {
        oneDeep.put("a", new Word("a"));
        assertEquals(oneDeep.searchTrie("a").getWord(), new Word("a"));
    }

    @org.junit.jupiter.api.Test
    void searchTrie() {
        oneDeep.put("a", null);
        assertEquals(oneDeep.searchTrie("a"), new Trie('a', null));
        assertNull(oneDeep.searchTrie("b"));

        threeDeep.put("aaa", null);
        threeDeep.put("aba", null);
        assertEquals(threeDeep.searchTrie("aaa"), new Trie('a', null));
        assertEquals(threeDeep.searchTrie("aba"), new Trie('a', null));
    }

    @org.junit.jupiter.api.Test
    void searchWord() {
        oneDeep.put("a", new Word("a"));
        assertEquals(oneDeep.searchWord("a"), new Word("a"));
    }

    @org.junit.jupiter.api.Test
    void searchPartialMatches() {
        assertEquals(varDeep.searchPartialMatches("a").size(), 0);
        varDeep.put("a", new Word("a"));
        assertEquals(varDeep.searchPartialMatches("a").size(), 1);
        assertEquals(varDeep.searchPartialMatches("b").size(), 0);

        varDeep.put("baa", new Word("baa"));
        varDeep.put("bba", new Word("bba"));
        assertEquals(varDeep.searchPartialMatches("b").size(), 2);
        varDeep.put("bab", new Word("bab"));
        assertEquals(varDeep.searchPartialMatches("bac").size(), 2);
        assertEquals(varDeep.searchPartialMatches("bada").size(), 2);
        assertEquals(varDeep.searchPartialMatches("c").size(), 0);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        oneDeep.put("a", null);
        assertEquals(oneDeep.toString(), " {a}");
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        oneDeep.put("a", null);
        oneDeep.put("b", null);
        varDeep.put("a", null);
        varDeep.put("b", null);
        assertEquals(oneDeep, varDeep);

        twoDeep = varDeep;
        assertEquals(twoDeep, varDeep);

        threeDeep = null;
        assertNotEquals(oneDeep, threeDeep);
    }
}