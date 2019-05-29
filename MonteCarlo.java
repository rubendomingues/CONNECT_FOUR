//NOT WORKING
import java.util.*;
public class MonteCarlo{
  Node raiz;
  public class Node{
    Matriz jogo;
    Node pai;
    double vitorias;
    int visitas;
    int jogador;
    int col;
    Node[] filhos;
  Node(Node n, Matriz jogo, int col,int player){
      this.jogo = new Matriz(jogo);
      this.jogo.jogar(player,col);
      this.pai = n;
      this.vitorias = 0;
      this.visitas = 0;
      this.jogador = player;
      this.col = col;
      filhos = new Node[7];
    }
  }  //FIM DA CLASS NODE


  //Melhor escolha
  public int BestChoice(Node n){
    long tempo = 2;
    Node raiz = n;
    for (long stop = System.currentTimeMillis() + tempo; stop > System.currentTimeMillis();) {
        Node selected = Select(n);
        Node expanded = Expand(selected);
        double result = Simulation(expanded);
        BackProg(expanded, result);
    }
    int maxIndex = -1;
    for(int i = 0; i < 7; i++) {
        if(raiz.filhos[i] != null) {
            if(maxIndex == -1 || raiz.filhos[i].vitorias > raiz.filhos[maxIndex].vitorias )
                maxIndex = i;
        }
    }

    return maxIndex;
  }


  //Seleção
  public  Node Select(Node n){
    for(int i = 0; i < 7; i++) {
        if(n.filhos[i] == null && n.jogo.valido(i)) {
            return n;
        }
    }
    Node melhor=n.filhos[0];
    for(int i=1; i<7; i++){
      if(n.filhos[i].vitorias > melhor.vitorias)
        melhor=n.filhos[i];
    }
    return melhor;
  }

  //Expandir fihos
  public Node Expand(Node atual){
    ArrayList<Integer> unvisited = new ArrayList<Integer>(7);
    for(int i=0;i<7;i++){
      if(atual.filhos[i]==null && atual.jogo.valido(i))
        unvisited.add(i);
    }

    int random = unvisited.get((int)(Math.random() * unvisited.size()));
    if(atual.jogador==0)
      atual.filhos[random] = new Node(atual,atual.jogo,random,1);
    else
      atual.filhos[random] = new Node(atual,atual.jogo,random,0);

    return atual.filhos[random];
  }


  //Simulação
  public  double Simulation(Node n){

    while(!n.jogo.checkwinner(0) || !n.jogo.checkwinner(1) || !n.jogo.completo()){

      if(n.jogador==0){
        n.jogador=1;
      }
      else{
        n.jogador=0;
      }
      int i = (int)(Math.random() * 7);
      if(n.jogo.valido(i)){
        n.jogo.jogar(n.jogador,i);
      }
    }
    if(n.jogo.checkwinner(1))
      return 1;
    else if(n.jogo.checkwinner(0))
      return 0;
    else
      return 0;
  }

  //BackPropagation
  public  void BackProg(Node n, double win){
    Node currNode = n;
    while(currNode.pai!=null){
      currNode.visitas++;
      currNode.vitorias += win;
      currNode = currNode.pai;
    }
  }


  //----------------------------------------------------------------------------
}
