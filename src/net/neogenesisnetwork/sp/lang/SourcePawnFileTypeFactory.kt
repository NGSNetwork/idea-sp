package net.neogenesisnetwork.sp.lang

import com.intellij.openapi.fileTypes.*
import net.neogenesisnetwork.sp.lang.SourcePawnFileType

class SourcePawnFileTypeFactory : FileTypeFactory() {
    override fun createFileTypes(fileTypeConsumer: FileTypeConsumer) {
        fileTypeConsumer.consume(SourcePawnFileType, "inc")
        fileTypeConsumer.consume(SourcePawnFileType, "sp")
    }
}