#/bin/sh

URL="http://knowledge:8080"
TOKEN="xKuWT1ArbcFrQR8PxIdUTf2Uk9AmmshMigc5Dq2a7JvsNMlDaAVcCr7xJNDN8n6y"
TITLE="title1"
TAGS="api,path:/aaaa/bbb/ccc.md"
#PRVIVATE=-private
CONTENTFILE="../README.md"

./run.sh -mode "POST" \
         -url $URL \
         -token $TOKEN \
         -title $TITLE \
         -tags $TAGS \
         $PRVIVATE \
         -file $CONTENTFILE

exit 0;
