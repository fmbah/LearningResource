###Nginx编译安装
#####硬件环境
* Ubuntu 16.04.6 LTS \n \l
* Linux sv-1632 4.4.0-21-generic #37-Ubuntu SMP Mon Apr 18 18:33:37 UTC 2016 x86_64 x86_64 x86_64 GNU/Linux
1. wget http://nginx.org/download/nginx-1.15.9.tar.gz
2. tar -xf nginx-1.15.9.tar.gz
3. cd nginx-1.15.9/
4. ./configure
    * ./configure: error: C compiler cc is not found
    * 解决: sudo apt install gcc
5. ./configure
    * ./configure: error: the HTTP gzip module requires the zlib library.
    * 解决: sudo apt install -y zlib-devel
6. ./configure
     ````
     Configuration summary
        + using system PCRE library
        + OpenSSL library is not used
        + using system zlib library
      
        nginx path prefix: "/usr/local/nginx"
        nginx binary file: "/usr/local/nginx/sbin/nginx"
        nginx modules path: "/usr/local/nginx/modules"
        nginx configuration prefix: "/usr/local/nginx/conf"
        nginx configuration file: "/usr/local/nginx/conf/nginx.conf"
        nginx pid file: "/usr/local/nginx/logs/nginx.pid"
        nginx error log file: "/usr/local/nginx/logs/error.log"
        nginx http access log file: "/usr/local/nginx/logs/access.log"
        nginx http client request body temporary files: "client_body_temp"
        nginx http proxy temporary files: "proxy_temp"
        nginx http fastcgi temporary files: "fastcgi_temp"
        nginx http uwsgi temporary files: "uwsgi_temp"
        nginx http scgi temporary files: "scgi_temp"
     ````
     * 显示以上字样,即代表安装环境正确
7. apt install make
8. make 
9. sudo make install
10. 经过以上步骤,已经成功安装nginx软件,可以在[/usr/local/]中看到nginx文件夹的目录结构
    ````
    .
    ├── conf
    │   ├── fastcgi.conf
    │   ├── fastcgi.conf.default
    │   ├── fastcgi_params
    │   ├── fastcgi_params.default
    │   ├── koi-utf
    │   ├── koi-win
    │   ├── mime.types
    │   ├── mime.types.default
    │   ├── nginx.conf
    │   ├── nginx.conf.default
    │   ├── scgi_params
    │   ├── scgi_params.default
    │   ├── uwsgi_params
    │   ├── uwsgi_params.default
    │   └── win-utf
    ├── html
    │   └── 50x.html
    ├── logs
    └── sbin
        └── nginx
    ````
11. 我这里讲的是简单的配置方式,实用性强,至于很多高级用法估计你需要用的时候也很清楚如何做....发挥浏览器的特性
    * sudo nginx -t 检查配置文件是否正确
    * sudo nginx -s reload 重新加载配置文件, 不需要重新启动服务
    * sudo nginx -s stop 停止服务
    * sudo nginx 启动服务
    ````
        worker_processes  1;
        
        events {
            worker_connections  1024;
        }
        
        
        http {
            include       mime.types;
            default_type  application/octet-stream;
        
            sendfile        on;
        
            keepalive_timeout  65;
        
            include /usr/local/nginx/vhost/conf.d/*.conf;[这个内容是将nginx.conf原本的注释内容删除掉,减少篇幅,只在原基础上增加了此行]
        }
    ````
    ````
        [以下内容是/usr/local/nginx/vhost/conf.d目录下的barraba.conf文件,可以看到对请求为http://localhost:80/的请求反向代理到9999端口的服务去了]
        [而http://localhost:80/barraba的请求则会替换为http://localhost:80/home/webgame/barraba-html/index.html]
        server{
            listen	80;
            server_name	localhost;
            location / {
                proxy_pass	http://localhost:9999;
            }
            location /barraba {
                alias	/home/webgame/barraba-html;
                index	index.html;
            }
        }
    ````
    ````
        [以下内容是/usr/local/nginx/vhost/conf.d目录下的barraba.conf文件,可以看到对请求为http://localhost:80/的请求反向代理到9999端口的服务去了]
        [而http://localhost:80/barraba的请求则会将/home/webgame/barraba-html/barraba目录下的文件夹显示出来，如果里面有静态文件资源则会直接显示出来]
        server{
            listen  80;
            server_name     localhost;
            client_max_body_size 20m;
            location / {
                proxy_pass      http://localhost:9999;
            }
            root    /home/webgame/barraba-html;
            location /barraba {
                autoindex       on;
            }
        }
    
    ````
    
