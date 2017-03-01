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

import co.edu.uniandes.csw.auth.provider.StatusCreated;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.book201620.api.IPrizeLogic;
import co.edu.uniandes.csw.book201620.dtos.detail.PrizeDetailDTO;
import co.edu.uniandes.csw.book201620.entities.PrizeEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: prizes/
 * @generated
 */
@Path("/prizes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PrizeResource {

    @Inject private IPrizeLogic prizeLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;

   
    /**
     * Convierte una lista de PrizeEntity a una lista de PrizeDetailDTO.
     *
     * @param entityList Lista de PrizeEntity a convertir.
     * @return Lista de PrizeDetailDTO convertida.
     * @generated
     */
    private List<PrizeDetailDTO> listEntity2DTO(List<PrizeEntity> entityList){
        List<PrizeDetailDTO> list = new ArrayList<>();
        for (PrizeEntity entity : entityList) {
            list.add(new PrizeDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Prize
     *
     * @return Colección de objetos de PrizeDetailDTO
     * @generated
     */
    @GET
    public List<PrizeDetailDTO> getPrizes() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", prizeLogic.countPrizes());
            return listEntity2DTO(prizeLogic.getPrizes(page, maxRecords));
        }
        return listEntity2DTO(prizeLogic.getPrizes());
    }

    /**
     * Obtiene los datos de una instancia de Prize a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de PrizeDetailDTO con los datos del Prize consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public PrizeDetailDTO getPrize(@PathParam("id") Long id) {
        return new PrizeDetailDTO(prizeLogic.getPrize(id));
    }

    /**
     * Se encarga de crear un Prize en la base de datos
     *
     * @param dto Objeto de PrizeDetailDTO con los datos nuevos
     * @return Objeto de PrizeDetailDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public PrizeDetailDTO createPrize(PrizeDetailDTO dto) {
        return new PrizeDetailDTO(prizeLogic.createPrize(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Prize
     *
     * @param id Identificador de la instancia de Prize a modificar
     * @param dto Instancia de PrizeDetailDTO con los nuevos datos
     * @return Instancia de PrizeDetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public PrizeDetailDTO updatePrize(@PathParam("id") Long id, PrizeDetailDTO dto) {
        PrizeEntity entity = dto.toEntity();
        entity.setId(id);
        return new PrizeDetailDTO(prizeLogic.updatePrize(entity));
    }

    /**
     * Elimina una instancia de Prize de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePrize(@PathParam("id") Long id) {
        prizeLogic.deletePrize(id);
    }
    
}
