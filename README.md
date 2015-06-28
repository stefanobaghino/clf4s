CLF4S
=====

A simple Scala library to read logs in Apache's Common Log Format, like the following:

```in24.inetnebr.com - - [01/Aug/1995:00:00:01 -0400] "GET /shuttle/missions/sts-68/news/sts-68-mcc-05.txt HTTP/1.0" 200 1839```

* Use the ```LogEntry``` companion to parse a line and get ```Either``` a ```Throwable``` or an actual ```LogEntry```
* Each ```LogEntry``` includes:
    * ```host: String```
    * ```identity: Option[String]```
    * ```user: Option[String]```
    * ```date: DateTime```
    * ```request: Request```
    * ```status: Int```
    * ```bytes: Option[Long]``` (missing for 3xx responses)
* Each ```Request``` includes:
    * ```method: Method``` (an ADT that models HTTP request methods)
    * ```resource: String```
    * ```protocol: Protocol``` (an ADT that models HTTP protocol versions)

You can import it with SBT like this:
* ```libraryDependencies += "me.baghino" % "clf4s_2.11" % "0.1.0"```

