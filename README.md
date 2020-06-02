# GameDepot - Track your favourite games.
## Udacity Part-5 Capstone Project

The Udacity Capstone project which provides users information on the latest and greatest upcoming games. The application fethces games from Rawg.io which is one of the best repositories containing tons of data on games. 

The app follows an MVVM architecture

                                                 Activity
                                                 Fragments
                                                View Model  
                                                 Live Data
                                              Games Repository

A worker manager manages the game sync worker that is responsible to update the local cache of the game data in the repository.

The app also contains a widget that displays the screenshots of the most popular games of 2020.

## Rubric

- [x] App validates all input from servers and users. If data does not exist or is in the wrong format, the app logs this fact and does not crash.
- [x] App includes support for accessibility. That includes content descriptions, navigation using a D-pad, and, if applicable, non-audio versions of audio cues.
- [x] App keeps all strings in a strings.xml file and enables RTL layout switching on all layouts.
- [x] App provides a widget to provide relevant information to the user on the home screen.
- [x] App integrates two or more Google services. Google service integrations can be a part of Google Play Services or Firebase.
- [x] App theme extends AppCompat.
- [x] App uses standard and simple transitions between activities.
- [x] App builds and deploys using the installRelease Gradle task.


- [x] Must implement at least one of the three:
If it regularly pulls or sends data to/from a web service or API, app updates data in its cache at regular intervals using a SyncAdapter or JobDispatcher.
OR
If it needs to pull or send data to/from a web service or API only once, or on a per request basis (such as a search application), app uses an IntentService to do so.
OR
It it performs short duration, on-demand requests(such as search), app uses an AsyncTask.

## Attaching Screenshots 





![](/Screnshots/list1.png?raw=true)
![](/Screnshots/detail2.png?raw=true)
![](/Screnshots/detail1.png?raw=true)
