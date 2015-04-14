/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class PessoaJaExisteException  extends Exception {
    
    private String cpf;
    private String tipoPessoa;

    public PessoaJaExisteException(String cpf, String tipoPessoa) {
        
        super();
        
        this.cpf = cpf;
        this.tipoPessoa = tipoPessoa;
        
    }
    
    public PessoaJaExisteException(String cpf, String tipoPessoa, String message) {
        
        super(message);
        
        this.cpf = cpf;
        this.tipoPessoa = tipoPessoa;
        
    }

    public PessoaJaExisteException(String cpf, String tipoPessoa, String message, Throwable t) {

        super(message, t);
        
        this.cpf = cpf;
        this.tipoPessoa = tipoPessoa;

    }

    public String getCpf() {
        return cpf;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }
    
}
