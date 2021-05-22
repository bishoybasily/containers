### Session 6.0 (Kubernetes)

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
  the pod spec attributes run ```kubectl explain services.spec.ports```

##### Create ur first DEPLOYMENT

* create a yaml file (for instance deployment.yaml) with the following contents

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: containers-deployment
spec:
  replicas: 2
  strategy:
    type: RollingUpdate
  selector:
    matchLabels:
      app: containers
      type: springboot
  template:
    metadata:
      name: containers
      labels:
        app: containers
        type: springboot
    spec:
      containers:
        - name: containers
          image: bishoybasily/containers:1.0-SNAPSHOT
          resources:
            requests:
              cpu: "0.5"
              memory: "512Mi"
            limits:
              cpu: "2000m"
              memory: "2Gi"
          readinessProbe:
            httpGet:
              port: 8080
              path: /api
            initialDelaySeconds: 30
            periodSeconds: 10
            failureThreshold: 3
            successThreshold: 5
          livenessProbe:
            httpGet:
              port: 8080
              path: /api
            initialDelaySeconds: 30
            periodSeconds: 10
            failureThreshold: 3
            successThreshold: 1
```
* the spec of this deployment defines 4 important things:
  * `replicas`: how many instances of my pod will be running
  * `strategy`: how updating this deployment will affect the currently running pods:
      * `Recreate`: will terminate all the currently running pods and then creates the new pods
      * `RollingUpdate`: will create the new pods before terminating the previous ones and will move the traffic gradually to the new ones
  * `selector`: what other pods currently in the cluster that have to be included in this deployment which will be selected with labels matching
  * `template`: the full pod description (you can copy the entire content of your pod yaml file without the apiVersion and the kind and put it under the template)

* run ```kubectl apply -f /path/to/deployment.yaml``` to apply (create) this service
* verify that your deployment is created, use ```kubectl get deployments``` this will show a table of all deployment in the
  cluster (in the default namespace)
* now you can try to access the deployment with a service from the previous session
