ALTER TABLE consulta ADD CONSTRAINT fk_paciente FOREIGN KEY (paciente_id) REFERENCES paciente(id);
ALTER TABLE consulta ADD CONSTRAINT fk_medico FOREIGN KEY (medico_id) REFERENCES medico(id);