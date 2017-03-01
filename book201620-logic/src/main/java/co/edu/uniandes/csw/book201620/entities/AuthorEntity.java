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
import java.util.List;
import java.util.ArrayList;


/**
 * @generated
 */
@Entity
public class AuthorEntity extends BaseEntity implements Serializable {

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date birthDate;

    @PodamExclude
    @OneToMany(mappedBy = "author")
    private List<PrizeEntity> prize = new ArrayList<>();

    /**
     * Obtiene el atributo birthDate.
     *
     * @return atributo birthDate.
     * @generated
     */
    public Date getBirthDate(){
        return birthDate;
    }

    /**
     * Establece el valor del atributo birthDate.
     *
     * @param birthDate nuevo valor del atributo
     * @generated
     */
    public void setBirthDate(Date birthDate){
        this.birthDate = birthDate;
    }

    /**
     * Obtiene la colección de prize.
     *
     * @return colección prize.
     * @generated
     */
    public List<PrizeEntity> getPrize() {
        return prize;
    }

    /**
     * Establece el valor de la colección de prize.
     *
     * @param prize nuevo valor de la colección.
     * @generated
     */
    public void setPrize(List<PrizeEntity> prize) {
        this.prize = prize;
    }
}
