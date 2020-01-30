import java.util.*;

public class Main{
  static int jogador=0;

  static void jogador() {
    if(jogador==0)
      jogador=1;
    else
      jogador=0;
  }

  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    Matriz jogo = new Matriz(6,7);
    Computador PC = new Computador();
    Computador PC2= new Computador();
    MenuInicial();
    MenuTipo();
    int tipo = scan.nextInt();
    if(tipo == 1){ // JOGADOR VS JOGADOR
      jogo.printMatriz();
      while(!jogo.completo()){
        //JOGADOR 1
        System.out.print("Jogador 1: ");
        int j1 = scan.nextInt();
        while(true){
          if(jogo.valido(j1)){
            jogo.jogar(jogador,j1);
            jogo.printMatriz();
            break;
          }
          else{
            System.out.println("Movimento inválido!");
            System.out.print("Jogador 1: ");
            j1 = scan.nextInt();
          }
        }
        if(jogo.checkwinner(jogador)){
          System.out.println("Ganhou jogador 1!");
          return;
        }
        jogador();//Trocar para jogador 2
        //JOGADOR 2
        System.out.print("Jogador 2: ");
        int j2 = scan.nextInt();
        while(true){
          if(jogo.valido(j2)){
            jogo.jogar(jogador,j2);
            jogo.printMatriz();
            break;
          }
          else{
            System.out.println("Movimento inválido!");
            System.out.print("Jogador 2: ");
            j2 = scan.nextInt();
          }
        }
        if(jogo.checkwinner(jogador)){
          System.out.println("Ganhou jogador 2!");
          return;
        }

        jogador();//Trocar para jogador 1
      }
      //EMPATE
      System.out.println("EMPATE!");
      return;
    }

    else if(tipo == 2){ // JOGADOR VS COMPUTADOR
      MenuPrimeiro();
      int primeiro = scan.nextInt();
      while(primeiro != 1 && primeiro != 2){
        MenuPrimeiro();
        primeiro = scan.nextInt();
      }
      MenuAlg();
      int alg = scan.nextInt();
      while(alg!=1 && alg!=2){
        MenuAlg();
        alg = scan.nextInt();
      }
      if(primeiro == 1){ // JOGADOR COMEÇA
        jogo.printMatriz();
        while(!jogo.completo()){
          //JOGADOR
          System.out.print("Jogador : ");
          int j1 = scan.nextInt();
          while(true){
            if(jogo.valido(j1)){
              jogo.jogar(jogador,j1);
              jogo.printMatriz();
              break;
            }
            else{
              System.out.println("Movimento inválido!");
              System.out.print("Jogador : ");
              j1 = scan.nextInt();
            }
          }
          if(jogo.checkwinner(jogador)){
            System.out.println("Ganhou jogador!");
            return;
          }
          jogador();//Trocar para o computador
          //COMPUTADOR
          long tempoInicial = System.currentTimeMillis();
          int pos=0;
          int passos=0;
          if(alg==1){
            No atual = new No(jogo,jogador);
            atual.nos=0;
            No jogada = PC.minimax(atual,0,jogador);
            jogo.jogar(jogador,jogada.movimento);
            passos=jogada.nos;
            pos=jogada.movimento;

          }
          else if(alg==2){
            No atual = new No(jogo,jogador);
            atual.nos=0;
            No jogada = PC.alpha_beta(atual,0,jogador,atual.alpha,atual.beta);
            jogo.jogar(jogador,jogada.movimento);
            pos=jogada.movimento;
            passos=jogada.nos;
          }
          long tempoFinal = System.currentTimeMillis();
          jogo.printMatriz();
          System.out.println("Computador:");
          System.out.println("Tempo: " + (tempoFinal-tempoInicial) + "ms" + " | Nós gerados: " + passos + " | Jogada:" + pos);
          if(jogo.checkwinner(jogador)){
            System.out.println("Ganhou Computador!");
            return;
          }
          jogador();//Trocar para o jogador
        }
        //EMPATE
        System.out.println("EMPATE!");
        return;
      }
      else if(primeiro == 2){ // COMPUTADOR COMEÇA
        jogador();
        while(!jogo.completo()){
          //COMPUTADOR
          int passos=0;
          int pos=0;
          long tempoInicial = System.currentTimeMillis();
          if(alg==1){
            No atual = new No(jogo,jogador);
            atual.nos=0;
            No jogada = PC.minimax(atual,0,jogador);
            jogo.jogar(jogador,jogada.movimento);
            pos=jogada.movimento;
            passos=jogada.nos;
          }
          else if(alg==2){
            No atual = new No(jogo,jogador);
            atual.nos=0;
            No jogada = PC.alpha_beta(atual,0,jogador,atual.alpha,atual.beta);
            jogo.jogar(jogador,jogada.movimento);
            pos=jogada.movimento;
            passos=jogada.nos;
          }
          jogo.printMatriz();
          long tempoFinal = System.currentTimeMillis();
          System.out.println("Computador: " );
          System.out.println("Tempo: " + (tempoFinal-tempoInicial) + "ms" + " | Nós gerados: " + passos + " | Jogada:" + pos);
          if(jogo.checkwinner(jogador)){
            System.out.println("Ganhou Computador!");
            return;
          }
          jogador();//Trocar para o jogador
          //JOGADOR
          System.out.print("Jogador : ");
          int j1 = scan.nextInt();
          while(true){
            if(jogo.valido(j1)){
              jogo.jogar(jogador,j1);
              jogo.printMatriz();
              break;
            }
            else{
              System.out.println("Movimento inválido!");
              System.out.print("Jogador : ");
              j1 = scan.nextInt();
            }
          }
          if(jogo.checkwinner(jogador)){
            System.out.println("Ganhou jogador!");
            return;
          }
          jogador();//Trocar para o computador
        }
        System.out.println("EMPATE!");
        return;
      }
    }
    else if(tipo==3){
      MenuAlg();
      int alg = scan.nextInt();
      if(alg==1){ //Minimax vs Minimax
        while(!jogo.completo()){
          //PC1
          int passos=0;
          int pos=0;
          long tempoInicial = System.currentTimeMillis();
          No atual = new No(jogo,jogador);
          atual.nos=0;
          No jogada = PC.minimax(atual,0,jogador);
          jogo.jogar(jogador,jogada.movimento);
          pos=jogada.movimento;
          passos=jogada.nos;
          long tempoFinal = System.currentTimeMillis();
          jogo.printMatriz();
          System.out.println("Computador 1");
          System.out.println("Tempo: " + (tempoFinal-tempoInicial) + "ms" + " | Nós gerados: " + passos + " | Jogada:" + pos);
          if(jogo.checkwinner(jogador)){
            System.out.println("Ganhou Computador 1!");
            return;
          }
          atual.nos=0;
          //PC2
          jogador();
          passos=0;
          pos=0;
          tempoInicial = System.currentTimeMillis();
          atual = new No(jogo,jogador);
          jogada = PC2.minimax(atual,0,jogador);
          jogo.jogar(jogador,jogada.movimento);
          pos=jogada.movimento;
          passos=jogada.nos;
          tempoFinal = System.currentTimeMillis();
          jogo.printMatriz();
          System.out.println("Computador 2");
          System.out.println("Tempo: " + (tempoFinal-tempoInicial) + "ms" + " | Nós gerados: " + passos + " | Jogada:" + pos);
          if(jogo.checkwinner(jogador)){
            System.out.println("Ganhou Computador 2!");
            return;
          }
          jogador();
        }
        System.out.println("EMPATE!");
        return;
      }
      else if(alg==2){ // AlphaBeta vs AlphaBeta
        while(!jogo.completo()){
          //PC1
          int passos=0;
          int pos=0;
          long tempoInicial = System.currentTimeMillis();
          No atual = new No(jogo,jogador);
          atual.nos=0;
          No jogada = PC.alpha_beta(atual,0,jogador,atual.alpha,atual.beta);
          jogo.jogar(jogador,jogada.movimento);
          pos=jogada.movimento;
          passos=jogada.nos;
          long tempoFinal = System.currentTimeMillis();
          jogo.printMatriz();
          System.out.println("Computador 1");
          System.out.println("Tempo: " + (tempoFinal-tempoInicial) + "ms" + " | Nós gerados: " + passos + " | Jogada:" + pos);
          if(jogo.checkwinner(jogador)){
            System.out.println("Ganhou Computador 1!");
            return;
          }
          //PC2
          jogador();
          passos=0;
          pos=0;
          tempoInicial = System.currentTimeMillis();
          atual = new No(jogo,jogador);
          atual.nos=0;
          jogada = PC2.alpha_beta(atual,0,jogador,atual.alpha,atual.beta);
          jogo.jogar(jogador,jogada.movimento);
          pos=jogada.movimento;
          passos=jogada.nos;
          tempoFinal = System.currentTimeMillis();
          jogo.printMatriz();
          System.out.println("Computador 2");
          System.out.println("Tempo: " + (tempoFinal-tempoInicial) + "ms" + " | Nós gerados: " + passos + " | Jogada:" + pos);
          if(jogo.checkwinner(jogador)){
            System.out.println("Ganhou Computador 2!");
            return;
          }
          jogador();
        }
        System.out.println("EMPATE!");
        return;
      }
    }


  }
  //----------------------------FIM---------------------------------------
  //---------------------------MENUS--------------------------------------
  static void MenuInicial(){
    System.out.println("******************************************");
    System.out.println("**                                      **");
    System.out.println("**             BEM-VINDO                **");
    System.out.println("**                AO                    **");
    System.out.println("**            4 EM LINHA                **");
    System.out.println("**                                      **");
    System.out.println("******************************************");
  }

  static void MenuTipo(){
    System.out.println("Tipo de jogo:");
    System.out.println("1) Jogador vs Jogador");
    System.out.println("2) Jogador vs Computador");
    System.out.println("3) Computador vs Computador");
  }

  static void MenuPrimeiro(){
    System.out.println("Quem começa a jogar:");
    System.out.println("1) Jogador");
    System.out.println("2) Computador");
  }

  static void MenuAlg(){
    System.out.println("Selecione o algoritmo do computador:");
    System.out.println("1) Minimax");
    System.out.println("2) Alpha-beta");
  }

}
