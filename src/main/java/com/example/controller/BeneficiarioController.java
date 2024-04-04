package com.example.controller;

import com.example.model.Beneficiario;
import com.example.service.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;



import java.util.List;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController { 
    private final BeneficiarioService beneficiarioService;

    @Autowired
    public BeneficiarioController(BeneficiarioService beneficiarioService) {
        this.beneficiarioService = beneficiarioService;
    }

    @GetMapping
    public ResponseEntity<List<Beneficiario>> getAllBeneficiarios() {
        List<Beneficiario> beneficiarios = beneficiarioService.getAllBeneficiarios();
        return new ResponseEntity<>(beneficiarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficiario> getBeneficiarioById(@PathVariable("id") Long id) {
        Beneficiario beneficiario = beneficiarioService.getBeneficiarioById(id);
        if (beneficiario != null) {
            return new ResponseEntity<>(beneficiario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Beneficiario> createBeneficiario(@RequestBody Beneficiario beneficiario) {
        Beneficiario createdBeneficiario = beneficiarioService.createBeneficiario(beneficiario);
        return new ResponseEntity<>(createdBeneficiario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Beneficiario> updateBeneficiario(@PathVariable("id") Long id, @RequestBody Beneficiario beneficiario) {
        Beneficiario existingBeneficiario = beneficiarioService.getBeneficiarioById(id);
        if (existingBeneficiario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Beneficiario updatedBeneficiario = beneficiarioService.updateBeneficiario(id, beneficiario);
        if (updatedBeneficiario != null) {
            return new ResponseEntity<>(updatedBeneficiario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeneficiario(@PathVariable("id") Long id) {
        boolean deleted = beneficiarioService.deleteBeneficiario(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
