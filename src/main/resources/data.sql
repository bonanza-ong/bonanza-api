--Categorias Principais
insert into CATEGORIAS_ITENS(id, nome)
values (
        'e5bcd686-efe2-4963-b61c-8d02d6e2cb06',
        'Vestuário'
    );
--Categorias Vestuário/
insert into CATEGORIAS_ITENS(id, pai_id, nome)
values (
        '3a9f96e6-694f-4af5-b3ae-c0d732b962a8',
        'e5bcd686-efe2-4963-b61c-8d02d6e2cb06',
        'Masculino'
    );
insert into CATEGORIAS_ITENS(id, pai_id, nome)
values (
        '7bb0383b-cbe7-4609-953a-d9ada0435b93',
        'e5bcd686-efe2-4963-b61c-8d02d6e2cb06',
        'Feminino'
    );
--Categorias Vestuário/Feminino/
insert into CATEGORIAS_ITENS(id, pai_id, nome)
values (
        'b8b716c0-981e-4453-b5cc-6d0de533ea1b',
        '7bb0383b-cbe7-4609-953a-d9ada0435b93',
        'Inverno'
    );
--Item Vestuário/Feminino/Inverno
insert into ITENS(ID, NOME, CATEGORIA_PRINCIPAL_ID)
values (
        'cc7b660f-2eb1-4c42-80c8-55ab98a61ac4',
        'Calça',
        'b8b716c0-981e-4453-b5cc-6d0de533ea1b'
    );
--Categorias Item Vestuário/Feminino/Inverno/*Calça
insert into ITENS_CATEGORIAS(ITEM_ID, CATEGORIA_ID)
values(
        'cc7b660f-2eb1-4c42-80c8-55ab98a61ac4',
        'b8b716c0-981e-4453-b5cc-6d0de533ea1b'
    );
insert into ITENS_CATEGORIAS(ITEM_ID, CATEGORIA_ID)
values(
        'cc7b660f-2eb1-4c42-80c8-55ab98a61ac4',
        '7bb0383b-cbe7-4609-953a-d9ada0435b93'
    );
insert into ITENS_CATEGORIAS(ITEM_ID, CATEGORIA_ID)
values(
        'cc7b660f-2eb1-4c42-80c8-55ab98a61ac4',
        'e5bcd686-efe2-4963-b61c-8d02d6e2cb06'
    );