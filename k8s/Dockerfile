FROM centos:7.6.1810
LABEL maintainer "g_ops@imlaidian.com"

#install epel and tookits(Heavy , with openjdk-1.8.0 , with nginx depends)
RUN yum makecache;yum install epel-release -y;yum makecache;yum install logrotate git sudo vim curl wget telnet cronie nc  unzip net-tools \
    perl-Net-SSLeay perl-IO-Socket-SSL perl-Net-SMTP-SSL tcpdump python-pip \
    java-1.8.0-openjdk \
    java-1.8.0-openjdk-devel \
    wget gperftools-libs gperftools perl perl-devel perl-ExtUtils-Embed libxslt libxslt-devel libxml2 libxml2-devel gd gd-devel GeoIP GeoIP-devel \
    -y;yum groupinstall -y 'Development Tools'  ;yum clean all

#add ld spec user (admin user dev) nginx working user 
RUN useradd -m -u 1000 ld_admin;echo "C2Ay7NMsXK9puMlN">passwd  server ;echo 'ld_admin ALL = (ALL) NOPASSWD: ALL' | sudo EDITOR='tee -a' visudo
RUN useradd -m -u 1001 ld_user;echo "q7IKiBaiLGx2yv5y">passwd  server ;echo 'ld_user ALL = (ALL) NOPASSWD: ALL' | sudo EDITOR='tee -a' visudo
RUN useradd -m -u 1002 ld_dev;echo "FLYa676AlcEyX03o">passwd  server ;echo 'ld_dev ALL = (ALL) NOPASSWD: ALL' | sudo EDITOR='tee -a' visudo
RUN useradd -M -u 1005 -s /sbin/nologin nginx;

#add jdk env to profile
ENV  JAVA_HOME=/etc/alternatives/java_sdk  CLASSPATH=.:$JAVA_HOME/lib:$JAVA_HOME/jre/lib PATH=$PATH:$JAVA_HOME/bin:$JAVA_HOME/jre/bin

# install nginx to /usr/sbin/nginx
# next version convert to muitl-stage build mode

RUN cd /root ; \
wget https://nginx.org/download/nginx-1.14.2.tar.gz && tar zxvf nginx-1.14.2.tar.gz; \
wget https://ftp.pcre.org/pub/pcre/pcre-8.42.tar.gz && tar xzvf pcre-8.42.tar.gz; \
wget https://www.zlib.net/zlib-1.2.11.tar.gz && tar xzvf zlib-1.2.11.tar.gz; \
wget https://www.openssl.org/source/openssl-1.1.1a.tar.gz && tar xzvf openssl-1.1.1a.tar.gz; \
git clone git://github.com/vozlt/nginx-module-vts.git; \
cd ~/nginx-1.14.2 ;\
sed 's#1014002#9871234#g' src/core/nginx.h -i ;\
sed 's#1.14.2#V1.00#g' src/core/nginx.h -i ;\
sed 's#nginx/#Ld-shine/#g' src/core/nginx.h -i ;\
./configure --prefix=/usr/share/nginx --sbin-path=/usr/sbin/nginx --modules-path=/usr/lib64/nginx/modules --conf-path=/etc/nginx/nginx.conf --error-log-path=/var/log/nginx/error.log --http-log-path=/var/log/nginx/access.log --http-client-body-temp-path=/var/lib/nginx/tmp/client_body --http-proxy-temp-path=/var/lib/nginx/tmp/proxy --http-fastcgi-temp-path=/var/lib/nginx/tmp/fastcgi --http-uwsgi-temp-path=/var/lib/nginx/tmp/uwsgi --http-scgi-temp-path=/var/lib/nginx/tmp/scgi --pid-path=/run/nginx.pid --lock-path=/run/lock/subsys/nginx --user=nginx --group=nginx --with-file-aio --with-http_auth_request_module --with-http_ssl_module --with-http_v2_module --with-http_realip_module --with-http_addition_module --with-http_xslt_module=dynamic --with-http_image_filter_module=dynamic --with-http_geoip_module=dynamic --with-http_sub_module --with-http_dav_module --with-http_flv_module --with-http_mp4_module --with-pcre=../pcre-8.42 --with-mail=dynamic --with-pcre-jit --with-zlib=../zlib-1.2.11 --with-http_gunzip_module --with-http_gzip_static_module --with-http_random_index_module --with-http_secure_link_module --with-http_degradation_module --with-http_slice_module --with-http_stub_status_module --with-http_perl_module=dynamic --with-mail_ssl_module --with-openssl=../openssl-1.1.1a --with-openssl-opt=no-nextprotoneg --add-module=../nginx-module-vts --with-stream=dynamic --with-stream_ssl_module --with-google_perftools_module --with-debug --with-ld-opt="-Wl,-E" ; \
make -j6 && make install ;rm -rf /root/* ; mkdir -p /var/lib/nginx/tmp/{client_body,fastcgi,proxy,scgi,uwsgi}; mkdir -p /data/ ; chown -R ld_dev:ld_dev /var/lib/nginx/tmp


#update system timezone
ENV TZ=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" >> /etc/timezone

#fix system locale posix
RUN localedef -c -f UTF-8 -i en_US en_US.UTF-8 ;echo "export LC_ALL=en_US.UTF-8" >> /etc/profile && source /etc/profile
ENV LC_ALL "en_US.UTF-8"  

USER ld_dev

# ADD SkyWalking agent& put_to_down
USER root
COPY put_to_down /usr/local/bin/put_to_down
COPY laidian/ /usr/local/laidian/
RUN cd /data/ ; wget 172.16.200.200/agent.tar.gz ; tar xvf agent.tar.gz -C /data; rm agent.tar.gz ; chown -R ld_dev:ld_dev /data/agent ; chmod +x -R  /data/agent
RUN cd /data/ ; wget 172.16.200.200/agent8.tar.gz ; tar xvf agent8.tar.gz -C /data; rm agent8.tar.gz ; chown -R ld_dev:ld_dev /data/agent8 ; chmod +x -R  /data/agent8
RUN cd /data/ ; wget 172.16.200.200/jacocoagent.jar; chown -R ld_dev:ld_dev /data/jacocoagent.jar
RUN pip install --upgrade --force-reinstall 'requests==2.6.0' urllib3 -i https://pypi.tuna.tsinghua.edu.cn/simple && rm /etc/yum.repos.d/epel.repo && chmod +x /usr/local/bin/put_to_down

USER ld_dev

EXPOSE 80
EXPOSE 443
STOPSIGNAL SIGTERM

CMD ["nginx", "-g", "daemon off;"]
