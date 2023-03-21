package other.architecturetemplates.src.app_package

import com.android.tools.idea.wizard.template.ProjectTemplateData
import com.android.tools.idea.wizard.template.underscoreToCamelCase
import other.getCleanEntityName
import other.getFragmentName

fun fragmentBindingTemplate(
    packageName: String,
    entityName: String,
    layoutName: String,
    projectData: ProjectTemplateData
) = """
package $packageName

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import ${projectData.applicationPackage}.BR;
import ${projectData.applicationPackage}.R;
import ${projectData.applicationPackage}.databinding.${underscoreToCamelCase(layoutName)}Binding

class ${entityName.getFragmentName()} : Fragment() {
    private val viewModel: ${entityName.getCleanEntityName()}ViewModel by lazy {
        viewModel(ViewModelProvider.NewInstanceFactory())
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.${layoutName}, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DataBindingUtil.bind<${underscoreToCamelCase(layoutName)}Binding>(view)?.apply {
            setVariable(BR.state, viewModel.view)
            setVariable(BR.model, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }
    }
}
"""

fun fragmentBindingLayoutTemplate(
    packageName: String,
    entityName: String) = """
<?xml version="1.0" encoding="utf-8"?>

<layout>
	<data>
		<variable 
			name="state"
			type="${packageName}.${entityName.getCleanEntityName()}ViewState"/>
		<variable 
			name="model"
			type="${packageName}.${entityName.getCleanEntityName()}ViewModel"/>
	</data>
	
    <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
    </androidx.constraintlayout.widget.ConstraintLayout>

</layout>
"""