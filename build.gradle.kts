// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(stack.plugins.android.application) apply false
    alias(stack.plugins.android.library) apply false
    alias(stack.plugins.jetbrains.kotlin.android) apply false
    alias(stack.plugins.compose.compiler) apply false
    alias(stack.plugins.serialization) apply false
}