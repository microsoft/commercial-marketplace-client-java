pushd ./generator

./generateCode.sh

popd

pushd ./sdk

mvn -B package 

popd