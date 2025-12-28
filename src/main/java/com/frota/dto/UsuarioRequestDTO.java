package com.frota.dto;

import com.frota.model.enums.Perfil;

public record UsuarioRequestDTO(String nome, String email, String senha, Perfil perfil) {}