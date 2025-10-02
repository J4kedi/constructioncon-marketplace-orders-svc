
# Stage 1: Build Stage
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiar o pom.xml e baixar as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar o resto do código-fonte e construir o projeto
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Production Stage
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copiar o JAR do estágio de build
COPY --from=builder /app/target/*.war app.war

# Expor a porta da aplicação
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.war"]
