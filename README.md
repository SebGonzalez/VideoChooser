[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/beryx-gist/badass-jlink-example-log4j2-javafx/blob/master/LICENSE)
[![Build Status](https://img.shields.io/travis/beryx-gist/badass-jlink-example-log4j2-javafx/master.svg?label=Build)](https://travis-ci.org/beryx-gist/badass-jlink-example-log4j2-javafx)

## Video Chooser using JavaFX ##

Javafx program use for create a video chooser. Users have a choice between two videos and they can select one of them

### Configuration

Create dir app/externalResources near to the exe and put config.properties file which contains :

```
choiceNumbers = 2 //The number of choice

//List titles of videos
video.1.1 = Title video 1
video.1.2 = Title video 2

video.2.1 = Title video 1
video.2.2 = Title video 2

etc ...
```

Put images of choices in "images" folder and videos in "videos" folder.

### Usage
**Running with gradle:**
```
./gradlew run
```

A window containing home background appears.

A click launch the introduction video.
After that two choice is possible. When clicking on image the selected video start and etc until finishing.


**Creating and executing a custom runtime image:**
```
./gradlew jlink
cd build/image/bin
./helloFX
```


**Creating installable packages**
```
./gradlew jpackage
```

The packages are created in the `build/jpackage` directory.
