package com.example.service;

import com.example.model.Documento;
import com.example.model.Beneficiario;
import com.example.repository.DocumentoRepository;
import com.example.repository.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class DocumentoService {
    private final DocumentoRepository documentoRepository;
    private final BeneficiarioRepository beneficiarioRepository;
    
    @Autowired
    public DocumentoService(DocumentoRepository documentoRepository, BeneficiarioRepository beneficiarioRepository) {
        this.documentoRepository = documentoRepository;
        this.beneficiarioRepository = beneficiarioRepository;
    }

    public List<Documento> getAllDocumentos() {
        return documentoRepository.findAll();
    }

    public Documento getDocumentoById(Long id) {
        return documentoRepository.findById(id).orElse(null);
    }

    public Documento createDocumento(Documento documento) {
        if (documento.getDataInclusao() == null) {
            documento.setDataInclusao(LocalDate.now());
        }
        if (documento.getDataAtualizacao() == null) {
            documento.setDataAtualizacao(LocalDate.now());
        }
        Long beneficiarioId = documento.getBeneficiarioId();
        if (beneficiarioId != null) {
            Optional<Beneficiario> beneficiarioOptional = beneficiarioRepository.findById(beneficiarioId);
            if (beneficiarioOptional.isPresent()) {
                Beneficiario beneficiario = beneficiarioOptional.get();
                documento.setBeneficiario(beneficiario);
                beneficiario.getDocumentos().add(documento);
            }
        }
        return documentoRepository.save(documento);
    }
    
    
    
    public Documento updateDocumento(Long id, Documento documento) {
        Documento existingDocumento = documentoRepository.findById(id).orElse(null);
        if (existingDocumento != null) {
            if (documento.getDataInclusao() == null) {
                documento.setDataInclusao(existingDocumento.getDataInclusao()); 
            }
            if (documento.getDataAtualizacao() == null) {
                documento.setDataAtualizacao(LocalDate.now());
            }
            documento.setId(id);
            return documentoRepository.save(documento);
        }
        return null;
    }


    public void deleteDocumento(Long id) {
        documentoRepository.deleteById(id);
    }
    
}


