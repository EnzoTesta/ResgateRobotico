# 🤖 Sistema de Coordenação de Robôs para Missões de Resgate

## 📌 Sobre o projeto

O **Sistema de Coordenação de Robôs para Missões de Resgate** é um projeto desenvolvido em Java com o objetivo de gerenciar robôs autônomos responsáveis por executar missões de resgate em diferentes áreas de operação.

O sistema permite cadastrar e gerenciar robôs, registrar missões, acompanhar seus status e realizar a distribuição automática de tarefas de acordo com as características e a disponibilidade dos robôs.

## ⚙️ Funcionalidades

- Cadastro e gerenciamento de robôs.
- Suporte a diferentes tipos de robôs, como drones e robôs terrestres.
- Cadastro e gerenciamento de áreas de operação.
- Registro e acompanhamento de missões.
- Controle de bateria e disponibilidade dos robôs.
- Verificação de compatibilidade entre robôs e missões.
- Fila de missões pendentes.
- Despacho inteligente e automático de robôs.
- Geração de relatórios sobre o sistema.
- Gravação e recuperação dos dados por meio de arquivos.

## 🧠 Tecnologias e conceitos utilizados

O projeto foi desenvolvido utilizando Java e conceitos de programação orientada a objetos.

Entre os principais recursos utilizados estão:

- **Herança:** especialização da classe abstrata `Robo` em diferentes tipos de robôs.
- **Polimorfismo:** utilização de diferentes implementações de métodos nas subclasses.
- **Encapsulamento:** proteção dos atributos e controle de acesso aos dados.
- **Classes abstratas e interfaces:** organização e padronização das funcionalidades.
- **Collections Framework:** utilização de `HashMap`, `List` e `Queue` para gerenciamento dos dados.
- **Streams API:** filtragem e seleção automática de robôs.
- **Optional:** tratamento de possíveis resultados ausentes durante a busca por robôs.
- **Serialização:** armazenamento dos objetos em arquivos para manter os dados entre execuções.

## 🚀 Despacho inteligente

Uma das principais funcionalidades do projeto é o despacho inteligente automático.

O sistema utiliza a API de Streams para encontrar o robô mais adequado para determinada missão, considerando critérios como:

1. Disponibilidade do robô.
2. Compatibilidade com o tipo de missão.
3. Nível de bateria suficiente para executar a tarefa.
4. Maior nível de bateria entre os robôs elegíveis.

Após a seleção, o robô é atribuído à missão e realiza sua execução.

## 💾 Persistência de dados

O projeto utiliza serialização de objetos para armazenar os dados do sistema em um arquivo chamado `dados_central.dat`.

São utilizadas as classes `ObjectOutputStream` e `ObjectInputStream` para, respectivamente, gravar e recuperar os objetos.

Dessa forma, as informações cadastradas podem ser preservadas mesmo após o encerramento do programa.

As classes que precisam ser armazenadas implementam a interface `Serializable`.

## 🏗️ Organização do sistema

| Classe | Responsabilidade |
|---|---|
| `CentralResgate` | Gerenciamento central do sistema |
| `Robo` | Classe abstrata que representa os robôs |
| `Drone` | Representação dos robôs aéreos |
| `RoboTerrestre` | Representação dos robôs terrestres |
| `Missao` | Representação das missões de resgate |
| `AreaOperacao` | Gerenciamento das áreas de operação |
| `Relatorio` | Geração de informações e relatórios |

O projeto também utiliza enums para representar estados, tipos, prioridades e classificações do sistema.

## 👥 Desenvolvedores

Projeto acadêmico desenvolvido como atividade prática de programação em Java, com foco em orientação a objetos, estruturas de dados, manipulação de arquivos e automação de processos.

**Instituição:** PUCRS – Pontifícia Universidade Católica do Rio Grande do Sul
