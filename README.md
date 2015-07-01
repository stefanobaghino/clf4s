CLF4S
=====

A simple Scala library to read logs in the [Common Log Format](https://en.wikipedia.org/wiki/Common_Log_Format), like the following:

```127.0.0.1 - frank [10/Oct/2000:13:55:36 -0700] "GET /apache_pb.gif HTTP/1.0" 200 2326```

Just call the ```LogEntry``` function to parse a line and get ```Either``` a ```Throwable``` or an actual ```LogEntry```.

Model
-----

The ```LogEntry```:
* ```host: String```
* ```identity: Option[String]```
* ```user: Option[String]```
* ```date: DateTime```
* ```request: Request```
* ```status: Int```
* ```bytes: Option[Long]``` (missing for 3xx responses)

The ```Request```:
* ```method: Method``` (an ADT that models HTTP request methods)
* ```resource: String```
* ```protocol: Protocol``` (an ADT that models HTTP protocol versions)

How to use it
-------------

Just import it with SBT:
* ```libraryDependencies += "me.baghino" % "clf4s_2.11" % "0.0.1"```

