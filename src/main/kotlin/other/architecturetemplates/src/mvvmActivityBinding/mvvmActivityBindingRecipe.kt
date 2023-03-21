package other.architecturetemplates.src.mvvmActivityBinding

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.architecturetemplates.src.app_package.*
import other.getActivityName
import other.getCleanEntityName

fun RecipeExecutor.mvvmBindingSetup(
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

    val activityClass = entityName.getActivityName()
    val viewModelClass = "${entityName.getCleanEntityName()}ViewModel"
    val viewStateClass = "${entityName.getCleanEntityName()}ViewState"
//    This will generate new manifest (with activity) to merge it with existing, classes generation
//    are working but is failing when calling functions like addAllKotlinDependencies and generateManifest
//    generateManifest(
//        moduleData = moduleData,
//        activityClass = activityClass,
//        packageName = packageName,
//        isLauncher = false,
//        hasNoActionBar = true,
//        generateActivityTitle = true
//    )

    addDependency(mavenCoordinate = "androidx.lifecycle:lifecycle-extensions:+", configuration = "implementation")
    addDependency(mavenCoordinate = "androidx.databinding:databinding-runtime:+", configuration = "implementation")
    addDependency(mavenCoordinate = "androidx.databinding:databinding-compiler:+", configuration = "annotationProcessor")

    // Classes
    val activityFile = srcOut.resolve("$activityClass.kt")
    val viewModelFile = srcOut.resolve("$viewModelClass.kt")
    val viewStateFile = srcOut.resolve("$viewStateClass.kt")
    save(
        activityBindingTemplate(packageName, entityName, layoutName, projectData),
        activityFile
    )
    save(
        viewModelTemplate(packageName, entityName),
        viewModelFile
    )
    save(
        viewStateTemplate(packageName, entityName),
        viewStateFile
    )

    // Resources
    save(
        activityBindingLayoutTemplate(packageName, entityName),
        resOut.resolve("layout/$layoutName.xml")
    )

    open(viewStateFile)
    open(viewModelFile)
    open(activityFile)
}