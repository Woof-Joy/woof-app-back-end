# WoofJoy
<<<<<<< HEAD
## Como iniciar Banco H2 dentro do Docker
### `1. Baixe Docker Desktop: ` 
- [ link para instalação do Docker Desktop](https://www.docker.com/products/docker-desktop/)

### `2. No terminal:`
 - `Criar container:`   docker-compose -f "C:\Users\asusn\Desktop\3 SEMESTRE\SPRINT PROJETO\woof-app-back-end\src\main\resources\docker-compose.yml" up -d
    <br><br>
 - `Matar o container:` docker-compose -f "C:\Users\asusn\Desktop\3 SEMESTRE\SPRINT PROJETO\woof-app-back-end\src\main\resources\docker-compose.yml" down 
=======
## Como iniciar banco Mysql com Docker Compose
1. ### `Baixe DOCKER DESKTOP`
link: https://www.docker.com/products/docker-desktop/
2. ### `No terminal:`
#### `Criar container`   docker-compose -f "C:\Users\asusn\Desktop\3 SEMESTRE\SPRINT PROJETO\woof-app-back-end\src\main\resources\docker-compose.yml" up -d
#### `Matar o container` docker-compose -f "C:\Users\asusn\Desktop\3 SEMESTRE\SPRINT PROJETO\woof-app-back-end\src\main\resources\docker-compose.yml" down 
3. ### `Console para consultas `
Com a aplicação rodando e seguindo os passos anteriores, acesse este link abaixo no navegador, que é uma interface de consulta semelhante ao do H2( O DBeaver também pode ser utilizado).

link: http://localhost:8000/index.php
#### username:root
#### password:bp1234

>>>>>>> 0b6030f141916ca708281bff22111ff0e11f97da

# Boas práticas 
Nomenclatura de branch: 
`feature/nome-da-atividade`

Nomenclatura de commit: 
 
1. `feat`: uma nova Feature (recurso) que você está adicionando a aplicação específica.
2. `fix`: a resolução de um bug.
3. `style`: recurso e atualizações relacionadas à estilização.
4. `refactor`: refatoração de uma seção específica da base de código.
5. `test`: tudo o que for relacionado a testes.
6. `docs`: tudo o que for relacionado a documentação.
7. `chore`: manutenção regular do código.

Exemplos:
`fix/validacao-usuario`
