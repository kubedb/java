# ApisApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAPIVersions**](ApisApi.md#getAPIVersions) | **GET** /apis/ | 


<a name="getAPIVersions"></a>
# **getAPIVersions**
> V1APIGroupList getAPIVersions()



get available API versions

### Example
```java
// Import classes:
//import com.kubedb.client.ApiException;
//import com.kubedb.client.apis.ApisApi;


ApisApi apiInstance = new ApisApi();
try {
    V1APIGroupList result = apiInstance.getAPIVersions();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ApisApi#getAPIVersions");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**V1APIGroupList**](V1APIGroupList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/yaml, application/vnd.kubernetes.protobuf
 - **Accept**: application/json, application/yaml, application/vnd.kubernetes.protobuf

