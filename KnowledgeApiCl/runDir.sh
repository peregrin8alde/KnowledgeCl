#/bin/sh

URL=$1
TOKEN=$2
BASEDIRNAME=$3
CONTENTSDIR=$4

find $CONTENTSDIR -name "*.md" | while read FILE
do
  TITLE=`sed -n -E 's/^# (.*)/\1/p' $FILE | head -n 1`
  if [ -z "$TITLE" ]; then
    TITLE=$(basename $FILE)
  fi
  
  TAGS="api,path:$BASEDIRNAME/${FILE#$CONTENTSDIR}"
  CONTENTFILE=${FILE}

  java -cp target/KnowledgeApiCl-1.0-SNAPSHOT.jar:target/dependency/* \
    myapps.App $URL $TOKEN $TITLE $TAGS $CONTENTFILE
done



exit 0;
