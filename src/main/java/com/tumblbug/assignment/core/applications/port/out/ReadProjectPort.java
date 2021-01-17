package com.tumblbug.assignment.core.applications.port.out;

import com.tumblbug.assignment.core.domains.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public interface ReadProjectPort {

    Optional<Project> findOneById(UUID uuid);

    Page<Project> findAll(ProjectQueryParams projectQueryParams);

    interface ProjectQueryParams {
        int getPageNumber();
        int getPageSize();

        SortBy getSortBy();
        SortDirection getSortDirection();

        @Getter
        @AllArgsConstructor
        enum SortBy {
            BEGIN_DATE("beginDate"),
            DUE_DATE("dueDate"),
            TARGET_AMOUNT("targetAmount"),
            SPONSORED_AMOUNT("sponsoredAmount");

            String value;
        }

        @AllArgsConstructor
        enum SortDirection {
            ASC(sort -> sort.ascending()),
            DESC(sort -> sort.descending());

            Consumer<Sort> sortConsumer;

            public void sort(Sort sort) {
                this.sortConsumer.accept(sort);
            }
        }
    }
}
