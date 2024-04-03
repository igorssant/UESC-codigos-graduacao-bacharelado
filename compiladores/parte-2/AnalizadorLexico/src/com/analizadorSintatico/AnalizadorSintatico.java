package com.analizadorSintatico;

import com.analizadorSintatico.automato.Automato;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AnalizadorSintatico {
    public static void analizadorSintatico(File arquivo) {
        Automato automato = new Automato();

        try(FileInputStream bufferLeitura = new FileInputStream(arquivo)) {
            Character caractereAtual;

            while(bufferLeitura.available() > 0) {
                caractereAtual = (char) bufferLeitura.read();
                automato.lerCaractere(caractereAtual.toString());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Finalizando execução do programa...");
        }
    }

    public static void main(String[] args) {
        File arquivo = new File("src/entrada/entrada.cic");

        if(!arquivo.exists()) {
            System.out.println("O caminho passado está incorreto ou não existe nenhum arquivo com este nome!");
            System.exit(-1);
        }

        if(!(arquivo.isFile() && arquivo.canRead())) {
            System.out.println("Não foi possível abrir o arquivo: " + arquivo.getName() + "\n");
            System.exit(-1);
        }

        System.out.println("Inicializando programa...");
        analizadorSintatico(arquivo);
    }
}
