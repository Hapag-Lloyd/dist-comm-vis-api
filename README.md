[![Actions](https://github.com/Hapag-Lloyd/dist-comm-vis-api/workflows/Release/badge.svg)](https://github.com/Hapag-Lloyd/dist-comm-vis-api/actions) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.hlag.tools.commvis/api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.hlag.tools.commvis/api)

# API for Distributed Communication Visualizer
This project contains some API classes to allow users to define their own scanners. This way the 
[Distributed Communication Visualizer](https://github.com/Hapag-Lloyd/dist-comm-vis) can be extended.

# Maven Dependency
```
<dependency>
    <groupId>com.hlag.tools.commvis</groupId>
    <artifactId>api</artifactId>
    <version>2.5.5</version>
    <scope>provided</scope>
</dependency>
```
## Writing A User-Defined Scanner
Before writing your own/private scanner: If you are solving a general use cases, please think about
contributing to https://github.com/Hapag-Lloyd/dist-comm-vis to help the community. Thanks!

The scanner is automatically detected if the `Service` annotation from Spring is used and the scanner
is placed in the `com.hlag.tools.commvis.analyzer.scanner.user` package.

```java
package com.hlag.tools.commvis.analyzer.scanner.user;
// use this package name! Otherwise the scanner is not found

import com.hlag.tools.commvis.analyzer.model.ISenderReceiverCommunication;
import java.util.Collections;
import org.springframework.stereotype.Service;

@Service
public class MyUserDefinedScanner implements IScannerService {
    @Override
    public Collection<ISenderReceiverCommunication> scanSenderAndReceiver(String rootPackageName) {
        // do whatever is necessary
        return Collections.emptySet();
    }
}
```

## Annotate Producer Methods
Scanning consumers (like http(s) endpoints or JMS receivers) is simple, but detecting the producers
(e.g. methods calling a http(s) endpoint) is much harder or even impossible. To solve this problem a
special annotation is provided:
- `@VisualizeHttpsCall(type="GET", path="/customer/{customerId}", projectId="1234", projectName="Customer Service")`

Note 1: The `projectName` is optional and might be nice to see in the code.

Note 2: There is always an annotation of the same name but ending in `s` (e.g. `@VisualizeHttpsCalls`) in case you need
to place more than one annotation on the method.

## Annotate Consumer Methods

- `VisualizeSqsConsumer(queueName="location_sync", projectName="master data")`