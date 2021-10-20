#!/bin/bash

./gradlew build -x test

echo "Services starting (will wait for kafka to start)."

docker compose up -d --build

trap ctrl_c INT
trap "trap - SIGTERM && kill -- -$$" SIGINT SIGTERM EXIT

function ctrl_c() {
  docker compose down
  exit
}

echo "Proxying local traces to honeycomb via k8s."
kubectx dev
kubens default
TELEPRESENCE_USE_DEPLOYMENT=1 telepresence --deployment telepresence --also-proxy otel-collector.observability &

wait-for-url() {
    echo "Testing $1"
    timeout -s TERM 45 bash -c \
    'while [[ "$(curl -H "Content-Type: application/json"  -s -o /dev/null -L -w ''%{http_code}'' ${0})" != "200" ]];\
    do echo "Waiting for ${0}" && sleep 2;\
    done' ${1}
    echo "OK!"
    curl -I $1
}
wait-for-url http://otel-collector.observability:55681/v1/traces

echo "Press CTRL+C to stop the services..."
echo "./send_message.sh will send a message to the producer"
read -r -d '' _ </dev/tty
