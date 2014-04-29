/* inserir menus */
LOCK TABLES `bup`.`menu` WRITE;
INSERT INTO `bup`.`menu`(`idMenu`,`descricao`,`nome`, `idOrdem`,`camada`) VALUES 
(1, 'Cadastro Base','Cadastro Base', 1,0);



/* sub menus */
LOCK TABLES `bup`.`menu` WRITE;
INSERT INTO `bup`.`menu`(`idMenu`,`descricao`,`descricaoUrl`,`nome`,`url`,`menuPai`, `idOrdem`,`camada`) VALUES 
(2, 'Medidor','#{principalBean.showTelaMedidor}','Medidor','Tela medidor',1,2,1),
(3, 'Tanque','#{principalBean.showTelaTanque}','Tanque','Tela Tanque',1,3,1),
(4, 'Sensor','#{principalBean.showTelaSensor}','Sensor','Tela Sensor',1,4,1),
(5, 'Antena','#{principalBean.showTelaAntena}','Antena','Tela Antena',1,5,1),
(13, 'Usuario','#{principalBean.showTelaUsuario}','Usuario','Tela Usuario',1,13,1);

UNLOCK TABLES;