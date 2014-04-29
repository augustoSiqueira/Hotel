LOCK TABLES `bup`.`TanquePosicao` WRITE;
INSERT INTO `bup`.`TanquePosicao`(`idTanquePosicao`, `posicao`) VALUES
(1, 'HORIZONTAL'),
(2, 'VERTICAL');

LOCK TABLES `bup`.`ErbModelo` WRITE;
INSERT INTO `bup`.`ErbModelo`(`idErbModelo`, `Nome`) VALUES
(1, 'ERB-915-IR-1W-EGSD');

LOCK TABLES `bup`.`Sigla` WRITE;
INSERT INTO `bup`.`Sigla`(`idSigla`, `Sigla`) VALUES
(1, 'O Botão'),
(2,'Rio Mar');

UNLOCK TABLES;