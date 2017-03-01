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
package co.edu.uniandes.csw.book201620.dtos.minimum;

import co.edu.uniandes.csw.book201620.entities.BookEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import uk.co.jemos.podam.common.PodamStrategyValue;
import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;

/**
 * @generated
 */
@XmlRootElement
public class BookDTO  implements Serializable{

    private Long id;
    private String name;
    private String isbn;
    private String description;
    private String image;
    @PodamStrategyValue(DateStrategy.class)
    private Date publishingdate;


    /**
     * @generated
     */
    public BookDTO() {
    }

    /**
     * Crea un objeto BookDTO a partir de un objeto BookEntity.
     *
     * @param entity Entidad BookEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public BookDTO(BookEntity entity) {
	   if (entity!=null){
        this.id=entity.getId();
        this.name=entity.getName();
        this.isbn=entity.getIsbn();
        this.description=entity.getDescription();
        this.image=entity.getImage();
        this.publishingdate=entity.getPublishingdate();
       }
    }

    /**
     * Convierte un objeto BookDTO a BookEntity.
     *
     * @return Nueva objeto BookEntity.
     * @generated
     */
    public BookEntity toEntity() {
        BookEntity entity = new BookEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setIsbn(this.getIsbn());
        entity.setDescription(this.getDescription());
        entity.setImage(this.getImage());
        entity.setPublishingdate(this.getPublishingdate());
    return entity;
    }

    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el atributo name.
     *
     * @return atributo name.
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el valor del atributo name.
     *
     * @param name nuevo valor del atributo
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el atributo isbn.
     *
     * @return atributo isbn.
     * @generated
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Establece el valor del atributo isbn.
     *
     * @param isbn nuevo valor del atributo
     * @generated
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene el atributo description.
     *
     * @return atributo description.
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece el valor del atributo description.
     *
     * @param description nuevo valor del atributo
     * @generated
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene el atributo image.
     *
     * @return atributo image.
     * @generated
     */
    public String getImage() {
        return image;
    }

    /**
     * Establece el valor del atributo image.
     *
     * @param image nuevo valor del atributo
     * @generated
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Obtiene el atributo publishingdate.
     *
     * @return atributo publishingdate.
     * @generated
     */
    public Date getPublishingdate() {
        return publishingdate;
    }

    /**
     * Establece el valor del atributo publishingdate.
     *
     * @param publishingdate nuevo valor del atributo
     * @generated
     */
    public void setPublishingdate(Date publishingdate) {
        this.publishingdate = publishingdate;
    }


}
