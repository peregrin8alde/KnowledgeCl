#/bin/sh

URL="http://knowledge:8080"
TOKEN="xKuWT1ArbcFrQR8PxIdUTf2Uk9AmmshMigc5Dq2a7JvsNMlDaAVcCr7xJNDN8n6y"
TITLE="title1"
TAGS="api,path:/aaaa/bbb/ccc.md"
CONTENTFILE="../README.md"

./run.sh $URL $TOKEN $TITLE $TAGS $CONTENTFILE

exit 0;
