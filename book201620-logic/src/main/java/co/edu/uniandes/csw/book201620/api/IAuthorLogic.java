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
package co.edu.uniandes.csw.book201620.api;

import co.edu.uniandes.csw.book201620.entities.AuthorEntity;
import co.edu.uniandes.csw.book201620.entities.PrizeEntity;
import java.util.List;

public interface IAuthorLogic {
    public int countAuthors();
    public List<AuthorEntity> getAuthors();
    public List<AuthorEntity> getAuthors(Integer page, Integer maxRecords);
    public AuthorEntity getAuthor(Long id);
    public AuthorEntity createAuthor(AuthorEntity entity); 
    public AuthorEntity updateAuthor(AuthorEntity entity);
    public void deleteAuthor(Long id);
    public List<PrizeEntity> listPrize(Long authorId);
    public PrizeEntity getPrize(Long authorId, Long prizeId);
    public PrizeEntity addPrize(Long authorId, Long prizeId);
    public List<PrizeEntity> replacePrize(Long authorId, List<PrizeEntity> list);
    public void removePrize(Long authorId, Long prizeId);
}
