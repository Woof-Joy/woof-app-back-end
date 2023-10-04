package org.woof.woofjoybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.entity.Parceiro;
import org.woof.woofjoybackend.entity.Usuario;
import org.woof.woofjoybackend.repository.ClienteRepository;
import org.woof.woofjoybackend.repository.ParceiroRepository;
import org.woof.woofjoybackend.entity.object.Item;
import org.woof.woofjoybackend.repository.ItemRepository;
import org.woof.woofjoybackend.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class ServiceUser {
    private UsuarioRepository usuarioRepository;
    private ClienteRepository clienteRepository;
    private ParceiroRepository parceiroRepository;
    private ItemRepository itemRepository;

    @Autowired
    public ServiceUser(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository, ParceiroRepository parceiroRepository, ItemRepository itemRepository) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.parceiroRepository = parceiroRepository;
        this.itemRepository = itemRepository;
    }

    public void postUsuario(Usuario usuario, int tipo) {
        if (usuarioExiste(usuario.getEmail())) {
            usuarioRepository.save(usuario);
        }
        String email = usuario.getEmail();
        List<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(email);
        Usuario usuarioFk = usuarioEncontrado.get(0);

        if (tipo == 0) {
            Cliente cliente = new Cliente(usuarioFk);
            usuario.setCliente(cliente);
            clienteRepository.save(cliente);
            return;
        }
        Parceiro parceiro = new Parceiro(usuarioFk);
        usuario.setParceiro(parceiro);
        parceiroRepository.save(parceiro);
    }

    public boolean putUsuario(Usuario usuario, int id) {
        Optional<Usuario> usuarioOriginal = usuarioRepository.findById(id);
        if (usuarioOriginal.isPresent()) {
            usuario.setId(id);
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    public boolean deleteUsuario(Integer id) {
        if (existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Usuario> listaUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario listaUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id).get();
    }


    public boolean existsById(Integer id) {
        return usuarioRepository.existsById(id);
    }

    public boolean usuarioPodeSerCadastrado(Usuario usuario, int tipo) {
        String email = usuario.getEmail();
        List<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(email);
        //VERIFICA SE O USUARIO NO BANCO TEM UM CLIENTE OU PARCEIRO CADASTRADO
        if (usuarioEncontrado.size() > 0) {
            Cliente cliente = usuarioEncontrado.get(0).getCliente();
            Parceiro parceiro = usuarioEncontrado.get(0).getParceiro();
            if (tipo == 0 && isNull(cliente)) {
                return true;
            }
            if (tipo == 1 && isNull(parceiro)) {
                return true;
            }
        }
        if (usuarioEncontrado.size() <= 0 || usuarioEncontrado.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean usuarioExiste(String email) {
        List<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(email);
        if (usuarioEncontrado.size() <= 0 || usuarioEncontrado.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean cadastrado(Usuario usuario, int tipo) {
        if (tipo == 0 && isNull(usuario.getCliente()) || tipo == 1 && isNull(usuario.getParceiro())) {
            return false;
        }
        return true;
    }

    public Item cadastrarItem(Item it, Integer idUsuario) {
        Usuario usuario = listaUsuarioPorId(idUsuario);
        it.setDono(usuario);
        return itemRepository.save(it);
    }

    public Item listaItemPorId(Integer id) {
        return itemRepository.findById(id).get();
    }

    public Item attItem(Item it, Integer idItem) {
        it.setId(idItem);
        return itemRepository.save(it);
    }

    public void deleteItem(Integer idItem) {
        itemRepository.deleteById(idItem);
    }

//    public ResponseEntity<String> loginUsuario(String email, String senha) {
//        if (!verificarEmailSenha(email, senha)) {
//            return ResponseEntity.status(400).build();
//        }
//        for (int i = 0; i < usuarioList.size(); i++) {
//            String emailCadastrado = usuarioList.get(i).getEmail();
//            String senhaCadastrado = usuarioList.get(i).getSenha();
//            if (email.equals(emailCadastrado)) {
//                if (senha.equals(senhaCadastrado)) {
//                    setIndexUsuarioLogado(i);
//                    return ResponseEntity.status(200).body("Login Realizado com sucesso\nBem vindo!");
//                }
//            }
//        }
//        return ResponseEntity.status(401).build();
//    }

//    public int getIndexUsuarioLogado() {
//        return indexUsuarioLogado;
//    }
//
//    public void setIndexUsuarioLogado(int indexUsuarioLogado) {
//        this.indexUsuarioLogado = indexUsuarioLogado;
//    }
}