/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class DisciplinaNaoEncontradaException extends Exception {

    public DisciplinaNaoEncontradaException() {

        super();

    }

    public DisciplinaNaoEncontradaException(String message) {

        super(message);

    }

    public DisciplinaNaoEncontradaException(String message, Throwable t) {

        super(message, t);

    }
}
