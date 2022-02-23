# GreenLAC
The AWS Greengrass Lambda Autoscaler Core (GreenLAC) is a load balancer and autoscaler
for AWS Greengrass that leverages core resources for scaling AWS Lambdas in combination
with the edge IoT devices running AWS Greengrass Core.

## Architecture
GreenLAC is a [AWS Greengrass component](https://github.com/aws-greengrass/aws-greengrass-software-catalog#community-components) that will 
soon be available to the community. It uses the core component of Greengrass, the Nucleus, to forward requests to local Lambdas managed by the 
Lambda Manager. GreenGrass scales out Lambdas in the Core cloud whenever its local resources reach saturation. 

![](images/architecture.jpg)
## Build Instruction

### Download
```
git clone https://github.com/pacslab/GreenLAC.git
```

### Prerequisite Setup
- AWS Greengrass Nucleus (Core)
- AWS Greengrass LambdaManager
- AWS Greengrass CLI

### Build From Source
Please make sure Java 11 and Maven are installed before building the package. To build package, run:

```
mvn clean package
```

You need to upload the ``greenlac.jar`` to a S3 Bucket on AWS.

## Component Deployment on AWS 
We have provided the JSON recipe for deployment [here](https://github.com/pacslab/GreenLAC/blob/main/greengrass/deployment.json). Please make sure to 
change any configuration and parameters according to your deployment needs. 
```json
  "ComponentConfiguration": {
    "DefaultConfiguration": {
		"greenlac.endpoint.core": "https://gydrsqutj5.execute-api.us-east-1.amazonaws.com/default/230mb_model_1_image",
		"greenlac.scaling.policy": "core-edge-pc",
		"greenlac.scaling.utilizationThreshold.cpu": 50,
		"greenlac.loadBalancer.buffersize": 100
    }
  }
```
## Log Debug
On device running Greengrass core, logs are stored in specific greengrass folder.
For GreenLAC log, check /greengrass/v2/logs/GrenLAC.log
```log
2022-02-23T01:36:25.149Z [INFO] (Copier) GreenLAC-a: stdout. [30m2022-02-23 01:36:25,147 INFO  [http-nio-8080-exec-18] com.jaimedantas.greenlac.loadbalancer.Lambda: Sending request to: https://gydrsqutj5.execute-api.us-east-1.amazonaws.com/default/230mb_model_1_image. {scriptName=services.GreenLAC-a.lifecycle.Run, serviceName=GreenLAC-a, currentState=RUNNING}
2022-02-23T01:36:25.159Z [INFO] (Copier) GreenLAC-a: stdout. [30m2022-02-23 01:36:25,156 INFO  [scheduling-1] com.jaimedantas.greenlac.monitor.Metrics: CPU: 2.5 % - Memory: 7.998381370355275 %. {scriptName=services.GreenLAC-a.lifecycle.Run, serviceName=GreenLAC-a, currentState=RUNNING}
2022-02-23T01:36:25.184Z [INFO] (Copier) GreenLAC-a: stdout. [30m2022-02-23 01:36:25,181 INFO  [http-nio-8080-exec-20] com.jaimedantas.greenlac.controller.Controller: Request to ml endpoint - 87e86207-218f-4b9c-afb3-5974b811bede. {scriptName=services.GreenLAC-a.lifecycle.Run, serviceName=GreenLAC-a, currentState=RUNNING}

```
## Citation
This is work is currently under review.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

