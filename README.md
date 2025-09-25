# parse



## Build and run


With JDK21
```bash
mvn package
java -jar target/parse.jar
```

## Exercise the application

Basic:
```
curl -X GET http://localhost:8080/simple-greet
Hello World!
```


JSON:
```
curl -X GET http://localhost:8080/greet
{"message":"Hello World!"}

curl -X GET http://localhost:8080/greet/Joe
{"message":"Hello Joe!"}

curl -X PUT -H "Content-Type: application/json" -d '{"greeting" : "Hola"}' http://localhost:8080/greet/greeting

curl -X GET http://localhost:8080/greet/Jose
{"message":"Hola Jose!"}
```



## Exercise Webclient

First, start the server:

```
java -jar target/parse.jar
```

Note the port number that it displays. For example:

```
WEB server is up! http://localhost:8080/simple-greet
```

Then run the client, passing the port number. It will connect
to the server:

```
java -cp "target/classes:target/libs/*" pathcreator.ru.parse.WebClientMain PORT
```



## Try JWT

```bash
curl -u "jack:changeit" http://localhost:8080/propagate
curl -u "jack:changeit" http://localhost:8080/override
curl -u "jill:changeit" http://localhost:8080/propagate
curl -u "jill:changeit" http://localhost:8080/override
```


## Building the Docker Image

```
docker build -t parse .
```

## Running the Docker Image

```
docker run --rm -p 8080:8080 parse:latest
```

Exercise the application as described above.
                                
# proxy
