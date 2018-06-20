
# V1alpha1SnapshotSpec

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**azure** | [**V1alpha1AzureSpec**](V1alpha1AzureSpec.md) |  |  [optional]
**databaseName** | **String** | Database name | 
**gcs** | [**V1alpha1GCSSpec**](V1alpha1GCSSpec.md) |  |  [optional]
**local** | [**V1alpha1LocalSpec**](V1alpha1LocalSpec.md) |  |  [optional]
**resources** | [**V1ResourceRequirements**](V1ResourceRequirements.md) | Compute Resources required by the sidecar container. |  [optional]
**s3** | [**V1alpha1S3Spec**](V1alpha1S3Spec.md) |  |  [optional]
**storageSecretName** | **String** |  |  [optional]
**swift** | [**V1alpha1SwiftSpec**](V1alpha1SwiftSpec.md) |  |  [optional]



