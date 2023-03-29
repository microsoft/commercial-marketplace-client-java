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

curl https://raw.githubusercontent.com/microsoft/commercial-marketplace-openapi/main/Microsoft.Marketplace.Metering/2018-08-31/meteringapi.v1.json > meteringapi.v1.json
curl https://raw.githubusercontent.com/microsoft/commercial-marketplace-openapi/0c7ce552cb6d5121b983f8351697965b6551f8cd/Microsoft.Marketplace.SaaS/2018-08-31/saasapi.v2.json > saasapi.v2.json

docker run --rm --name autorest \
    -v $current_dir/temp/saas:/out \
    -v $current_dir:/input \
    local/autorest \
    --input-file:/input/saasapi.v2.json \
    --v3 \
    --java \
    --use:@autorest/java@4.0.1 \
    --output-folder:/out  \
    --add-credentials \
    --namespace:com.microsoft.marketplace.saas \
    --verbose \
    --clear-output-folder:true \
    --public-clients:true \
    --license-header:MICROSOFT_MIT_NO_VERSION

docker run --rm --name autorest \
    -v $current_dir/temp/meter:/out \
    -v $current_dir:/input \
    local/autorest \
    --input-file:/input/meteringapi.v1.json \
    --v3 \
    --java \
    --use:@autorest/java@4.0.1 \
    --output-folder:/out  \
    --add-credentials \
    --namespace:com.microsoft.marketplace.meter \
    --verbose \
    --clear-output-folder:true \
    --public-clients:true \
    --license-header:MICROSOFT_MIT_NO_VERSION

cp -r ./temp/meter/src/* ../sdk/src
cp -r ./temp/saas/src/* ../sdk/src