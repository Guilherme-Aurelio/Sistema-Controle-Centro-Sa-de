create table paciente (
    id int auto_increment primary key,
    nome VARCHAR(200) not null,
    cpf VARCHAR(100) not null,
    historico VARCHAR(300) not null
);