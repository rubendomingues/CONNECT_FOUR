import java.util.*;

class Computador{

  public Computador(){
  }

  //Algoritmo MiniMax----------------------------------------------------------
  public static No minimax(No n, int depth, int jogadorAt){
    int score;
    if((n.jogo.checkwinner(jogadorAt)) || n.jogo.completo() || (depth==8)){
      if(jogadorAt==0){
        n.pontos=n.jogo.pontuacao(jogadorAt)-depth;
        return n;
      }
      else{
        n.pontos=n.jogo.pontuacao(jogadorAt)+depth;
        return n;
      }
    }
    else if(jogadorAt==0){
      score=Max(n,depth,jogadorAt).pontos;
      n.pontos=score;
      return n;
    }
    else{
      score=Min(n,depth,jogadorAt).pontos;
      n.pontos=score;
      return n;
    }

  }

  public static No Min(No n,int depth,int jogadorAt){
    int min=Integer.MAX_VALUE;
    int max=Integer.MAX_VALUE;
    int movimento=0;

    for(No x : n.fazerFilhos()){
      No temp = new No(minimax(x,depth+1,0));
      min = Math.min(min,temp.pontos);
      if(min<max){
        max=min;
        movimento=temp.movimento;
      }
    }
    n.pontos=min;
    if(depth==0)
    n.movimento=movimento;
    return n;
  }

  public static No Max(No n,int depth,int jogadorAt){
    int min=Integer.MIN_VALUE;
    int max=Integer.MIN_VALUE;
    int movimento=0;

    for(No x : n.fazerFilhos()){
      No temp = new No(minimax(x,depth+1,1));
      min = Math.max(min,temp.pontos);
      if(min>max){
        max=min;
        movimento=temp.movimento;
      }
    }
    n.pontos=min;
    if(depth==0)
    n.movimento=movimento;
    return n;
  }
  //Algoritmo Alpha-beta-------------------------------------------------------
  public static No alpha_beta(No n,int depth,int jogadorAt, int alpha, int beta){
    int score;
    if((n.jogo.checkwinner(jogadorAt)) || n.jogo.completo() || (depth==8)){
      if(jogadorAt==0){
        n.pontos=n.jogo.pontuacao(jogadorAt)-depth;
        return n;
      }
      else{
        n.pontos=n.jogo.pontuacao(jogadorAt)+depth;
        return n;
      }
    }
    else if(jogadorAt==0){
      score=alpha_betaMax(n,depth,jogadorAt,alpha,beta).pontos;
      n.pontos=score;
      return n;
    }
    else{
      score=alpha_betaMin(n,depth,jogadorAt,alpha,beta).pontos;
      n.pontos=score;
      return n;
    }
  }

  public static No alpha_betaMin(No n,int depth,int jogadorAt, int alpha, int beta){
    int min=Integer.MAX_VALUE;
    int max=Integer.MAX_VALUE;
    int movimento=0;
    n.alpha=alpha;

    for(No x : n.fazerFilhos()){
      No temp = new No(alpha_beta(x,depth+1,0,alpha,beta));
      min = Math.min(min,temp.pontos);
      if(min<max){
        max=min;
        movimento=temp.movimento;
      }
      if(min <= n.alpha){
        n.pontos=min;
        return n;
      }
      beta=Math.min(beta,min);
    }
    n.pontos=min;
    if(depth==0)
    n.movimento=movimento;
    return n;
  }

  public static No alpha_betaMax(No n,int depth,int jogadorAt, int alpha, int beta){
    int min=Integer.MIN_VALUE;
    int max=Integer.MIN_VALUE;
    int movimento=0;
    n.beta=beta;

    for(No x : n.fazerFilhos()){
      No temp = new No(alpha_beta(x,depth+1,1,n.alpha,n.beta));
      min = Math.max(min,temp.pontos);
      if(min>max){
        max=min;
        movimento=temp.movimento;
      }
      if(min >= n.beta){
        n.pontos=min;
        return n;
      }
      alpha=Math.max(alpha,min);
    }
    n.pontos=min;
    if(depth==0)
    n.movimento=movimento;
    return n;
  }

//------------------------------------------------------------------------------
}
