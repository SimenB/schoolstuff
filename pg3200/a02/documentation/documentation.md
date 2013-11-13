#Assignment 02 - PG3200-13
####by Simen Bekkhus
---
###Quick description of the images:
Image 1 is the opening screen, showing just a blank map and 2 tabs

Image 2 shows the same thing, except the action-item is pressed, so My Location is enabled (pressing it again will disable it)

Image 3 shows what's shown after goinf to the Forecasts-tab with no data added

Image 4 the screen after the user pressed on the map. Data is being fetched from the server

Image 5 Shows the list of forecasts after 3 batches of data has been fetched

Image 6 shows the scrteen after either a row in the previous list has been pressedf, or a marker on the map (image 7)

Image 7 shows the map after adding a couple of forecasts, as well as the menu - allowing you to delete data on the device

---
###Reasoning around choices
My database-design is rather mediocre. It's something I made the first weekend, and never really got around to refactoring. Its PKs and FKs are based on the hash of objects rather than a more logical ID, as that was the quickest way to get it working.

I used Gson as JSON-parser, as I have a lot of experience with it from work and other projects, and I know it's structure quite well.

The use of Maven was mainly, again, as a habit. It allowed me to easily integrate Google Play Services though, so that's good.

And as usual, little to no commenting is the norm...

---
## Device used for development was a Sony Xperia Z1, Android 4.2.2
---

###Sources:
 - http://stackoverflow.com/questions/10768449/android-can-i-put-asynctask-in-a-separate-class-and-have-a-callback
 - Icons:
  - https://cdn3.iconfinder.com/data/icons/weather-and-forecast/51/Weather_icons_grey-03-512.png
  - http://icons.iconarchive.com/icons/visualpharm/icons8-metro-style/512/Maps-and-Geolocation-Define-location-icon.png
 - In addition I've made use of multple chunks of my own code, much of which I probably found on blogs or StackOverflow at one time, but didn't write down the source.
