/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class ProfessorNaoEncontradoException extends Exception {

    public ProfessorNaoEncontradoException() {

        super();

    }

    public ProfessorNaoEncontradoException(String message) {

        super(message);

    }

    public ProfessorNaoEncontradoException(String message, Throwable t) {

        super(message, t);

    }

}
