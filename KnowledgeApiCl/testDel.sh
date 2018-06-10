#/bin/sh

URL="http://knowledge:8080"
TOKEN="xKuWT1ArbcFrQR8PxIdUTf2Uk9AmmshMigc5Dq2a7JvsNMlDaAVcCr7xJNDN8n6y"
TITLE="KnowledgeCl"
TAGS="api,path:/base/README.md"

./run.sh -mode "DEL" \
         -url $URL \
         -title $TITLE \
         -token $TOKEN \
         -tags $TAGS

exit 0;
