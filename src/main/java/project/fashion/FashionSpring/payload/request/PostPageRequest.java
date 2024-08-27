package project.fashion.FashionSpring.payload.request;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class PostPageRequest implements Pageable {
    Integer limit;
    Integer offset;
    private final Sort sort;

    public PostPageRequest(Integer limit, Integer offset, Sort sort) {
        this.limit = limit;
        this.offset = offset;
        this.sort = sort; // truyền các sắp xếp riêng
    }

    public PostPageRequest(Integer limit, Integer offset) {
        this(limit, offset, Sort.unsorted());
    }

    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        // trang tiếp theo
        return new PostPageRequest(getPageSize(), (int) (getOffset() + getPageSize()));
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    public Pageable previous() {
        return hasPrevious() ? new PostPageRequest(getPageSize(), (int) (getOffset() - getPageSize())) : this;
    }

    @Override
    public Pageable first() {
        return new PostPageRequest(getPageSize(), 0);
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new PostPageRequest(getPageSize(), pageNumber * getPageSize());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }
}
