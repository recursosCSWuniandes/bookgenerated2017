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
import co.edu.uniandes.csw.book201620.api.IAuthorLogic;
import co.edu.uniandes.csw.book201620.dtos.detail.PrizeDetailDTO;
import co.edu.uniandes.csw.book201620.entities.PrizeEntity;
import java.util.ArrayList;
/**
 * URI: authors/{authorsId: \\d+}/prize
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthorPrizeResource {

    @Inject private IAuthorLogic authorLogic;
    @Context private HttpServletResponse response;

    /**
     * Convierte una lista de PrizeEntity a una lista de PrizeDetailDTO.
     *
     * @param entityList Lista de PrizeEntity a convertir.
     * @return Lista de PrizeDetailDTO convertida.
     * @generated
     */
    private List<PrizeDetailDTO> prizeListEntity2DTO(List<PrizeEntity> entityList){
        List<PrizeDetailDTO> list = new ArrayList<>();
        for (PrizeEntity entity : entityList) {
            list.add(new PrizeDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de PrizeDetailDTO a una lista de PrizeEntity.
     *
     * @param dtos Lista de PrizeDetailDTO a convertir.
     * @return Lista de PrizeEntity convertida.
     * @generated
     */
    private List<PrizeEntity> prizeListDTO2Entity(List<PrizeDetailDTO> dtos){
        List<PrizeEntity> list = new ArrayList<>();
        for (PrizeDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Obtiene una colección de instancias de PrizeDetailDTO asociadas a una
     * instancia de Author
     *
     * @param authorsId Identificador de la instancia de Author
     * @return Colección de instancias de PrizeDetailDTO asociadas a la instancia de Author
     * @generated
     */
    @GET
    public List<PrizeDetailDTO> listPrize(@PathParam("authorsId") Long authorsId) {
        return prizeListEntity2DTO(authorLogic.listPrize(authorsId));
    }

    /**
     * Obtiene una instancia de Prize asociada a una instancia de Author
     *
     * @param authorsId Identificador de la instancia de Author
     * @param prizeId Identificador de la instancia de Prize
     * @generated
     */
    @GET
    @Path("{prizeId: \\d+}")
    public PrizeDetailDTO getPrize(@PathParam("authorsId") Long authorsId, @PathParam("prizeId") Long prizeId) {
        return new PrizeDetailDTO(authorLogic.getPrize(authorsId, prizeId));
    }

    /**
     * Asocia un Prize existente a un Author
     *
     * @param authorsId Identificador de la instancia de Author
     * @param prizeId Identificador de la instancia de Prize
     * @return Instancia de PrizeDetailDTO que fue asociada a Author
     * @generated
     */
    @POST
    @Path("{prizeId: \\d+}")
    public PrizeDetailDTO addPrize(@PathParam("authorsId") Long authorsId, @PathParam("prizeId") Long prizeId) {
        return new PrizeDetailDTO(authorLogic.addPrize(authorsId, prizeId));
    }

    /**
     * Remplaza las instancias de Prize asociadas a una instancia de Author
     *
     * @param authorsId Identificador de la instancia de Author
     * @param prizes Colección de instancias de PrizeDTO a asociar a instancia de Author
     * @return Nueva colección de PrizeDTO asociada a la instancia de Author
     * @generated
     */
    @PUT
    public List<PrizeDetailDTO> replacePrize(@PathParam("authorsId") Long authorsId, List<PrizeDetailDTO> prizes) {
        return prizeListEntity2DTO(authorLogic.replacePrize(authorsId, prizeListDTO2Entity(prizes)));
    }

    /**
     * Desasocia un Prize existente de un Author existente
     *
     * @param authorsId Identificador de la instancia de Author
     * @param prizeId Identificador de la instancia de Prize
     * @generated
     */
    @DELETE
    @Path("{prizeId: \\d+}")
    public void removePrize(@PathParam("authorsId") Long authorsId, @PathParam("prizeId") Long prizeId) {
        authorLogic.removePrize(authorsId, prizeId);
    }
}
