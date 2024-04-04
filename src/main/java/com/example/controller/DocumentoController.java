package com.example.controller;

import com.example.model.Documento;
import com.example.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {
    private static final Logger logger = LoggerFactory.getLogger(DocumentoController.class);
    private final DocumentoService documentoService;

    @Autowired
    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @GetMapping
    public ResponseEntity<List<Documento>> getAllDocumentos() {
        logger.info("Recebida solicitação para listar todos os documentos.");
        List<Documento> documentos = documentoService.getAllDocumentos();
        return ResponseEntity.ok(documentos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Documento> getDocumentoById(@PathVariable Long id) {
        logger.info("Recebida solicitação para obter documento por ID: {}", id);
        Documento documento = documentoService.getDocumentoById(id);
        if (documento != null) {
            return ResponseEntity.ok(documento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Documento> createDocumento(@RequestBody Documento documento) {
        Documento createdDocumento = documentoService.createDocumento(documento);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDocumento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Documento> updateDocumento(@PathVariable Long id, @RequestBody Documento documento) {
        Documento updatedDocumento = documentoService.updateDocumento(id, documento);
        if (updatedDocumento != null) {
            return ResponseEntity.ok(updatedDocumento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumento(@PathVariable Long id) {
        documentoService.deleteDocumento(id);
        return ResponseEntity.noContent().build();
    }
}


