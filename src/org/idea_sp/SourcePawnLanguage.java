package org.idea_sp;

import com.intellij.lang.Language;

public class SourcePawnLanguage extends Language {
    public static final SourcePawnLanguage INSTANCE = new SourcePawnLanguage();

    private SourcePawnLanguage() {
        super("SourcePawn");
    }
}
