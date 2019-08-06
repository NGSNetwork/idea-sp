package net.neogenesisnetwork.sp.lang;

import com.intellij.lang.Language;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class SourcePawnLanguage extends Language {
    public static final @NotNull
    SourcePawnLanguage INSTANCE = new SourcePawnLanguage();

    private SourcePawnLanguage() {
        super("SourcePawn");
    }

    @Override
    @Contract(pure = true)
    public boolean isCaseSensitive() {
        return false;
    }
}
