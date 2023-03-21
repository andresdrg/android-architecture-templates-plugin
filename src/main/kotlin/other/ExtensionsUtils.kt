package other

fun String.getActivityName(): String {
    if (this.isEmpty()) {
        return ""
    }

    replaceFirstChar { it.uppercase() }

    val regex = Regex("activity", RegexOption.IGNORE_CASE)
    val modifiedString = regex.replace(this, "Activity")

    return if (modifiedString.endsWith("Activity")) {
        modifiedString
    } else {
        "${modifiedString}Activity"
    }
}

fun String.getFragmentName(): String {
    if (this.isEmpty()) {
        return ""
    }

    replaceFirstChar { it.uppercase() }

    val regex = Regex("fragment", RegexOption.IGNORE_CASE)
    val modifiedString = regex.replace(this, "Fragment")

    return if (modifiedString.endsWith("Fragment")) {
        modifiedString
    } else {
        "${modifiedString}Fragment"
    }
}

fun String.getCleanEntityName(): String = replace("Activity","").replace("Fragment", "")

fun String.getActivityLayoutName(): String {
    val regex = Regex("activity", RegexOption.IGNORE_CASE)
    val modifiedString = regex.replace(this, "Activity")

    // Check if the modified string has a duplicated "Activity" and remove the last occurrence
    val lastIndex = modifiedString.lastIndexOf("Activity")
    if (lastIndex != -1 && modifiedString.substring(0, lastIndex).endsWith("Activity")) {
        return modifiedString.substring(0, lastIndex)
    }

    return modifiedString
}

fun String.getFragmentLayoutName(): String {
    val regex = Regex("fragment", RegexOption.IGNORE_CASE)
    val modifiedString = regex.replace(this, "Fragment")

    // Check if the modified string has a duplicated "Activity" and remove the last occurrence
    val lastIndex = modifiedString.lastIndexOf("Fragment")
    if (lastIndex != -1 && modifiedString.substring(0, lastIndex).endsWith("Fragment")) {
        return modifiedString.substring(0, lastIndex)
    }

    return modifiedString
}