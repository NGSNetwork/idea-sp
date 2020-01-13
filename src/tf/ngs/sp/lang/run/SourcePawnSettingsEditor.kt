package tf.ngs.sp.lang.run

import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.ui.ComponentWithBrowseButton
import com.intellij.openapi.ui.LabeledComponent
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import javax.swing.JComponent
import javax.swing.JPanel


class SourcePawnSettingsEditor : SettingsEditor<SourcePawnRunConfiguration?>() {
    private val myPanel: JPanel? = null
    private var myMainClass: LabeledComponent<ComponentWithBrowseButton<*>>? = null

    override fun resetEditorFrom(sourcePawnRunConfiguration: SourcePawnRunConfiguration) {
        // left empty intentionally
    }

    override fun applyEditorTo(sourcePawnRunConfiguration: SourcePawnRunConfiguration) {
        // left empty intentionally
    }

    override fun createEditor(): JComponent {
        return myPanel!!
    }

    private fun createUIComponents() {
        myMainClass = LabeledComponent()
        myMainClass!!.component = TextFieldWithBrowseButton()
    }
}