package other.architecturetemplates.src.app_package

import com.android.tools.idea.wizard.template.ProjectTemplateData
import other.getActivityName
import other.getCleanEntityName

fun activityBindingTemplate(
    packageName: String,
    entityName: String,
    layoutName: String,
    projectData: ProjectTemplateData
) = """
package $packageName

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import ${projectData.applicationPackage}.R;
import ${projectData.applicationPackage}.databinding.Activity${entityName.getCleanEntityName()}Binding;

class ${entityName.getActivityName()} : AppCompatActivity() {
    private lateinit var vm: ${entityName.getCleanEntityName()}ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: Activity${entityName.getCleanEntityName()}Binding = DataBindingUtil.setContentView(this, R.layout.${layoutName.lowercase()})
        
        vm = ViewModelProvider(this)[${entityName.getCleanEntityName()}ViewModel::class.java]
        vm.viewState.observe(this) { handleState(it) }
    }
    
    private fun handleState(viewState: ${entityName.getCleanEntityName()}ViewState) {
        // Do something
    }
}
"""

fun activityBindingLayoutTemplate(
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
        android:layout_height="match_parent"
        tools:context="${packageName}.${entityName.getActivityName()}">
    
    </androidx.constraintlayout.widget.ConstraintLayout>

</layout>
"""