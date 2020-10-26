import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Trie {
    private final char c;
    private Word w;
    private boolean root;
    private List<Trie> children;

    public Trie() {
        c = ' ';
        w = null;
        root = true;
        children = new ArrayList<>();
    }

    public Trie(char character, Word word) {
        c = character;
        w = word;
        root = false;
        children = new ArrayList<>();
    }

    public void put(String key, Word word) {
        if (key.length() != 0) {
            Trie child = searchOrAdd(key.charAt(0));
            if (key.length() == 1) {
                child.setWord(word);
            }
            else {
                child.put(key.substring(1), word);
            }
        }
    }

    public char getChar() {
        return c;
    }

    private void setWord(Word word) {
        w = word;
    }

    public Word getWord() {
        return w;
    }

    public Trie search(char c) {
        for (Trie child : children) {
            if (child.getChar() == c) {
                return child;
            }
        }
        return null;
    }

    private Trie searchOrAdd(char c) {
        if (search(c) == null) {
            Trie child = new Trie(c, null);
            children.add(child);
            return child;
        }
        else {
            return search(c);
        }
    }

    public Trie searchTrie(String str) {
        if (str.length() == 1) {
            Trie trie = search(str.charAt(0));
            if (trie != null) {
                return trie;
            }
        }
        if (str.length() > 1) {
            Trie trie = search(str.charAt(0));
            if (trie != null) {
                return trie.searchTrie(str.substring(1));
            }
        }
        return null;
    }

    public Word searchWord(String str) {
        return searchTrie(str).getWord();
    }

    private Trie searchPartialTrie(String str) {
        if (str.length() == 1) {
            Trie trie = search(str.charAt(0));
            if (trie != null) {
                return trie;
            }
            if (!root) {
                return this;
            }
        }
        if (str.length() > 1) {
            Trie trie = search(str.charAt(0));
            if (trie != null) {
                return trie.searchPartialTrie(str.substring(1));
            }
            if (!root) {
                return this;
            }
        }
        return null;
    }

    private List<Word> getChildWords() {
        List<Word> list = new ArrayList<>();
        for (Trie child : children) {
            if (child.getWord() != null) {
                list.add(child.getWord());
            }
            list.addAll(child.getChildWords());
        }
        return list;
    }

    public List<Word> searchPartialMatches(String str) {
        List<Word> words = new ArrayList<>();
        Trie trie = searchPartialTrie(str);
        if (trie == null) {
            return words;
        }
        if (trie.getWord() != null) {
            words.add(trie.getWord());
        }
        words.addAll(trie.getChildWords());
        return words;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", "{", "}");
        for (Trie child : children) {
            sj.add(child.toString());
        }
        return c + (children.size() > 0 ? sj.toString() : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Trie trie = (Trie) o;
        return c == trie.c &&
                Objects.equals(w, trie.w) &&
                Objects.equals(children, trie.children);
    }
}
