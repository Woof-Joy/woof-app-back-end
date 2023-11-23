package org.woof.woofjoybackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.entity.Residencia;
import org.woof.woofjoybackend.repository.ResidenciaRepository;

@Service
@RequiredArgsConstructor
public class ServiceResidencia {
    private ResidenciaRepository residenciaRepository;
}
