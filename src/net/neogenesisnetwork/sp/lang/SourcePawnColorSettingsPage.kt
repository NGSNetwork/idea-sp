package net.neogenesisnetwork.sp.lang

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage

class SourcePawnColorSettingsPage : ColorSettingsPage {

    override fun getIcon() = SourcePawnIcons.FILE

    override fun getHighlighter() = SourcePawnSyntaxHighlighter()

    override fun getDemoText() =
            "// Here's a comment!\n" +
                    "static a = 4;\n" +
                    "public static const char s[] = \"test\";"

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? = null

    override fun getAttributeDescriptors() = DESCRIPTORS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName() = "SourcePawn"

    companion object {
        private val DESCRIPTORS = arrayOf(AttributesDescriptor("Operator", SourcePawnSyntaxHighlighter.OPERATOR), AttributesDescriptor("Literal", SourcePawnSyntaxHighlighter.LITERAL), AttributesDescriptor("Reserved", SourcePawnSyntaxHighlighter.RESERVED), AttributesDescriptor("Comment", SourcePawnSyntaxHighlighter.COMMENT))
    }
}
