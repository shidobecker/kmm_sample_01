# Set up an environment for KMM development
[Kotlin docs](https://kotlinlang.org/docs/mobile/setup.html).  
First steps MacOS:
 
* Upgrade Brew - upgrade brew
* Install Ruby - brew install ruby
* Install CocoaPods - brew install cocoapods


1. If you are going to work with shared code or Android-specific code, you can work on any computer with an operating system supported by Android Studio.
1. If you also want to write iOS-specific code and run an iOS application on a simulated or real device, use a Mac with a macOS. These steps cannot be performed on other operating systems, such as Microsoft Windows. This is due to an Apple requirement.

1. Install Android Studio – version 4.2 or higher.
1. You will use Android Studio for creating your multiplatform applications and running them on simulated or hardware devices.

1. If you need to write iOS-specific code and run an iOS application, install Xcode – version 11.3 or higher.
1. Most of the time, Xcode will work in the background. You will use it to add Swift or Objective-C code to your iOS application.

1. Make sure that you have a compatible Kotlin plugin installed.
1. In Android Studio, select _Tools | Kotlin | Configure Kotlin Plugin Updates_ and check the current Kotlin plugin version. If needed, update to the latest version in the Stable update channel.

1. Install the Kotlin Multiplatform Mobile plugin.
1. In Android Studio, select _Preferences | Plugins_, search for the plugin _Kotlin Multiplatform Mobile_ in Marketplace and install it.


# Creating First App
[Kotlin Docs] (https://kotlinlang.org/docs/mobile/create-first-app.html)


In Android Studio, select File | New | New Project.

Select KMM Application in the list of project templates, and click Next.

Mobile Multiplatform project template
Specify a name for your first application, and click Next.

Mobile Multiplatform project - general settings
In the window that opens, do the following:

Keep the default names for the application and shared folders.

Select the checkbox to generate sample tests for your project.

Select Xcode build phases (packForXcode task) in the list of iOS framework distribution options.

Click Finish to create a new project.

Mobile Multiplatform project - additional settings
Now wait while your project is set up. It may take some time to download and set up the required components when you do this for the first time.

To view the complete structure of your mobile multiplatform project, switch the view from Android to Project. You can understand the KMM project structure and how you can use this.

### Issues on this step:
Gradle JDK Location invalid: https://stackoverflow.com/questions/32654016/invalid-jdk-configuration-found-while-importing-a-project-via-gradle

How to solve: Deleting .gradle and .idea will likely solve the problem.

Git not working:
https://stackoverflow.com/questions/52522565/git-is-not-working-after-macos-update-xcrun-error-invalid-active-developer-pa

Emulator not running: https://stackoverflow.com/questions/67264212/android-emulator-crash-when-start-hvf-error-hv-error

Error: can't grab Xcode schemes with /usr/bin/xcodebuild -workspace /Users/julioribeiro/Documents/KMM_Sample01/iosApp/iosApp.xcworkspace -list
https://youtrack.jetbrains.com/issue/KT-41691



Issue Pod Framework:
https://stackoverflow.com/questions/29865899/ld-framework-not-found-pods


# Understand the KMM project structure
[Kotlin Docs](https://kotlinlang.org/docs/mobile/discover-kmm-project.html)

The purpose of the Kotlin Multiplatform Mobile (KMM) technology is unifying the development of applications with common logic for Android and iOS platforms. To make this possible, KMM uses a mobile-specific structure of Kotlin Multiplatform projects. On this page, we’ll describe the structure of a basic KMM project. Note that this structure isn’t the only possible way to organize a KMM project; however, we recommend it as a starting point.

A basic Kotlin Mobile Multiplatform (KMM) project consists of three components:

Shared module- a Kotlin module that contains common logic for both Android and iOS applications. Builds into an Android library and an iOS framework. Uses Gradle as a build system.

Android application- a Kotlin module that builds into the Android application. Uses Gradle as a build system.

iOS application- an Xcode project that builds into the iOS application.

## Root project
The root project is a Gradle project that holds the shared module and the Android application as its subprojects. They are linked together via the Gradle multi-project mechanism.

settings.gradle.kts
``` kotlin
include(":shared")
include(":androidApp")
```

settings.gradle
``` groovy
include ':shared'
include ':androidApp'
```



The iOS application is produced from an Xcode project. It’s stored in a separate directory within the root project. Xcode uses its own build system; thus, the iOS application project isn’t connected with other parts of the KMM project via Gradle. Instead, *it uses the shared module as an external artifact - framework*. For details on integration between the shared module and the iOS application, see iOS application.


## Shared module
Shared module contains the core application logic used in both target platforms: classes, functions, and so on. This is a Kotlin Multiplatform module that compiles into an Android library and an iOS framework. It uses Gradle with the Kotlin Multiplatform plugin applied and has targets for Android and iOS.

``` groovy
plugins {
    id 'org.jetbrains.kotlin.multiplatform' version '1.4.30'
    //..
}

kotlin {
    android()
    ios()
}
```


## Source sets 
The shared module contains the code that is common for Android and iOS applications. However, to implement the same logic on Android and iOS, you sometimes need to write two platform-specific versions of it. To handle such cases, Kotlin offers the *expect/actual* mechanism. The source code of the shared module is organized in three source sets accordingly:

- commonMain stores the code that works on both platforms, including the expect declarations

- androidMain stores Android-specific parts, including actual implementations

- iosMain stores iOS-specific parts, including actual implementations

Each source set has its own dependencies. Kotlin standard library is added automatically to all source sets, you don’t need to declare it in the build script.


```groovy
kotlin {
    sourceSets {
        commonMain {
        }
        androidMain {
            dependencies {
                implementation 'androidx.core:core-ktx:1.2.0'
            }
        }
        iosMain {
        }

        // ...
    }
}
```

When you write your code, add the dependencies you need to the corresponding source sets. Read Multiplatform documentation on adding dependencies for more information.

Along with *Main source sets, there are three matching test source sets:

-commonTest
-androidTest
-iosTest
Use them to store unit tests for common and platform-specific source sets accordingly. By default, they have dependencies on Kotlin test library, providing you with means for Kotlin unit testing: annotations, assertion functions and other. You can add dependencies on other test libraries you need.

```
kotlin {
    sourceSets {
        //...

        commonTest {
            dependencies {
                implementation kotlin('test-common')
                implementation kotlin('test-annotations-common')
            }
        }
        androidTest {

        }
        iosTest {

        }
    }
} 
```

## Android library
The configuration of the Android library produced from the shared module is typical for Android projects. To learn about Android libraries creation, see Create an Android library in the Android developer documentation.

To produce the Android library, two more Gradle plugins are used in addition to Kotlin Multiplatform:

Android library

Kotlin Android extensions
 
```
plugins {
    // ...
    id 'com.android.library'
    id 'kotlin-android-extensions'
}
```
 
The configuration of Android library is stored in the android {} top-level block of the shared module’s build script.


## iOS framework 
For using in iOS applications, the shared module compiles into a framework - a kind of hierarchical directory with shared resources used on the Apple platforms. This framework connects to the Xcode project that builds into an iOS application.

The framework is produced via the Kotlin/Native compiler. The framework configuration is stored in the ios {} block of the build script within kotlin {}. It defines the output type framework and the string identifier baseName that is used to form the name of the output artifact. Its default value matches the Gradle module name. For a real project, it’s likely that you’ll need a more complex configuration of the framework production. For details, see Multiplatform documentation.

```
kotlin {
    // ...
    ios {
        binaries {
            framework {
                baseName = 'shared'
            }
        }
    }
}
```

