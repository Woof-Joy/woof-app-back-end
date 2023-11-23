package org.woof.woofjoybackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.repository.RelarorioRepository;

@Service
@RequiredArgsConstructor
public class ServiceRelatorio {
    private RelarorioRepository relarorioRepository;
}
