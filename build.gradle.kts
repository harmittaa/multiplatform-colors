
plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.3.0").apply(false)
    id("com.android.library").version("7.3.0").apply(false)
    kotlin("android").version("1.7.20").apply(false)
    kotlin("multiplatform").version("1.7.20").apply(false)
    // classpath 'com.diffplug.spotless:spotless-plugin-gradle:5.15.0'
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
