
package org.woof.woofjoybackend.dto;

import lombok.Data;


@Data
public class ParceiroPUTDTO {
    private Boolean aceitaDogEspecial;
    private Boolean aceitaDogIdoso;
    private Boolean aceitaDogBravo;
    private Boolean aceitaDogGrande;
    private Boolean aceitaDogCio;
    private Boolean temAreaExterna;
    private String descricao = "";
}