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
import co.edu.uniandes.csw.book201620.entities.BookEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class BookDetailDTO extends BookDTO{


    @PodamExclude
    private EditorialDTO editorial;

    /**
     * @generated
     */
    public BookDetailDTO() {
        super();
    }

    /**
     * Crea un objeto BookDetailDTO a partir de un objeto BookEntity incluyendo los atributos de BookDTO.
     *
     * @param entity Entidad BookEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public BookDetailDTO(BookEntity entity) {
        super(entity);
        if (entity.getEditorial()!=null){
        this.editorial = new EditorialDTO(entity.getEditorial());
        }
        
    }

    /**
     * Convierte un objeto BookDetailDTO a BookEntity incluyendo los atributos de BookDTO.
     *
     * @return Nueva objeto BookEntity.
     * @generated
     */
    @Override
    public BookEntity toEntity() {
        BookEntity entity = super.toEntity();
        if (this.getEditorial()!=null){
        entity.setEditorial(this.getEditorial().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo editorial.
     *
     * @return atributo editorial.
     * @generated
     */
    public EditorialDTO getEditorial() {
        return editorial;
    }

    /**
     * Establece el valor del atributo editorial.
     *
     * @param editorial nuevo valor del atributo
     * @generated
     */
    public void setEditorial(EditorialDTO editorial) {
        this.editorial = editorial;
    }

}
