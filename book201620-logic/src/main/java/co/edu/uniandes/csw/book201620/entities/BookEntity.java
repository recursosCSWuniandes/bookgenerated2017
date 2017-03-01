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
package co.edu.uniandes.csw.book201620.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;
import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;


/**
 * @generated
 */
@Entity
public class BookEntity extends BaseEntity implements Serializable {

    private String isbn;

    private String description;

    private String image;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date publishingdate;

    @PodamExclude
    @ManyToOne
    private EditorialEntity editorial;

    @PodamExclude
    @OneToMany(mappedBy = "bookReviews", cascade = CascadeType.REMOVE)
    private List<ReviewEntity> reviews = new ArrayList<>();

    /**
     * Obtiene el atributo isbn.
     *
     * @return atributo isbn.
     * @generated
     */
    public String getIsbn(){
        return isbn;
    }

    /**
     * Establece el valor del atributo isbn.
     *
     * @param isbn nuevo valor del atributo
     * @generated
     */
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    /**
     * Obtiene el atributo description.
     *
     * @return atributo description.
     * @generated
     */
    public String getDescription(){
        return description;
    }

    /**
     * Establece el valor del atributo description.
     *
     * @param description nuevo valor del atributo
     * @generated
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Obtiene el atributo image.
     *
     * @return atributo image.
     * @generated
     */
    public String getImage(){
        return image;
    }

    /**
     * Establece el valor del atributo image.
     *
     * @param image nuevo valor del atributo
     * @generated
     */
    public void setImage(String image){
        this.image = image;
    }

    /**
     * Obtiene el atributo publishingdate.
     *
     * @return atributo publishingdate.
     * @generated
     */
    public Date getPublishingdate(){
        return publishingdate;
    }

    /**
     * Establece el valor del atributo publishingdate.
     *
     * @param publishingdate nuevo valor del atributo
     * @generated
     */
    public void setPublishingdate(Date publishingdate){
        this.publishingdate = publishingdate;
    }

    /**
     * Obtiene el atributo editorial.
     *
     * @return atributo editorial.
     * @generated
     */
    public EditorialEntity getEditorial() {
        return editorial;
    }

    /**
     * Establece el valor del atributo editorial.
     *
     * @param editorial nuevo valor del atributo
     * @generated
     */
    public void setEditorial(EditorialEntity editorial) {
        this.editorial = editorial;
    }

    /**
     * Obtiene la colección de reviews.
     *
     * @return colección reviews.
     * @generated
     */
    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    /**
     * Establece el valor de la colección de reviews.
     *
     * @param reviews nuevo valor de la colección.
     * @generated
     */
    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }
}
