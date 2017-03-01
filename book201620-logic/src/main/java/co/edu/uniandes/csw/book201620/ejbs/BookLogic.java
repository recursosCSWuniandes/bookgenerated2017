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

import co.edu.uniandes.csw.book201620.api.IBookLogic;
import co.edu.uniandes.csw.book201620.entities.BookEntity;
import co.edu.uniandes.csw.book201620.persistence.BookPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class BookLogic implements IBookLogic {

    @Inject private BookPersistence persistence;


    /**
     * Obtiene el número de registros de Book.
     *
     * @return Número de registros de Book.
     * @generated
     */
    public int countBooks() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de BookEntity.
     * @generated
     */
    @Override
    public List<BookEntity> getBooks() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de Book indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @return Colección de objetos de BookEntity.
     * @generated
     */
    @Override
    public List<BookEntity> getBooks(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de BookEntity con los datos del Book consultado.
     * @generated
     */
    public BookEntity getBook(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Book en la base de datos.
     *
     * @param entity Objeto de BookEntity con los datos nuevos
     * @return Objeto de BookEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public BookEntity createBook(BookEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param entity Instancia de BookEntity con los nuevos datos.
     * @return Instancia de BookEntity con los datos actualizados.
     * @generated
     */
    @Override
    public BookEntity updateBook(BookEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteBook(Long id) {
        persistence.delete(id);
    }
  
}
