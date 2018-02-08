#!/bin/bash

BASEDIR=/opt/CSE/code/cse-java-chassis/integration-tests
JAVA_HOME=/opt/CSE/jdk1.8.0_144

cd ${BASEDIR}/account-service/target
${JAVA_HOME}/bin/java -jar integration-tests-account-service-2.1.SNAPSHOT.jar >/dev/null 2>&1 &

cd ${BASEDIR}/customer-service/target
${JAVA_HOME}/bin/java -jar integration-tests-customer-service-2.1.SNAPSHOT.jar >/dev/null 2>&1 &

cd ${BASEDIR}/edge-service/target
${JAVA_HOME}/bin/java -jar integration-tests-edge-service-2.1.SNAPSHOT.jar >/dev/null 2>&1 &

cd ${BASEDIR}/product-service/target
${JAVA_HOME}/bin/java -jar integration-tests-product-service-2.1.SNAPSHOT.jar >/dev/null 2>&1 &

cd ${BASEDIR}/tx-coordinator/target
${JAVA_HOME}/bin/java -jar integration-tests-tx-coordinator-2.1.SNAPSHOT.jar >/dev/null 2>&1 &


cd ${BASEDIR}/user-service/target
${JAVA_HOME}/bin/java -jar integration-tests-user-service-2.1.SNAPSHOT.jar >/dev/null 2>&1 &

count=1
while ! nc -z localhost 8092 ; do
    echo "Process not ready..."
    sleep 1
    if [ $count -gt 120 ]; then
      break
    fi
    let "count++"
done

while ! nc -z localhost 8089 ; do
    echo "Process not ready..."
    sleep 1
    if [ $count -gt 120 ]; then
      break
    fi
    let "count++"
done

while ! nc -z localhost 18080 ; do
    echo "Process not ready..."
    sleep 1
    if [ $count -gt 120 ]; then
      break
    fi
    let "count++"
done

echo "install finished"