docker build -t local/autorest .

generated_code_dir="../sdk/src/main/java/com/azure/marketplace"

if [[ -d $generated_code_dir ]]; then
    echo "Cleaning out previously generated files"
    rm -rf "$generated_code_dir/models"
    rm "$generated_code_dir/FulfillmentOperations.java"
    rm "$generated_code_dir/MeteringOperations.java"
    rm "$generated_code_dir/package-info.java"
    rm "$generated_code_dir/SaaSFulfillmentAPIsVersion2.java"
    rm "$generated_code_dir/SaaSFulfillmentAPIsVersion2Builder.java"
    rm "$generated_code_dir/SubscriptionOperations.java"
fi

mkdir -p "../sdk/src/"

current_dir=$(pwd)

docker run --rm --name autorest \
    -v $current_dir/temp:/out \
    -v $current_dir:/input \
    local/autorest \
    --input-file:/input/marketplace.json \
    --v3 \
    --java \
    --use:@autorest/java@4.0.1 \
    --output-folder:/out  \
    --add-credentials \
    --namespace:com.azure.marketplace \
    --verbose \
    --clear-output-folder:true \
    --public-clients:true \
    --license-header:MICROSOFT_MIT_NO_VERSION

cp -r ./temp/src/* ../sdk/src
