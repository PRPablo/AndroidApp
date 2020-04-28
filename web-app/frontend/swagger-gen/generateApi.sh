#/bin/sh
rm -Rf client/web/

curl http://localhost:8080/private/api/swagger.json -H "Accept: application/json" -H "Content-Type: application/json" -X GET > swagger.json

java -jar swagger-codegen-cli-2.3.1.jar generate  -i swagger.json  -o client -l typescript-angular --invoker-package web.app --api-package web.app.service --model-package web.app.model

cp -R client/web/ ../web-app/src/app/api/

rm -R client/web/
