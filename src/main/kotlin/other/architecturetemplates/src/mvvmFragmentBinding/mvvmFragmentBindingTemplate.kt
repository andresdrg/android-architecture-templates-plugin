package other.architecturetemplates.src.mvvmFragmentBinding

import com.android.tools.idea.wizard.template.*
import other.getFragmentLayoutName

private const val MIN_SDK = 21

val mvvmBindingFragmentSetupTemplate
    get() = template {
        name = "MVVM Binding Fragment Template"
        description = "Creates a new fragment along layout file with view binding."
        minApi = MIN_SDK
        category = Category.Fragment
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
            WizardUiContext.NewProject, WizardUiContext.NewModule)

        val packageNameParam = defaultPackageNameParameter
        val entityName = stringParameter {
            name = "Entity Name"
            default = "MvvmBindingFragment"
            help = "The name of the entity class to create and use in Fragment"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "mvvmbinding"
            help = "The name of the layout to create for the fragment"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { fragmentToLayout(entityName.value.getFragmentLayoutName()) }
        }

        widgets(
            TextFieldWidget(entityName),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageNameParam)
        )

        recipe = { data: TemplateData ->
            mvvmFragmentBindingSetup(
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

