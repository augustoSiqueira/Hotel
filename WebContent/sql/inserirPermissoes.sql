/* inserir permissões */
LOCK TABLES `bup`.`permissaoMenuUsuario` WRITE;
INSERT INTO `bup`.`permissaoMenuUsuario`(`idMenu`,`idUsuario`,`descricao`,`nome`, `idOrdem`) VALUES 
(1,1, 'Cadastro Base','Cadastro Base', 1),
(2,1, 'Medidor','Medidor',2),
(3,1, 'Tanque','Tanque',3),
(4,1, 'Sensor','Sensor',4),
(5,1, 'Antena','Antena',5),
(13,1, 'Usuario','Usuario',13);

UNLOCK TABLES;
