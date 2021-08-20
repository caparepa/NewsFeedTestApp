# NewsFeedTestApp

This is a simple newsfeed app made as a technical test for an Android Developer position, using [Hacker News Algolia's Search API](https://hn.algolia.com/api/v1/search_by_date?query=mobile) as data source.

### Requirements (summary of sorts)

* On startup, and when the listview is pulled down to refresh, the app should connect to this API
  which shows recently posted articles about Android or iOS on Hacker News.
* If the app is used offline, it should show the items downloaded last time.
* The main view of the app is a scrolling list view of recent posts in date order. If the user taps on a
  post, show a web view (within the app) with the linked article. Also, you should be able to swipe
  on a cell, and delete an individual post from this view (see mockups).
* Once a post is deleted it should not reappear even if the data is refreshed.

### Development info (stack, patterns and other boring stuff)

This application was developed using the MVVM design pattern for Android applications, alongside Navigation Components (they don't see much action since there's only one activity and fragment, thou). The stack used was the following:

* Kotlin (programming language)
* Kotlin Coroutines (Android and Core)
* Lifecycle-LiveData (for reactive shenanigans)
* [Android-SpinKit](https://github.com/ybq/Android-SpinKit) (for the loader/progress bar thingy)
* Koin (for dependency injection)
* Retrofit2 (including Kotlin coroutines adapter)
* Room as database wrapper
* Other stuff I don't remember/didn't use.

The build.gradle was refactored from Groovy to Kotlin DSL for consistency and future integration with the Kotlin codebase (in case there's need to manage dependency versions programmatically).

The package structure is the following, explained in a short, concise way:

* data: contains everything related to data definition (Models) and database (Entities). Also contains the on-board database declaration.
* di: contains anything and everything related to dependency injection declaration.
* network: contains all stuff related to API calls and wotnot (Retrofit client, endpoint declaration, interceptors, etc)
* repository: self-explanatory.
* ui: contains all things related to user interfaces (activities, fragments, adapters, viewmodels, etc)
* utils: nice-to-have utility package with classes and extensions.

### Known issues/bugs, and other information of interest

* The data received from the API encompasses both articles and comments. I decided to filter out comments and keep only the root articles in order to have a smooth development process. You can see the validation in the extension List<Hit>?.filterStories() (NewsFeedRepositoryImpl.kt).
* The WebView activity was replaced with a Chrome Custom Tabs intent, since the URL wouldn't load into the WebView element due some security issue.
* When swiping to delete an item, the RecyclerView might not update correctly and refresh the view.
* The initial loading element might cause artifacts when starting the app (there's no splash screen)
* When the data source API is down, there might appear a "timeout" toast. Kinf of.

### Guides and Reference
* [Converting your Android Gradle scripts to Kotlin](https://proandroiddev.com/converting-your-android-gradle-scripts-to-kotlin-1172f1069880): used to refresh/update knowledge on Groovy-Kotlin migration
* [RecyclerviewWithBenefits](https://github.com/SG-K/RecyclerviewWithBenefits): used as guide for implementing the "swipe to delete" feature.

### License

The license is GPLv3, because I don't know any other that's as nice... maybe the [CC by-nc-sa](https://creativecommons.org/licenses/by-nc-sa/4.0/)? ¯\\_(ツ)_/¯
