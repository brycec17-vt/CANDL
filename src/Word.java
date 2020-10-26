import java.util.Objects;

public class Word {
    private final String script;
    private final String romanization;
    private final String IPA;
    private final String partOfSpeech;
    private final String definition;
    private final String root;

    public Word(String str) {
        script = str;
        romanization = "";
        IPA = "";
        partOfSpeech = "";
        definition = "";
        root = "";
    }

    public Word(String scr, String ipa, String pos, String def, String rt) {
        script = scr;
        romanization = "";
        IPA = ipa;
        partOfSpeech = pos;
        definition = def;
        root = rt;
    }

    public Word(String scr, String rom, String ipa, String pos, String def, String rt) {
        script = scr;
        romanization = rom;
        IPA = ipa;
        partOfSpeech = pos;
        definition = def;
        root = rt;
    }

    public String getScript() {
        return script;
    }

    public String getRomanization() {
        return romanization;
    }

    public String getIPA() {
        return IPA;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public String getDefinition() {
        return definition;
    }

    public String getRoot() {
        return root;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Word word = (Word) o;
        return script.equals(word.script) &&
                romanization.equals(word.romanization) &&
                IPA.equals(word.IPA) &&
                partOfSpeech.equals(word.partOfSpeech) &&
                definition.equals(word.definition);
    }
}
