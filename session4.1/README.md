### Session 4.1 (Kubernetes)

### VERY IMPORTANT

* any kubernetes yaml/json resource has to include the following 4 root attrs
  * apiVersion
  * kind
  * metadata
  * spec (is optional for namespaces)
* you can always use ```kubectl api-resources``` to list all the available resources in your cluster so that you can
  know the required values for the `apiVersion` & `kind`
* metadata is an object and must include a `name` attribute which is a unique identifier for the resource
* you can always refer to ```kubectl explain``` for documentation about a particular resource for example to know what
  the pod spec attributes run ```kubectl explain pods.spec.containers```

##### Create ur first NAMESPACE and update the POD

* create a yaml file (for instance namespace.yaml) with the following contents

```yaml
apiVersion: v1
kind: Namespace
metadata:
  name: namespace1
```

* run ```kubectl apply -f /path/to/namespace.yaml``` to apply (create) this namespace
* verify that your namespace is created, use ```kubectl get namespaces``` this will show a table of all namespaces in
  the cluster

* create a yaml file (for instance payload.yaml) with the following contents which represents the following:
  * a pod named `pod1`
  * has one container `mysql:8`
  * has one volume of type hostPath and uses the `/data/mysql` directory
  * mysql container has on env var `MYSQL_ROOT_PASSWORD=toor`
  * mysql container uses one volume mounted inside it as `/var/lib/mysql`

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: pod1
  namespace: namespace1
spec:
  containers:
    - name: mysql
      image: mysql:8
      env:
        - name: MYSQL_ROOT_PASSWORD
          value: toor
      volumeMounts:
        - name: volume01
          mountPath: /var/lib/mysql
  volumes:
    - name: volume01
      hostPath:
        path:  /data/mysql
```

* apply the pod resource again (make sure that pod created in the previous session is removed before creating it,
  otherwise you'll get an error)
* make sure that the pod is running, and it's part of namespace1 using ```kubectl get pods -n namespace1```
