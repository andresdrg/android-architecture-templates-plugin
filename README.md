## AndroidArchitectureTemplates

<!-- Plugin description -->

In order to speed up the development according to this architecture you can use the templates created in the following repo.

How to install the templates

As this is a actual plugin, it can be published to be available in the marketplace, but as is a plugin that generates boilerplate code it can change and get deprecated very easily, so will be better to keep the installation of it manually:

Download the [latest release](https://github.com/andresdrg/MVVMActivityTemplate/releases/latest) and generate a new .jar plugin by launching intellij gradle task "buildPlugin", it will generate a .jar at "rootProject/build/libs" with the name "AndroidArchitectureTemplates-{versionNumber}.jar"
Then go <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

Select the previously generated .jar plugin and restart Android Studio.

Then simple try to generate a new Activity or Fragment and select the new available templates.

<!-- Plugin description end -->

## Available Templates

### MVVM Activity
This template will generate three classes (Activity, State, ViewModel) and
one .xml file with basic boiler plate code.

### MVVM Binding Activity
This template will generate the same classes as in the previous one plus the layout resource associated with the view.

> **Note**
> Activity templates will not include the generated activity in the manifest, you will have to add it
> manually, this improve is currently WIP

### MVVM Binding Fragment
This template will generate 4 classes (Fragment, ViewState, FragmentState, ViewModel) and one .xml file
with basic boiler plate code, along with the associated resources with the view binding

### RecyclerView  WIP 
Template with main extension functions and methods for the RecyclerView widget. /*TODO*/

Some notes about templates
Templates requires that your project minimum target SDK is API level 21.

If you want to update Android Studio you would need to remove them before updating.

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
