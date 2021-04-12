### Session 1.0
##### use ubuntu as the base image and add wget (cli tool) to it and tag the new image with "session:1.0"

##### cd into this directory and run
```docker build -t session:1.0 .```
##### run the created image with command ```wget --help```
```docker run session:1.0 wget --help```

