--Categorias Principais
insert into CATEGORIAS_ITENS(id, pai_id, nome)
values (
        'e5bcd686-efe2-4963-b61c-8d02d6e2cb06',
        null,
        'Vestuário'
    );
--Categorias Vestuário/
insert into CATEGORIAS_ITENS(id, pai_id, nome)
values (
        '3a9f96e6-694f-4af5-b3ae-c0d732b962a8',
        'e5bcd686-efe2-4963-b61c-8d02d6e2cb06',
        'Agasalho'
    );
insert into CATEGORIAS_ITENS(id, pai_id, nome)
values (
        '7bb0383b-cbe7-4609-953a-d9ada0435b93',
        'e5bcd686-efe2-4963-b61c-8d02d6e2cb06',
        'Calça'
    );