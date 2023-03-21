package other.architecturetemplates.src.app_package

import other.getCleanEntityName

fun viewModelFragmentTemplate(
    packageName: String,
    entityName: String
) = """
package $packageName

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ${entityName.getCleanEntityName()}ViewModel(
    val view: ${entityName.getCleanEntityName()}ViewState
) : ViewModel() {
    // Add your logic here
}

inline fun <reified T: ViewModel> Fragment.viewModel(
    viewModelFactory: ViewModelProvider.Factory
) : T = ViewModelProvider(this, viewModelFactory)[T::class.java]
"""

fun viewStateFragmentTemplate(
    packageName: String,
    entityName: String
) = """
package $packageName

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ${entityName.getCleanEntityName()}ViewState {
    private val _viewState = MutableLiveData<${entityName.getCleanEntityName()}State>()
    val viewState: LiveData<${entityName.getCleanEntityName()}State>
        get() = _viewState

    fun updateViewState(newState: ${entityName.getCleanEntityName()}State) {
        _viewState.value = newState
    }
}
"""

fun fragmentStateTemplate(
    packageName: String,
    entityName: String
) = """
package $packageName

sealed class ${entityName.getCleanEntityName()}State {
    object Idle: ${entityName.getCleanEntityName()}State()
    object Loading: ${entityName.getCleanEntityName()}State()
    class Result(val data: Any): ${entityName.getCleanEntityName()}State()
    class Error(val error: Throwable): ${entityName.getCleanEntityName()}State()
}
"""
