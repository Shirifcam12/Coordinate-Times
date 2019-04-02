DROP SCHEMA IF EXISTS Base CASCADE;
CREATE SCHEMA Base;
CREATE TABLE Base.usuario(
	idUsuario INT NOT NULL AUTO_INCREMENT,
	nombreUsuario text NOT NULL,
	contraseña text NOT NULL,
	correo text NOT NULL,
	tipo int NOT NULL,
	CONSTRAINT id_Usuario_pkey PRIMARY KEY(idUsuario)
);

CREATE TABLE Base.tema(
	idTema INT NOT NULL AUTO_INCREMENT,
	idUsuario INT NOT NULL,
	nombreTema text NOT NULL,
	CONSTRAINT id_tema_pkey PRIMARY KEY(idTema),
	CONSTRAINT fk_idUsuario FOREIGN KEY (idUsuario) REFERENCES Base.usuario(idUsuario)
);

CREATE TABLE Base.marcador(
	idMarcador INT NOT NULL AUTO_INCREMENT,
	idTema INT NOT NULL,
	latitud FLOAT NOT NULL,
	longitud FLOAT NOT NULL,
	datos_utiles text NOT NULL,
	descripcion text NOT NULL,
	color int NOT NULL,
	CONSTRAINT id_Marcador_pkey PRIMARY KEY(idMarcador,idTema),
	CONSTRAINT fk_idTema FOREIGN KEY (idTema) REFERENCES Base.tema(idTema)
);

CREATE TABLE Base.comentario(
	idComentario INT NOT NULL AUTO_INCREMENT ,
	idUsuario INT not NULL,
	idMarcador INT not NULL,
	idTema INT not NULL,
	comentario text NOT NULL,
	fecha date,
	CONSTRAINT id_comentario_pkey PRIMARY KEY(idComentario,idMarcador,idTema),
	CONSTRAINT fk_idComeentario FOREIGN KEY(idMarcador,idTema) REFERENCES Base.marcador(idMarcador,idTema)
);
	
