package com.alkemy.ong.infrastructure.common;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class PaginatedResultsRetrieved {

  public static String createLinkHeader(final String uri, final String rel) {
    return "<" + uri + ">; rel=\"" + rel + "\"";
  }

  public void addLinkHeaderOnPagedResourceRetrieval(
      final UriComponentsBuilder uriBuilder, final HttpServletResponse response,
      String path, final int page, final int totalPages, final int pageSize) {

    uriBuilder.path(path);

    final StringBuilder linkHeader = new StringBuilder();
    if (hasFirstPage(page)) {
      final String uriForFirstPage = constructFirstPageUri(uriBuilder, pageSize);
      appendCommaIfNecessary(linkHeader);
      linkHeader.append(createLinkHeader(uriForFirstPage, "first"));
    }
    if (hasLastPage(page, totalPages)) {
      final String uriForLastPage = constructLastPageUri(uriBuilder, totalPages, pageSize);
      appendCommaIfNecessary(linkHeader);
      linkHeader.append(createLinkHeader(uriForLastPage, "last"));
    }
    if (hasNextPage(page, totalPages)) {
      final String uriForNextPage = constructNextPageUri(uriBuilder, page, pageSize);
      linkHeader.append(createLinkHeader(uriForNextPage, "next"));
    }
    if (hasPreviousPage(page)) {
      final String uriForPrevPage = constructPrevPageUri(uriBuilder, page, pageSize);
      appendCommaIfNecessary(linkHeader);
      linkHeader.append(createLinkHeader(uriForPrevPage, "prev"));
    }
    response.addHeader("Link", linkHeader.toString());
  }

  boolean hasNextPage(final int page, final int totalPages) {
    return page < totalPages - 1;
  }

  boolean hasPreviousPage(final int page) {
    return page > 0;
  }

  boolean hasFirstPage(final int page) {
    return hasPreviousPage(page);
  }

  boolean hasLastPage(final int page, final int totalPages) {
    return totalPages > 1 && hasNextPage(page, totalPages);
  }

  String constructNextPageUri(final UriComponentsBuilder uriBuilder, final int page,
      final int size) {
    return uriBuilder.replaceQueryParam("page", page + 1)
        .replaceQueryParam("size", size).build().encode().toUriString();
  }

  String constructPrevPageUri(final UriComponentsBuilder uriBuilder, final int page,
      final int size) {
    return uriBuilder.replaceQueryParam("page", page - 1)
        .replaceQueryParam("size", size).build().encode().toUriString();
  }

  String constructFirstPageUri(final UriComponentsBuilder uriBuilder, final int size) {
    return uriBuilder.replaceQueryParam("page", 0)
        .replaceQueryParam("size", size).build().encode().toUriString();
  }

  String constructLastPageUri(final UriComponentsBuilder uriBuilder,
      final int totalPages, final int size) {
    return uriBuilder.replaceQueryParam("page", totalPages -1)
        .replaceQueryParam("size", size).build().encode().toUriString();
  }

  void appendCommaIfNecessary(final StringBuilder linkHeader) {
    if (linkHeader.length() > 0) {
      linkHeader.append(", ");
    }
  }
}
