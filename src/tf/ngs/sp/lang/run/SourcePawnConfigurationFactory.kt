package tf.ngs.sp.lang.run

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.project.Project

class SourcePawnConfigurationFactory(type: ConfigurationType) : ConfigurationFactory(type) {
    companion object {
        private const val FACTORY_NAME = "SourcePawn configuration factory"
    }

    override fun createTemplateConfiguration(project: Project): RunConfiguration {
        return SourcePawnRunConfiguration(project, this, "Demo")
    }

    override fun getName() = FACTORY_NAME
}