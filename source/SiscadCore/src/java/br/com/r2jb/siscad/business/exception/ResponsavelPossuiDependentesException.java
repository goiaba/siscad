/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.exception;

import br.com.r2jb.siscad.business.entity.Aluno;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class ResponsavelPossuiDependentesException extends Exception {

    private List<Aluno> alunos;

    public ResponsavelPossuiDependentesException() {

        super();

    }

    public ResponsavelPossuiDependentesException(List<Aluno> alunos) {
    
        super();
        this.alunos = alunos;
        
    }
    
    public ResponsavelPossuiDependentesException(String message) {

        super(message);

    }

    public ResponsavelPossuiDependentesException(String message, Throwable t) {

        super(message, t);

    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
    
}
