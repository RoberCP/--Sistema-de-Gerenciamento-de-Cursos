# Novas funcionalidades

A prova escolhida foi a 1, e a nova funcionalidade a ser implementada foi a de filtragem de produtos.

Para a prova utilizei o WildFly e o H2. O acesso à aplicação se dá da seguinte forma:

- É possível iniciar o acesso tanto por http://localhost:8080/jakarta/login quanto por http://localhost:8080/jakarta/cadastroUsuario.
- O cadastro redireciona para o login, e o login, por sua vez, redireciona até http://localhost:8080/jakarta/produtos.
- Na sessão de produtos é possível consultar todos os produtos e filtrar por nome ou descrição.
- É possível acessar também http://localhost:8080/jakarta/carrinhos.

A aplicação estava funcionando, mas por algum motivo comecei a ter um erro de deployment que não consegui corrigir.