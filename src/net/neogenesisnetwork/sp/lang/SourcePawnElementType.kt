package net.neogenesisnetwork.sp.lang

import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.NonNls

class SourcePawnElementType internal constructor(@NonNls debugName: String) : IElementType(debugName, SourcePawnLanguage.INSTANCE)
