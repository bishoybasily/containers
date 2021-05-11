### Session 5.0 (Kubernetes)

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

##### Create ur first SERVICE to access the pod containers

* create a yaml file (for instance service.yaml) with the following contents

```yaml
apiVersion: v1
kind: Service
metadata:
  name: pod1
spec:
  selector:
    app: mysql
    type: database
    team: db_admins
    provider: oracle
  ports:
    - port: 3306
      targetPort: 3306
      protocol: TCP
  type: NodePort
```

* run ```kubectl apply -f /path/to/service.yaml``` to apply (create) this service
* verify that your service is created, use ```kubectl get services``` this will show a table of all services in the
  cluster (in the default namespace)

* use the same pod created from the previous session (pod.yaml), remember to remove the namespace value before applying
  it.

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: pod1
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
* now you can access mysql using the public ip of "any" node & the port shown in the retrieved service (the output
  of ```kubectl get services```) 
