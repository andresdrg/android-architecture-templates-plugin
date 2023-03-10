package com.github.andresdrg.mvvmactivitytemplate.services

import com.github.andresdrg.mvvmactivitytemplate.MyBundle

class MyApplicationService {

    init {
        println(MyBundle.message("applicationService"))

        System.getenv("CI")
            ?: TODO("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }
}
