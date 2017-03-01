/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.book201620.dtos.detail;

import co.edu.uniandes.csw.book201620.dtos.minimum.*;
import co.edu.uniandes.csw.book201620.entities.PrizeEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class PrizeDetailDTO extends PrizeDTO{


    @PodamExclude
    private AuthorDTO author;

    /**
     * @generated
     */
    public PrizeDetailDTO() {
        super();
    }

    /**
     * Crea un objeto PrizeDetailDTO a partir de un objeto PrizeEntity incluyendo los atributos de PrizeDTO.
     *
     * @param entity Entidad PrizeEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public PrizeDetailDTO(PrizeEntity entity) {
        super(entity);
        if (entity.getAuthor()!=null){
        this.author = new AuthorDTO(entity.getAuthor());
        }
        
    }

    /**
     * Convierte un objeto PrizeDetailDTO a PrizeEntity incluyendo los atributos de PrizeDTO.
     *
     * @return Nueva objeto PrizeEntity.
     * @generated
     */
    @Override
    public PrizeEntity toEntity() {
        PrizeEntity entity = super.toEntity();
        if (this.getAuthor()!=null){
        entity.setAuthor(this.getAuthor().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo author.
     *
     * @return atributo author.
     * @generated
     */
    public AuthorDTO getAuthor() {
        return author;
    }

    /**
     * Establece el valor del atributo author.
     *
     * @param author nuevo valor del atributo
     * @generated
     */
    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

}
