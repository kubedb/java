
# V1alpha1RedisSpec

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**affinity** | [**V1Affinity**](V1Affinity.md) | If specified, the pod&#39;s scheduling constraints |  [optional]
**doNotPause** | **Boolean** | If DoNotPause is true, controller will prevent to delete this Postgres object. Controller will create same Postgres object and ignore other process. |  [optional]
**imagePullSecrets** | [**List&lt;V1LocalObjectReference&gt;**](V1LocalObjectReference.md) | ImagePullSecrets is an optional list of references to secrets in the same namespace to use for pulling any of the images used by this PodSpec. If specified, these secrets will be passed to individual puller implementations for them to use. |  [optional]
**monitor** | [**ComGithubAppscodeKubeMonApiAgentSpec**](ComGithubAppscodeKubeMonApiAgentSpec.md) | Monitor is used monitor database instance |  [optional]
**nodeSelector** | **Map&lt;String, String&gt;** | NodeSelector is a selector which must be true for the pod to fit on a node |  [optional]
**replicas** | **Integer** | Number of instances to deploy for a Redis database. |  [optional]
**resources** | [**V1ResourceRequirements**](V1ResourceRequirements.md) | Compute Resources required by the sidecar container. |  [optional]
**schedulerName** | **String** | If specified, the pod will be dispatched by specified scheduler. If not specified, the pod will be dispatched by default scheduler. |  [optional]
**storage** | [**V1PersistentVolumeClaimSpec**](V1PersistentVolumeClaimSpec.md) | Storage spec to specify how storage shall be used. | 
**tolerations** | [**List&lt;V1Toleration&gt;**](V1Toleration.md) | If specified, the pod&#39;s tolerations. |  [optional]
**version** | **String** | Version of Redis to be deployed. | 



