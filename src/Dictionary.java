import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {
    private Trie scriptTrie;
    private Trie romanizationTrie;
    private Map<Word, String> rootMap;
    private Map<String, List<Word>> derivativeMap;

    public Dictionary() {
        scriptTrie = new Trie();
        romanizationTrie = new Trie();
        rootMap = new HashMap<>();
        derivativeMap = new HashMap<>();
    }

    public void put(Word word) {
        scriptTrie.put(word.getScript(), word);
        scriptTrie.put(word.getRomanization(), word);
        rootMap.put(word, word.getRoot());
        if (!derivativeMap.containsKey(word.getRoot())) {
            derivativeMap.put(word.getRoot(), new ArrayList<>());
        }
        derivativeMap.get(word.getRoot()).add(word);
    }

    public List<Word> searchByScript(String str) {

    }
}
