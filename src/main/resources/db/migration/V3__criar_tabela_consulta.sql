create table consulta (
    id int auto_increment primary key,
    data_consulta timestamp not null,
    diagnostico VARCHAR(300) not null,
    prescricao VARCHAR(300) not null,
    paciente_id int not null,
    medico_id int not null,
    
    FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    FOREIGN KEY (medico_id) REFERENCES medico(id)
);