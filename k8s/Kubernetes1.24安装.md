# 基于rocky linux 版本8.6 安装Kubernetes 1.24
# 使用kubeadm安装模式
目标3 master 3node 
运行时为containerd


# 机器初始化部分 (和安装无关)
```bash
systemctl disable firewalld
systemctl stop firewalld
systemctl disable iptables
systemctl stop iptables
systemctl disable ufw
systemctl stop ufw



setenforce 0
sed 's/SELINUX=enforcing/SELINUX=disabled/g'  /etc/selinux/config   -i
cat /etc/selinux/config  |grep SELINUX=





# 内核参数,关闭了ipv6堆栈.
# 新版内核不存在参数tcp_tw_recycle,无需增加.

cat << EOF >> /etc/sysctl.conf
# Kernel parameter turning 
fs.file-max=1048576
net.ipv4.tcp_slow_start_after_idle = 0
net.core.somaxconn = 65534
net.ipv4.tcp_keepalive_time=300
net.ipv4.tcp_tw_reuse=1
net.ipv4.tcp_no_metrics_save=1
net.core.somaxconn=262144
net.ipv4.tcp_max_orphans=262144
net.ipv4.tcp_synack_retries=2
net.ipv4.tcp_syn_retries=2
net.ipv4.tcp_syncookies=0
net.ipv4.tcp_max_syn_backlog=262144
net.core.wmem_max=16777216
net.core.rmem_max=16777216
net.ipv4.tcp_wmem=4096 4096 16777216
net.ipv4.tcp_mem = 786432 2097152 3145728
net.ipv4.tcp_rmem=4096 4096 16777216
net.core.netdev_max_backlog=30000
net.ipv4.ip_local_port_range=5000 65535
net.ipv4.tcp_fin_timeout=30
net.core.rmem_default = 262144
net.core.wmem_default = 262144
net.core.netdev_max_backlog=16384
net.core.rmem_max=16777216
net.core.wmem_max=16777216
net.ipv4.tcp_syncookies=1
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
net.bridge.bridge-nf-call-arptables = 1
vm.max_map_count=655360
net.ipv6.conf.all.disable_ipv6 = 1
net.ipv6.conf.default.disable_ipv6 = 1
EOF


sed -i 's/root soft nofile 65535/root soft nofile 1048576/g' /etc/security/limits.conf
sed -i 's/root hard nofile 65535/root hard nofile 1048576/g' /etc/security/limits.conf

sed -i 's/* soft nofile 65535/* soft nofile 1048576/g' /etc/security/limits.conf
sed -i 's/* hard nofile 65535/* hard nofile 1048576/g' /etc/security/limits.conf



ulimit -HSn 1048576

sudo sysctl -p
```

# 机器初始化完成.


# 本次安装使用3master 3node 共计6台机器
ld-vm-61-wb-test-k8s-master1   2C 4G   40G
ld-vm-62-wb-test-k8s-master2   2C 4G   40G
ld-vm-63-wb-test-k8s-master3   2C 4G   40G
ld-vm-64-wb-test-k8s-node1     4C 8G   40G+120G
ld-vm-65-wb-test-k8s-node2     4C 8G   40G+120G
ld-vm-66-wb-test-k8s-node3     4C 8G   40G+120G


# 设置主机hostname和ip
hostnamectl set-hostname  ld-vm-61-wb-test-k8s-master1
hostnamectl set-hostname ld-vm-62-wb-test-k8s-master2
hostnamectl set-hostname ld-vm-63-wb-test-k8s-master3
hostnamectl set-hostname ld-vm-64-wb-test-k8s-node1
hostnamectl set-hostname ld-vm-65-wb-test-k8s-node2
hostnamectl set-hostname ld-vm-66-wb-test-k8s-node3 


# 设置host
cat << EOF >> /etc/hosts
192.168.10.61 master-1 
192.168.10.62 master-2 
192.168.10.63 master-3 
192.168.10.64 node-1 
192.168.10.65 node-2 
192.168.10.66 node-3 
EOF

# 开启br_netfilter和overlay模块
cat <<EOF | sudo tee /etc/modules-load.d/k8s.conf
overlay
br_netfilter
EOF

sudo modprobe overlay
sudo modprobe br_netfilter

## 确认修改有效
lsmod | grep br_netfilter
lsmod | grep overlay


# 允许内核流量转发
### ipv6堆栈已经关闭了,开不开都行.
echo "net.ipv4.ip_forward = 1" >> /etc/sysctl.conf
echo "net.ipv6.conf.all.forwarding = 1"  >> /etc/sysctl.conf

sysctl -p




# 接下来使用kubespray进行集群引导,kubespray从版本2.3开始使用kubeadm进行引导控制,所以今后也支持kubeadm的升级等操作

# master1 机器创建ssh私钥,并发送到其他机器
ssh-keygen -t rsa
ssh-copy-id -i ~/.ssh/id_rsa.pub 192.168.10.61
ssh-copy-id -i ~/.ssh/id_rsa.pub 192.168.10.62
ssh-copy-id -i ~/.ssh/id_rsa.pub 192.168.10.63
ssh-copy-id -i ~/.ssh/id_rsa.pub 192.168.10.64
ssh-copy-id -i ~/.ssh/id_rsa.pub 192.168.10.65
ssh-copy-id -i ~/.ssh/id_rsa.pub 192.168.10.66

# master1机器安装python39和git
dnf install python39 git

# 克隆kubespray项目 (PATH目前位于root下面)
git clone https://github.com/kubernetes-sigs/kubespray.git
cd kubespray


# 自动安装ansible对应版本,并创建虚拟环境
# 这里使用了pip3和清华repo,阿里云的太慢了.
VENVDIR=kubespray-venv
KUBESPRAYDIR=kubespray
ANSIBLE_VERSION=2.12
virtualenv  --python=$(which python3) $VENVDIR
source $VENVDIR/bin/activate
cd $KUBESPRAYDIR
pip3 install -U -r requirements-$ANSIBLE_VERSION.txt -i https://pypi.tuna.tsinghua.edu.cn/simple
test -f requirements-$ANSIBLE_VERSION.yml && \
  ansible-galaxy role install -r requirements-$ANSIBLE_VERSION.yml && \
  ansible-galaxy collection -r requirements-$ANSIBLE_VERSION.yml

# 创建配置
cp -rfp inventory/sample inventory/mycluster
cd /root/kubespray/inventory/mycluster

# 自动创建host配置,需要手工修改名称
declare -a IPS=(192.168.10.61 192.168.10.62 192.168.10.63 192.168.10.64 192.168.10.65 192.168.10.66)
CONFIG_FILE=inventory/mycluster/hosts.yaml python3 contrib/inventory_builder/inventory.py ${IPS[@]}


# 调整部署配置

## 第一个
vim inventory/mycluster/group_vars/all/all.yml

### 启动NTP 并开机启动
ntp_enabled: true
ntp_manage_config: true

  - "ntp.aliyun.com iburst"
  - "ntp1.aliyun.com iburst"
  - "ntp2.aliyun.com iburst"
  - "ntp3.aliyun.com iburst"


## 第二个
vim inventory/mycluster/group_vars/k8s_cluster/k8s-cluster.yml 

## 例如
10.61.*.* 我自己测试k8spod
10.62.*.* 我自己测试k8s svc地址范围

### 网络插件设置
kube_network_plugin: flannel
### kubesvc和pod子网地址
kube_service_addresses: 10.233.0.0/18
kube_pods_subnet: 10.233.64.0/18

### 单个node最多运行pod数量
kubelet_max_pods: 240

### 每个月自动更新证书
auto_renew_certificates: true


# 执行部署
ansible-playbook -i inventory/mycluster/hosts.yaml  --become --become-user=root cluster.yml


# 一些典型报错处理.

# 因为我已经提前彻底关闭了selinux ,需要调整段落Set selinux policy 不执行它.
# 注释段落即可
vim roles/kubernetes/preinstall/tasks/0080-system-configurations.yml


## 如果碰巧下载不动,可以指定一个代理,虽然kubespary项目本身就已经指定了镜像源,但可能装runc的时候还是从github下载.....
## 注意两个都要配置.
## 我安装的时候6台有2台下载不动,就把代理指向了我自己机器上的猫猫头
vim inventory/mycluster/group_vars/all/all.yml

http_proxy: "http://192.168.26.218:1080"
https_proxy: "http://192.168.26.218:1080"


## 一切就绪,可以正常使用了



### 扩展:

# Kubernetes-dashboard
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.5.0/aio/deploy/recommended.yaml
kubectl --namespace kubernetes-dashboard patch svc kubernetes-dashboard -p '{"spec": {"type": "NodePort"}}'


## 创建登录鉴权
## 手工创建一个yml文件

---
apiVersion: v1
kind: ServiceAccount
metadata:
  name: ops-operator
  namespace: kubernetes-dashboard
---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: ops-operator
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
subjects:
- kind: ServiceAccount
  name: ops-operator
  namespace: kubernetes-dashboard

# duration是具体的超时时间 设置为0永不超时,默认1小时
kubectl -n kubernetes-dashboard create token ops-operator --duration=0

## 即可获得token.

eyJhbGciOiJSUzI1NiIsImtpZCI6IllKalhraEE5N0tTVzBBN1FLckNFNkxSVDV1Vkk5TDB0c1pWTkhaS0pLMTQifQ.eyJhdWQiOlsiaHR0cHM6Ly9rdWJlcm5ldGVzLmRlZmF1bHQuc3ZjLmNsdXN0ZXIud2J0ZXN0Il0sImV4cCI6MTY2MTMzNjMyMCwiaWF0IjoxNjYxMzMyNzIwLCJpc3MiOiJodHRwczovL2t1YmVybmV0ZXMuZGVmYXVsdC5zdmMuY2x1c3Rlci53YnRlc3QiLCJrdWJlcm5ldGVzLmlvIjp7Im5hbWVzcGFjZSI6Imt1YmVybmV0ZXMtZGFzaGJvYXJkIiwic2VydmljZWFjY291bnQiOnsibmFtZSI6Im9wcy1vcGVyYXRvciIsInVpZCI6ImVmNDRmNmZlLTZlMTMtNGY5Yi1iYWU5LTE1NDAxNjM3MWZlMSJ9fSwibmJmIjoxNjYxMzMyNzIwLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6a3ViZXJuZXRlcy1kYXNoYm9hcmQ6b3BzLW9wZXJhdG9yIn0.oz-iJIkPw5IIpNRp3LPlmC9yeOk31QEpIGmLuLf-T97yGzB6KA8ouLJWbpr48v7FUUM3VMH7_wB1sTbads7ofEtequG6ydZKstSttOOly7WYDZJt-QRpEcJFrgWjuicn-UgdUq2yxRyEbkKYuWVJNX0jCjirDF0b2oVPlUn8PaRkD6jn9rvRhRRTEbgsrVUJ3Hm0wOCIgEaqP9vfJBx_qaTNFzA01MWnH80f8IA_o2gOphqRPOciXVNMgLTqexy2-Jg5EFb_mgEkFBYwO_zudop-ol0r3_-IpgbYkp-NFkJlvEBsGLQJKlypAZTbYBj5RAtj_3GxkNdncmKU7-gZ6g