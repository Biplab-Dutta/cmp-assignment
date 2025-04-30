This project is a simple mobile app that uses Pixabay API to display data. The app also has "search" functionality.

The project here uses an effective architecture inspired by MVVM.

### pixa_images_overview
This is the main feature for this project. A page that allows users to view images on the basis of their search operation.
This module is divided into 3 sub-modules.
  - data
  - domain
  - presentation

Data layer is responsible for handling logic relating to network call. The API call implementation, JSON parsing and error handling is done here.
Domain layer is responsible for handling core business operation. It is here where an interface for the repository is defined. It is done to avoid having data layer imports in the presentation layer.
Presentation layer is responsible for UIs and viewmodels.

Both the data layer and presentation layer can depend on the domain layer but not the other way around.

The source code makes use of many reusable components and helper extensions.

Also, the task was to create an android app using Jetpack Compose and an iOS app using SwiftUI in KMP. Since I have no experience with SwiftUI, I couldn't build an UI using SwiftUI.
To make up for it, I implemented an extra feature, "Search" in android.

### App demo
https://raw.githubusercontent.com/Biplab-Dutta/cmp-assignment/refs/heads/main/demo/PaleBlueDemo.gif