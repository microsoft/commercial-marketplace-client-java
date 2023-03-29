docker build -t local/autorest .

$generatedCodeDir = "../sdk/src/main/java/com/azure/marketplace"

if (Test-Path $generatedCodeDir) {
    Write-Host "Cleaning out previously generated files"
    Remove-Item -Recurse -Force "$generatedCodeDir/models"
    Remove-Item "$generatedCodeDir/FulfillmentOperations.java"
    Remove-Item "$generatedCodeDir/MeteringOperations.java"
    Remove-Item "$generatedCodeDir/package-info.java"
    Remove-Item "$generatedCodeDir/SaaSFulfillmentAPIsVersion2.java"
    Remove-Item "$generatedCodeDir/SaaSFulfillmentAPIsVersion2Builder.java"
    Remove-Item "$generatedCodeDir/SubscriptionOperations.java"
}

if ($false -eq (Test-Path "../sdk/src")){
    New-Item -Path "../sdk/src/" -ItemType Directory
}

$currentDir = Get-Location

Invoke-WebRequest -Uri https://raw.githubusercontent.com/microsoft/commercial-marketplace-openapi/main/Microsoft.Marketplace.Metering/2018-08-31/meteringapi.v1.json -OutFile meteringapi.v1.json
Invoke-WebRequest -Uri https://raw.githubusercontent.com/microsoft/commercial-marketplace-openapi/0c7ce552cb6d5121b983f8351697965b6551f8cd/Microsoft.Marketplace.SaaS/2018-08-31/saasapi.v2.json -OutFile saasapi.v2.json

docker run --rm --name autorest `
    -v $currentDir/temp/saas:/out `
    -v ${currentDir}:/input `
    local/autorest `
    --input-file:/input/saasapi.v2.json `
    --v3 `
    --java `
    --use:@autorest/java@4.0.1 `
    --output-folder:/out  `
    --add-credentials `
    --namespace:com.microsoft.marketplace.saas `
    --verbose `
    --clear-output-folder:true `
    --public-clients:true `
    --license-header:MICROSOFT_MIT_NO_VERSION

docker run --rm --name autorest `
    -v $currentDir/temp/meter:/out `
    -v ${currentDir}:/input `
    local/autorest `
    --input-file:/input/meteringapi.v1.json `
    --v3 `
    --java `
    --use:@autorest/java@4.0.1 `
    --output-folder:/out  `
    --add-credentials `
    --namespace:com.microsoft.marketplace.meter `
    --verbose `
    --clear-output-folder:true `
    --public-clients:true `
    --license-header:MICROSOFT_MIT_NO_VERSION

Copy-Item ./temp/meter/src/* -Destination ../sdk/src -Recurse -Force
Copy-Item ./temp/saas/src/* -Destination ../sdk/src -Recurse -Force