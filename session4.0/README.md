### Session 4.1 (Kubernetes)

### VERY IMPORTANT

* any kubernetes yaml/json resource has to include the following 4 root attrs
  * apiVersion
  * kind
  * metadata
  * spec
* you can always use ```kubectl api-resources``` to list all the available resources in your cluster so that you can
  know the required values for the `apiVersion` & `kind`
* metadata is an object and must include a `name` attribute which is a unique identifier for the resource
* you can always refer to ```kubectl explain``` for documentation about a particular resource for example to know what
  the pod spec attributes run ```kubectl explain pods.spec```

##### Create ur first NAMESPACE and update the POD

* create a yaml file (for instance payload.yaml) with the following contents which represents the following:
  * a pod named `pod1`
  * has two containers `nginx:1.19.9-alpine` & `redis:6.2-buster`

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: pod1
spec:
  containers:
    - name: nginx
      image: nginx:1.19.9-alpine
    - name: redis
      image: redis:6.2-buster
```

* run ```kubectl apply -f /path/to/payload.yaml``` to apply (create) this pod
* verify that your pod is running, use ```kubectl get pods``` this will show a table of all the running pods
* to get a full description of ur pod use ```kubectl describe pods pod1```, `pod1` is the name we specified in the yaml
  resource in `metadata.name`
* to verify that both are running on the same network and can access each other's ports on localhost
  * jump in redis buster shell session using ```kubectl exec -it pod1 -c redis -- /bin/bash```, redis is the container
    name we specified in `spec.containers[1].name`, remember that in case of multi container pod
    only `-c container-name` has to be specified to tell kubectl which container to use.
  * install curl (inside "redis" container) using ```apt update && apt install curl -y```
  * access nginx with curl (from inside "redis" container) using ```curl localhost:80```
  * great, now```exit``` this session
* finally, delete the pod using ```kubectl delete pods pod1```
