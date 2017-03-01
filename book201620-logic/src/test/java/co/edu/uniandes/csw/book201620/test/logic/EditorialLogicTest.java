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
package co.edu.uniandes.csw.book201620.test.logic;

import co.edu.uniandes.csw.book201620.ejbs.EditorialLogic;
import co.edu.uniandes.csw.book201620.api.IEditorialLogic;
import co.edu.uniandes.csw.book201620.entities.EditorialEntity;
import co.edu.uniandes.csw.book201620.persistence.EditorialPersistence;
import co.edu.uniandes.csw.book201620.entities.BookEntity;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class EditorialLogicTest {

    /**
     * @generated
     */

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IEditorialLogic editorialLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    private UserTransaction utx;

    /**
     * @generated
     */
    private List<EditorialEntity> data = new ArrayList<EditorialEntity>();
    /**
     * @generated
     */
    private List<BookEntity> editedbooksData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EditorialEntity.class.getPackage())
                .addPackage(EditorialLogic.class.getPackage())
                .addPackage(IEditorialLogic.class.getPackage())
                .addPackage(EditorialPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from BookEntity").executeUpdate();
        em.createQuery("delete from EditorialEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
            for (int i = 0; i < 3; i++) {
                BookEntity editedbooks = factory.manufacturePojo(BookEntity.class);
                em.persist(editedbooks);
                editedbooksData.add(editedbooks);
            }
        for (int i = 0; i < 3; i++) {
            EditorialEntity entity = factory.manufacturePojo(EditorialEntity.class);

            em.persist(entity);
            data.add(entity);

            if (i == 0) {
                editedbooksData.get(i).setEditorial(entity);
            }
        }
    }
    /**
     * Prueba para crear un Editorial
     *
     * @generated
     */
    @Test
    public void createEditorialTest() {
        EditorialEntity newEntity = factory.manufacturePojo(EditorialEntity.class);
        EditorialEntity result = editorialLogic.createEditorial(newEntity);
        Assert.assertNotNull(result);
        EditorialEntity entity = em.find(EditorialEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Editorials
     *
     * @generated
     */
    @Test
    public void getEditorialsTest() {
        List<EditorialEntity> list = editorialLogic.getEditorials();
        Assert.assertEquals(data.size(), list.size());
        for (EditorialEntity entity : list) {
            boolean found = false;
            for (EditorialEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Editorial
     *
     * @generated
     */
    @Test
    public void getEditorialTest() {
        EditorialEntity entity = data.get(0);
        EditorialEntity resultEntity = editorialLogic.getEditorial(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * Prueba para eliminar un Editorial
     *
     * @generated
     */
    @Test
    public void deleteEditorialTest() {
        EditorialEntity entity = data.get(0);
        editorialLogic.removeEditedbooks(entity.getId(), editedbooksData.get(0).getId());
        editorialLogic.deleteEditorial(entity.getId());
        EditorialEntity deleted = em.find(EditorialEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Editorial
     *
     * @generated
     */
    @Test
    public void updateEditorialTest() {
        EditorialEntity entity = data.get(0);
        EditorialEntity pojoEntity = factory.manufacturePojo(EditorialEntity.class);

        pojoEntity.setId(entity.getId());

        editorialLogic.updateEditorial(pojoEntity);

        EditorialEntity resp = em.find(EditorialEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }

    /**
     * Prueba para obtener una instancia de Editedbooks asociada a una instancia Editorial
     *
     * @generated
     */
    @Test
    public void getEditedbooksTest() {
        EditorialEntity entity = data.get(0);
        BookEntity bookEntity = editedbooksData.get(0);
        BookEntity response = editorialLogic.getEditedbooks(entity.getId(), bookEntity.getId());

        Assert.assertEquals(bookEntity.getId(), response.getId());
        Assert.assertEquals(bookEntity.getName(), response.getName());
        Assert.assertEquals(bookEntity.getIsbn(), response.getIsbn());
        Assert.assertEquals(bookEntity.getDescription(), response.getDescription());
        Assert.assertEquals(bookEntity.getImage(), response.getImage());
        Assert.assertEquals(bookEntity.getPublishingdate(), response.getPublishingdate());
    }

    /**
     * Prueba para obtener una colección de instancias de Editedbooks asociadas a una instancia Editorial
     *
     * @generated
     */
    @Test
    public void listEditedbooksTest() {
        List<BookEntity> list = editorialLogic.listEditedbooks(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     *Prueba para asociar un Editedbooks existente a un Editorial
     *
     * @generated
     */
    @Test
    public void addEditedbooksTest() {
        EditorialEntity entity = data.get(0);
        BookEntity bookEntity = editedbooksData.get(1);
        BookEntity response = editorialLogic.addEditedbooks(entity.getId(), bookEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(bookEntity.getId(), response.getId());
    }

    /**
     * Prueba para remplazar las instancias de Editedbooks asociadas a una instancia de Editorial
     *
     * @generated
     */
    @Test
    public void replaceEditedbooksTest() {
        EditorialEntity entity = data.get(0);
        List<BookEntity> list = editedbooksData.subList(1, 3);
        editorialLogic.replaceEditedbooks(entity.getId(), list);

        entity = editorialLogic.getEditorial(entity.getId());
        Assert.assertFalse(entity.getEditedbooks().contains(editedbooksData.get(0)));
        Assert.assertTrue(entity.getEditedbooks().contains(editedbooksData.get(1)));
        Assert.assertTrue(entity.getEditedbooks().contains(editedbooksData.get(2)));
    }

    /**
     * Prueba para desasociar un Editedbooks existente de un Editorial existente
     *
     * @generated
     */
    @Test
    public void removeEditedbooksTest() {
        editorialLogic.removeEditedbooks(data.get(0).getId(), editedbooksData.get(0).getId());
        BookEntity response = editorialLogic.getEditedbooks(data.get(0).getId(), editedbooksData.get(0).getId());
        Assert.assertNull(response);
    }
}

