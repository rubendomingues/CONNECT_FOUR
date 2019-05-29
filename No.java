import java.util.*;

class No{
  static int nos=0;
  Matriz jogo;
  int movimento;
  int jogadorAtual;
  int pontos;
  int alpha;
  int beta;

  //Construtor raiz
  No(Matriz jogo,int jogador){
    this.jogo = new Matriz(jogo);
    this.jogadorAtual=jogador;
    this.movimento=0;
    this.pontos = 0;
    this.alpha=Integer.MIN_VALUE;
    this.beta=Integer.MAX_VALUE;
  }

  //Construtor dos filhos
  No(Matriz jogo,int jogador,int move){
    this.jogo = new Matriz(jogo);
    this.jogadorAtual = jogador;
    this.movimento = move;
    this.pontos = 0;
    this.alpha = Integer.MIN_VALUE;
    this.beta = Integer.MAX_VALUE;
  }

  No(No m){
    this.jogo=m.jogo;
    this.jogadorAtual=m.jogadorAtual;
    this.movimento=m.movimento;
    this.pontos=m.pontos;
    this.alpha = m.alpha;
    this.beta = m.beta;
  }

  //Criar filhos
  public ArrayList<No> fazerFilhos(){
    Matriz temp = new Matriz(jogo);
    ArrayList<No> filhos = new ArrayList<No>();
    for(int i = 0; i < temp.c;i++){
      if(temp.valido(i)){
        temp = new Matriz(jogo);
        temp.jogar(jogadorAtual,i);

        if(jogadorAtual==0) {
          No filho= new No(temp, 1,i);
          filhos.add(filho);
        }
        else{
          No filho = new No(temp,0,i);
          filhos.add(filho);
        }
      }
    }
    nos+=filhos.size();
    return filhos;
  }

}
