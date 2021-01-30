TIME_STAMP=$(date +"%Y-%m-%d_%H-%M")
newdir=./blobStorage/java/$TIME_STAMP/$1
echo Creating $newdir
mkdir -p $newdir
echo "$newdir created"

echo "Copying jars"
cp ./sdk/target/*.jar $newdir
echo "Copying pom.xml"
cp ./sdk/pom.xml $newdir/azure-marketplace.pom
pushd $newdir

echo "Moving jar files"

mv *-javadoc.jar azure-marketplace-javadoc.jar
mv *-sources.jar azure-marketplace-sources.jar
mv azure-marketplace-*.*.*.jar azure-marketplace.jar
popd