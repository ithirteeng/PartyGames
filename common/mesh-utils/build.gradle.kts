plugins {
    common.library
    `kotlinx-serialization`
}

android {
    namespace = "com.ith.partygames.common.mesh_utils"
}

dependencies {
    api(project(":lib-meshrabiya-local"))

//    api(stack.kotlinx.serialization.json)
}
