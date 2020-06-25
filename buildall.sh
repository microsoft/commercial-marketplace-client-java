. variables.conf

pushd ./generator

./generateCode.sh

popd

mvn -B package --file sdk/pom.xml
