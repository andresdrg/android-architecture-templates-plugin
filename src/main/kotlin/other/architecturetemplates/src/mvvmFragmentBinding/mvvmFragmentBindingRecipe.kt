package other.architecturetemplates.src.mvvmFragmentBinding

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.architecturetemplates.src.app_package.*
import other.getCleanEntityName
import other.getFragmentName

fun RecipeExecutor.mvvmFragmentBindingSetup(
    moduleData: ModuleTemplateData,
    packageName: String,
    entityName: String,
    layoutName: String
) {
    val (projectData, srcOut, resOut) = moduleData
//    val useAndroidX = moduleData.projectTemplateData.androidXSupport

//    if (projectData.language == Language.Kotlin) {
//        addAllKotlinDependencies(moduleData)
//    }

    val fragmentClass = entityName.getFragmentName()
    val viewModelClass = "${entityName.getCleanEntityName()}ViewModel"
    val viewStateClass = "${entityName.getCleanEntityName()}ViewState"
    val fragmentStateClass = "${entityName.getCleanEntityName()}State"
//    This will generate new manifest (with activity) to merge it with existing, classes generation
//    are working but is failing when calling functions like addAllKotlinDependencies and generateManifest
//    generateManifest(
//        moduleData = moduleData,
//        fragmentClass = fragmentClass,
//        packageName = packageName,
//        isLauncher = false,
//        hasNoActionBar = true,
//        generateActivityTitle = true
//    )

    addDependency(mavenCoordinate = "androidx.lifecycle:lifecycle-extensions:+", configuration = "implementation")
    addDependency(mavenCoordinate = "androidx.databinding:databinding-runtime:+", configuration = "implementation")
    addDependency(mavenCoordinate = "androidx.databinding:databinding-compiler:+", configuration = "annotationProcessor")

    // Classes
    val fragmentFile = srcOut.resolve("$fragmentClass.kt")
    val viewModelFile = srcOut.resolve("$viewModelClass.kt")
    val viewStateFile = srcOut.resolve("$viewStateClass.kt")
    val fragmentStateFile = srcOut.resolve("$fragmentStateClass.kt")
    save(
        fragmentBindingTemplate(packageName, entityName, layoutName, projectData),
        fragmentFile
    )
    save(
        viewModelFragmentTemplate(packageName, entityName),
        viewModelFile
    )
    save(
        viewStateFragmentTemplate(packageName, entityName),
        viewStateFile
    )
    save(
        fragmentStateTemplate(packageName, entityName),
        fragmentStateFile
    )

    // Resources
    save(
        fragmentBindingLayoutTemplate(packageName, entityName),
        resOut.resolve("layout/$layoutName.xml")
    )

    open(fragmentStateFile)
    open(viewStateFile)
    open(viewModelFile)
    open(fragmentFile)
}