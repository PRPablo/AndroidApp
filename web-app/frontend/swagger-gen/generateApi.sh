#/bin/sh
rm -Rf client/co/

curl http://localhost:8080/api/swagger.json -H "Accept: application/json" -H "Content-Type: application/json" -X GET > swagger.json

java -jar swagger-codegen-cli-2.3.1.jar generate  -i swagger.json  -o client -l typescript-angular  --api-package web.app.service --model-package web.app.model --invoker-package web.app

cp -R client/co/ ../web-app/src/app/api/

rm -R client/co/
