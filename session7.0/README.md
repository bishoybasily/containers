### Session 7.0 (Kubernetes)

### VERY IMPORTANT

* any kubernetes yaml/json resource has to include the following 4 root attrs
  * apiVersion
  * kind
  * metadata
  * spec
* you can always use ```kubectl api-resources``` to list all the available resources in your cluster so that you can
  know the required values for the `apiVersion` & `kind`
* metadata is an object and must include a `name` attribute which is a unique identifier for the resource
* different tasks in kubernetes can be done in two ways
  * Imperative: using kubectl commands (run, expose, scale, rollout...) without having a yaml, examples:
    * to create a service for an existing pod or deployment `kubectl expose pod pod1 --type=LoadBalancer --port=8090`
    * to undo a deployment (revert it back to the previous state) `kubectl rollout undo deploy deployment2`
    * to create a namespace named hello `kubectl create ns hello`
    * the full documentation can be found [here](https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands)
  * Declarative: by applying a yaml resource that will update or create a particular resource as we saw in all of our previous sessions
    * the yaml file for this approach can be generated using the Imperative way by appending `--dry-run=client -oyaml` to your command which tells kubectl not to send the yaml for the cluster and prints it as yaml that can be saved in a file.

##### Create ur first CONFIGMAP & SECRET

* you can think of configmaps and secrets in kubernetes like a config file or config source that has different config files for your application.
* configmaps and secrets can be mounted to one or more pod as a collection of environment variables or as a volume where every key represents a file and its value will be the file content.
* secrets are base64 encoded by default (or encrypted based on your cluster configuration) unlike config maps which keeps their values as plain text.
* writing a yaml file manually (the Declarative way) for configmaps or secrets is not common, as you have to handle the multi-line texts, and values encoding manually, that's why people tend to use the Imperative way more often for these two kinds.

###### ConfigMap example

* to create a configmap named config1 with two key-value pairs (DATABASE_HOST=mysql.com, CONFIG_PATH=/path/to/file.conf) run `kubectl create configmap config1 --from-literal=DATABASE_HOST=mysql.com --from-literal=CONFIG_PATH=/path/to/file.conf --dry-run=client -oyaml` remove the `--dry-run=client` param to execute it instead of only printing the yaml.
* to create a configmap named config2 with a file for instance app.conf run `kubectl create configmap config2 --from-file=/path/to/app.conf --dry-run=client -oyaml` remove the `--dry-run=client` param to execute it instead of only printing the yaml.
* you can list and describe them to verify that they are created successfully.

###### Secret example

* to create a secret named secret1 with two key-value pairs (DATABASE_USERNAME=root, DATABASE_PASSWORD=toor) run `kubectl create secret generic secret1 --from-literal=DATABASE_USERNAME=root --from-literal=DATABASE_PASSWORD=toor --dry-run=client -oyaml` remove the `--dry-run=client` param to execute it instead of only printing the yaml.
* to create a secret named secret2 with a file for instance app.conf run `kubectl create secret generic secret2 --from-file=/path/to/app.conf --dry-run=client -oyaml` remove the `--dry-run=client` param to execute it instead of only printing the yaml.
* to create a tls secret named website-tls from a server.key & server.cert run `kubectl create secret tls --key=/path/to/server.key --cert=/path/to/server.cert --dry-run=client -oyaml` remove the `--dry-run=client` param to execute it instead of only printing the yaml.
* check the yaml output for the value to see the encoded value, if you're on linux you can decode it using `echo -n 'encodedValue' | base64 --decode` this will output the original value or `echo -n 'plainTextValue' | base64` to encode the plain text manually.
* you can list and describe them to verify that they are created successfully.

###### Usage from a pod

below a sample pod uses the previously created configmaps & secrets:
  * configmap `config1` is used as collection of env vars
  * secret `secret1` is used as collection of env vars
  * configmap `config2` is used as a volume mounted at `/data/configs/` inside the container
  * secret `secret2` is used as a volume mounted at `/data/secrets/` inside the container

```yaml
apiVersion: v1
kind: Pod
metadata:
  labels:
    run: pod1
  name: pod1
spec:
  containers:
    - image: repository/application:1.0
      name: pod1
      envFrom:
        - configMapRef:
            name: config1
        - secretRef:
            name: secret1
      volumeMounts:
        - mountPath: /data/configs/
          name: configmap1-volume
        - mountPath: /data/secrets/
          name: secret1-volume
  volumes:
    - name: configmap1-volume
      configMap:
        name: config2
    - name: secret1-volume
      secret:
        secretName: secret2
```