package tf.ngs.sp.lang.run

import com.intellij.execution.Executor
import com.intellij.execution.configurations.*
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project


class SourcePawnRunConfiguration internal constructor(project: Project, factory: ConfigurationFactory?, name: String?) : RunConfigurationBase<Any?>(project, factory, name) {
    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration?> {
        return SourcePawnSettingsEditor()
    }

    override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState? {
        return null
    }

    override fun checkConfiguration() {
        // Left empty intentionally
    }
}