# Wipro readme

Dear reviewer, kindly go through this file before reviewing this project.

Item completion status

  - Specification - Yes
  - Guidelines - Yes
  
Things to note

  - Implemented both swipe to refresh and refresh on button click.
  - The app targets the android version 28 and is backward compatible.
  
  - Will make optimizations on request.
  - There are 2 tests, one is for successful app launch and 
    2nd one is for receiving empty data from the server.
  - Caching has been implemented at 3 levels 
(i) Retrofit request uses servers max-age parameter to fetch data again only after that interval.
(ii) View model ensures you data is not fetched again on rotation until you hit refresh or restart the app.
(iii) I have also cached response from server to local file (so page is not blank when the app starts), however deleting it has not been implemented yet, it gets overridden on every request or app start.
  - I haven't added a loader right now for the network call, but for the same 
       I would ideally use my library below and show a beautiful gif instead of a std popup.
  
### Libraries used
  - **android support libraries for backward compatibility** version: 28.0.0, **constraint layout** version:1.1.3
  - **android lifecycle libraries for live data and view model support** version: 1.1.1
  - **android testing framework** version 1.0.2, **espresso** version :3.0.2
  - **retrofit and gson for fetching data from server** version :2.4.0
  - **Picasso for downloading and using images** version:2.5.2
  

### Other

[My library](https://github.com/ikartiks/kartiksCustomViewsGradle/) - I would encourage you to go through this one as well, since it supports gifs and custom fonts by default (although custom font support has been added in support library for android, I had wirttten this code much prior to that)

### Installation

```sh
use the git clone https://github.com/ikartiks/wiproTest.git in Android Studio
Once cloned select default gradle wrapper 
```

### Todos
 - Optimise if required

