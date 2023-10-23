package org.woof.woofjoybackend.entity.response;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.woof.woofjoybackend.entity.Usuario;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
        @Id
        private String cep;
        private String    logradouro;
        private String    complemento;
        private String    bairro;
        private String    localidade;
        private String    uf;

        @OneToOne
        private Usuario usuario;


        public String getCep() {
                return cep;
        }

        public void setCep(String cep) {
                this.cep = cep;
        }

        public String getLogradouro() {
                return logradouro;
        }

        public void setLogradouro(String logradouro) {
                this.logradouro = logradouro;
        }

        public String getComplemento() {
                return complemento;
        }

        public void setComplemento(String complemento) {
                this.complemento = complemento;
        }

        public String getBairro() {
                return bairro;
        }

        public void setBairro(String bairro) {
                this.bairro = bairro;
        }

        public String getLocalidade() {
                return localidade;
        }

        public void setLocalidade(String localidade) {
                this.localidade = localidade;
        }

        public String getUf() {
                return uf;
        }

        public void setUf(String uf) {
                this.uf = uf;
        }
}
