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
package co.edu.uniandes.csw.book201620.resources;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.book201620.api.IEditorialLogic;
import co.edu.uniandes.csw.book201620.dtos.detail.BookDetailDTO;
import co.edu.uniandes.csw.book201620.entities.BookEntity;
import java.util.ArrayList;
/**
 * URI: editorials/{editorialsId: \\d+}/editedbooks
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EditorialEditedbooksResource {

    @Inject private IEditorialLogic editorialLogic;
    @Context private HttpServletResponse response;

    /**
     * Convierte una lista de BookEntity a una lista de BookDetailDTO.
     *
     * @param entityList Lista de BookEntity a convertir.
     * @return Lista de BookDetailDTO convertida.
     * @generated
     */
    private List<BookDetailDTO> editedbooksListEntity2DTO(List<BookEntity> entityList){
        List<BookDetailDTO> list = new ArrayList<>();
        for (BookEntity entity : entityList) {
            list.add(new BookDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de BookDetailDTO a una lista de BookEntity.
     *
     * @param dtos Lista de BookDetailDTO a convertir.
     * @return Lista de BookEntity convertida.
     * @generated
     */
    private List<BookEntity> editedbooksListDTO2Entity(List<BookDetailDTO> dtos){
        List<BookEntity> list = new ArrayList<>();
        for (BookDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Obtiene una colección de instancias de BookDetailDTO asociadas a una
     * instancia de Editorial
     *
     * @param editorialsId Identificador de la instancia de Editorial
     * @return Colección de instancias de BookDetailDTO asociadas a la instancia de Editorial
     * @generated
     */
    @GET
    public List<BookDetailDTO> listEditedbooks(@PathParam("editorialsId") Long editorialsId) {
        return editedbooksListEntity2DTO(editorialLogic.listEditedbooks(editorialsId));
    }

    /**
     * Obtiene una instancia de Book asociada a una instancia de Editorial
     *
     * @param editorialsId Identificador de la instancia de Editorial
     * @param editedbooksId Identificador de la instancia de Book
     * @generated
     */
    @GET
    @Path("{editedbooksId: \\d+}")
    public BookDetailDTO getEditedbooks(@PathParam("editorialsId") Long editorialsId, @PathParam("editedbooksId") Long editedbooksId) {
        return new BookDetailDTO(editorialLogic.getEditedbooks(editorialsId, editedbooksId));
    }

    /**
     * Asocia un Book existente a un Editorial
     *
     * @param editorialsId Identificador de la instancia de Editorial
     * @param editedbooksId Identificador de la instancia de Book
     * @return Instancia de BookDetailDTO que fue asociada a Editorial
     * @generated
     */
    @POST
    @Path("{editedbooksId: \\d+}")
    public BookDetailDTO addEditedbooks(@PathParam("editorialsId") Long editorialsId, @PathParam("editedbooksId") Long editedbooksId) {
        return new BookDetailDTO(editorialLogic.addEditedbooks(editorialsId, editedbooksId));
    }

    /**
     * Remplaza las instancias de Book asociadas a una instancia de Editorial
     *
     * @param editorialsId Identificador de la instancia de Editorial
     * @param books Colección de instancias de BookDTO a asociar a instancia de Editorial
     * @return Nueva colección de BookDTO asociada a la instancia de Editorial
     * @generated
     */
    @PUT
    public List<BookDetailDTO> replaceEditedbooks(@PathParam("editorialsId") Long editorialsId, List<BookDetailDTO> books) {
        return editedbooksListEntity2DTO(editorialLogic.replaceEditedbooks(editorialsId, editedbooksListDTO2Entity(books)));
    }

    /**
     * Desasocia un Book existente de un Editorial existente
     *
     * @param editorialsId Identificador de la instancia de Editorial
     * @param editedbooksId Identificador de la instancia de Book
     * @generated
     */
    @DELETE
    @Path("{editedbooksId: \\d+}")
    public void removeEditedbooks(@PathParam("editorialsId") Long editorialsId, @PathParam("editedbooksId") Long editedbooksId) {
        editorialLogic.removeEditedbooks(editorialsId, editedbooksId);
    }
}
