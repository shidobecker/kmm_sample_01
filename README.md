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
