package other.architecturetemplates.src.mvvmActivityBinding

import com.android.tools.idea.wizard.template.*
import other.getActivityLayoutName

private const val MIN_SDK = 21

val mvvmBindingSetupTemplate
    get() = template {
        name = "MVVM Binding Activity Template"
        description = "Creates a new activity along layout file with view binding."
        minApi = MIN_SDK
        category = Category.Activity
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry,
            WizardUiContext.NewProject, WizardUiContext.NewModule)

        val packageNameParam = defaultPackageNameParameter
        val entityName = stringParameter {
            name = "Entity Name"
            default = "MvvmBindingActivity"
            help = "The name of the entity class to create and use in Activity"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "mvvmbinding"
            help = "The name of the layout to create for the activity"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { activityToLayout(entityName.value.getActivityLayoutName()) }
        }

        widgets(
            TextFieldWidget(entityName),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageNameParam)
        )

        recipe = { data: TemplateData ->
            mvvmBindingSetup(
                data as ModuleTemplateData,
                packageNameParam.value,
                entityName.value.replaceFirstChar { it.uppercase() },
                layoutName.value
            )
        }
    }

val defaultPackageNameParameter get() = stringParameter {
    name = "Package name"
    visible = { !isNewModule }
    default = "com.mycompany.myapp"
    constraints = listOf(Constraint.PACKAGE)
    suggest = { packageName }
}