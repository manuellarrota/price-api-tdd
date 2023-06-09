# Prices spring boot api

Prices spring boot api permite a partir de una fecha, la marca y el identificador del producto determinar el precio final 
del producto en stock segun el rango de fechas y prioridad determinados para el producto.

![img_2.png](documents/swagger_home.png)

## Tecnologias

- Spring boot, como framework base de desarollo.
- Maven como gestor de paquetes.
- H2, como motor de base de datos.
- Swagger, para documentar y manipular los servicios expuestos.
- Docker, como servicio de empaquetado de contenedores.

## Estructura

- controller: endpoints que se exponen.
- service: capa de servicios.
- dto: capa de transformadores de datos.
- domain: mapeo de entidades. 
- repository: abstraccion de acceso a datos.
- data.sql: contiene insert inicial para pruebas.
- test: pruebas unitarias para capas.

![img_1.png](documents/sequence_diagram.png)


## Dockerizando

# Generar los artefactos en el paquete target:

.\mvnw.cmd clean package install

# Generar imagen de docker y dejarla en un container:

docker build -t inditex/prices-api .

# Ejecutar contenedor:

docker run -d  --name prices-api  --net bridge  -p 8091:8091  inditex/prices-api

Una vez se este ejecutado el servicio se debe visualizar la url de swagger en el log:

![img.png](documents/log.png)


