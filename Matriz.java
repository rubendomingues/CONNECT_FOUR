import java.util.*;

class Matriz{
  char[][] jogo;
  int l;
  int c;
  int colPlayed;
  //Construtor da Matriz usada nas pesquisas
  Matriz(int linhas, int colunas){
    this.l=linhas;
    this.c=colunas;
    this.jogo = new char[this.l][this.c];
    for(int i=0; i<linhas; i++){
      for(int j=0; j<colunas; j++){
        this.jogo[i][j] = '-';
      }
    }
  }

  //Construtor da matriz antes de efetuar movimento
  Matriz(Matriz matrizCopia) {
    this.jogo = new char[matrizCopia.l][matrizCopia.c];
    for (int l = 0 ; l < matrizCopia.l ; l++){
      for (int c = 0; c < matrizCopia.c ; c++) {
        jogo[l][c] = matrizCopia.jogo[l][c];
      }
    }
    this.l = matrizCopia.l;
    this.c = matrizCopia.c;
  }

  //Verficar se a jogado é válida
  boolean valido(int coluna){
    if(coluna>6 || coluna <0 || this.jogo[0][coluna] != '-')
      return false;
    return true;
  }

  //Fazer jogada
  void jogar(int jogador, int coluna){
    char mov;
    if(jogador==0)
      mov = 'X';
    else
      mov = 'O';
    for(int i=l-1; i>=0; i--){
        if(this.jogo[i][coluna] == '-'){
          this.jogo[i][coluna] = mov;
          break;
        }
    }
    this.colPlayed=coluna;
  }

  //Verificar se alguém ganhou
  boolean checkwinner(int jogador){
    int counter=0;
    char mov;
    if(jogador==0)
      mov = 'X';
    else
      mov = 'O';
    //Verificar linhas
    for(int i=0; i<l; i++){
      for(int j=0; j<c; j++){
        if(jogo[i][j]== mov){
          counter++;
        }
        else{
          counter=0;
        }
        if(counter == 4)
          return true;
      }
      counter=0;
    }
    counter=0;

    //Verificar colunas
    for(int i=0; i<c; i++){
      for(int j=0; j<l; j++){
        if(jogo[j][i]==mov){
          counter++;
        }
        else{
          counter=0;
        }
        if(counter == 4)
          return true;
      }
      counter=0;
    }
    counter=0;

    //Verificar diagonais principais
    for(int i=3; i<l; i++){
      for(int j=0; j<c-3; j++){
        if(jogo[i][j] == mov && jogo[i-1][j+1] == mov && jogo[i-2][j+2] == mov && jogo[i-3][j+3] == mov)
          return true;
      }
    }

    //Verificar diagonais secundárias
    for(int i=3; i<l; i++){
      for(int j=3; j<c; j++){
        if(jogo[i][j] == mov && jogo[i-1][j-1] == mov && jogo[i-2][j-2] == mov && jogo[i-3][j-3] == mov)
          return true;
      }
    }

    return false;
  }
  //Valor dos pontos
  int pontos(int xcounter, int ocounter){
    if(xcounter==4 && ocounter==0)
      return 512;
    else if(xcounter==3 && ocounter==0)
      return 50;
    else if(xcounter==2 && ocounter==0)
      return 10;
    else if(xcounter==1 && ocounter==0)
      return 1;
    else if(ocounter==1 && xcounter==0)
      return -1;
    else if(ocounter==2 && xcounter==0)
      return -10;
    else if(ocounter==3 && xcounter==0)
      return -50;
    else if(ocounter==4 && xcounter==0)
      return -512;
    else
      return 0;
  }
  //Pontuações
  int pontuacao(int jogador){
    int xcounter=0;
    int ocounter=0;
    int total=0;
    if(jogador==0)
      if(this.checkwinner(jogador))
        return 512;
    if(jogador==1)
      if(this.checkwinner(jogador))
        return -512;
    if(this.completo())
      return 0;
    //Pontos das linhas
    for(int i=0; i<l; i++){
      for(int j=0; j<c-3; j++){
        if(jogo[i][j]=='X')
          xcounter++;
        if(jogo[i][j+1]=='X')
          xcounter++;
        if(jogo[i][j+2]=='X')
          xcounter++;
        if(jogo[i][j+3]=='X')
          xcounter++;

        if(jogo[i][j]=='O')
          ocounter++;
        if(jogo[i][j+1]=='O')
          ocounter++;
        if(jogo[i][j+2]=='O')
          ocounter++;
        if(jogo[i][j+3]=='O')
          ocounter++;

        total+=pontos(xcounter,ocounter);
        xcounter=0;
        ocounter=0;
      }
    }

    //Pontos das colunas
    for(int i=0; i<c-4; i++){
      for(int j=0; j<l+1; j++){
        if(jogo[i][j]=='X')
          xcounter++;
        if(jogo[i+1][j]=='X')
          xcounter++;
        if(jogo[i+2][j]=='X')
          xcounter++;
        if(jogo[i+3][j]=='X')
          xcounter++;

        if(jogo[i][j]=='O')
          ocounter++;
        if(jogo[i+1][j]=='O')
          ocounter++;
        if(jogo[i+2][j]=='O')
          ocounter++;
        if(jogo[i+3][j]=='O')
          ocounter++;


        total+=pontos(xcounter,ocounter);
        xcounter=0;
        ocounter=0;
      }
    }

    //Pontos da diagonal principal
    for(int i=3; i<l; i++){
      for(int j=0; j<c-3; j++){
        if(jogo[i][j]=='X')
          xcounter++;
        if(jogo[i-1][j+1]=='X')
          xcounter++;
        if(jogo[i-2][j+2]=='X')
          xcounter++;
        if(jogo[i-3][j+3]=='X')
          xcounter++;

        if(jogo[i][j]=='O')
          ocounter++;
        if(jogo[i-1][j+1]=='O')
          ocounter++;
        if(jogo[i-2][j+2]=='O')
          ocounter++;
        if(jogo[i-3][j+3]=='O')
          ocounter++;


        total+=pontos(xcounter,ocounter);
        xcounter=0;
        ocounter=0;
      }
    }

    //Pontos da diagonal secundária
    for(int i=3; i<l; i++){
      for(int j=c-1; j>=c-4; j--){
        if(jogo[i][j]=='X')
          xcounter++;
        if(jogo[i-1][j-1]=='X')
          xcounter++;
        if(jogo[i-2][j-2]=='X')
          xcounter++;
        if(jogo[i-3][j-3]=='X')
          xcounter++;

        if(jogo[i][j]=='O')
          ocounter++;
        if(jogo[i-1][j-1]=='O')
          ocounter++;
        if(jogo[i-2][j-2]=='O')
          ocounter++;
        if(jogo[i-3][j-3]=='O')
          ocounter++;


        total+=pontos(xcounter,ocounter);
        xcounter=0;
        ocounter=0;
      }
    }
    if(jogador==1)
      total-=16;
    else
      total +=16;
    return total; //Return dos Pontos do tabuleiro
  }

  //Verifica se o jogo está cheio(acabou/empate);
  boolean completo(){
    for(int i=0; i<l; i++){
      for(int j=0; j<c; j++){
        if(jogo[i][j]=='-')
          return false;
      }
    }
    return true;
  }

  //Imprime o jogo
  void printMatriz(){
    System.out.println();
    for(int i=0; i<l; i++){
      for(int j=0; j<c; j++){
        System.out.print(this.jogo[i][j] + " ");
      }
      System.out.println("");
    }
    System.out.println("0|1|2|3|4|5|6");
  }

}
