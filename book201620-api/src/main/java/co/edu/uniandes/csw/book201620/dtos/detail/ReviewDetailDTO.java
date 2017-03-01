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
import co.edu.uniandes.csw.book201620.entities.ReviewEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class ReviewDetailDTO extends ReviewDTO{


    @PodamExclude
    private BookDTO bookReviews;

    /**
     * @generated
     */
    public ReviewDetailDTO() {
        super();
    }

    /**
     * Crea un objeto ReviewDetailDTO a partir de un objeto ReviewEntity incluyendo los atributos de ReviewDTO.
     *
     * @param entity Entidad ReviewEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ReviewDetailDTO(ReviewEntity entity) {
        super(entity);
        if (entity.getBookReviews()!=null){
        this.bookReviews = new BookDTO(entity.getBookReviews());
        }
        
    }

    /**
     * Convierte un objeto ReviewDetailDTO a ReviewEntity incluyendo los atributos de ReviewDTO.
     *
     * @return Nueva objeto ReviewEntity.
     * @generated
     */
    @Override
    public ReviewEntity toEntity() {
        ReviewEntity entity = super.toEntity();
        if (this.getBookReviews()!=null){
        entity.setBookReviews(this.getBookReviews().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo bookReviews.
     *
     * @return atributo bookReviews.
     * @generated
     */
    public BookDTO getBookReviews() {
        return bookReviews;
    }

    /**
     * Establece el valor del atributo bookReviews.
     *
     * @param bookReviews nuevo valor del atributo
     * @generated
     */
    public void setBookReviews(BookDTO bookreviews) {
        this.bookReviews = bookreviews;
    }

}
