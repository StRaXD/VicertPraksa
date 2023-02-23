CREATE TABLE IF NOT EXISTS `user` (
                                      `id_user` INT NOT NULL AUTO_INCREMENT,
                                    `username` VARCHAR(45) ,
    `password` VARCHAR(45) ,
    `active` TINYINT ,
    `roles` VARCHAR(80) ,
    PRIMARY KEY (`id_user`));