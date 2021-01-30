TIME_STAMP=$(date +"%Y-%m-%d_%H-%M")
newdir=./blobStorage/java/$TIME_STAMP/$1
mkdir -p $newdir
cp ./sdk/target/*.jar $newdir
cp ./sdk/pom.xml $newdir/azure-marketplace.pom
pushd $newdir
mv *-javadoc.jar azure-marketplace-javadoc.jar
mv *-sources.jar azure-marketplace-sources.jar
mv azure-marketplace-*.*.*.jar azure-marketplace.jar
popd