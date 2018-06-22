# client-java

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>com.kubedb</groupId>
    <artifactId>client-java</artifactId>
    <version>0.1.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "com.kubedb:client-java:0.1.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/client-java-0.1.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import com.kubedb.client.*;
import com.kubedb.client.auth.*;
import com.kubedb.client.models.*;
import com.kubedb.client.apis.ApisApi;

import java.io.File;
import java.util.*;

public class ApisApiExample {

    public static void main(String[] args) {
        
        ApisApi apiInstance = new ApisApi();
        try {
            V1APIGroupList result = apiInstance.getAPIVersions();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ApisApi#getAPIVersions");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://localhost*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*ApisApi* | [**getAPIVersions**](docs/ApisApi.md#getAPIVersions) | **GET** /apis/ | 
*KubedbComApi* | [**getAPIGroup**](docs/KubedbComApi.md#getAPIGroup) | **GET** /apis/kubedb.com/ | 
*KubedbComV1alpha1Api* | [**createNamespacedDormantDatabase**](docs/KubedbComV1alpha1Api.md#createNamespacedDormantDatabase) | **POST** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/dormantdatabases | 
*KubedbComV1alpha1Api* | [**createNamespacedElasticsearch**](docs/KubedbComV1alpha1Api.md#createNamespacedElasticsearch) | **POST** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/elasticsearches | 
*KubedbComV1alpha1Api* | [**createNamespacedMemcached**](docs/KubedbComV1alpha1Api.md#createNamespacedMemcached) | **POST** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/memcacheds | 
*KubedbComV1alpha1Api* | [**createNamespacedMongoDB**](docs/KubedbComV1alpha1Api.md#createNamespacedMongoDB) | **POST** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/mongodbs | 
*KubedbComV1alpha1Api* | [**createNamespacedMySQL**](docs/KubedbComV1alpha1Api.md#createNamespacedMySQL) | **POST** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/mysqls | 
*KubedbComV1alpha1Api* | [**createNamespacedPostgres**](docs/KubedbComV1alpha1Api.md#createNamespacedPostgres) | **POST** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/postgreses | 
*KubedbComV1alpha1Api* | [**createNamespacedRedis**](docs/KubedbComV1alpha1Api.md#createNamespacedRedis) | **POST** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/redises | 
*KubedbComV1alpha1Api* | [**createNamespacedSnapshot**](docs/KubedbComV1alpha1Api.md#createNamespacedSnapshot) | **POST** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/snapshots | 
*KubedbComV1alpha1Api* | [**deleteCollectionNamespacedDormantDatabase**](docs/KubedbComV1alpha1Api.md#deleteCollectionNamespacedDormantDatabase) | **DELETE** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/dormantdatabases | 
*KubedbComV1alpha1Api* | [**deleteCollectionNamespacedElasticsearch**](docs/KubedbComV1alpha1Api.md#deleteCollectionNamespacedElasticsearch) | **DELETE** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/elasticsearches | 
*KubedbComV1alpha1Api* | [**deleteCollectionNamespacedMemcached**](docs/KubedbComV1alpha1Api.md#deleteCollectionNamespacedMemcached) | **DELETE** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/memcacheds | 
*KubedbComV1alpha1Api* | [**deleteCollectionNamespacedMongoDB**](docs/KubedbComV1alpha1Api.md#deleteCollectionNamespacedMongoDB) | **DELETE** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/mongodbs | 
*KubedbComV1alpha1Api* | [**deleteCollectionNamespacedMySQL**](docs/KubedbComV1alpha1Api.md#deleteCollectionNamespacedMySQL) | **DELETE** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/mysqls | 
*KubedbComV1alpha1Api* | [**deleteCollectionNamespacedPostgres**](docs/KubedbComV1alpha1Api.md#deleteCollectionNamespacedPostgres) | **DELETE** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/postgreses | 
*KubedbComV1alpha1Api* | [**deleteCollectionNamespacedRedis**](docs/KubedbComV1alpha1Api.md#deleteCollectionNamespacedRedis) | **DELETE** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/redises | 
*KubedbComV1alpha1Api* | [**deleteCollectionNamespacedSnapshot**](docs/KubedbComV1alpha1Api.md#deleteCollectionNamespacedSnapshot) | **DELETE** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/snapshots | 
*KubedbComV1alpha1Api* | [**deleteNamespacedDormantDatabase**](docs/KubedbComV1alpha1Api.md#deleteNamespacedDormantDatabase) | **DELETE** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/dormantdatabases/{name} | 
*KubedbComV1alpha1Api* | [**deleteNamespacedElasticsearch**](docs/KubedbComV1alpha1Api.md#deleteNamespacedElasticsearch) | **DELETE** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/elasticsearches/{name} | 
*KubedbComV1alpha1Api* | [**deleteNamespacedMemcached**](docs/KubedbComV1alpha1Api.md#deleteNamespacedMemcached) | **DELETE** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/memcacheds/{name} | 
*KubedbComV1alpha1Api* | [**deleteNamespacedMongoDB**](docs/KubedbComV1alpha1Api.md#deleteNamespacedMongoDB) | **DELETE** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/mongodbs/{name} | 
*KubedbComV1alpha1Api* | [**deleteNamespacedMySQL**](docs/KubedbComV1alpha1Api.md#deleteNamespacedMySQL) | **DELETE** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/mysqls/{name} | 
*KubedbComV1alpha1Api* | [**deleteNamespacedPostgres**](docs/KubedbComV1alpha1Api.md#deleteNamespacedPostgres) | **DELETE** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/postgreses/{name} | 
*KubedbComV1alpha1Api* | [**deleteNamespacedRedis**](docs/KubedbComV1alpha1Api.md#deleteNamespacedRedis) | **DELETE** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/redises/{name} | 
*KubedbComV1alpha1Api* | [**deleteNamespacedSnapshot**](docs/KubedbComV1alpha1Api.md#deleteNamespacedSnapshot) | **DELETE** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/snapshots/{name} | 
*KubedbComV1alpha1Api* | [**getAPIResources**](docs/KubedbComV1alpha1Api.md#getAPIResources) | **GET** /apis/kubedb.com/v1alpha1/ | 
*KubedbComV1alpha1Api* | [**listDormantDatabaseForAllNamespaces**](docs/KubedbComV1alpha1Api.md#listDormantDatabaseForAllNamespaces) | **GET** /apis/kubedb.com/v1alpha1/dormantdatabases | 
*KubedbComV1alpha1Api* | [**listElasticsearchForAllNamespaces**](docs/KubedbComV1alpha1Api.md#listElasticsearchForAllNamespaces) | **GET** /apis/kubedb.com/v1alpha1/elasticsearches | 
*KubedbComV1alpha1Api* | [**listMemcachedForAllNamespaces**](docs/KubedbComV1alpha1Api.md#listMemcachedForAllNamespaces) | **GET** /apis/kubedb.com/v1alpha1/memcacheds | 
*KubedbComV1alpha1Api* | [**listMongoDBForAllNamespaces**](docs/KubedbComV1alpha1Api.md#listMongoDBForAllNamespaces) | **GET** /apis/kubedb.com/v1alpha1/mongodbs | 
*KubedbComV1alpha1Api* | [**listMySQLForAllNamespaces**](docs/KubedbComV1alpha1Api.md#listMySQLForAllNamespaces) | **GET** /apis/kubedb.com/v1alpha1/mysqls | 
*KubedbComV1alpha1Api* | [**listNamespacedDormantDatabase**](docs/KubedbComV1alpha1Api.md#listNamespacedDormantDatabase) | **GET** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/dormantdatabases | 
*KubedbComV1alpha1Api* | [**listNamespacedElasticsearch**](docs/KubedbComV1alpha1Api.md#listNamespacedElasticsearch) | **GET** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/elasticsearches | 
*KubedbComV1alpha1Api* | [**listNamespacedMemcached**](docs/KubedbComV1alpha1Api.md#listNamespacedMemcached) | **GET** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/memcacheds | 
*KubedbComV1alpha1Api* | [**listNamespacedMongoDB**](docs/KubedbComV1alpha1Api.md#listNamespacedMongoDB) | **GET** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/mongodbs | 
*KubedbComV1alpha1Api* | [**listNamespacedMySQL**](docs/KubedbComV1alpha1Api.md#listNamespacedMySQL) | **GET** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/mysqls | 
*KubedbComV1alpha1Api* | [**listNamespacedPostgres**](docs/KubedbComV1alpha1Api.md#listNamespacedPostgres) | **GET** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/postgreses | 
*KubedbComV1alpha1Api* | [**listNamespacedRedis**](docs/KubedbComV1alpha1Api.md#listNamespacedRedis) | **GET** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/redises | 
*KubedbComV1alpha1Api* | [**listNamespacedSnapshot**](docs/KubedbComV1alpha1Api.md#listNamespacedSnapshot) | **GET** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/snapshots | 
*KubedbComV1alpha1Api* | [**listPostgresForAllNamespaces**](docs/KubedbComV1alpha1Api.md#listPostgresForAllNamespaces) | **GET** /apis/kubedb.com/v1alpha1/postgreses | 
*KubedbComV1alpha1Api* | [**listRedisForAllNamespaces**](docs/KubedbComV1alpha1Api.md#listRedisForAllNamespaces) | **GET** /apis/kubedb.com/v1alpha1/redises | 
*KubedbComV1alpha1Api* | [**listSnapshotForAllNamespaces**](docs/KubedbComV1alpha1Api.md#listSnapshotForAllNamespaces) | **GET** /apis/kubedb.com/v1alpha1/snapshots | 
*KubedbComV1alpha1Api* | [**patchNamespacedDormantDatabase**](docs/KubedbComV1alpha1Api.md#patchNamespacedDormantDatabase) | **PATCH** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/dormantdatabases/{name} | 
*KubedbComV1alpha1Api* | [**patchNamespacedElasticsearch**](docs/KubedbComV1alpha1Api.md#patchNamespacedElasticsearch) | **PATCH** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/elasticsearches/{name} | 
*KubedbComV1alpha1Api* | [**patchNamespacedMemcached**](docs/KubedbComV1alpha1Api.md#patchNamespacedMemcached) | **PATCH** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/memcacheds/{name} | 
*KubedbComV1alpha1Api* | [**patchNamespacedMongoDB**](docs/KubedbComV1alpha1Api.md#patchNamespacedMongoDB) | **PATCH** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/mongodbs/{name} | 
*KubedbComV1alpha1Api* | [**patchNamespacedMySQL**](docs/KubedbComV1alpha1Api.md#patchNamespacedMySQL) | **PATCH** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/mysqls/{name} | 
*KubedbComV1alpha1Api* | [**patchNamespacedPostgres**](docs/KubedbComV1alpha1Api.md#patchNamespacedPostgres) | **PATCH** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/postgreses/{name} | 
*KubedbComV1alpha1Api* | [**patchNamespacedRedis**](docs/KubedbComV1alpha1Api.md#patchNamespacedRedis) | **PATCH** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/redises/{name} | 
*KubedbComV1alpha1Api* | [**patchNamespacedSnapshot**](docs/KubedbComV1alpha1Api.md#patchNamespacedSnapshot) | **PATCH** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/snapshots/{name} | 
*KubedbComV1alpha1Api* | [**readNamespacedDormantDatabase**](docs/KubedbComV1alpha1Api.md#readNamespacedDormantDatabase) | **GET** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/dormantdatabases/{name} | 
*KubedbComV1alpha1Api* | [**readNamespacedElasticsearch**](docs/KubedbComV1alpha1Api.md#readNamespacedElasticsearch) | **GET** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/elasticsearches/{name} | 
*KubedbComV1alpha1Api* | [**readNamespacedMemcached**](docs/KubedbComV1alpha1Api.md#readNamespacedMemcached) | **GET** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/memcacheds/{name} | 
*KubedbComV1alpha1Api* | [**readNamespacedMongoDB**](docs/KubedbComV1alpha1Api.md#readNamespacedMongoDB) | **GET** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/mongodbs/{name} | 
*KubedbComV1alpha1Api* | [**readNamespacedMySQL**](docs/KubedbComV1alpha1Api.md#readNamespacedMySQL) | **GET** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/mysqls/{name} | 
*KubedbComV1alpha1Api* | [**readNamespacedPostgres**](docs/KubedbComV1alpha1Api.md#readNamespacedPostgres) | **GET** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/postgreses/{name} | 
*KubedbComV1alpha1Api* | [**readNamespacedRedis**](docs/KubedbComV1alpha1Api.md#readNamespacedRedis) | **GET** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/redises/{name} | 
*KubedbComV1alpha1Api* | [**readNamespacedSnapshot**](docs/KubedbComV1alpha1Api.md#readNamespacedSnapshot) | **GET** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/snapshots/{name} | 
*KubedbComV1alpha1Api* | [**replaceNamespacedDormantDatabase**](docs/KubedbComV1alpha1Api.md#replaceNamespacedDormantDatabase) | **PUT** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/dormantdatabases/{name} | 
*KubedbComV1alpha1Api* | [**replaceNamespacedElasticsearch**](docs/KubedbComV1alpha1Api.md#replaceNamespacedElasticsearch) | **PUT** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/elasticsearches/{name} | 
*KubedbComV1alpha1Api* | [**replaceNamespacedMemcached**](docs/KubedbComV1alpha1Api.md#replaceNamespacedMemcached) | **PUT** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/memcacheds/{name} | 
*KubedbComV1alpha1Api* | [**replaceNamespacedMongoDB**](docs/KubedbComV1alpha1Api.md#replaceNamespacedMongoDB) | **PUT** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/mongodbs/{name} | 
*KubedbComV1alpha1Api* | [**replaceNamespacedMySQL**](docs/KubedbComV1alpha1Api.md#replaceNamespacedMySQL) | **PUT** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/mysqls/{name} | 
*KubedbComV1alpha1Api* | [**replaceNamespacedPostgres**](docs/KubedbComV1alpha1Api.md#replaceNamespacedPostgres) | **PUT** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/postgreses/{name} | 
*KubedbComV1alpha1Api* | [**replaceNamespacedRedis**](docs/KubedbComV1alpha1Api.md#replaceNamespacedRedis) | **PUT** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/redises/{name} | 
*KubedbComV1alpha1Api* | [**replaceNamespacedSnapshot**](docs/KubedbComV1alpha1Api.md#replaceNamespacedSnapshot) | **PUT** /apis/kubedb.com/v1alpha1/namespaces/{namespace}/snapshots/{name} | 


## Documentation for Models

 - [ComGithubAppscodeKubeMonApiAgentSpec](docs/ComGithubAppscodeKubeMonApiAgentSpec.md)
 - [ComGithubAppscodeKubeMonApiPrometheusSpec](docs/ComGithubAppscodeKubeMonApiPrometheusSpec.md)
 - [V1alpha1AzureSpec](docs/V1alpha1AzureSpec.md)
 - [V1alpha1BackupScheduleSpec](docs/V1alpha1BackupScheduleSpec.md)
 - [V1alpha1DormantDatabase](docs/V1alpha1DormantDatabase.md)
 - [V1alpha1DormantDatabaseList](docs/V1alpha1DormantDatabaseList.md)
 - [V1alpha1DormantDatabaseSpec](docs/V1alpha1DormantDatabaseSpec.md)
 - [V1alpha1DormantDatabaseStatus](docs/V1alpha1DormantDatabaseStatus.md)
 - [V1alpha1Elasticsearch](docs/V1alpha1Elasticsearch.md)
 - [V1alpha1ElasticsearchClusterTopology](docs/V1alpha1ElasticsearchClusterTopology.md)
 - [V1alpha1ElasticsearchList](docs/V1alpha1ElasticsearchList.md)
 - [V1alpha1ElasticsearchNode](docs/V1alpha1ElasticsearchNode.md)
 - [V1alpha1ElasticsearchSpec](docs/V1alpha1ElasticsearchSpec.md)
 - [V1alpha1ElasticsearchStatus](docs/V1alpha1ElasticsearchStatus.md)
 - [V1alpha1EtcdSpec](docs/V1alpha1EtcdSpec.md)
 - [V1alpha1GCSSpec](docs/V1alpha1GCSSpec.md)
 - [V1alpha1InitSpec](docs/V1alpha1InitSpec.md)
 - [V1alpha1LocalSpec](docs/V1alpha1LocalSpec.md)
 - [V1alpha1Memcached](docs/V1alpha1Memcached.md)
 - [V1alpha1MemcachedList](docs/V1alpha1MemcachedList.md)
 - [V1alpha1MemcachedSpec](docs/V1alpha1MemcachedSpec.md)
 - [V1alpha1MemcachedStatus](docs/V1alpha1MemcachedStatus.md)
 - [V1alpha1MongoDB](docs/V1alpha1MongoDB.md)
 - [V1alpha1MongoDBList](docs/V1alpha1MongoDBList.md)
 - [V1alpha1MongoDBSpec](docs/V1alpha1MongoDBSpec.md)
 - [V1alpha1MongoDBStatus](docs/V1alpha1MongoDBStatus.md)
 - [V1alpha1MySQL](docs/V1alpha1MySQL.md)
 - [V1alpha1MySQLList](docs/V1alpha1MySQLList.md)
 - [V1alpha1MySQLSpec](docs/V1alpha1MySQLSpec.md)
 - [V1alpha1MySQLStatus](docs/V1alpha1MySQLStatus.md)
 - [V1alpha1Origin](docs/V1alpha1Origin.md)
 - [V1alpha1OriginSpec](docs/V1alpha1OriginSpec.md)
 - [V1alpha1Postgres](docs/V1alpha1Postgres.md)
 - [V1alpha1PostgresArchiverSpec](docs/V1alpha1PostgresArchiverSpec.md)
 - [V1alpha1PostgresList](docs/V1alpha1PostgresList.md)
 - [V1alpha1PostgresSpec](docs/V1alpha1PostgresSpec.md)
 - [V1alpha1PostgresStatus](docs/V1alpha1PostgresStatus.md)
 - [V1alpha1PostgresWALSourceSpec](docs/V1alpha1PostgresWALSourceSpec.md)
 - [V1alpha1Redis](docs/V1alpha1Redis.md)
 - [V1alpha1RedisList](docs/V1alpha1RedisList.md)
 - [V1alpha1RedisSpec](docs/V1alpha1RedisSpec.md)
 - [V1alpha1RedisStatus](docs/V1alpha1RedisStatus.md)
 - [V1alpha1S3Spec](docs/V1alpha1S3Spec.md)
 - [V1alpha1ScriptSourceSpec](docs/V1alpha1ScriptSourceSpec.md)
 - [V1alpha1Snapshot](docs/V1alpha1Snapshot.md)
 - [V1alpha1SnapshotList](docs/V1alpha1SnapshotList.md)
 - [V1alpha1SnapshotSourceSpec](docs/V1alpha1SnapshotSourceSpec.md)
 - [V1alpha1SnapshotSpec](docs/V1alpha1SnapshotSpec.md)
 - [V1alpha1SnapshotStatus](docs/V1alpha1SnapshotStatus.md)
 - [V1alpha1SnapshotStorageSpec](docs/V1alpha1SnapshotStorageSpec.md)
 - [V1alpha1SwiftSpec](docs/V1alpha1SwiftSpec.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author

hello@appscode.com

