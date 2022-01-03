package graphql;

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
public class FilesQuery {

    @Inject
    private FileBean fileBean;

    @GraphQLQuery
    public PaginationWrapper<File> allImageMetadata(@GraphQLArgument(name = "pagination") Pagination pagination,
                                                    @GraphQLArgument(name = "sort") Sort sort,
                                                    @GraphQLArgument(name = "filter") Filter filter) {

        return GraphQLUtils.process(fileBean.getAllFiles(), pagination, sort, filter);
    }

}
