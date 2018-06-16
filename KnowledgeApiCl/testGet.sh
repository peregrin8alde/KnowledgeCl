#/bin/sh

URL="http://knowledge:8080"
TOKEN="xKuWT1ArbcFrQR8PxIdUTf2Uk9AmmshMigc5Dq2a7JvsNMlDaAVcCr7xJNDN8n6y"
TITLE=""
TAGS=""
DIR="./down"

./run.sh -mode "GET" \
         -url "$URL" \
         -token $TOKEN \
         -dir "$DIR"

exit 0;
