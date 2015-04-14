/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.entity.PeriodoAvaliacao;
import br.com.r2jb.siscad.business.facade.periodoAvaliacao.PeriodoAvaliacaoFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=PeriodoAvaliacao.class)
@FacesValidator("periodoAvaliacaoValidator")
public class PeriodoAvaliacaoConverterValidator extends BaseConverterValidator<PeriodoAvaliacao, PeriodoAvaliacaoFacade>{

}
