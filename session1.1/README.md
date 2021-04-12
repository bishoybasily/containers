### Session 1.1
##### use session1.0 as the base image and specify the default entrypoint and command and then tag it with "session:1.1"

##### jump inside this directory and run
```docker build -t session:1.1 .```
##### run the created image without passing any command
```docker run session:1.1 wget --help```
##### run the image again but with a different command ```--version``` to override the default one
```docker run session:1.1 --version```
