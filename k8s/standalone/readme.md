# standalone kubernetes deploy

## k8s init
    参考k8s.txt
    kubectl create namespace flink
    kubectl create serviceaccount flink -n flink
    kubectl create clusterrolebinding flink-role-binding-flink --clusterrole=edit --serviceaccount=flink:flink

## flink run on session
    在 Kubernetes 上部署一个基本的 Flink session 集群 时，一般包括下面三个组件：
    运行 JobManager 的 Deployment；
    运行 TaskManagers 的 Deployment；
    暴露 JobManager 上 REST 和 UI 端口的 Service；

### configmap
    kubectl create configmap my-config --from-file=key1=../flink-conf.yaml  --from-file=key2=../log4j.properties -n flink
    kubectl describe configmap my-config

    kubectl apply -f ./flink-configuration-configmap.yaml
    kubectl apply -f ./flink-reactive-mode-configuration-configmap.yaml

    kubectl delete configmap flink-config -n flink

### service 
    kubectl apply -f ./jobmanager-service.yaml
    kubectl apply -f ./jobmanager-rest-service.yaml

    kubectl apply -f ./taskmanager-query-state-service.yaml

    kubectl get svc flink-jobmanager-rest -n flink
    kubectl describe svc flink-jobmanager-rest -n flink

### deployment
    kubectl apply -f ./session/jobmanager-session-deployment-non-ha.yaml
    kubectl apply -f ./session/jobmanager-session-deployment-ha.yaml

    kubectl apply -f ./session/taskmanager-session-deployment.yaml

    kubectl delete -f ./session/jobmanager-session-deployment-non-ha.yaml

### run job
    运行 kubectl port-forward ${flink-jobmanager-pod} 8081:8081 将 jobmanager 的 web ui 端口映射到本地 8081。
    在浏览器中导航到 http://localhost:8081 页面。
    此外，也可以使用如下命令向集群提交作业：
    ./bin/flink run -m localhost:30081 \
        ./examples/streaming/TopSpeedWindowing.jar

    ./bin/flink stop -m localhost:30081 eb42f08d310d5903df9fb4f5a1a61a87
    ./bin/flink cancel -m localhost:30081 eb42f08d310d5903df9fb4f5a1a61a87

### 停止运行flink集群或任务
    kubectl delete -f ./jobmanager-service.yaml -n flink
    kubectl delete -f ./jobmanager-rest-service.yaml -n flink
    kubectl delete -f ./flink-configuration-configmap.yaml -n flink

    kubectl delete -f ./session/taskmanager-session-deployment.yaml -n flink
    kubectl delete -f ./session/jobmanager-session-deployment-non-ha.yaml -n flink


## flink run on application 
    在 Kubernetes 上部署一个基本的 Flink Application 集群 时，一般包括下面三个组件：
    一个运行 JobManager 的 Application；
    运行若干个 TaskManager 的 Deployment；
    暴露 JobManager 上 REST 和 UI 端口的 Service；

### job artifacts 定义
    参数必须可以从 资源定义示例 中的 job-artifacts-volume 处获取
        挂载为主机的本地目录
        任何其它可用类型的 volume 来提供 job artifact
        
    构建一个已经包含 job artifacts 参数的自定义镜像。
        Dockerfile: 需要捆绑用户代码
        FROM apache/flink:1.16.0-scala_2.12
        RUN mkdir -p $FLINK_HOME/usrlib
        COPY ./k8s/standalone/jar/flink-shaded-hadoop-2-2.8.3-10.0.jar $FLINK_HOME/usrlib/TopSpeedWindowing.jar
        COPY ./examples/streaming/TopSpeedWindowing.jar $FLINK_HOME/usrlib/TopSpeedWindowing.jar
    
        docker build -f ./Dockerfile -t flink-xander:1.16.0-scala_2.12 .
        docker tag flink-xander:1.16.0-scala_2.12 192.168.10.217:8074/bigdata/flink-xander:1.16.0-scala_2.12
        docker push 192.168.10.217:8074/bigdata/flink-xander:1.16.0-scala_2.12

### configmap
    kubectl apply -f ./flink-configuration-configmap.yaml
    kubectl apply -f ./flink-reactive-mode-configuration-configmap.yaml

### service
    kubectl apply -f ./jobmanager-service.yaml
    kubectl apply -f ./jobmanager-rest-service.yaml

    kubectl apply -f ./taskmanager-query-state-service.yaml

    kubectl get svc -n flink

### deployment run job
    kubectl apply -f ./application/jobmanager-application-non-ha.yaml
    kubectl apply -f ./application/jobmanager-application-ha.yaml

    kubectl apply -f ./application/taskmanager-job-deployment.yaml






