
class No {
    int chave, altura;
    No esquerdo, direito;

    No(int chave) {
        this.chave = chave;
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
        return (a > b) ? a : b;
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

    No inserir(No no, int chave) {

        if (no == null) return (new No(chave));

        if (chave < no.chave) no.esquerdo = inserir(no.esquerdo, chave);
        else if (chave > no.chave) no.direito = inserir(no.direito, chave);
        else return no;

        no.altura = 1 + max(altura(no.esquerdo), altura(no.direito));

        int fb = getFB(no);
        if (fb > 1 && chave < no.esquerdo.chave) return rotacaoDireita(no);
        if (fb < -1 && chave > no.direito.chave) return rotacaoEsquerda(no);

        if (fb > 1 && chave > no.esquerdo.chave) {
            no.esquerdo = rotacaoEsquerda(no.esquerdo);
            return rotacaoDireita(no);
        }

        if (fb < -1 && chave < no.direito.chave) {
            no.direito = rotacaoDireita(no.direito);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    void preOrder(No no) {
        if (no != null) {
            System.out.print(no.chave + " ");
            preOrder(no.esquerdo);
            preOrder(no.direito);
        }
    }

    No noDeMenorValor(No no) {
        No noCorrente = no;
        while (noCorrente != null) noCorrente = noCorrente.esquerdo;
        return noCorrente;
    }

    No deletar(No raiz, int chave) {
        if (raiz == null) return raiz;

        if (chave < raiz.chave) raiz.esquerdo  = deletar(raiz.esquerdo, chave);
        else if (chave > raiz.chave) raiz.direito = deletar(raiz.direito, chave);
        else {
            if ((raiz.esquerdo == null) || (raiz.direito == null)) {
                No temp = null;
                if (temp == raiz.esquerdo) temp = raiz.direito;
                else temp = raiz.direito;

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
        tree.raiz = tree.inserir(tree.raiz, 9);
        tree.raiz = tree.inserir(tree.raiz, 5);
        tree.raiz = tree.inserir(tree.raiz, 10);
        tree.raiz = tree.inserir(tree.raiz, 0);
        tree.raiz = tree.inserir(tree.raiz, 6);
        tree.raiz = tree.inserir(tree.raiz, 11);
        tree.raiz = tree.inserir(tree.raiz, -1);
        tree.raiz = tree.inserir(tree.raiz, 1);
        tree.raiz = tree.inserir(tree.raiz, 2);

        /* The constructed AVL Tree would be
        9
        / \
        1 10
        / \ \
        0 5 11
        / / \
        -1 2 6
        */
        System.out.println("Preorder traversal of "+
                "constructed tree is : ");
        tree.preOrder(tree.raiz);

        tree.raiz = tree.deletar(tree.raiz, 10);

        /* The AVL Tree after deletion of 10
        1
        / \
        0 9
        /     / \
        -1 5 11
        / \
        2 6
        */
        System.out.println("");
        System.out.println("Preorder traversal after "+
                "deletion of 10 :");
        tree.preOrder(tree.raiz);
    }
}

