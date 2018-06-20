
# ComGithubAppscodeKubeMonApiPrometheusSpec

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**interval** | **String** | Interval at which metrics should be scraped |  [optional]
**labels** | **Map&lt;String, String&gt;** | Labels are key value pairs that is used to select Prometheus instance via ServiceMonitor labels. |  [optional]
**namespace** | **String** | Namespace of Prometheus. Service monitors will be created in this namespace. |  [optional]
**port** | **Integer** | Port number for the exporter side car. |  [optional]



