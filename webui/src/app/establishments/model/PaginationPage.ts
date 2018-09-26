export class PaginationPropertySort {
    direction: string;
    property: string;
}

export class PaginationPage<T> {
    content?: Array<T>;
    last?: boolean;
    first?: boolean;
    totalPages?: number;
    itemsPerPage?: number;
    sort?: Array<PaginationPropertySort>;
}
