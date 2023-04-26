import { LoanPage } from "./LoanPage";

export const LOANS: LoanPage = {
    content: [
        {
            id: 1, game: {id: 1, title: "Juego 1", age: 12, category:{id: 1, name: 'Categoria 1'}, author:{id:1, name:'Autor 1', nationality: 'España'} }, client: {id: 1, name: "Cliente 1"}, dateLoan: new Date("2023-01-01"), dateReturn: new Date("2023-01-02")
        },
        {
            id: 2, game: {id: 2, title: "Juego 2", age: 12, category:{id: 2, name: 'Categoria 2'}, author:{id:2, name:'Autor 2', nationality: 'España'} }, client: {id: 2, name: "Cliente 2"}, dateLoan: new Date("2023-02-01"), dateReturn: new Date("2023-02-02")
        },
        {
            id: 3, game: {id: 3, title: "Juego 3", age: 12, category:{id: 3, name: 'Categoria 3'}, author:{id:3, name:'Autor 3', nationality: 'España'} }, client: {id: 3, name: "Cliente 3"}, dateLoan: new Date("2023-03-01"), dateReturn: new Date("2023-03-02")
        },
        {
            id: 4, game: {id: 4, title: "Juego 4", age: 12, category:{id: 2, name: 'Categoria 2'}, author:{id:4, name:'Autor 4', nationality: 'España'} }, client: {id: 1, name: "Cliente 1"}, dateLoan: new Date("2023-04-01"), dateReturn: new Date("2023-04-02")
        }
    ],
    pageable: {
        pageSize: 10,
        pageNumber: 0,
        sort: [
            { property: "id", direction: "ASC" }
        ]
    },
    totalElements: 7
}
