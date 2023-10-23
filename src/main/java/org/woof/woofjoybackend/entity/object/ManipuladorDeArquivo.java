package org.woof.woofjoybackend.entity.object;

import org.woof.woofjoybackend.domain.ListaObj;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

//Classe criada para manipular arquivos csv e txt
public class ManipuladorDeArquivo {

    public static void gravaArquivoCsv(ListaObj<Dog> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean algoErrado = false;

        nomeArq += ".csv";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try {
            for (int i = 0; i < lista.getTamanho(); i++) {
                Dog dog = lista.getElemento(i);
                saida.format("%d;%s;%s;%.2f\n", dog.getId(), dog.getNome(),
                        dog.getPorte(), dog.getPeso());
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
            algoErrado = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                algoErrado = true;
            }
            if (algoErrado) {
                System.exit(1);
            }
        }

    }

}
