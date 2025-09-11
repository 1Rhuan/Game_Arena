package com.unifucamp.gamearena.infra.armazenamento;

import com.unifucamp.gamearena.shared.ArquivoDTO;

public interface ArmazenamentoArquivos {
    ArquivoDTO salvar(byte[] arquivo);
    byte[] recuperar(String caminhoArquivo);
}
