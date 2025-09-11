package com.unifucamp.gamearena.infra.armazenamento;

import com.unifucamp.gamearena.shared.ArquivoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class ArmazenamentoArquivosLocalImpl implements ArmazenamentoArquivos {

    @Value("${app.storage.local-path:uploads}")
    private String diretorioBase;

    @Override
    public ArquivoDTO salvar(byte[] arquivo) {
        try {
            File dir = new File(diretorioBase);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String novoNome = UUID.randomUUID().toString();

            Path caminho = Path.of(diretorioBase, novoNome);

            Files.write(caminho, arquivo);

            return new ArquivoDTO(novoNome, caminho.toString());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo", e);
        }
    }

    @Override
    public byte[] recuperar(String nomeArquivo) {
        try {
            Path arquivo = Path.of(diretorioBase, nomeArquivo);
            return Files.readAllBytes(arquivo);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao recuperar o arquivo", e);
        }
    }
}
