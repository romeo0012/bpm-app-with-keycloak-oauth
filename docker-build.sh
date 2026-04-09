# Stáhněte certifikát
# openssl s_client -connect dev-mgmt.prg1paas.t-cloud.eu:443 -showcerts </dev/null | openssl x509 -outform PEM > tcloud.crt
# Import do truststore
# keytool -import -alias tcloud -file tcloud.crt -keystore truststore.jks -storepass changeit
# Spusťte s truststore
# java -Djavax.net.ssl.trustStore=	truststore.jks -Djavax.net.ssl.trustStorePassword=BezpecneHeslo.123! -jar app.jar
# docker system prune -a

docker build -t bpm-app:latest .
docker tag bpm-app:latest ghcr.io/romeo0012/bpm-app:latest
export TOKEN=$(cat image-token.txt)
echo $TOKEN | docker login ghcr.io -u romeo0012 --password-stdin
docker push ghcr.io/romeo0012/bpm-app:latest

git add -A
git commit --amend --no-edit
git push --force-with-lease origin main