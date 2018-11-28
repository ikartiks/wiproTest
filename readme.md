# Wipro readme

Dear reviewer, kindly go through this file before reviewing this project.

Item completion status

  - Specification - Yes
  - Guidelines - Yes
  
Things to note

  - Used kotlin (its DI for views as well) , retrofit, picasso, roboelectric
  - MVVM pattern with view models and live data as requested
  - I havent provided gradle wrapper, please select default one. Gradle.properties file has also been modified for instrumentation tests and hence commited
  - Implimented both swipe to refresh and refresh on button click
  - The app targets the android version 28 and is backword compatible.
  - I havent added a loader right now for the network call, but for the same I would ideally use my library below and show a beautiful gif insted of a std popup.
  - Will make optimizations on request.

### Other

[My library](https://github.com/ikartiks/kartiksCustomViewsGradle/) - I would encourage you to go through this one as well, since it supports gifs and custom fonts by default (although custom font support has been added in support library for android, I had wirttten this code much prior to that)

### Installation

```sh
use the git clone https://github.com/ikartiks/wiproTest.git in Android Studio
Once cloned select default gradle wrapper 
```

### Todos
 - Optimise if required

