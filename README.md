# Reportes del SIAYF-RH (siayf-rh-reportes)
Este proyecto separa el API de reportes del proyecto [SIAYF-RH](https://github.com/folf-asesores/siayf-rh),
como un servicio web (RESTFUL).

## Instalación
### Requerimientos
Este proyecto require:
* JDK 1.7
* WildFly 9.0.2. Final como servidor de aplicaciones Java EE.
* MySQL Server como motor de base de datos.
* Maven como adminstrador de dependencia y rutinas de compilación

### Compilación apartir del código
Este proyecto esta listo para su uso apoyandode de Maven.
- Compilar con `mvn clean install`
- Para ejecutarlo, inicie el servidor de aplicaciones y coloque el archivo war
 en la carpeta de desplieges de WildFly y él se encargara de desplegarlo.
