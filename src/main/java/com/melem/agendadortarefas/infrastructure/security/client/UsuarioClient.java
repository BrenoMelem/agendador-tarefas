package com.melem.agendadortarefas.infrastructure.security.client;
import com.melem.agendadortarefas.business.dtos.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
//Apontar o nome da API que vai utilizar, e a URL que está rodando o microserviço.-"localhost:8080" não seria a forma mais correta para apontar a url(usuario.url)
//nome padrão para o pacote de integração com o openFeing(Comunicação Sincrona) de microserviços.
// Apontar para API que vai trazer os dados do usuario(findByEmail)
//
@FeignClient(name= "usuario", url = "${usuario.url")

public interface UsuarioClient {
    @GetMapping("/usuario")
    UsuarioDto buscaUsuarioPorEmail(@RequestParam ("email") String email,
                                    @RequestHeader ("Authorization") String token);

}
