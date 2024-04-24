# create index
curl -XPOST http://127.0.0.1:3000/api/v1/indexes --header "content-type: application/yaml" --data-binary @./beer-index.yaml

# populate index
for file in ./beer-dataset/beer-database/*.json
do
  data=$(jq -c '.data[]' "$file")
  curl -XPOST -H "Content-Type: application/json" "http://127.0.0.1:3000/api/v1/beer/ingest?commit=force" -d "$data"
done
