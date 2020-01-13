package tf.ngs.sp.lang.run

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import tf.ngs.sp.lang.SourcePawnIcons

class SourcePawnRunConfigurationType : ConfigurationType {
    override fun getDisplayName() = "SourcePawn Build"

    override fun getConfigurationTypeDescription() = "SourcePawn Build"

    override fun getIcon() = SourcePawnIcons.FILE

    override fun getId() = "SOURCEPAWN_BUILD_CONFIGURATION_TYPE"

    override fun getConfigurationFactories(): Array<ConfigurationFactory> {
        return arrayOf(SourcePawnConfigurationFactory(this))
    }
}