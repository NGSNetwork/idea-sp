package org.idea_sp;

import com.intellij.lexer.FlexAdapter;

public class SourcePawnLexerAdapter extends FlexAdapter {
    public SourcePawnLexerAdapter() {
        super(new SourcePawnLexer(null));
    }
}
