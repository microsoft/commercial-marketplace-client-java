pushd ./generator

./generateCode.sh

popd

pushd ./sdk

mvn -e -B package 

popd