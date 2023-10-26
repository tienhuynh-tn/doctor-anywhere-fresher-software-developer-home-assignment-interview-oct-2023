package com.tienhuynhtn.util;

import com.tienhuynhtn.basemodels.BasePaginationRequest;
import com.tienhuynhtn.constant.SortConstant;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaginationUtil {

    private static Sort.Direction getSortDirection(String direction) {
        if (direction.equals(SortConstant.ASC)) {
            return Sort.Direction.ASC;
        } else if (direction.equals(SortConstant.DESC)) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    public static List<Sort.Order> getSortOrders(List<String> sort) {
        List<Sort.Order> orders = new ArrayList<>();

        if (sort.get(0).contains(",")) {
            // will sort more than 2 columns
            for (String sortOrder : sort) {
                // sortOrder="column, direction"
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(
                        getSortDirection(_sort[1]),
                        _sort[0].replaceFirst(_sort[0].substring(0, 1), _sort[0].substring(0, 1).toUpperCase())));
            }
        } else {
            // sort=[column, direction]
            orders.add(new Sort.Order(
                    getSortDirection(sort.get(1)),
                    sort.get(0).replaceFirst(sort.get(0).substring(0, 1), sort.get(0).substring(0, 1).toUpperCase())));
        }

        return orders;
    }

    public static Pageable getPageable(BasePaginationRequest paginationRequest, Sort.Order defaultSortOrder) {
        Integer pageIndex = 1;
        Integer pageSize = 10;
        List<Sort.Order> sortOrders = Collections.singletonList(defaultSortOrder);

        if (paginationRequest.getPageIndex() != null){
            pageIndex = paginationRequest.getPageIndex();
        }
        if (paginationRequest.getPageSize() != null){
            pageSize = paginationRequest.getPageSize();
        }
        if (paginationRequest.getSort() != null) {
            sortOrders = getSortOrders(paginationRequest.getSort());
        }

        return PageRequest.of(pageIndex - 1, pageSize, Sort.by(sortOrders));
    }

    public static Pageable getPageable(BasePaginationRequest paginationRequest, List<Sort.Order> sortOrders) {
        Integer pageIndex = 1;
        Integer pageSize = 10;

        if (paginationRequest.getPageIndex() != null){
            pageIndex = paginationRequest.getPageIndex();
        }
        if (paginationRequest.getPageSize() != null){
            pageSize = paginationRequest.getPageSize();
        }
        if (paginationRequest.getSort() != null) {
            sortOrders = getSortOrders(paginationRequest.getSort());
        }

        return PageRequest.of(pageIndex - 1, pageSize, Sort.by(sortOrders));
    }
}
