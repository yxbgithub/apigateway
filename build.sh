mvn clean package -Dmaven.test.skip=true
docker build -t registry.cn-hangzhou.aliyuncs.com/yxbimages/apigateway .
docker push registry.cn-hangzhou.aliyuncs.com/yxbimages/apigateway