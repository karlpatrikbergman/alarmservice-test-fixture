# How to
### Prerequisites
docker-compose and docker installed
## Set up test fixture and run acceptance test from IntelliJ
#### Set up text fixture
```shell
$ docker-compose -f docker-compose-setup-test-fixture.yml up
```
This will bring up a number of docker containers running Alarmservice, MariaDB, 
and two TM nodes. It will also run a container (once) that adds cards to the TM nodes.

Note:
Alarmservice docker image must be build and pushed first.
 
#### Run alarmservice-acceptance-test from IDE
Right click on:  
com.infinera.metro.alarm.MainApp  
and choose run (or ctrl-shift-10)

This will run tests against previously started test fixture.

## Set up test fixture and run acceptance test with docker-compose 

#### Build alarmservice-acceptance-test docker image
```shell
$ docker-compose build alarmservice-acceptance-test 
``` 
docker-compose will by default use docker-compose.yml file  

#### Run local docker registry
```shell
$ docker run -d -p 5000:5000 --name registry registry:2
```

#### Push alarmservice-acceptance-test docker image local docker registry
```shell
$ docker-compose push alarmservice-acceptance-test
```
#### Set up test fixture and run acceptance test with docker compose
```shell
$ docker-compose -f docker-compose-setup-test-fixture.yml -f docker-compose-run-alarmservice-acceptance-test.yml up
 ```