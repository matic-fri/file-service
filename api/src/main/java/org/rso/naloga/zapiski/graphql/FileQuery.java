package org.rso.naloga.zapiski.graphql;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import com.kumuluz.ee.graphql.classes.Filter;
import com.kumuluz.ee.graphql.classes.Pagination;
import com.kumuluz.ee.graphql.classes.PaginationWrapper;
import com.kumuluz.ee.graphql.classes.Sort;
import com.kumuluz.ee.graphql.utils.GraphQLUtils;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import user.lib.File;
import user.services.beans.FileBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@GraphQLClass
@ApplicationScoped
public class FileQuery {

    @Inject
    private FileBean fileBean;

    @GraphQLQuery
    public PaginationWrapper<File> allFiles(@GraphQLArgument(name = "pagination") Pagination pagination,
                                                    @GraphQLArgument(name = "sort") Sort sort,
                                                    @GraphQLArgument(name = "filter") Filter filter) {

        return GraphQLUtils.process(fileBean.getAllFiles(), pagination, sort, filter);
    }

    @GraphQLQuery
    public File getFile(@GraphQLArgument(name = "id") long id) {
        return fileBean.getFile(id);
    }

}
