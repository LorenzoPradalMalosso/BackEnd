| ID | Tipo | Requisito | Descrição detalhada | Prioridade |
|---|---|---|---|---|
| RF-01 | Funcional | Cadastro de funcionários | O sistema deve permitir o cadastro de funcionários para acesso à aplicação. | Alta |
| RF-02 | Funcional | Login por NIF e senha | O sistema deve permitir autenticação de funcionários usando NIF e senha. | Alta |
| RF-03 | Funcional | Restrição de acesso por NIF autorizado | O sistema deve permitir criar conta apenas para funcionários previamente autorizados na base de pré-cadastro. | Alta |
| RF-04 | Funcional | Controle de status do funcionário | O sistema deve permitir marcar funcionário como ativo ou inativo para controlar o acesso ao sistema. | Média |
| RF-05 | Funcional | Gerenciamento de materiais e categorias | O sistema deve permitir cadastrar, visualizar, atualizar e excluir materiais e suas categorias. | Alta |
| RF-06 | Funcional | Movimentações de estoque | O sistema deve permitir registrar entradas e saídas de materiais no estoque. | Alta |
| RF-07 | Funcional | Histórico de movimentações | O sistema deve armazenar e consultar o histórico de movimentações de estoque. | Alta |
| RF-08 | Funcional | Visualização de inventário | O sistema deve permitir visualizar o inventário de materiais em estoque. | Alta |
| RF-09 | Funcional | Gerenciamento de ativos patrimoniais | O sistema deve permitir adicionar, visualizar, atualizar e excluir ativos patrimoniais da instituição. | Alta |
| RF-10 | Funcional | Gestão de ativos patrimoniais | O sistema deve permitir registrar e consultar os dados dos ativos patrimoniais cadastrados. | Alta |
| RF-11 | Funcional | Interface de gerenciamento | A interface deve permitir cadastrar materiais, registrar movimentações, visualizar inventário e gerenciar ativos patrimoniais. | Alta |
| RF-12 | Funcional | Página inicial do sistema | O sistema deve disponibilizar uma tela inicial com acesso a login e cadastro. | Média |
| RF-13 | Funcional | Área interna autenticada | O sistema deve possuir uma área interna acessível apenas após login. | Média |
| RF-14 | Funcional | Logout | O sistema deve permitir encerrar a sessão do usuário autenticado. | Média |
| RF-15 | Funcional | API de gerenciamento | A aplicação deve disponibilizar operações para cadastrar, listar, atualizar e excluir materiais, categorias, movimentações e ativos patrimoniais. | Alta |
| RF-16 | Funcional | Persistência em banco de dados | O sistema deve armazenar materiais, categorias, movimentações, funcionários e ativos patrimoniais em banco de dados. | Alta |
| RF-17 | Funcional | Relacionamento entre entidades | O banco deve relacionar materiais, categorias, movimentações e ativos de forma coerente. | Alta |
| RF-18 | Funcional | Unicidade do NIF | O sistema deve garantir que o NIF do funcionário seja único no banco. | Alta |
| RF-19 | Funcional | Validação e retorno no acesso | O sistema deve validar o login e o cadastro de funcionários, exibindo mensagens de erro ou sucesso conforme a operação realizada. | Alta |
| RNF-01 | Não funcional | Identidade visual SENAI-SP | A interface deve seguir o Manual de Identidade Visual do SENAI-SP, utilizando corretamente cores, tipografia, logo e aplicação da marca nas telas do sistema. | Alta |
| RNF-02 | Não funcional | Usabilidade | A interface deve ser intuitiva, amigável e fácil de usar. | Alta |
| RNF-03 | Não funcional | Responsividade | A interface deve ser responsiva para diferentes tamanhos de tela. | Média |
| RNF-04 | Não funcional | Segurança de acesso | Apenas funcionários autorizados podem acessar o sistema. | Alta |
| RNF-05 | Não funcional | Segurança de sessão | O sistema deve proteger áreas internas contra acesso sem autenticação. | Alta |
| RNF-06 | Não funcional | Integridade dos dados | O sistema deve garantir consistência entre materiais, categorias, movimentações e ativos patrimoniais. | Alta |