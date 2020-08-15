. variables.conf

pushd ./generator

./generateCode.sh

popd

pushd ./sdk

mvn -B package 
mvn javadoc:javadoc

popd