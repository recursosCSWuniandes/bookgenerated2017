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
import co.edu.uniandes.csw.book201620.api.IReviewLogic;
import co.edu.uniandes.csw.book201620.dtos.detail.ReviewDetailDTO;
import co.edu.uniandes.csw.book201620.entities.ReviewEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: books/{booksId: \\d+}/reviews
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReviewsResource {

    @Inject private IReviewLogic reviewLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @PathParam("booksId") private Long booksId;

   
    /**
     * Convierte una lista de ReviewEntity a una lista de ReviewDetailDTO
     *
     * @param entityList Lista de ReviewEntity a convertir
     * @return Lista de ReviewDetailDTO convertida
     * @generated
     */
    private List<ReviewDetailDTO> listEntity2DTO(List<ReviewEntity> entityList){
        List<ReviewDetailDTO> list = new ArrayList<>();
        for (ReviewEntity entity : entityList) {
            list.add(new ReviewDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Review asociados a un Book
     *
     * @return Colección de objetos de ReviewDetailDTO
     * @generated
     */
    @GET
    public List<ReviewDetailDTO> getReviewss() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", reviewLogic.countReviews());
            return listEntity2DTO(reviewLogic.getReviews(page, maxRecords, booksId));
        }
        return listEntity2DTO(reviewLogic.getReviews(booksId));
    }

    /**
     * Obtiene los datos de una instancia de Review a partir de su ID asociado a un Book
     *
     * @param reviewsId Identificador de la instancia a consultar
     * @return Instancia de ReviewDetailDTO con los datos del Review consultado
     * @generated
     */
    @GET
    @Path("{reviewsId: \\d+}")
    public ReviewDetailDTO getReviews(@PathParam("reviewsId") Long reviewsId) {
        ReviewEntity entity = reviewLogic.getReview(reviewsId);
        if (entity.getBookReviews() != null && !booksId.equals(entity.getBookReviews().getId())) {
            throw new WebApplicationException(404);
        }
        return new ReviewDetailDTO(entity);
    }

    /**
     * Asocia un Review existente a un Book
     *
     * @param dto Objeto de ReviewDetailDTO con los datos nuevos
     * @return Objeto de ReviewDetailDTOcon los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public ReviewDetailDTO createReviews(ReviewDetailDTO dto) {
        return new ReviewDetailDTO(reviewLogic.createReview(booksId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Review.
     *
     * @param reviewsId Identificador de la instancia de Review a modificar
     * @param dto Instancia de ReviewDetailDTO con los nuevos datos.
     * @return Instancia de ReviewDetailDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{reviewsId: \\d+}")
    public ReviewDetailDTO updateReviews(@PathParam("reviewsId") Long reviewsId, ReviewDetailDTO dto) {
        ReviewEntity entity = dto.toEntity();
        entity.setId(reviewsId);
        return new ReviewDetailDTO(reviewLogic.updateReview(booksId, entity));
    }

    /**
     * Elimina una instancia de Review de la base de datos.
     *
     * @param reviewId Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("reviewsId: \\d+}")
    public void deleteReviews(@PathParam("reviewsId") Long reviewsId) {
        reviewLogic.deleteReview(reviewsId);
    }
    
}
