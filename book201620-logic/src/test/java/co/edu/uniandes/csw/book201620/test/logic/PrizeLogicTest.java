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

import co.edu.uniandes.csw.book201620.ejbs.PrizeLogic;
import co.edu.uniandes.csw.book201620.api.IPrizeLogic;
import co.edu.uniandes.csw.book201620.entities.PrizeEntity;
import co.edu.uniandes.csw.book201620.persistence.PrizePersistence;
import co.edu.uniandes.csw.book201620.entities.AuthorEntity;
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
public class PrizeLogicTest {

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
    private IPrizeLogic prizeLogic;

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
    private List<PrizeEntity> data = new ArrayList<PrizeEntity>();
    /**
     * @generated
     */
    private List<AuthorEntity> authorData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PrizeEntity.class.getPackage())
                .addPackage(PrizeLogic.class.getPackage())
                .addPackage(IPrizeLogic.class.getPackage())
                .addPackage(PrizePersistence.class.getPackage())
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
                AuthorEntity author = factory.manufacturePojo(AuthorEntity.class);
                em.persist(author);
                authorData.add(author);
            }
        for (int i = 0; i < 3; i++) {
            PrizeEntity entity = factory.manufacturePojo(PrizeEntity.class);
                entity.setAuthor(authorData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Prize
     *
     * @generated
     */
    @Test
    public void createPrizeTest() {
        PrizeEntity newEntity = factory.manufacturePojo(PrizeEntity.class);
        PrizeEntity result = prizeLogic.createPrize(newEntity);
        Assert.assertNotNull(result);
        PrizeEntity entity = em.find(PrizeEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDatePrize(), entity.getDatePrize());
    }

    /**
     * Prueba para consultar la lista de Prizes
     *
     * @generated
     */
    @Test
    public void getPrizesTest() {
        List<PrizeEntity> list = prizeLogic.getPrizes();
        Assert.assertEquals(data.size(), list.size());
        for (PrizeEntity entity : list) {
            boolean found = false;
            for (PrizeEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Prize
     *
     * @generated
     */
    @Test
    public void getPrizeTest() {
        PrizeEntity entity = data.get(0);
        PrizeEntity resultEntity = prizeLogic.getPrize(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getDatePrize(), resultEntity.getDatePrize());
    }

    /**
     * Prueba para eliminar un Prize
     *
     * @generated
     */
    @Test
    public void deletePrizeTest() {
        PrizeEntity entity = data.get(0);
        prizeLogic.deletePrize(entity.getId());
        PrizeEntity deleted = em.find(PrizeEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Prize
     *
     * @generated
     */
    @Test
    public void updatePrizeTest() {
        PrizeEntity entity = data.get(0);
        PrizeEntity pojoEntity = factory.manufacturePojo(PrizeEntity.class);

        pojoEntity.setId(entity.getId());

        prizeLogic.updatePrize(pojoEntity);

        PrizeEntity resp = em.find(PrizeEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getDatePrize(), resp.getDatePrize());
    }
}

