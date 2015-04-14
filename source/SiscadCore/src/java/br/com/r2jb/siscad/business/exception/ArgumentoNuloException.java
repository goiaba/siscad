/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at google.com>
 */
public class ArgumentoNuloException extends RuntimeException {
    
    private String argumentName;
    private Class argumentType;

    public ArgumentoNuloException(String argumentName, Class argumentType) {

        super("O argumento \"" + argumentName + "\" do tipo \"" + argumentType.getName() + "\" passado para o método não pode ser nulo.");

        this.argumentName = argumentName;
        this.argumentType = argumentType;

    }

    public ArgumentoNuloException(Class argumentType) {

        super("Argumento do tipo \"" + argumentType.getName() + "\" passado para o método não pode ser nulo.");

        this.argumentType = argumentType;

    }

    public ArgumentoNuloException() {

        super("Argumento passado para o método não pode ser nulo.");

    }

    public Class getArgumentType() {
        return argumentType;
    }

    public String getArgumentName() {
        return argumentName;
    }
    
}
