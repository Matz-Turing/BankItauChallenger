# Solução do Desafio Técnico - API de Transações

<img src="https://user-images.githubusercontent.com/74038190/212284115-f47cd8ff-2ffb-4b04-b5bf-4d1c14c0247f.gif" width="1000">

## Visão Geral
Este documento descreve a solução implementada para o desafio técnico de criar uma API REST para processamento de transações financeiras e geração de estatísticas em tempo real.

---

## Tecnologias Escolhidas
- **Java 17** por sua estabilidade e recursos modernos  
- **Spring Boot 3.2.2** como framework principal  
- **Maven** para gerenciamento de dependências  
- **Docker** para containerização  

---

## Arquitetura da Solução

### Organização do Código
A aplicação foi estruturada em três camadas principais:
- **Camada de Controladores**: Responsável por receber as requisições HTTP  
- **Camada de Serviços**: Contém a lógica de negócios  
- **Camada de Modelos**: Define as estruturas de dados  

### Armazenamento de Dados
- Implementação totalmente em memória, conforme requisito  
- Utilização de estruturas **thread-safe** para concorrência  
- Sistema de **limpeza automática** para dados antigos  

---

## Funcionalidades Implementadas

### 1. Gestão de Transações
- **Recebimento** de novas transações com validação completa  
- **Limpeza** de todas as transações  

#### Validações implementadas:
- Campos obrigatórios  
- Valores não negativos  
- Datas não futuras  
- Formato ISO 8601  

### 2. Cálculo de Estatísticas
- **Processamento em tempo real**  
- **Janela móvel de 60 segundos**  

#### Métricas calculadas:
- Contagem de transações  
- Soma total  
- Média  
- Valor mínimo  
- Valor máximo  

### 3. Respostas HTTP
- **201** para criação bem-sucedida  
- **422** para dados inválidos  
- **400** para requisições mal formatadas  
- **200** para operações bem-sucedidas  

---

## Recursos Extras Implementados

### 1. Qualidade de Código
- **Testes unitários abrangentes**  
- **Validação de casos de borda**  
- **Tratamento adequado de erros**  

### 2. Observabilidade
- **Endpoints de health check**  
- **Métricas operacionais**  
- **Logs estruturados**  

### 3. Documentação
- **Interface Swagger** para API  
- **Especificação OpenAPI**  
- **Instruções detalhadas de uso**  

### 4. Operação
- **Suporte a containerização**  
- **Configurações externalizadas**  
- **Fácil implantação**  

---

## Decisões Técnicas Importantes

### Performance
- Uso de **estruturas de dados otimizadas**  
- **Processamento eficiente** de estatísticas  
- **Limpeza automática** de dados antigos  

### Segurança
- **Validação rigorosa** de entradas  
- **Proteção contra dados inválidos**  
- **Respostas seguras**  

### Manutenibilidade
- **Código bem organizado**  
- **Responsabilidades bem definidas**  
- **Fácil extensibilidade**  

---

## Configurações Disponíveis
- **Tamanho da janela de estatísticas**  
- **Porta do servidor**  
- **Configurações de logging**  
- **Opções de monitoramento**  

---

## Requisitos para Execução
- **JDK 17** ou superior  
- **Maven ou Docker**  
- **Memória suficiente para processamento**  

---

## Monitoramento e Saúde
- **Endpoint de health check**  
- **Métricas de performance**  
- **Status da aplicação**  

---

Esta solução atende a todos os requisitos obrigatórios do desafio e implementa diversos recursos extras, resultando em uma aplicação **robusta, bem documentada e fácil de manter**. A arquitetura escolhida permite **fácil extensão** para novas funcionalidades e a **documentação completa** facilita o trabalho de outros desenvolvedores com o código.

## Créditos

Desenvolvido por Mateus S.  
GitHub: [Matz-Turing](https://github.com/Matz-Turing)
