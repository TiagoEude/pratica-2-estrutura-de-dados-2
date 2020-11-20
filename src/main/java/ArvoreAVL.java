
class No {
    String chave;
    Integer valor;
    int altura;
    No esquerdo, direito;

    No(String chave) {
        this.chave = chave;
        this.valor = 1;
        this.altura = 1;
    }
}

public class ArvoreAVL {
    No raiz;

    int altura(No N) {
        if (N == null) return 0;
        return N.altura;
    }

    int max(int a, int b) {
        return Math.max(a, b);
    }

    No rotacaoDireita(No x) {
        No y = x.esquerdo;
        No z = y.direito;

        y.direito = x;
        x.esquerdo = z;

        x.altura = max(altura(x.esquerdo), altura(x.direito)) + 1;
        y.altura = max(altura(y.esquerdo), altura(y.direito)) + 1;

        return y;
    }

    No rotacaoEsquerda(No x) {
        No y = x.direito;
        No z = y.esquerdo;

        y.esquerdo = x;
        x.direito = z;

        x.altura = max(altura(x.esquerdo), altura(x.direito)) + 1;
        y.altura = max(altura(y.esquerdo), altura(y.direito)) + 1;

        return y;
    }

    int getFB(No no) {
        if (no == null) return 0;
        return altura(no.esquerdo) - altura(no.direito);
    }

    No inserir(No no, String chave) {

        if (no == null) return (new No(chave));

        if (chave.compareTo(no.chave) < 0) no.esquerdo = inserir(no.esquerdo, chave);
        else if (chave.compareTo(no.chave) > 0) no.direito = inserir(no.direito, chave);
        else {
            no.valor += 1;
            return no;
        }

        no.altura = 1 + max(altura(no.esquerdo), altura(no.direito));

        int fb = getFB(no);
        if (fb > 1 && chave.compareTo(no.esquerdo.chave) < 0) return rotacaoDireita(no);
        if (fb < -1 && chave.compareTo(no.direito.chave) > 0) return rotacaoEsquerda(no);

        if (fb > 1 && chave.compareTo(no.esquerdo.chave) > 0) {
            no.esquerdo = rotacaoEsquerda(no.esquerdo);
            return rotacaoDireita(no);
        }

        if (fb < -1 && chave.compareTo(no.direito.chave) < 0) {
            no.direito = rotacaoDireita(no.direito);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    void preOrder(No no) {
        if (no != null) {
            System.out.print(no.chave + " - " + no.valor + "; ");
            preOrder(no.esquerdo);
            preOrder(no.direito);
        }
    }

    No noDeMenorValor(No no) {
        No noCorrente = no;
        while (noCorrente.esquerdo != null) noCorrente = noCorrente.esquerdo;
        return noCorrente;
    }

    No deletar(No raiz, String chave) {
        if (raiz == null) return raiz;

        if (chave.compareTo(raiz.chave) < 0) raiz.esquerdo  = deletar(raiz.esquerdo, chave);

        else if (chave.compareTo(raiz.chave) > 0) raiz.direito = deletar(raiz.direito, chave);

        else {
            if ((raiz.esquerdo == null) || (raiz.direito == null)) {
                No temp = null;
                if (temp == raiz.esquerdo) temp = raiz.direito;
                else temp = raiz.esquerdo;

                if (temp == null) {
                    temp = raiz;
                    raiz = null;
                } else raiz = temp;
            }
            else {
                No temp = noDeMenorValor(raiz.direito);

                raiz.chave = temp.chave;

                raiz.direito = deletar(raiz.direito, temp.chave);
            }
        }

        if (raiz == null) {
            return raiz;
        }
        raiz.altura = max(altura(raiz.esquerdo), altura(raiz.direito)) + 1;

        int fb = getFB(raiz);

        if (fb > 1 && getFB(raiz.esquerdo) >= 0) return rotacaoDireita(raiz);

        if (fb > 1 && getFB(raiz.esquerdo) < 0) {
            raiz.esquerdo = rotacaoEsquerda(raiz.esquerdo);
            return rotacaoDireita(raiz);
        }

        if (fb < -1 && getFB(raiz.direito) <= 0) return rotacaoEsquerda(raiz);

        if (fb < -1 && getFB(raiz.direito) > 0) {
            raiz.direito = rotacaoEsquerda(raiz.direito);
            return rotacaoEsquerda(raiz);
        }

        return raiz;
    }

    public static void main(String[] args)
    {
        ArvoreAVL tree = new ArvoreAVL();

        /* Constructing tree given in the above figure */
        tree.raiz = tree.inserir(tree.raiz, "a");
        tree.raiz = tree.inserir(tree.raiz, "b");
        tree.raiz = tree.inserir(tree.raiz, "c");
        tree.raiz = tree.inserir(tree.raiz, "c");
        tree.raiz = tree.inserir(tree.raiz, "c");
        tree.raiz = tree.inserir(tree.raiz, "c");
        tree.raiz = tree.inserir(tree.raiz, "d");
        tree.raiz = tree.inserir(tree.raiz, "e");
        tree.raiz = tree.inserir(tree.raiz, "f");
        tree.raiz = tree.inserir(tree.raiz, "g");
        tree.raiz = tree.inserir(tree.raiz, "h");
        tree.raiz = tree.inserir(tree.raiz, "i");

        System.out.println("Preorder traversal of "+
                "constructed tree is : ");
        tree.preOrder(tree.raiz);

        tree.raiz = tree.deletar(tree.raiz, "f");
        System.out.println("");
        System.out.println("Preorder traversal after "+
                "deletion of 10 :");
        tree.preOrder(tree.raiz);
    }
}

