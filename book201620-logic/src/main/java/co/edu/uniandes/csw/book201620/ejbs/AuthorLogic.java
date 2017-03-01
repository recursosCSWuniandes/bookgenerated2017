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
package co.edu.uniandes.csw.book201620.ejbs;

import co.edu.uniandes.csw.book201620.api.IAuthorLogic;
import co.edu.uniandes.csw.book201620.entities.AuthorEntity;
import co.edu.uniandes.csw.book201620.persistence.AuthorPersistence;
import co.edu.uniandes.csw.book201620.entities.PrizeEntity;
import co.edu.uniandes.csw.book201620.api.IPrizeLogic;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class AuthorLogic implements IAuthorLogic {

    @Inject private AuthorPersistence persistence;


    @Inject private IPrizeLogic prizeLogic;

    /**
     * Obtiene el número de registros de Author.
     *
     * @return Número de registros de Author.
     * @generated
     */
    public int countAuthors() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Author.
     *
     * @return Colección de objetos de AuthorEntity.
     * @generated
     */
    @Override
    public List<AuthorEntity> getAuthors() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de Author indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @return Colección de objetos de AuthorEntity.
     * @generated
     */
    @Override
    public List<AuthorEntity> getAuthors(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    /**
     * Obtiene los datos de una instancia de Author a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de AuthorEntity con los datos del Author consultado.
     * @generated
     */
    public AuthorEntity getAuthor(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Author en la base de datos.
     *
     * @param entity Objeto de AuthorEntity con los datos nuevos
     * @return Objeto de AuthorEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public AuthorEntity createAuthor(AuthorEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Author.
     *
     * @param entity Instancia de AuthorEntity con los nuevos datos.
     * @return Instancia de AuthorEntity con los datos actualizados.
     * @generated
     */
    @Override
    public AuthorEntity updateAuthor(AuthorEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Author de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteAuthor(Long id) {
        persistence.delete(id);
    }
  

    /**
     * Obtiene una colección de instancias de PrizeEntity asociadas a una
     * instancia de Author
     *
     * @param authorId Identificador de la instancia de Author
     * @return Colección de instancias de PrizeEntity asociadas a la instancia de Author
     * @generated
     */
    @Override
    public List<PrizeEntity> listPrize(Long authorId) {
        return getAuthor(authorId).getPrize();
    }

    /**
     * Obtiene una instancia de PrizeEntity asociada a una instancia de Author
     *
     * @param authorId Identificador de la instancia de Author
     * @param prizeId Identificador de la instancia de Prize
     * @generated
     */
    @Override
    public PrizeEntity getPrize(Long authorId, Long prizeId) {
        List<PrizeEntity> list = getAuthor(authorId).getPrize();
        PrizeEntity prizeEntity = new PrizeEntity();
        prizeEntity.setId(prizeId);
        int index = list.indexOf(prizeEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Prize existente a un Author
     *
     * @param authorId Identificador de la instancia de Author
     * @param prizeId Identificador de la instancia de Prize
     * @return Instancia de PrizeEntity que fue asociada a Author
     * @generated
     */
    @Override
    public PrizeEntity addPrize(Long authorId, Long prizeId) {
        AuthorEntity authorEntity = getAuthor(authorId);
        PrizeEntity prizeEntity = prizeLogic.getPrize(prizeId);
        prizeEntity.setAuthor(authorEntity);
        return prizeEntity;
    }

    /**
     * Remplaza las instancias de Prize asociadas a una instancia de Author
     *
     * @param authorId Identificador de la instancia de Author
     * @param list Colección de instancias de PrizeEntity a asociar a instancia de Author
     * @return Nueva colección de PrizeEntity asociada a la instancia de Author
     * @generated
     */
    @Override
    public List<PrizeEntity> replacePrize(Long authorId, List<PrizeEntity> list) {
        AuthorEntity authorEntity = getAuthor(authorId);
        List<PrizeEntity> prizeList = prizeLogic.getPrizes();
        for (PrizeEntity prize : prizeList) {
            if (list.contains(prize)) {
                prize.setAuthor(authorEntity);
            } else {
                if (prize.getAuthor() != null && prize.getAuthor().equals(authorEntity)) {
                    prize.setAuthor(null);
                }
            }
        }
        authorEntity.setPrize(list);
        return authorEntity.getPrize();
    }

    /**
     * Desasocia un Prize existente de un Author existente
     *
     * @param authorId Identificador de la instancia de Author
     * @param prizeId Identificador de la instancia de Prize
     * @generated
     */
    @Override
    public void removePrize(Long authorId, Long prizeId) {
        PrizeEntity entity = prizeLogic.getPrize(prizeId);
        entity.setAuthor(null);
    }
}
