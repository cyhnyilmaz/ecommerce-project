#!/bin/sh

set -e


cd ./product-app
# ./gradlew clean build test

cd ../order-app
./gradlew clean build test

cd ../user-app
./gradlew clean build test

cd ../EurekaServer
./gradlew clean build test

echo "$(tput setaf 2)Build project.sh tamamlandi.$(tput setaf 7)"

exit 0
