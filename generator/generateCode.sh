docker build -t local/autorest .

generated_code_dir="../sdk/src/main/java/com/azure/marketplace"

if [[ -d $generated_code_dir ]]; then
    echo "Cleaning out previously generated files"
    rm -rf "$generated_code_dir/implementation"
    rm -rf "$generated_code_dir/models"
    rm "$generated_code_dir/FulfillmentOperations.java"
    rm "$generated_code_dir/MarketplaceClient.java"
    rm "$generated_code_dir/MeteringOperations.java"
    rm "$generated_code_dir/package-info.java"
    rm "$generated_code_dir/SubscriptionOperations.java"
fi

mkdir -p "../sdk/src/"

current_dir=$(pwd)

docker run --rm --name autorest \
    -v $current_dir/temp:/out \
    -v $current_dir:/input \
    local/autorest \
    /input/readme.md \
    --v3 \
    --java \
    --java-sdks-folder=/out  \
    --add-credentials 

cp -r ./temp/src/* ../sdk/src

# Fix FullfillmentOperationsImpl.java for an issue with javadoc generation
FulfillmentOperationsImplFile='../sdk/src/main/java/com/azure/marketplace/implementation/FulfillmentOperationsImpl.java'
sed -i 's/ServiceResponse<PageImpl<Subscription>> \* @param/ \* @param/' $FulfillmentOperationsImplFile