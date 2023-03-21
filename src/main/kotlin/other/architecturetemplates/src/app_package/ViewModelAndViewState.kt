package other.architecturetemplates.src.app_package

import other.getCleanEntityName

fun viewModelTemplate(
    packageName: String,
    entityName: String
) = """
package $packageName

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ${entityName.getCleanEntityName()}ViewModel : ViewModel() {
    val viewState = MutableLiveData<${entityName.getCleanEntityName()}ViewState>()
    
    init {
        viewState.value = ${entityName.getCleanEntityName()}ViewState()
    }
}
"""
fun viewStateTemplate(
    packageName: String,
    entityName: String
) = """
package $packageName

data class ${entityName.getCleanEntityName()}ViewState(
    var loading: Boolean = false,
    var error: Throwable? = null,
    var data: Any? = null
)
"""