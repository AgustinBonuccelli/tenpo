# tenpo-challenge
## Objetivos
1)  Debes desarrollar una API REST en Spring Boot utilizando java 11 o superior, con las siguientes funcionalidades:

  - Debe contener un servicio llamado por api-rest que reciba 2 números, los sume, y le aplique una suba de un porcentaje que debe ser adquirido de un servicio externo (por ejemplo, si el servicio recibe 5 y 5 como valores, y el porcentaje devuelto por el servicio externo es 10, entonces (5 + 5) + 10% = 11). Se deben tener en cuenta las siguientes consideraciones:
    -	El servicio externo puede ser un mock, tiene que devolver el % sumado.
    - Dado que ese % varía poco, podemos considerar que el valor que devuelve ese servicio no va cambiar por 30 minutos.
    - Si el servicio externo falla, se debe devolver el último valor retornado. Si no hay valor, debe retornar un error la api.
    - Si el servicio falla, se puede reintentar hasta 3 veces.
    
  - Historial de todos los llamados a todos los endpoint junto con la respuesta en caso de haber sido exitoso. Responder en Json, con data paginada. El guardado del historial de llamadas no debe sumar tiempo al servicio invocado, y en caso de falla, no debe impactar el llamado al servicio principal.
 
  -	La api soporta recibir como máximo 3 rpm (request / minuto), en caso de superar ese umbral, debe retornar un error con el código http y mensaje adecuado.

  -	El historial se debe almacenar en una database PostgreSQL.

  -	Incluir errores http. Mensajes y descripciones para la serie 4XX.

2.	Se deben incluir tests unitarios.
3.	Esta API debe ser desplegada en un docker container. Este docker puede estar en un dockerhub público. La base de datos también debe correr en un contenedor docker. Recomendación usar docker compose
4.	Debes agregar un Postman Collection o Swagger para que probemos tu API
5.	Tu código debe estar disponible en un repositorio público, junto con las instrucciones de cómo desplegar el servicio y cómo utilizarlo.
6.	Tener en cuenta que la aplicación funcionará de la forma de un sistema distribuido donde puede existir más de una réplica del servicio funcionando en paralelo.

## Requisitos
Para poder ejecutar el proyecto es necesario tener descargado e instalado docker y docker compose.
- https://www.docker.com/
- https://docs.docker.com/compose/install/

## Docker Hub Publico
La imagen se puede descargar con el siguiente comando
docker pull comodin27/tenpo-challengue

## Puesta en marcha
Existen 3 formas de ejecutar el proyecto.
1. Descargando el proyecto y ejecutando el comando "docker-compose up" levanta el proyecto y las dependencias de db automaticamente. (Mejor opción)
2. Descargando la imagen del hub e inicializando previamente con "docker-compose up -d" una imagen de postgres y por ultimo inicializando la imagen de docker del proyecto con docker run -p 8080:8080 comodin27/tenpo-challengue:1.0
3. Corriendo el codigo desde java con un ide de desarrollo, previamente a haber levantado una imagen de la db postgres.

## Swagger
Se deja adjunta en la raiz del proyecto un swagger para probar el mismo.
