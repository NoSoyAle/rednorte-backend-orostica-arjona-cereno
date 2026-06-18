# Informe de Análisis de Calidad de Código - SonarCloud
## Proyecto: RedNorte Backend - Microservicios Admin y Paciente

**Fecha:** Junio 2026  
**Herramienta:** SonarCloud  
**Organización:** catvstrofe  
**Rama analizada:** master  

---

## 1. Introducción

Este documento presenta los resultados del análisis de calidad de código realizado con SonarCloud sobre los microservicios **admin-service** y **paciente-service** del proyecto RedNorte Backend. El análisis se ejecuta automáticamente mediante GitHub Actions en cada push a las ramas `main`, `master` y `Catalina`.

---

## 2. Configuración del Pipeline CI/CD

### 2.1 Workflow de GitHub Actions

El pipeline de análisis está configurado en `.github/workflows/sonarcloud.yml` y ejecuta los siguientes pasos:

1. Checkout del código
2. Configuración de JDK 21
3. Build y tests con cobertura (JaCoCo)
4. Análisis con SonarCloud

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla del workflow de GitHub Actions mostrando la ejecución exitosa del pipeline "SonarCloud Analysis" con los 3 jobs completados (admin-service-analysis, paciente-analysis, quality-gate)]**

### 2.2 Integración con JaCoCo

Se configuró el plugin JaCoCo en los archivos `pom.xml` de ambos microservicios para generar reportes de cobertura de código en formato XML, que SonarCloud consume para calcular el porcentaje de cobertura.

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla del archivo pom.xml mostrando la configuración del plugin JaCoCo (jacoco-maven-plugin) con las ejecuciones prepare-agent y report]**

---

## 3. Resultados del Análisis

### 3.1 Resumen de Métricas

| Métrica | Admin Service | Paciente Service |
|---------|---------------|------------------|
| **Cobertura (Coverage)** | 53.5% | 38.4% |
| **Bugs** | 0 | 0 |
| **Vulnerabilidades** | 8 | 5 |
| **Code Smells** | 46 | 16 |
| **Duplicación** | 0.0% | 0.0% |
| **Líneas de código** | 1017 | 737 |

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla del dashboard de SonarCloud (https://sonarcloud.io) mostrando la organización catvstrofe con ambos proyectos listados y sus métricas principales visibles]**

### 3.2 Admin Service - Análisis Detallado

**URL del proyecto:** https://sonarcloud.io/project/overview?id=catvstrofe_rednorte-admin-service

#### Métricas clave:
- **Cobertura:** 53.5% - Más de la mitad del código está cubierto por tests unitarios
- **Bugs:** 0 - No se detectaron errores funcionales
- **Vulnerabilidades:** 8 - Puntos de seguridad a revisar
- **Code Smells:** 46 - Mejoras de mantenibilidad sugeridas
- **Duplicación:** 0.0% - Sin código duplicado

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla del proyecto Admin Service en SonarCloud (overview) mostrando el panel de métricas con Coverage, Bugs, Vulnerabilities, Code Smells y Duplications]**

#### Vulnerabilidades detectadas:

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla de la pestaña "Issues" filtrada por tipo "Vulnerability" del Admin Service, mostrando las 8 vulnerabilidades encontradas con su severidad (Blocker, Critical, Major, Minor, Info)]**

#### Code Smells principales:

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla de la pestaña "Issues" filtrada por tipo "Code Smell" del Admin Service, mostrando los 46 code smells con su severidad]**

### 3.3 Paciente Service - Análisis Detallado

**URL del proyecto:** https://sonarcloud.io/project/overview?id=catvstrofe_rednorte-paciente-service

#### Métricas clave:
- **Cobertura:** 38.4% - Cobertura moderada, se recomienda aumentar tests
- **Bugs:** 0 - No se detectaron errores funcionales
- **Vulnerabilidades:** 5 - Puntos de seguridad a revisar
- **Code Smells:** 16 - Mejoras de mantenibilidad sugeridas
- **Duplicación:** 0.0% - Sin código duplicado

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla del proyecto Paciente Service en SonarCloud (overview) mostrando el panel de métricas con Coverage, Bugs, Vulnerabilities, Code Smells y Duplications]**

#### Vulnerabilidades detectadas:

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla de la pestaña "Issues" filtrada por tipo "Vulnerability" del Paciente Service, mostrando las 5 vulnerabilidades encontradas con su severidad]**

#### Code Smells principales:

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla de la pestaña "Issues" filtrada por tipo "Code Smell" del Paciente Service, mostrando los 16 code smells con su severidad]**

---

## 4. Análisis de Cobertura de Código

### 4.1 Admin Service (53.5%)

El microservicio de administración tiene una cobertura del 53.5%, lo que indica que más de la mitad del código está ejercitado por tests unitarios. Los tests cubren principalmente:

- Controladores (AuthController, UsuarioController)
- Servicios (UsuarioServiceImpl)
- Utilidades (JwtUtil)

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla del reporte de cobertura del Admin Service en SonarCloud, mostrando el porcentaje 53.5% y la lista de archivos con su respectivo % de cobertura (verde para alto, amarillo para medio, rojo para bajo)]**

### 4.2 Paciente Service (38.4%)

El microservicio de pacientes tiene una cobertura del 38.4%, que aunque funcional, podría mejorarse agregando más tests unitarios. Los tests cubren principalmente:

- Servicios (PacienteServiceImpl, ServicioClinicoServiceImpl)
- Tests de contexto de la aplicación

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla del reporte de cobertura del Paciente Service en SonarCloud, mostrando el porcentaje 38.4% y la lista de archivos con su respectivo % de cobertura]**

---

## 5. Dockerización de Microservicios

### 5.1 Arquitectura Docker

Se implementó dockerización para ambos microservicios con la siguiente arquitectura:

| Servicio | Imagen | Puerto Host | Puerto Container | Descripción |
|----------|--------|-------------|------------------|-------------|
| mysql-admin | mysql:8.0 | 3307 | 3306 | Base de datos para admin-service |
| mysql-paciente | mysql:8.0 | 3308 | 3306 | Base de datos para paciente |
| admin-service | Custom (multi-stage) | 8092 | 8082 | Microservicio de administración |
| paciente | Custom (multi-stage) | 8093 | 8083 | Microservicio de pacientes |

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla del comando `docker-compose ps` ejecutado en terminal mostrando los 4 contenedores corriendo con estado "Up" y sus puertos mapeados]**

### 5.2 Dockerfile Multi-stage

Se implementaron Dockerfiles con multi-stage build para optimizar el tamaño de las imágenes:

- **Stage 1 (Build):** Maven 3.9 con Eclipse Temurin JDK 21 para compilar el JAR
- **Stage 2 (Runtime):** Eclipse Temurin JRE 21 Alpine para ejecutar (imagen más liviana)

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla del archivo Dockerfile de admin-service o paciente mostrando la configuración multi-stage con FROM maven AS build y FROM eclipse-temurin:21-jre-alpine]**

### 5.3 Docker Compose

El archivo `docker-compose.yml` orquesta todos los servicios con:
- Healthchecks para MySQL (mysqladmin ping cada 10s)
- Volúmenes para persistencia de datos (mysql-admin-data, mysql-paciente-data)
- Red aislada (rednorte-network, driver: bridge)
- Variables de entorno desde archivo `.env`
- Dependencias con condición `service_healthy`

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla del archivo docker-compose.yml mostrando la configuración completa de los 4 servicios, volúmenes y redes]**

---

## 6. Configuración de SonarCloud

### 6.1 Proyectos Configurados

Se crearon dos proyectos en SonarCloud bajo la organización `catvstrofe`:

1. **catvstrofe_rednorte-admin-service**
2. **catvstrofe_rednorte-paciente-service**

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla de la página de proyectos en SonarCloud (https://sonarcloud.io/organizations/catvstrofe/projects) mostrando ambos proyectos listados con sus nombres y estado]**

### 6.2 Configuración de Archivos

Se configuraron los siguientes archivos para la integración:

- `admin-service/sonar-project.properties` - Configuración específica del proyecto admin
- `paciente/sonar-project.properties` - Configuración específica del proyecto paciente
- Variables de Sonar en `pom.xml` (sonar.projectKey, sonar.organization, sonar.host.url)
- Workflow de GitHub Actions (`.github/workflows/sonarcloud.yml`)

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla del archivo sonar-project.properties del admin-service mostrando las propiedades sonar.projectKey, sonar.sources, sonar.tests, sonar.coverage.jacoco.xmlReportPaths]**

### 6.3 Token de Autenticación

Se generó un token de usuario en SonarCloud (https://sonarcloud.io/account/security/) y se configuró como secreto `SONAR_TOKEN` en GitHub Actions (Settings → Secrets and variables → Actions).

**[ACÁ DEBES PONER LA EVIDENCIA: Captura de pantalla de la página de secretos de GitHub Actions mostrando el secreto SONAR_TOKEN configurado (solo se ve el nombre, no el valor)]**

---

## 7. Conclusiones

### 7.1 Aspectos Positivos

✅ **0 Bugs detectados** en ambos microservicios - El código es funcionalmente correcto  
✅ **0% de duplicación** de código en ambos proyectos - Buenas prácticas de DRY  
✅ **Pipeline CI/CD automatizado** con GitHub Actions - Análisis en cada push  
✅ **Dockerización completa** con MySQL aislado por servicio - Entornos reproducibles  
✅ **Cobertura de tests** implementada con JaCoCo (53.5% y 38.4%)  
✅ **Multi-stage Dockerfile** - Imágenes optimizadas y seguras  
✅ **Healthchecks** en contenedores MySQL - Servicios dependientes esperan correctamente  

### 7.2 Áreas de Mejora

⚠️ **Vulnerabilidades:** Se detectaron 8 en admin-service y 5 en paciente-service (13 total) que deben revisarse  
⚠️ **Code Smells:** 46 en admin-service y 16 en paciente-service (62 total) que afectan la mantenibilidad  
⚠️ **Cobertura:** El paciente-service tiene 38.4% de cobertura, se recomienda aumentar al menos al 60%  
⚠️ **Quality Gate:** No está configurado un Quality Gate que bloquee merges con baja calidad  

### 7.3 Recomendaciones

1. **Revisar vulnerabilidades:** Priorizar la corrección de las 13 vulnerabilidades detectadas entre ambos servicios, especialmente las de severidad Critical y Blocker
2. **Refactorizar Code Smells:** Dedicar tiempo a mejorar la calidad del código siguiendo las recomendaciones de SonarCloud (nombres de variables, complejidad ciclomática, código muerto)
3. **Aumentar cobertura:** Agregar más tests unitarios, especialmente en el paciente-service, apuntando a mínimo 60% de cobertura
4. **Configurar Quality Gate:** Establecer un Quality Gate en SonarCloud que bloquee merges si:
   - La cobertura baja del 50%
   - Hay vulnerabilidades Critical o Blocker
   - Hay bugs nuevos
5. **Integrar con Pull Requests:** Configurar el Quality Gate como check obligatorio en los PRs para mantener la calidad del código

---

## 8. Anexos

### 8.1 Enlaces de Referencia

| Recurso | URL |
|---------|-----|
| Repositorio GitHub | https://github.com/NoSoyAle/rednorte-backend-orostica-arjona-cereno |
| SonarCloud Admin Service | https://sonarcloud.io/project/overview?id=catvstrofe_rednorte-admin-service |
| SonarCloud Paciente Service | https://sonarcloud.io/project/overview?id=catvstrofe_rednorte-paciente-service |
| GitHub Actions | https://github.com/NoSoyAle/rednorte-backend-orostica-arjona-cereno/actions |
| Documentación SonarCloud | https://docs.sonarsource.com/sonarcloud/ |
| Documentación JaCoCo | https://www.jacoco.org/jacoco/ |

### 8.2 Comandos Útiles

```bash
# Levantar contenedores Docker
docker-compose up -d

# Ver estado de contenedores
docker-compose ps

# Ver logs de un servicio
docker-compose logs admin-service
docker-compose logs paciente

# Ver logs en tiempo real
docker-compose logs -f admin-service

# Detener contenedores (manteniendo datos)
docker-compose down

# Detener contenedores (borrando datos y volúmenes)
docker-compose down -v

# Ejecutar tests con cobertura localmente
mvn clean verify

# Ejecutar análisis SonarCloud localmente
mvn sonar:sonar -Dsonar.token=TU_TOKEN -Dsonar.host.url=https://sonarcloud.io

# Acceder a MySQL de admin-service
docker exec -it mysql-admin mysql -uroot -proot123 bd-admin

# Acceder a MySQL de paciente
docker exec -it mysql-paciente mysql -uroot -proot123 bd-paciente
```

### 8.3 Estructura de Archivos Relevantes

```
rednorte-backend-orostica-arjona-cereno/
├── .github/
│   ── workflows/
│       └── sonarcloud.yml          # Pipeline CI/CD
├── admin-service/
│   ├── Dockerfile                   # Docker multi-stage
│   ├── .dockerignore
│   ├── pom.xml                      # Config JaCoCo + Sonar
│   ├── sonar-project.properties
│   └── src/
│       ├── main/java/
│       └── test/java/               # Tests unitarios
├── paciente/
│   ├── Dockerfile                   # Docker multi-stage
│   ├── .dockerignore
│   ├── pom.xml                      # Config JaCoCo + Sonar
│   ├── sonar-project.properties
│   └── src/
│       ├── main/java/
│       ├── main/resources/
│       │   └── data.sql             # Datos seed
│       └── test/java/               # Tests unitarios
│           └── resources/
│               └── application.properties  # H2 para tests
├── docker-compose.yml               # Orquestación de servicios
└── .env                             # Variables de entorno
```

---

**Documento generado como parte del proceso de aseguramiento de calidad del proyecto RedNorte Backend.**  
**Equipo: Oróstica, Arjona, Cereno**
