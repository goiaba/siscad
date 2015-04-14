/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.entity.Turno;
import br.com.r2jb.siscad.business.facade.turno.TurnoFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=Turno.class)
@FacesValidator(value="turnoValidator")
public class TurnoConverterValidator extends BaseConverterValidator<Turno, TurnoFacade> {

}
