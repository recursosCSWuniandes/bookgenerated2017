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
package co.edu.uniandes.csw.book201620.tests.selenium;

import co.edu.uniandes.csw.book201620.dtos.minimum.PrizeDTO;
import co.edu.uniandes.csw.book201620.resources.PrizeResource;
import co.edu.uniandes.csw.book201620.tests.selenium.pages.prize.PrizeCreatePage;
import co.edu.uniandes.csw.book201620.tests.selenium.pages.prize.PrizeListPage;
import co.edu.uniandes.csw.book201620.tests.selenium.pages.LoginPage;
import co.edu.uniandes.csw.book201620.tests.selenium.pages.prize.PrizeDeletePage;
import co.edu.uniandes.csw.book201620.tests.selenium.pages.prize.PrizeDetailPage;
import co.edu.uniandes.csw.book201620.tests.selenium.pages.prize.PrizeEditPage;
import java.io.File;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(Arquillian.class)
public class PrizeIT {

    private static PodamFactory factory = new PodamFactoryImpl();

    @ArquillianResource
    private URL deploymentURL;

    @Drone
    private WebDriver browser;

    @Page
    private PrizeCreatePage createPage;

    @Page
    private PrizeDetailPage detailPage;

    @Page
    private PrizeEditPage editPage;

    @Page
    private PrizeDeletePage deletePage;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega las dependencias
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(PrizeResource.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo shiro.ini es necesario para injeccion de dependencias
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
                .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                        .importDirectory("src/main/webapp").as(GenericArchive.class), "/");
    }

    @Before
    public void setup() {
        browser.manage().window().maximize();
        browser.get(deploymentURL.toExternalForm());
    }

    @Test
    @InSequence(0)
    public void login(@InitialPage LoginPage loginPage) {
        browser.manage().deleteAllCookies();
        loginPage.login();
    }

    @Test
    @InSequence(1)
    public void createPrize(@InitialPage PrizeListPage listPage) {
        Integer expected = 0;
        Assert.assertEquals(expected, listPage.countPrizes());

        listPage.create();

        PrizeDTO expected_prize = factory.manufacturePojo(PrizeDTO.class);
        createPage.savePrize(expected_prize);

        PrizeDTO actual_prize = detailPage.getData();

        Assert.assertEquals(expected_prize.getName(), actual_prize.getName());
    }

    @Test
    @InSequence(2)
    public void editPrize(@InitialPage PrizeListPage listPage) {
        PrizeDTO expected_prize = factory.manufacturePojo(PrizeDTO.class);

        listPage.editPrize(0);

        editPage.savePrize(expected_prize);

        PrizeDTO actual_prize = detailPage.getData();

        Assert.assertEquals(expected_prize.getName(), actual_prize.getName());
    }

    @Test
    @InSequence(3)
    public void deletePrize(@InitialPage PrizeListPage listPage) {
        listPage.deletePrize(0);
        deletePage.confirm();
        Integer expected = 0;
        Assert.assertEquals(expected, listPage.countPrizes());
    }

}
