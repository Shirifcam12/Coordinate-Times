package com.charlie.proyecto.web;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class perfilC{
	
	public void eliminaPerfil(Usuario user){
		boolean perfil = eliminar(user);
		if(perfil == false){
			FacesContext.getCurrentInstance().addMessage(null
                                                         , new FacesMessage(FacesMessage.SEVERITY_ERROR, "El perfil a eliminar no existe", ""));
		} else{
			FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamenten el perfil", ""));
		}
	}

}