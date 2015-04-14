/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.converterAndValidator;

import br.com.r2jb.siscad.business.entity.Serie;
import br.com.r2jb.siscad.business.facade.serie.SerieFacade;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@FacesConverter(forClass=Serie.class)
@FacesValidator(value="serieValidator")
public class SerieConverterValidator extends BaseConverterValidator<Serie, SerieFacade> {

}