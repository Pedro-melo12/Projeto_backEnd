package com.projetobackEnd.dto;

import com.projetobackEnd.model.UserRole;

public record RegisterDTO(String username, String password, UserRole role){
}
