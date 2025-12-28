package com.frota.exception;

import com.frota.dto.ErroRespostaDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Trata erros de "Não Encontrado" (Ex: findById que falha)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErroRespostaDTO> handleNotFound(NoSuchElementException ex, HttpServletRequest request) {
        ErroRespostaDTO erro = new ErroRespostaDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "O recurso solicitado não foi encontrado no sistema.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // Trata erros de Regra de Negócio (Exceções customizadas que podemos criar)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroRespostaDTO> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        ErroRespostaDTO erro = new ErroRespostaDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // Trata qualquer outro erro inesperado (Erro 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroRespostaDTO> handleGeneral(Exception ex, HttpServletRequest request) {
        ErroRespostaDTO erro = new ErroRespostaDTO(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro interno no servidor. Tente novamente mais tarde.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}