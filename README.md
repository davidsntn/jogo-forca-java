# 🔮 Jogo do Enforcado - Edição Tarô 🔮

Bem-vindo ao Jogo do Enforcado, uma releitura mística do clássico Jogo da Forca! Este projeto back-end, desenvolvido em Java com Gradle, desafia você a desvendar palavras secretas retiradas diretamente do universo das cartas de tarô.

---

## 📜 Descrição do Projeto

Este é um projeto back-end que implementa a lógica do tradicional Jogo da Forca. A temática "Jogo do Enforcado" é uma referência à carta de mesmo nome do tarô, e todas as palavras secretas do jogo são nomes de arcanos maiores, arcanos menores ou outros conceitos do tarô, tornando cada partida uma experiência única e interessante.

---

## 🎲 Regras e Funcionamento do Jogo

### 1. O Jogo
Forca (ou Enforcado, nesta versão) é um jogo clássico em que o objetivo do jogador é adivinhar uma palavra secreta, sugerindo uma letra por vez, dentro de um número limitado de tentativas.

### 2. Como Jogar
O fluxo da partida funciona da seguinte forma:

- **Início:** Uma palavra secreta, previamente escolhida pelo sistema (o "anfitrião"), é apresentada ao jogador como uma série de espaços vazios (`_ _ _ _ _`), cada um correspondendo a uma letra.

- **Ação do Jogador:** O jogador informa uma letra que acredita fazer parte da palavra.

- **Resultado:**
  - **Letra Correta:** Se a letra existir na palavra secreta, ela será revelada em todas as posições correspondentes.
  - **Letra Incorreta:** Se a letra não estiver na palavra, uma parte do corpo do enforcado é desenhada. O número de erros é limitado.

### 3. Condições de Vitória e Derrota

- **Vitória:** O jogador ganha o jogo se conseguir revelar a palavra completa antes que a figura do enforcado seja totalmente desenhada.
- **Derrota:** O jogador perde se o limite de erros for atingido, o que é visualmente representado pela figura completa do enforcado.

---

## ✨ Funcionalidades Adicionais

Durante a partida, o jogador pode interagir com o sistema através das seguintes funcionalidades:

- **Verificar Status:** A qualquer momento, o jogador pode consultar o status atual da partida, que pode ser:
  - `PENDENTE`: O jogo ainda está em andamento.
  - `GANHOU`: O jogador adivinhou a palavra e venceu.
  - `PERDEU`: O limite de erros foi atingido e o jogador foi derrotado.

- **Sair do Jogo:** O jogador tem a liberdade de abandonar a partida a qualquer momento.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java
- **Gerenciador de Dependências:** Gradle
