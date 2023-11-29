
# Servicio de Fusión de Comunidades - Documentación del Proyecto

Este repositorio contiene el desarrollo del servicio  "Servicio de Fusión de Comunidades", desarrollado en TypeScript y utilizando el framework Express.js. El objetivo de este README es proporcionar una guía paso a paso para comprender y ejecutar el proyecto, así como explicar el funcionamiento de los scripts disponibles y cómo ejecutar pruebas.

**Requisitos Previos**
Antes de comenzar a trabajar con este proyecto, asegúrate de tener instalados los siguientes requisitos previos:

- Node.js: Descargar e instalar Node.js
- npm (Administrador de paquetes de Node.js): Generalmente se instala automáticamente con Node.js.

#### Swagger
Una vez realizado todos los pasos que se describen a continuación , ingresar a  http://localhost:3000/api-docs para encontrar mejor documentacion sobre los endpoints del servicio

#### Instalación
Clone este repositorio en tu sistema local utilizando Git:

    git clone https://github.com/nachovilla123/service-01-merge-community-UTN.git

#### Navega al directorio del proyecto:
    cd servicio_fusion_comunidades_dds


#### Instala las dependencias del proyecto utilizando npm:
    npm install


#### Scripts Disponibles
Este proyecto incluye varios scripts definidos en el archivo package.json. Aquí tienes una descripción de cada uno de ellos:

**dev**: Inicia el servidor en modo de desarrollo utilizando ts-node-dev. El servidor se reiniciará automáticamente cada vez que se realice un cambio en los archivos fuente.

**start**: Inicia el servidor en modo de producción después de compilar los archivos TypeScript a JavaScript utilizando TypeScript. Este comando es adecuado para entornos de producción.

**tsc**: Compila los archivos TypeScript en archivos JavaScript en el directorio build/. Este script se usa internamente y generalmente no se ejecuta manualmente.

**test**: Ejecuta las pruebas utilizando Jest. Jest es un marco de prueba popular para JavaScript y TypeScript. Este comando buscará y ejecutará todos los archivos de prueba en la carpeta src/tests/.



#### Ejecución del Proyecto
Para ejecutar el proyecto en modo de desarrollo, puedes usar el siguiente comando:

    npm run dev


**El servidor estará disponible en http://localhost:3000 por defecto. Puedes cambiar el puerto en el archivo src/index.ts.**


Para ejecutar el proyecto en modo de producción, puedes usar el siguiente comando:

    npm start

Recuerda que debes haber compilado previamente el código TypeScript utilizando el comando npm run tsc.


#### Pruebas
Para ejecutar las pruebas del proyecto, utiliza el siguiente comando:

    npm test  || npm run test

Este comando buscará y ejecutará todas las pruebas en la carpeta src/tests/ y mostrará los resultados en la consola. Asegúrate de tener configuradas las pruebas adecuadamente en tus archivos de prueba.

## Si tienes alguna pregunta|duda|sugerencia o necesitas ayuda en cualquier paso del proceso, no dudes en abrir un issue en el repositorio o ponerte en contacto conmigo 
([ver mi perfil](https://github.com/nachovilla123)https://github.com/nachovilla123)!
