plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()

    explicitApi()
    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.dependencies {
            implementation(kotlin("stdlib"))
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        jvmMain.dependencies {
            implementation(kotlin("reflect"))
        }
    }
}
