package com.example.service;

import com.example.model.Beneficiario;
import com.example.repository.BeneficiarioRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BeneficiarioService {
    private final BeneficiarioRepository beneficiarioRepository;

    @Autowired
    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
    }

    public List<Beneficiario> getAllBeneficiarios() {      
        return beneficiarioRepository.findAll();
    }

    public Beneficiario getBeneficiarioById(Long id) {
        Optional<Beneficiario> optionalBeneficiario = beneficiarioRepository.findById(id);
        return optionalBeneficiario.orElse(null);
    }

    public Beneficiario createBeneficiario(Beneficiario beneficiario) {      
        if (beneficiario.getDataInclusao() == null) {
            beneficiario.setDataInclusao(LocalDate.now());
        }
        return beneficiarioRepository.save(beneficiario);
    }

    public Beneficiario updateBeneficiario(Long id, Beneficiario beneficiario) {
        
        if (beneficiario.getDataAtualizacao() == null) {
            beneficiario.setDataAtualizacao(LocalDate.now());
        }

        if (beneficiarioRepository.existsById(id)) {
            beneficiario.setId(id);
            return beneficiarioRepository.save(beneficiario);
        } else {
            return null;
        }
    }

    public boolean deleteBeneficiario(Long id) {
        if (beneficiarioRepository.existsById(id)) {
            beneficiarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
