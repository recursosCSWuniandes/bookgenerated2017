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

import co.edu.uniandes.csw.book201620.ejbs.AuthorLogic;
import co.edu.uniandes.csw.book201620.api.IAuthorLogic;
import co.edu.uniandes.csw.book201620.entities.AuthorEntity;
import co.edu.uniandes.csw.book201620.persistence.AuthorPersistence;
import co.edu.uniandes.csw.book201620.entities.PrizeEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
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
public class AuthorLogicTest {

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
    private IAuthorLogic authorLogic;

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
    private List<AuthorEntity> data = new ArrayList<AuthorEntity>();
    /**
     * @generated
     */
    private List<PrizeEntity> prizeData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AuthorEntity.class.getPackage())
                .addPackage(AuthorLogic.class.getPackage())
                .addPackage(IAuthorLogic.class.getPackage())
                .addPackage(AuthorPersistence.class.getPackage())
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
        em.createQuery("delete from PrizeEntity").executeUpdate();
        em.createQuery("delete from AuthorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
            for (int i = 0; i < 3; i++) {
                PrizeEntity prize = factory.manufacturePojo(PrizeEntity.class);
                em.persist(prize);
                prizeData.add(prize);
            }
        for (int i = 0; i < 3; i++) {
            AuthorEntity entity = factory.manufacturePojo(AuthorEntity.class);

            em.persist(entity);
            data.add(entity);

            if (i == 0) {
                prizeData.get(i).setAuthor(entity);
            }
        }
    }
    /**
     * Prueba para crear un Author
     *
     * @generated
     */
    @Test
    public void createAuthorTest() {
        AuthorEntity newEntity = factory.manufacturePojo(AuthorEntity.class);
        AuthorEntity result = authorLogic.createAuthor(newEntity);
        Assert.assertNotNull(result);
        AuthorEntity entity = em.find(AuthorEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getBirthDate(), entity.getBirthDate());
    }

    /**
     * Prueba para consultar la lista de Authors
     *
     * @generated
     */
    @Test
    public void getAuthorsTest() {
        List<AuthorEntity> list = authorLogic.getAuthors();
        Assert.assertEquals(data.size(), list.size());
        for (AuthorEntity entity : list) {
            boolean found = false;
            for (AuthorEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Author
     *
     * @generated
     */
    @Test
    public void getAuthorTest() {
        AuthorEntity entity = data.get(0);
        AuthorEntity resultEntity = authorLogic.getAuthor(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getBirthDate(), resultEntity.getBirthDate());
    }

    /**
     * Prueba para eliminar un Author
     *
     * @generated
     */
    @Test
    public void deleteAuthorTest() {
        AuthorEntity entity = data.get(0);
        authorLogic.removePrize(entity.getId(), prizeData.get(0).getId());
        authorLogic.deleteAuthor(entity.getId());
        AuthorEntity deleted = em.find(AuthorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Author
     *
     * @generated
     */
    @Test
    public void updateAuthorTest() {
        AuthorEntity entity = data.get(0);
        AuthorEntity pojoEntity = factory.manufacturePojo(AuthorEntity.class);

        pojoEntity.setId(entity.getId());

        authorLogic.updateAuthor(pojoEntity);

        AuthorEntity resp = em.find(AuthorEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getBirthDate(), resp.getBirthDate());
    }

    /**
     * Prueba para obtener una instancia de Prize asociada a una instancia Author
     *
     * @generated
     */
    @Test
    public void getPrizeTest() {
        AuthorEntity entity = data.get(0);
        PrizeEntity prizeEntity = prizeData.get(0);
        PrizeEntity response = authorLogic.getPrize(entity.getId(), prizeEntity.getId());

        Assert.assertEquals(prizeEntity.getId(), response.getId());
        Assert.assertEquals(prizeEntity.getName(), response.getName());
        Assert.assertEquals(prizeEntity.getDatePrize(), response.getDatePrize());
    }

    /**
     * Prueba para obtener una colección de instancias de Prize asociadas a una instancia Author
     *
     * @generated
     */
    @Test
    public void listPrizeTest() {
        List<PrizeEntity> list = authorLogic.listPrize(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     *Prueba para asociar un Prize existente a un Author
     *
     * @generated
     */
    @Test
    public void addPrizeTest() {
        AuthorEntity entity = data.get(0);
        PrizeEntity prizeEntity = prizeData.get(1);
        PrizeEntity response = authorLogic.addPrize(entity.getId(), prizeEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(prizeEntity.getId(), response.getId());
    }

    /**
     * Prueba para remplazar las instancias de Prize asociadas a una instancia de Author
     *
     * @generated
     */
    @Test
    public void replacePrizeTest() {
        AuthorEntity entity = data.get(0);
        List<PrizeEntity> list = prizeData.subList(1, 3);
        authorLogic.replacePrize(entity.getId(), list);

        entity = authorLogic.getAuthor(entity.getId());
        Assert.assertFalse(entity.getPrize().contains(prizeData.get(0)));
        Assert.assertTrue(entity.getPrize().contains(prizeData.get(1)));
        Assert.assertTrue(entity.getPrize().contains(prizeData.get(2)));
    }

    /**
     * Prueba para desasociar un Prize existente de un Author existente
     *
     * @generated
     */
    @Test
    public void removePrizeTest() {
        authorLogic.removePrize(data.get(0).getId(), prizeData.get(0).getId());
        PrizeEntity response = authorLogic.getPrize(data.get(0).getId(), prizeData.get(0).getId());
        Assert.assertNull(response);
    }
}

