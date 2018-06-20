# Running examples

```sh
export REPO_ROOT=/path/to/client-java/repo

cd ${REPO_ROOT}/kubernetes
mvn install

cd ${REPO_ROOT}/examples
mvn package
mvn exec:java -Dexec.mainClass="com.kubedb.client.examples.Example"
```

