# native kubernetes deploy

## k8s init
    参考k8s.txt
    kubectl create namespace flink
    kubectl create serviceaccount flink -n flink
    kubectl create clusterrolebinding flink-role-binding-flink --clusterrole=edit --serviceaccount=flink:flink

## flink run on session
### jobmanager start
    ./bin/kubernetes-session.sh \
        -Dkubernetes.cluster-id=my-first-flink-cluster \
        -Dkubernetes.namespace=flink \
        -Dkubernetes.service-account=flink \
        -Dkubernetes.Djobmanager.cpu=0.5 \
        -Djobmanager.memory.process.size=700m \
        -Dkubernetes.taskmanager.cpu=0.5 \
        -Dtaskmanager.memory.process.size=1000m \
        -Dtaskmanager.numberOfTaskSlots=1

### jobmanager service
    kubectl port-forward service/my-first-flink-cluster-rest 8081:8081 -n flink

### job run taskmanager start
    ./bin/flink run \
        --target kubernetes-session \
        -Dkubernetes.cluster-id=my-first-flink-cluster \
        -Dkubernetes.namespace=flink \
        -Dkubernetes.service-account=flink \
        /var/www/project/xander/flink/flink-1.16.0/examples/streaming/TopSpeedWindowing.jar

## flink run on application 
### Dockerfile: 需要捆绑用户代码
    FROM apache/flink:1.16.0-scala_2.12
    RUN mkdir -p $FLINK_HOME/usrlib
    COPY ./examples/streaming/TopSpeedWindowing.jar $FLINK_HOME/usrlib/TopSpeedWindowing.jar

    docker build -f ./Dockerfile -t flink-xander:1.16.0-scala_2.12 .
    docker tag flink-xander:1.16.0-scala_2.12 192.168.10.217:8074/bigdata/flink-xander:1.16.0-scala_2.12
    docker push 192.168.10.217:8074/bigdata/flink-xander:1.16.0-scala_2.12

### job run jobmanage & taskmanager start
    ./bin/flink run-application \
        --target kubernetes-application \
        -Dkubernetes.cluster-id=my-first-application-cluster \
        -Dkubernetes.container.image=192.168.10.217:8074/bigdata/flink-xander:1.16.0-scala_2.12 \
        -Dkubernetes.container.image.pull-secrets="harbor" \
        -Dkubernetes.namespace=flink \
        -Dkubernetes.service-account=flink \
        -Dkubernetes.Djobmanager.cpu=0.5 \
        -Djobmanager.memory.process.size=700m \
        -Dkubernetes.taskmanager.cpu=0.5 \
        -Dtaskmanager.memory.process.size=1000m \
        -Dtaskmanager.numberOfTaskSlots=1 \
        -Dparallelism.default=1 \
        local:///opt/flink/usrlib/TopSpeedWindowing.jar

### jobmanager service
    kubectl port-forward service/my-first-application-cluster-rest 8081:8081 -n flink

