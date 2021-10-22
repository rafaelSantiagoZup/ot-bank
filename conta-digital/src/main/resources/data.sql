INSERT INTO public.account (id, account_number, agency, balance) VALUES('13c4cd8d-8c14-419c-91bc-65242a2e74f3', 1234, '2918-1', 3000);
INSERT INTO public.account (id, account_number, agency, balance) VALUES('15b5c802-86d4-4db9-b0d3-b999e01995b5', 2345, '2918-1', 100);
INSERT INTO public.account (id, account_number, agency, balance) VALUES('e9c5100f-a795-48db-b839-95ef856e0fb0', 3234, '2918-1', 543);
INSERT INTO public.account (id, account_number, agency, balance) VALUES('b91b4e82-06eb-4c89-9125-7a377ce5bdfa', 5432, '2918-1', 567);


INSERT INTO public.client (customer_id, email, "name", account_id) VALUES('a78c3155-c174-41da-9916-8b4385c24348', 'yuri.matheus@zup.com.br', 'Yuri Matheus', '13c4cd8d-8c14-419c-91bc-65242a2e74f3');
INSERT INTO public.client (customer_id, email, "name", account_id) VALUES('5fd1489b-5a64-43c4-b6bb-67b39b58b02c', 'joao.silva@zup.com.br', 'João Silva', '15b5c802-86d4-4db9-b0d3-b999e01995b5');
INSERT INTO public.client (customer_id, email, "name", account_id) VALUES('768a4198-a35f-4d44-b8ae-10ce9c541e4c', 'mauro.matheus@zup.com.br', 'Mauro Matheus', 'e9c5100f-a795-48db-b839-95ef856e0fb0');
INSERT INTO public.client (customer_id, email, "name", account_id) VALUES('b2639816-f9ef-416f-a4ad-7419b7dde843', 'steve.jobs@zup.com.br', 'Steve Jobs', 'b91b4e82-06eb-4c89-9125-7a377ce5bdfa');

