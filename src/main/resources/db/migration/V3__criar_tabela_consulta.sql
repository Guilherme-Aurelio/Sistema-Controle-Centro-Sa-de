create table consulta (
    id int auto_increment primary key,
    data_consulta timestamp not null,
    diagnostico VARCHAR(300) not null,
    prescricao VARCHAR(300) not null,
    paciente_id int,
    medico_id int
);