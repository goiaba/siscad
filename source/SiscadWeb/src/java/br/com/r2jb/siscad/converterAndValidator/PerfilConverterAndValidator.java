/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.util.entity.security.Perfil;
import br.com.r2jb.siscad.util.facade.security.perfil.PerfilFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=Perfil.class)
@FacesValidator(value = "perfilValidator")
public class PerfilConverterAndValidator extends BaseConverterValidator<Perfil, PerfilFacade> {

}
