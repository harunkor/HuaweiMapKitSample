# HuaweiMapKitSample

📌 What is Huawei Map Kit?

Huawei Map Kit is a SDK for map development. It covers map data of more than 200 countries and regions, and supports dozens of languages. With this SDK, we can easily integrate map-based functions into your apps.
Map Kit uses the WGS 84 GPS coordinate system, which meets most map development requirements outside China, including:
Map display: Displays buildings, roads, water systems, and Points of Interest (POIs).
Map interaction: Controls the interaction gestures and buttons on the map.
Map drawing: Adds location markers, map layers, overlays, and various shapes.


#### settings.gradle
``` //  
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        //Huawei repo maven link ********> Required to implement the library.
        maven {url 'https://developer.huawei.com/repo/'}
        // end *****
    }
}
rootProject.name = "Huawei Map Kit Sample"
include ':app'
```


<img src="https://github.com/harunkor/HuaweiMapKitSample/blob/master/Screenshot_20220127_223111.png?raw=true" width="350" height="800">&nbsp;<img src="https://github.com/harunkor/HuaweiMapKitSample/blob/master/Screenshot_20220127_223132.png?raw=true" width="350" height="800">&nbsp;<img src="https://github.com/harunkor/HuaweiMapKitSample/blob/master/Screenshot_20220127_224044.png?raw=true" width="350" height="800">&nbsp;<img src="https://github.com/harunkor/HuaweiMapKitSample/blob/master/Screenshot_20220127_224342.png?raw=true" width="350" height="800">



