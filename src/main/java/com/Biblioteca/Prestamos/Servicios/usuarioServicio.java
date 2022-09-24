package com.Biblioteca.Prestamos.Servicios;


import com.Biblioteca.Prestamos.Entidades.Usuario;
import com.Biblioteca.Prestamos.Respositorio.usuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class usuarioServicio {

    private usuarioRepositorio repositorio;

    public usuarioServicio(usuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Usuario agregarUsuario(Usuario usuario){

        return repositorio.save(usuario);
    }

    public Usuario buscarEmail(String email){
        return repositorio.findByEmail(email);
    }

    public Usuario existeUsuario(Map<String,Object> datos){

        Usuario user=buscarEmail((String) datos.get("email"));
        if (user == null){
            String name=(String) datos.get("nickname");
            String imagen=(String) datos.get("picture");
            String authid=(String) datos.get("sub");
            String correo=(String) datos.get("email");
            Usuario usuario= new  Usuario(correo,name,imagen,authid);
            user=agregarUsuario(usuario);
            return  user;
        }else {
            return user;
        }
    }
}
