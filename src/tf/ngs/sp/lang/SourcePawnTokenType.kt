package tf.ngs.sp.lang

import com.intellij.psi.tree.IElementType
import tf.ngs.sp.lang.SourcePawnLanguage
import org.jetbrains.annotations.NonNls

class SourcePawnTokenType constructor(@NonNls debugName: String) : IElementType(debugName, SourcePawnLanguage.INSTANCE) {
    override fun toString() =  "SourcePawnTokenType." + super.toString()
}
