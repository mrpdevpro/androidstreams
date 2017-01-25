# androidstreams
Android library providing similar to Java 8 streams for earlier versions of API25.
It was created with in mind to become obsolete when Android earlier versions will support streams.
But why wait for this to happen...
Using retrolmabda, we are able to write syntax like streams using one static method named streams.
e.g.

List<Integer> testListIntegers = Arrays.asList(2, 1, 3, 4, 10);

List<String> result = stream(testListIntegers)
                .filter(integer -> integer < 2)
                .map(integer -> "val:" + String.valueOf(integer))
                .filter(s -> s.endsWith("0"))
                .toList();
