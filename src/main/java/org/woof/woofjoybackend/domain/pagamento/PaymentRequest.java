package org.woof.woofjoybackend.domain.pagamento;

import lombok.Data;

@Data
public class PaymentRequest {
    private String amount;
    private String description;
}