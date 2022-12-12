val ktlint by configurations.creating

dependencies {
    ktlint("com.pinterest:ktlint:0.47.1") {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
}

val outputDir = "${project.buildDir}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

val ktlintCheck by tasks.creating(JavaExec::class) {
    description = "Check Kotlin code style."
    group = "verification"

    inputs.files(inputFiles)
    outputs.dir(outputDir)

    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("src/**/*.kt")
}

val ktlintFix by tasks.creating(JavaExec::class) {
    description = "Fix Kotlin code style deviations."
    group = "verification"

    inputs.files(inputFiles)
    outputs.dir(outputDir)

    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("-F", "src/**/*.kt")
}