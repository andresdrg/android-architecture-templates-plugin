package other.architecturetemplates.src.mvvmActivity

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.architecturetemplates.src.app_package.activityTemplate
import other.architecturetemplates.src.app_package.activityLayoutTemplate
import other.architecturetemplates.src.app_package.viewModelTemplate
import other.architecturetemplates.src.app_package.viewStateTemplate
import other.getActivityName
import other.getCleanEntityName

fun RecipeExecutor.mvvmSetup(
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
//    is working but is failing when calling functions like addAllKotlinDependencies and generateManifest
//    generateManifest(
//        moduleData = moduleData,
//        activityClass = activityClass,
//        packageName = packageName,
//        isLauncher = false,
//        hasNoActionBar = true,
//        generateActivityTitle = true
//    )

    // Classes
    val activityFile = srcOut.resolve("$activityClass.kt")
    val viewModelFile = srcOut.resolve("$viewModelClass.kt")
    val viewStateFile = srcOut.resolve("$viewStateClass.kt")
    save(
        activityTemplate(packageName, entityName, layoutName, projectData),
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
        activityLayoutTemplate(packageName, entityName),
        resOut.resolve("layout/$layoutName.xml")
    )

    open(viewStateFile)
    open(viewModelFile)
    open(activityFile)
}