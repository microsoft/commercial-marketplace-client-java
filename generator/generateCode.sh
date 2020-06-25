docker build -t local/autorest .

if [[ -d "../src/sdk" ]]; then
    rm -rf "../src/sdk"
fi

mkdir -p "../src/sdk"

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

cp -r ./temp/src/main/java/* ../src/sdk/

