package br.com.marvel.loja.cliente;

import org.hibernate.validator.constraints.br.CPF;

import br.com.marvel.loja.valida.Unico;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class ClienteMapper {
 @NotEmpty
 @NotNull
 @Size(min = 3)
 private String nome;

 @NotEmpty
 @NotNull
 @Size(min = 3, max = 50)
 @Email
 @Unico(atributo = "email", classe = Cliente.class)
 private String email;

 @CPF
 @NotEmpty
 @NotNull
 @Size(min = 3, max = 50)
 @Unico(atributo = "cpf", classe = Cliente.class)
 private String cpf;

 @NotNull
 @Valid
 private LocalDate dataNascimento;

 public Cliente map() {
 return new Cliente(nome, email, cpf, dataNascimento);
 }

 public String getNome() {
 return nome;
 }

 public String getEmail() {
 return email;
 }

 public String getCpf() {
 return cpf;
 }

 public LocalDate getDataNascimento() {
 return dataNascimento;
 }
}