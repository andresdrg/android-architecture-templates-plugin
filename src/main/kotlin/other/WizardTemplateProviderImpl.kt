package other

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import other.architecturetemplates.src.mvvmActivity.mvvmSetupTemplate
import other.architecturetemplates.src.mvvmActivityBinding.mvvmBindingSetupTemplate
import other.architecturetemplates.src.mvvmFragmentBinding.mvvmBindingFragmentSetupTemplate

class WizardTemplateProviderImpl : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> = listOf(
        mvvmSetupTemplate,
        mvvmBindingSetupTemplate,
        mvvmBindingFragmentSetupTemplate
    )
}